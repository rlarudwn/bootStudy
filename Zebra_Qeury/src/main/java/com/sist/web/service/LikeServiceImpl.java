package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.LikeDAO;
import com.sist.web.entity.LikeEntity;
@Service
public class LikeServiceImpl implements LikeService{
	@Autowired
	private LikeDAO dao;
	
	@Override
	public void save(LikeEntity e) {
		dao.save(e);
	}
	@Override
	public void deleteById(int lno) {
		dao.deleteById(lno);
	}
	@Override
	public LikeEntity findById(int lno) {
		return dao.findById(lno).get();
	}
	@Override
	public int isLike(String fno, String id) {
		return dao.isLike(fno, id);
	}
	@Override
	public void deleteLike(String fno, String id) {
		dao.deleteLike(fno, id);
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
