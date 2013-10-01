package com.meteor.service;



import java.util.List;

import org.codehaus.jackson.annotate.JsonAnyGetter;

import com.meteor.mapper.BoardMapper;
import com.meteor.model.Board;

public class Service_Board {

	
	private BoardMapper boardMapper;
	
	public void setBoardMapper(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}


	public Board getBoard(String tbId){
		
		return this.boardMapper.getTb(tbId);
	}
	public List<Board> getBoard_All(){
		
		return this.boardMapper.getTb_All();
	}
	public void insert_Board(Board board){
		
		this.boardMapper.insertTb(board);;
	}
	public void update_Board(Board board){
		
		this.boardMapper.updateTb(board);
	}
	
}
