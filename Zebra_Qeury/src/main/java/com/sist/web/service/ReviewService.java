package com.sist.web.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.sist.web.entity.ReviewEntity;

public interface ReviewService {
	public void save(ReviewEntity e);
	public int reviewMaxRno();
	public int reviewCount(int no);
	public List<ReviewEntity> reviewListData(int no);
	public int myReviewCount(int no,String id);
	public void reviewDelete(int rno);
	public ReviewEntity findById(int rno);
}
