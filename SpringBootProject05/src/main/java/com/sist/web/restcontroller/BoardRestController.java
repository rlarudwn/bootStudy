package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.BoardRepository;
import com.sist.web.dao.EBoardRepository;
import com.sist.web.entity.Board;
import com.sist.web.entity.EBoard;

@RestController
@CrossOrigin(origins = "*")
public class BoardRestController {
	@Autowired
	private EBoardRepository dao;
	
	@GetMapping("board/insertElastic")
	public String boardInsertElastic() {
		EBoard b=new EBoard();
		b.setName("김경주");
		b.setSubject("Elastic Practice");
		b.setContent("Elastic Practice");
		b.setPwd("1234");
		b.setRegdate("2024-10-31");
		b.setHit(0);
		b.setId((int)(dao.count()+1));
		dao.save(b);
		return "yes";
	}

}
