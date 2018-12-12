package com.jquery.ajax.comment;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
  //CRUD => method (select ,insert , update ,delete)
	private static CommentDAO instance = new CommentDAO();
	private static List<CommentVO> commentlist = new ArrayList<CommentVO>();
	// { } , static{ } 초기자
	static{ //commentlist에 강제로 값을 넣음
		for(int k = 0 ; k < 5 ;k++){
			commentlist.add(new CommentVO(k,k+ "번째 덧글입니다"));
			//0 , 0번쨰 덧글입니다
			//..
			//3 , 3번쨰 덧글입니다
		}
  	   }
	public static CommentDAO getInstance(){
		return instance;
	}
	
	//생성자 private. 싱글톤 패턴
	private CommentDAO(){}
	
  	public List<CommentVO> getCommentList(int bbsSeq){ //select ....
  		return commentlist;
  	}
  	
  	public void addComment(String comment){ // insert  글내용 
  		int size = commentlist.size();
  		commentlist.add(new CommentVO(size, comment)); //insert 
  	}
	
  	public void removeComment(int seq){  //delete 
  		for(int k =0 ; k <commentlist.size() ; k++){
  			if(commentlist.get(k).getSeq() == seq){
  				commentlist.remove(k);
  			}
  		}
  	}
}







