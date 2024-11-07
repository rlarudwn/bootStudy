package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	@GetMapping("/board/list")
	public String boardList(String page, Model model) {
		if(page==null)
			page="1";
		int curPage=Integer.parseInt(page);
		int rowSize=10;
		int start=(curPage-1)*rowSize;
		
		
		List<BoardData> list=dao.boardListData(start);
		int count=(int)dao.count();
		int totalPage=(int)(Math.ceil(count)/10.0);
		model.addAttribute("list", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);
		
		return "boardList";
	}
	
	@GetMapping("/board/insert")
	public String boardInsert() {
		return "boardInsert";
	}
	@PostMapping("/board/insert_ok")
	public String boardInsertOk(BoardEntity vo) {
		dao.save(vo);
		return "redirect:/board/list";
	}
	@GetMapping("/board/detail")
	public String boardDetail(int no, Model model) {
		BoardEntity e=dao.findByNo(no);
		model.addAttribute("detail", e);
		return "boardDetail";
	}
	
	@GetMapping("/board/delete")
	public String boardDelete(int no, Model model) {
		model.addAttribute("no", no);
		return "boardDelete";
	}
	
}
