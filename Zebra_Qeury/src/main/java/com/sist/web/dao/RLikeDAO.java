package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.RLikeEntity;

import jakarta.transaction.Transactional;

@Repository
public interface RLikeDAO  extends JpaRepository<RLikeEntity, Integer>{
	@Query(value = "SELECT COUNT(*) FROM recipe_like WHERE no = :no AND id = :id")
	public int isLike(@Param("no")int fno, @Param("id")String id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM recipe_like WHERE no = :no AND id = :id", nativeQuery = true)
	public void deleteLike(@Param("no")int no, @Param("id")String id);
	
	@Query(value = "SELECT IFNULL(MAX(lno)+1, 1) FROM recipe_like")
	public int likeMaxCount();
}
