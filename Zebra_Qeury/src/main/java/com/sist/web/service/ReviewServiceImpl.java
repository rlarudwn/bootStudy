package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.ReviewDAO;
import com.sist.web.entity.ReviewEntity;
@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewDAO dao;
	
	@Override
	public void save(ReviewEntity e) {
		dao.save(e);
	}
	
	@Override
	public int reviewMaxRno() {
		return dao.reviewMaxRno();
	}
	
	@Override
	public int reviewCount(int no) {
		return dao.reviewCount(no);
	}
	
	@Override
	public List<ReviewEntity> reviewListData(int no) {
		return dao.reviewListData(no);
	}
	
	@Override
	public int myReviewCount(int no, String id) {
		return dao.myReviewCount(no, id);
	}
	
	@Override
	public void reviewDelete(int rno) {
		dao.reviewDelete(rno);
	}
	
	@Override
	public ReviewEntity findById(int rno) {
		return dao.findById(rno).get();
	}
}
