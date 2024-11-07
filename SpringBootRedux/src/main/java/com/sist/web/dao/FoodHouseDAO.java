package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.FoodHouseEntity;
import com.sist.web.entity.FoodHouseVO;
@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{
	@Query(value = "SELECT fno, name, poster, score, hit, jjimcount, type, content, theme "
			+ "FROM food_house ORDER BY hit DESC "
			+ "LIMIT 0, 9", nativeQuery = true)
	public List<FoodHouseVO> foodHitTop();
	
	public FoodHouseEntity findByFno(int fno);
	
	@Query(value = "SELECT fno, name, poster, score, hit, jjimcount, type, content, theme "
			+ "FROM food_house ORDER BY fno ASC "
			+ "LIMIT :start, 12", nativeQuery = true)
	public List<FoodHouseVO> foodListData(@Param("start")int start);
	@Query(value = "SELECT fno, name, poster, score, hit, jjimcount, type, content, theme "
			+ "FROM food_house WHERE address LIKE concat('%', :address, '%') ORDER BY fno ASC "
			+ "LIMIT :start, 12", nativeQuery = true)
	public List<FoodHouseVO> foodFindList(@Param("address")String address, @Param("start")int start);
	@Query(value = "SELECT COUNT(*) FROM food_house WHERE address LIKE concat('%', :address, '%')" , nativeQuery = true)
	public int foodFindPage(@Param("address")String address);
}
