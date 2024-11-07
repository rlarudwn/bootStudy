package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.web.entity.*;
@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{
	
	public FoodHouseEntity findByFno(int fno);
	
	@Query(value = "SELECT fno, poster, name FROM food_house ORDER BY fno ASC "
			+ "LIMIT :start, 12", nativeQuery = true)
	public List<FoodHouseData> foodListData(@Param("start")int start);
}
