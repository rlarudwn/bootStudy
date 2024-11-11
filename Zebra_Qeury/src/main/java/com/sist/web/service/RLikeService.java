package com.sist.web.service;

import org.springframework.data.repository.query.Param;

import com.sist.web.entity.RLikeEntity;

public interface RLikeService {
	public void save(RLikeEntity e);
	public void deleteById(int lno);
	public RLikeEntity findById(int lno);
	public int isLike(int no, String id);
	public void deleteLike(int no, String id);
	public int count();
	public int likeMaxCount();
}
