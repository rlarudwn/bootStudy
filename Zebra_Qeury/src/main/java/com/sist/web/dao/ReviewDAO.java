package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ReviewEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ReviewDAO extends JpaRepository<ReviewEntity, Integer>{
	@Query(value = "SELECT IFNULL(MAX(rno)+1, 1) FROM review", nativeQuery = true)
	public int reviewMaxRno();
	@Query(value = "SELECT COUNT(*) FROM review WHERE no=:no", nativeQuery = true)
	public int reviewCount(@Param("no")int no);
	@Query(value = "SELECT * FROM review WHERE no=:no", nativeQuery = true)
	public List<ReviewEntity> reviewListData(@Param("no")int no);
	@Query(value = "SELECT COUNT(*) FROM review WHERE no=:no AND id=:id")
	public int myReviewCount(@Param("no")int no, @Param("id")String id);
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM review WHERE rno=:rno")
	public void reviewDelete(@Param("rno")int rno);
}
