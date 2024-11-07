package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.FoodHouseDAO;
import com.sist.web.entity.FoodHouseEntity;
import com.sist.web.entity.FoodHouseVO;

@RestController
@CrossOrigin(origins = "*")
public class FoodHouseRestController {
	@Autowired
	private FoodHouseDAO dao;
	@GetMapping("/food/list/{page}")
	public ResponseEntity<Map> foodList(@PathVariable int page){
		Map map=new HashMap();
		try {
			int start=(page-1)*12;
			List<FoodHouseVO> list=dao.foodListData(start);
			int count=(int)dao.count();
			int totalPage=(int)(Math.ceil(count)/12.0);
			int startPage=(page-1)/10*10+1;
			int endPage=startPage+10-1;
			if(endPage>totalPage)
				endPage=totalPage;
			map.put("list", list);
			map.put("curPage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("totalPage", totalPage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/food/find/{page}/{address}")
	public ResponseEntity<Map> foodFind(@PathVariable("page") int page, @PathVariable("address") String address){
		Map map=new HashMap();
		try {
			int start=(page-1)*12;
			List<FoodHouseVO> list=dao.foodFindList(address, start);
			int count=dao.foodFindPage(address);
			int totalPage=(int)(Math.ceil(count/12.0));
			int startPage=(page-1)/10*10+1;
			int endPage=startPage+10-1;
			if(endPage>totalPage)
				endPage=totalPage;
			System.out.println(totalPage);
			System.out.println(endPage);
			map.put("list", list);
			map.put("curPage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("totalPage", totalPage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("food/detail/{fno}")
	public ResponseEntity<FoodHouseEntity> foodDetailData(@PathVariable("fno")int fno){
		try {
			FoodHouseEntity e=dao.findByFno(fno);
			e.setHit(e.getHit()+1);
			dao.save(e);
			e=dao.findByFno(fno);
			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
