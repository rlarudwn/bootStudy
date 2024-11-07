package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.sist.web.entity.*;
import com.sist.web.dao.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FoodController {
	@Autowired
	private FoodHouseDAO dao;
	
	@GetMapping("/")
	public String foodMain(String page, Model model) {
		if(page==null)
			page="1";
		int curPage=Integer.parseInt(page);
		int rowSize=12;
		int start=(curPage-1)*rowSize;
		List<FoodHouseData> list = dao.foodListData(start);
		int count=(int)dao.count();
		int totalPage=(int)(Math.ceil(count)/12.0);
		final int BLOCK=10;
		int startPage=(curPage-1)/BLOCK*BLOCK+1;
		int endPage=startPage+BLOCK-1;
		if(endPage>totalPage)
			endPage=totalPage;
		model.addAttribute("list", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "main";
	}
	@GetMapping("/detail")
	public String foodDetail(int fno, Model model) {
		FoodHouseEntity e=dao.findByFno(fno);
		model.addAttribute("detail", e);
		return "detail";
	}
	
}
