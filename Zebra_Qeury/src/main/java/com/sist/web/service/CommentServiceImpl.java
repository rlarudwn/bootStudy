package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.CommentDAO;
import com.sist.web.entity.CommentEntity;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO dao;

	@Override
	public void save(CommentEntity e) {
		dao.save(e);
	}

	@Override
	public int commentMaxRno() {
		return dao.CommentMaxRno();
	}

	@Override
	public int commentCount(int mno) {
		return dao.CommentCount(mno);
	}

	@Override
	public List<CommentEntity> commentListData(int mno) {
		return dao.commentListData(mno);
	}

	@Override
	public int myCommentCount(int mno, String id) {
		return dao.myCommentCount(mno, id);
	}

	@Override
	public void commentDelete(int rno) {
		dao.CommentDelete(rno);
	}

	@Override
	public CommentEntity findById(int rno) {
		return dao.findById(rno).get();
	}
}
