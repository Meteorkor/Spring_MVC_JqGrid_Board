package com.meteor.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meteor.model.Board;
import com.meteor.service.Service_Board;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	Service_Board ser_bod;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "board";
	}
	
	@RequestMapping(value = "/{index}", method = RequestMethod.GET)
	public String home(@PathVariable("index")
	String index) {
		
		logger.info(ser_bod.getBoard(index).getSubject());
		
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	@RequestMapping(value = "/b{index}", method = RequestMethod.GET)
	public @ResponseBody Board board_json(@PathVariable("index")
	String index) {
		
		logger.info(ser_bod.getBoard(index).getSubject());
		
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		return ser_bod.getBoard(index);
		
		//return "home";
	}
	@RequestMapping(value = "/all.json")
	public @ResponseBody List<Board> boardAll_json() {
		
		//logger.info(ser_bod.getBoard(index).getSubject());
		
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		return ser_bod.getBoard_All();
		
		//return "home";
	}
	@RequestMapping(value = "/test")
	public String test() {
		
		//logger.info(ser_bod.getBoard(index).getSubject());
		
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		return "test";
		
		//return "home";
	}
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		
		Board board = new Board();
		
		board.sett_count(0);
		board.sett_description("desc123");
		board.setSubject("sub");
		board.setValid(0);
		board.setWriter("wri");
		
		//logger.info(board.getDesc());
		
		ser_bod.insert_Board(board);
		
		//logger.info(ser_bod.getBoard(index).getSubject());
		
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		return "test";
		
		//return "home";
	}
	//@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	@RequestMapping(value = "/update{id}")
	public String update(@PathVariable int id) {
		
		Board board = new Board();
		
		board.setId(id);
		
		board.sett_count(0);
		board.sett_description("desc123");
		board.setSubject("sub");
		board.setValid(0);
		board.setWriter("wri");
		
		//logger.info(board.getDesc());
		
		ser_bod.update_Board(board);
		
		//logger.info(ser_bod.getBoard(index).getSubject());
		
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		return "test";
		
		//return "home";
	}
	
	@RequestMapping(value = "/pu")
	public String pu(@RequestParam("id")int id, @RequestParam("subject")String subject) {
		
		Board board = new Board();
		
		//board.setId(5);
		board.setId(id);
		
		board.sett_count(0);
		board.sett_description("desc123");
		//board.setSubject("sub");
		board.setSubject( subject );
		
		board.setValid(0);
		board.setWriter("wri");
		
		//logger.info(board.getDesc());
		
		ser_bod.update_Board(board);
		
		//logger.info(ser_bod.getBoard(index).getSubject());
		
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		return "test";
		
		//return "home";
	}
	
}
