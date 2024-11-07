package com.sist.web.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.FoodHouseDAO;
import com.sist.web.dao.RecipeDAO;
import com.sist.web.entity.FoodHouseVO;
import com.sist.web.entity.RecipeEntity;

@RestController
@CrossOrigin(origins = "*")
public class MainRestController {
	@Autowired
	FoodHouseDAO fDao;
	@Autowired
	RecipeDAO rDao;
	@GetMapping("/main")
	public ResponseEntity<Map> foodMainReact() {
		Map map = new HashMap();
		try {
			List<FoodHouseVO> fList = fDao.foodHitTop();
			List<RecipeEntity> rList = rDao.recipeTopData();
			List<FoodHouseVO> twoData=new ArrayList<FoodHouseVO>();
			for(int i=1;i<=4;i++)
				twoData.add(fList.get(i));
			List<FoodHouseVO> threeData=new ArrayList<FoodHouseVO>();
			for(int i=5;i<=8;i++)
				threeData.add(fList.get(i));
			
			map.put("oneData", fList.get(0));
			map.put("twoData", twoData);
			map.put("threeData", threeData);
			map.put("rList", rList);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
}
