package com.sist.web.restcontroller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.SeoulDAO;
import com.sist.web.entity.SeoulEntity;

@RestController
@CrossOrigin(origins = "*")
public class SeoulRestController {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("/seoul/list/{page}")
	public ResponseEntity<Map> seoulListReact(@PathVariable("page")int page){
		Map map=new HashMap();
		try {
			int rowSize=12;
			Pageable pg=PageRequest.of(page-1, rowSize, Sort.by(Sort.Direction.ASC, "no"));
			Page<SeoulEntity> pList=dao.findAll(pg);
			
			List<SeoulEntity> list=new ArrayList<SeoulEntity>();
			if(pList!=null&&pList.hasContent()) {
				list=pList.getContent();
			}
			int totalPage=(int)(Math.ceil(dao.count()/12.0));
			
			int startPage=(page-1)/10*10+1;
			int endPage=startPage+10-1;
			map.put("list", list);
			map.put("totalPage", totalPage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
