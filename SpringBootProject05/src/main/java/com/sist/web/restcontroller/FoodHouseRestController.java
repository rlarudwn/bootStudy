package com.sist.web.restcontroller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.ChefDAO;
import com.sist.web.dao.FoodHouseDAO;
import com.sist.web.dao.RecipeDAO;
import com.sist.web.entity.ChefEntity;
import com.sist.web.entity.ChefVO;
import com.sist.web.entity.FoodHouseEntity;
import com.sist.web.entity.FoodHouseVO;
import com.sist.web.entity.RecipeEntity;

@RestController
@CrossOrigin(origins = "*")
public class FoodHouseRestController {
	@Autowired
	private FoodHouseDAO dao;
	@Autowired
	private RecipeDAO rDao;
	@Autowired
	private ChefDAO cDao;

	@GetMapping("/food/mainReact")
	public Map foodMainReact() {
		Map map = new HashMap();
		List<FoodHouseVO> fList = dao.foodHitTop();
		List<RecipeEntity> rList = rDao.recipeTopData();
		ChefEntity e = cDao.findByChef("핑콩이");
		System.out.println(e);
		map.put("fList", fList);
		map.put("rList", rList);
		map.put("chef", e);
		return map;
	}
	@GetMapping("/food/listReact")
	public Map foodListReact(int page) {
		Map map=new HashMap();
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
		return map;
	}
	@GetMapping("/food/detailReact")
	public FoodHouseEntity foodDetailReact(int fno) {
		FoodHouseEntity e=dao.findByFno(fno);
		e.setHit(e.getHit()+1);
		dao.save(e);
		e=dao.findByFno(fno);
		return e;
	}
	@GetMapping("/food/findReact")
	public Map foodFindReact(String address, int page){
		Map map=new HashMap();
		int start=(page-1)*12;
		List<FoodHouseVO> list=dao.foodFindList(address, start);
		int totalPage=dao.foodFindPage(address);
		int startPage=(page-1)/10*10+1;
		int endPage=startPage+10-1;
		if(endPage>totalPage)
			endPage=totalPage;
		map.put("list", list);
		map.put("curPage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalPage", totalPage);
		map.put("address", address);
		return map;
	}
}
