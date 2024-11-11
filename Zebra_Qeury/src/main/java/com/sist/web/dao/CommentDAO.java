package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.CommentEntity;
import jakarta.transaction.Transactional;

@Repository
public interface CommentDAO extends JpaRepository<CommentEntity, Integer>{
	@Query(value = "SELECT IFNULL(MAX(rno)+1, 1) FROM comment", nativeQuery = true)
	public int CommentMaxRno();
	@Query(value = "SELECT COUNT(*) FROM comment WHERE mno=:mno", nativeQuery = true)
	public int CommentCount(@Param("mno")int mno);
	@Query(value = "SELECT * FROM comment WHERE mno=:mno", nativeQuery = true)
	public List<CommentEntity> commentListData(@Param("mno")int mno);
	@Query(value = "SELECT COUNT(*) FROM comment WHERE mno=:mno AND id=:id")
	public int myCommentCount(@Param("mno")int mno, @Param("id")String id);
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM comment WHERE rno=:rno")
	public void CommentDelete(@Param("rno")int rno);
}