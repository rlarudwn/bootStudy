package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.MLikeDAO;
import com.sist.web.entity.MLikeEntity;
@Service
public class MLikeServiceImpl implements MLikeService{
	@Autowired
	private MLikeDAO dao;
	
	@Override
	public void save(MLikeEntity e) {
		dao.save(e);
	}
	@Override
	public void deleteById(int lno) {
		dao.deleteById(lno);
	}
	@Override
	public MLikeEntity findById(int mno) {
		return dao.findById(mno).get();
	}
	@Override
	public int isLike(int mno, String id) {
		return dao.isLike(mno, id);
	}
	@Override
	public void deleteLike(int mno, String id) {
		dao.deleteLike(mno, id);
	}
	@Override
	public int count() {
		return (int)dao.count();
	}
	@Override
	public int likeMaxCount() {
		return dao.likeMaxCount();
	}
}
