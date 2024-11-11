package com.sist.web.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.sist.web.entity.CommentEntity;

public interface CommentService {
	public void save(CommentEntity e);
	public int commentMaxRno();
	public int commentCount(int mno);
	public List<CommentEntity> commentListData(int mno);
	public int myCommentCount(int mno,String id);
	public void commentDelete(int rno);
	public CommentEntity findById(int rno);
}
