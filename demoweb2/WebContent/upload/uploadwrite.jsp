<%@page import="com.demoweb.model.dto.Member"%>
<%@page import="com.demoweb.common.Util"%>
<%@page import="com.demoweb.model.dto.UploadFile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page import="com.demoweb.model.dto.Upload"%>
<%@page import="com.demoweb.model.dao.UploadDao"%>
<%@page import="com.demoweb.model.dao.MemberDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//post 요청 데이터의 문자셋을 한글로 지정
if (request.getMethod().toLowerCase().equals("post"))
	request.setCharacterEncoding("UTF-8");

//read data from request
ArrayList<UploadFile> attaches = new ArrayList<UploadFile>();
Upload upload = new Upload();

String tempDirectoryPath =		
	application.getRealPath("/upload/temp/");//가상경로 -> 물리경로
System.out.println("tempDirectoryPath :" +tempDirectoryPath);	
File tempDirectory = new File(tempDirectoryPath);	
	
String realDirectoryPath = 
	application.getRealPath("/upload/uploadfiles/");

DiskFileItemFactory factory = new DiskFileItemFactory();
//이 크기보타 큰 파일은 임시 폴더에 저장한 후 실제 경로에 이동
factory.setSizeThreshold(1024 * 1024 * 1);
factory.setRepository(tempDirectory);//임시 폴더 지정
	
//여기까지 설정 끝.
ServletFileUpload uploader = new ServletFileUpload(factory);
uploader.setSizeMax(1024 * 1024 * 10);//업로드 최대 파일 사이즈 설정

//enctype=multipart/form-data 형식의 전송 데이터를 읽으세요
List<FileItem> items = uploader.parseRequest(request);
for (int i = 0; i < items.size(); i++) {
	FileItem item = items.get(i);
	if (item.isFormField()) {//input type=file이 아닌 입력 데이터	
		if (item.getFieldName().equals("title"))//title 입력 데이터
			upload.setTitle(item.getString("UTF-8"));//데이터 읽기
		if (item.getFieldName().equals("content"))//content 입력 데이터
			upload.setContent(item.getString("UTF-8"));//데이터 읽기
	} else {//input type=file인 입력 데이터
		if (item.getSize() > 0) {
			UploadFile attach = new UploadFile();
			String fieldName = item.getFieldName();//입력 요소의 이름
			String fileName = item.getName();//파일의 이름
			if (fileName.contains("\\")) {
				fileName = 
					fileName.substring(fileName.lastIndexOf("\\") + 1);
			}
			//사용자가 업로드한 파일의 고유한 파일이름 생성
			String savedName = 
				Util.getUniqueFileName(realDirectoryPath, fileName);
			
			attach.setUserFileName(fileName);
			attach.setSavedFileName(savedName);
			attaches.add(attach);
			
			File uploadFile = new File(realDirectoryPath, savedName);
			item.write(uploadFile);//목표 경로에 저장
			item.delete();//임시 파일 삭제
		}
	}
}
//uploader에 로그인한 사용자의 아이디를 설정
String memberId = 
	((Member)session.getAttribute("loginuser")).getMemberId();
upload.setUploader(memberId);

//insert data
//1. 자료정보를 데이터베이스에 저장 (등록된 자료 번호를 반환)
int uploadNo = 0;
UploadDao dao = new UploadDao();
uploadNo = dao.insertUpload(upload);

if (uploadNo != -1) {
	//2. 파일정보를 데이터베이스에 저장
	for (UploadFile file : attaches) {
		file.setUploadNo(uploadNo);//등록된 자료 번호 설정
		dao.insertUploadFile(file);
	}
	response.sendRedirect("/demoweb/upload/uploadlist.jsp");
} else {
	out.println("<script type='text/javascript'>");
	out.println("alert('등록 실패');");
	out.println("history.back();");
	out.println("</script>");
}
%>

















