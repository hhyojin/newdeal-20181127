<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.internet.MimeMultipart"%>
<%@page import="javax.mail.Multipart"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Message"%>
<%@page import="java.util.Properties"%>
<%@page import="javax.mail.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//요청 정보 한글 처리
if (request.getMethod().toLowerCase().equals("post")) {
	request.setCharacterEncoding("euc-kr");
}

//
String to = request.getParameter("to");
//String cc = "";
//String bcc = "";
String subject = request.getParameter("subject");
String content = request.getParameter("content");

//메일 서버와 연결
Properties prop = new Properties();
prop.put("mail.smtp.host", "localhost");//메일서버주소
Session sess = Session.getDefaultInstance(prop, null);

//메일 작성
Message message = new MimeMessage(sess);

Address[] addresses = {
	new InternetAddress(to), 
	new InternetAddress("be.next.to.none@gmail.com")
};

message.setRecipients(
	Message.RecipientType.TO, addresses);
message.setFrom(
	new InternetAddress("admin@example.com"));

message.setSubject(subject);
//message.setText(content);
message.setContent(content, "text/html;charset=euc-kr");

Transport.send(message);

response.sendRedirect("sendmailform.jsp");
%>











