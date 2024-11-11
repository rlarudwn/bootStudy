package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.RLikeDAO;
import com.sist.web.entity.RLikeEntity;
@Service
public class RLikeServiceImpl implements RLikeService{
	@Autowired
	private RLikeDAO dao;
	
	@Override
	public void save(RLikeEntity e) {
		dao.save(e);
	}
	@Override
	public void deleteById(int lno) {
		dao.deleteById(lno);
	}
	@Override
	public RLikeEntity findById(int no) {
		return dao.findById(no).get();
	}
	@Override
	public int isLike(int no, String id) {
		return dao.isLike(no, id);
	}
	@Override
	public void deleteLike(int no, String id) {
		dao.deleteLike(no, id);
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
