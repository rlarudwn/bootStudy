package com.sist.web.restcontroller;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.EBoardRepository;
import com.sist.web.entity.EBoard;

@RestController
@CrossOrigin("*")
public class EBoardRestController {
	@Autowired
	private EBoardRepository dao;

	@GetMapping("eboard/listElastic")
	public Map boardListElastic(int page) {
		int rowSize = 10;
		Pageable pg = PageRequest.of(page - 1, rowSize, Sort.by(Sort.Direction.DESC, "id"));
		Page<EBoard> pList = dao.findAll(pg);
		List<EBoard> list = new ArrayList<EBoard>();
		if (pList != null && pList.hasContent())
		{
			list = pList.getContent();
		}
		int count=(int)dao.count();
    int totalpage=(int)(Math.ceil(count/10.0));
    Map map=new HashMap();
    map.put("count", count);
    map.put("list", list);
    map.put("curPage", page);
    map.put("totalPage", totalpage);
    return map;

	}
	@PostMapping("eboard/insertElastic")
	public void boardInsertElastic(EBoard b) {
		b.setRegdate(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
		b.setHit(0);
		b.setId(getMaxId());
		dao.save(b);
	}
	public int getMaxId() {
		int max=0;
		try {
			int rowSize = 10;
			Pageable pg = PageRequest.of(0, rowSize, Sort.by(Sort.Direction.DESC, "id"));
			Page<EBoard> pList = dao.findAll(pg);
			List<EBoard> list = new ArrayList<EBoard>();
			if (pList != null && pList.hasContent())
			{
				list = pList.getContent();
			}
			max=list.get(0).getId()+1;
		} catch (Exception e) {
			max=0;
		}
		return max;
	}
	
	@GetMapping("eboard/detailElastic")
	public EBoard boardDetailData(int id) {
		EBoard board=dao.findById(id).get();
		board.setHit(board.getHit()+1);
		dao.save(board);
		board=dao.findById(id).get();
		return board;
	}
	
	@GetMapping("eboard/updateOkElastic")
	public void boardUpdateOkElastic(EBoard e) {
		dao.save(e);
	}
	
	@GetMapping("eboard/updateElastic")
	public EBoard boardUpdateElastic(int id) {
		EBoard e=dao.findById(id).get();
		return e;
	}
	
}
