package com.sist.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.MeterialVO;
import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeVO;

import jakarta.transaction.Transactional;

@Repository
public interface RecipeDAO extends JpaRepository<RecipeEntity, Integer>{
	@Query(value = "SELECT * FROM recipe WHERE type in (:types) ORDER BY RAND() LIMIT 1, 12", nativeQuery = true)
	public List<RecipeVO> recipeRandData(List<String> types);
	
	@Query(value = "SELECT distinct type FROM recipe ORDER BY RAND() LIMIT 5", nativeQuery = true)
	public List<String> recipeRandTypes();
	
	@Query(value = "SELECT * FROM recipe WHERE no IN (SELECT no FROM recipemeterial WHERE mno IN (:items) GROUP BY no HAVING COUNT(DISTINCT mno) = :length) ORDER BY no ASC LIMIT :start, 12", nativeQuery = true)
	public List<RecipeVO> makeRecipeList(@Param("items")List<String> items, @Param("length")int length, @Param("start")int start);
	
	@Query(value = "SELECT COUNT(*) FROM recipe WHERE no IN (SELECT no FROM recipemeterial WHERE mno IN (:items) GROUP BY no HAVING COUNT(DISTINCT mno) = :length)", nativeQuery = true)
	public int makeTotalCount(@Param("items")List<String> items, @Param("length")int length);
	
	@Query(value = "SELECT * FROM recipe WHERE type LIKE CONCAT('%', :#{#map['option']}, '%') "
			+ "AND info1 LIKE CONCAT('%', :#{#map['info1']}, '%') "
			+ "AND info2 LIKE CONCAT('%', :#{#map['info2']}, '%') "
			+ "AND info3 LIKE CONCAT('%', :#{#map['info3']}, '%') "
			+ "AND title LIKE CONCAT('%', :#{#map['fd']}, '%') "
			+ "AND step LIKE '%^%' "
			+ "ORDER BY NO ASC LIMIT :#{#map['start']}, 12", nativeQuery = true)
	public List<RecipeVO> recipeListData(@Param("map")Map map);
	
	@Query(value = "SELECT COUNT(*) FROM recipe WHERE type LIKE CONCAT('%', :#{#map['option']}, '%') "
			+ "AND info1 LIKE CONCAT('%', :#{#map['info1']}, '%') "
			+ "AND info2 LIKE CONCAT('%', :#{#map['info2']}, '%') "
			+ "AND info3 LIKE CONCAT('%', :#{#map['info3']}, '%') "
			+ "AND title LIKE CONCAT('%', :#{#map['fd']}, '%') "
			+ "AND step LIKE '%^%'")
	public int recipeTotalCount(@Param("map")Map map);
	
	@Query(value = "SELECT distinct type FROM recipe WHERE type!=''")
	public List<String> recipeTypeList();
	@Query(value = "SELECT distinct info1 FROM recipe WHERE info1!='' ORDER BY info1 ASC")
	public List<String> recipeInfo1List();
	@Query(value = "SELECT distinct info2 FROM recipe WHERE info2!='' ORDER BY info2 ASC")
	public List<String> recipeInfo2List();
	@Query(value = "SELECT distinct info3 FROM recipe WHERE info3!=''")
	public List<String> recipeInfo3List();
	
	@Query(value = "SELECT * FROM recipe WHERE type=(SELECT type FROM recipe WHERE no=:no) AND step LIKE '%^%' ORDER BY RAND() LIMIT 1, 7", nativeQuery = true)
	public List<RecipeVO> recipeRelateList(@Param("no")int no);
	
	@Query(value = "SELECT * FROM meterial WHERE mno IN (SELECT mno FROM recipemeterial WHERE no=:no)", nativeQuery = true)
	public List<MeterialVO> recipeDetailMeterial(@Param("no") int no);
	@Transactional
	@Modifying
	@Query(value = "UPDATE recipe SET score=(SELECT IFNULL(AVG(rating), 0.0) FROM review WHERE no = :no) WHERE no=:no", nativeQuery = true)
	public void recipeScoreUpdate(@Param("no")int no);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE recipe SET likecount=(SELECT COUNT(*) FROM recipe_like WHERE no = :no) WHERE no=:no", nativeQuery = true)
	public void recipeLikeUpdate(@Param("no")int no);
}
