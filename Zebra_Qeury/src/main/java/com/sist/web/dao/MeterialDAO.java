package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.MeterialEntity;
import com.sist.web.entity.MeterialVO;
import com.sist.web.entity.RecipeVO;

import jakarta.transaction.Transactional;
@Repository
public interface MeterialDAO extends JpaRepository<MeterialEntity, Integer>{
	@Query(value = "SELECT * FROM meterial WHERE poster != 'https://t3.ftcdn.net/jpg/04/84/88/76/360_F_484887682_Mx57wpHG4lKrPAG0y7Q8Q7bJ952J3TTO.jpg' AND name LIKE CONCAT('%',:fd , '%') ORDER BY name ASC LIMIT :start, 12", nativeQuery=true)
	public List<MeterialVO> meterialListData(@Param("start")int start, @Param("fd")String fd);
	
	@Query(value = "SELECT COUNT(*) FROM meterial WHERE poster != 'https://t3.ftcdn.net/jpg/04/84/88/76/360_F_484887682_Mx57wpHG4lKrPAG0y7Q8Q7bJ952J3TTO.jpg' AND name LIKE CONCAT('%',:fd , '%')", nativeQuery = true)
	public int meterialTotalPage(@Param("fd")String fd);
	
	@Query(value = "SELECT * FROM meterial WHERE mno in :items", nativeQuery = true)
	public List<MeterialVO> cartMeteralList(@Param("items")List<String> items);
	
	@Query(value = "SELECT * FROM recipe WHERE no IN (SELECT no FROM recipemeterial WHERE mno=:mno)", nativeQuery = true)
	public List<RecipeVO> meterialRelateList(@Param("mno")int mno);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE meterial SET score=(SELECT IFNULL(AVG(rating), 0.0) FROM comment WHERE mno = :mno) WHERE mno=:mno", nativeQuery = true)
	public void meterialScoreUpdate(@Param("mno")int mno);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE meterial SET likecount=(SELECT COUNT(*) FROM meterial_like WHERE mno = :mno) WHERE mno=:mno", nativeQuery = true)
	public void meterialLikeUpdate(@Param("mno")int mno);
}
