package com.meteor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.meteor.model.Board;

public interface BoardMapper {

	String Board ="mytable";
	
	//@Select("select id,subject,writer,count from mytable where id = #{tbId}")
	@Select("select * from "
			+ Board
			+ " where id = #{tbId}")
	Board getTb(@Param("tbId") String tbId);
	
	@Select("select * from "
			+ Board
			+ "")
	List<Board> getTb_All();
	/*
	@Insert("insert into mytable(subject,writer,count,valid,desc) "
			+ "value "
			+ "(#{subject},#{writer},#{count},#{valid},#{desc})")
	*/
	@Insert("insert into "
			+ Board
			+ "(subject,writer,valid,t_count) "
			+ "value "
			+ "(#{subject},#{writer},#{valid},#{t_count})")
	void insertTb(Board board);
	@Update("update "
			+ Board
			+ " set subject=#{subject} where id=#{id} ")
	void updateTb(Board board);
	
	
}
