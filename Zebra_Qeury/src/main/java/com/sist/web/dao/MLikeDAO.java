package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.sist.web.entity.MLikeEntity;


import jakarta.transaction.Transactional;

@Repository
public interface MLikeDAO  extends JpaRepository<MLikeEntity, Integer>{
	@Query(value = "SELECT COUNT(*) FROM meterial_like WHERE mno = :mno AND id = :id")
	public int isLike(@Param("mno")int mno, @Param("id")String id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM meterial_like WHERE mno = :mno AND id = :id", nativeQuery = true)
	public void deleteLike(@Param("mno")int mno, @Param("id")String id);
	
	@Query(value = "SELECT IFNULL(MAX(lno)+1, 1) FROM meterial_like")
	public int likeMaxCount();
}
