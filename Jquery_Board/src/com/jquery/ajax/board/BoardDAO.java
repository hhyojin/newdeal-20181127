package com.jquery.ajax.board;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	//싱글톤
	private static BoardDAO instance = new BoardDAO();
	
	private static List<BoardVO> boardList = new ArrayList<BoardVO>();
	
	private BoardDAO(){}
	
	public static BoardDAO getInstance(){
		return instance;
	}
	static{
		for(int k = 0 ; k < 5; k++){
			boardList.add(new BoardVO(k,k+" 번쨰 제목",k+" 번째 내용","2014-11-12",k));
		}
	}
	
	//CURD 메서드
	//전제조회 (READ)
	public List<BoardVO> getBoardList(){
		return boardList;
	}
	
	//데이터 추가(CREATE)
	public void addBoard(String board){
		int size = boardList.size();
		boardList.add(new BoardVO(size, board)); //데이터 추가 (글번호 ,글제목)
	}
	
	//데이터 삭제(DELETE)
	public void removeBoard(int seq){
		for(int k=0 ;  k < boardList.size() ; k++){
			if(boardList.get(k).getSeq() == seq){
				boardList.remove(k);
				break;
			}
		}
	}
	
	//데이터 수정(UPDATE)
	public boolean modifyBoard(BoardVO board){
		int seq = board.getSeq();
		
		for(int k = 0 ; k < boardList.size() ; k++){
			if(boardList.get(k).getSeq() == seq){
				boardList.set(k,board);
				
				//아래 처럼 해도 되요
				//boardList.get(k).setTitle(board.getTitle());
				//boardList.get(k).setContent(board.getContent());
				return true;
			}
		}
		return false;
	}

}
