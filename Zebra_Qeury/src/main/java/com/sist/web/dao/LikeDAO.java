package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.LikeEntity;

import jakarta.transaction.Transactional;

@Repository
public interface LikeDAO  extends JpaRepository<LikeEntity, Integer>{
	@Query(value = "SELECT COUNT(*) FROM food_like WHERE fno = :fno AND id = :id")
	public int isLike(@Param("fno")String fno, @Param("id")String id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM food_like WHERE fno = :fno AND id = :id", nativeQuery = true)
	public void deleteLike(@Param("fno")String fno, @Param("id")String id);
	
	@Query(value = "SELECT IFNULL(MAX(lno)+1, 1) FROM food_like")
	public int likeMaxCount();
}
