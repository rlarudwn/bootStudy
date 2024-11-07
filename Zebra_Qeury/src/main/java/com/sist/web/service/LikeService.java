package com.sist.web.service;

import org.springframework.data.repository.query.Param;

import com.sist.web.entity.LikeEntity;

public interface LikeService {
	public void save(LikeEntity e);
	public void deleteById(int lno);
	public LikeEntity findById(int lno);
	public int isLike(String fno, String id);
	public void deleteLike(String fno, String id);
	public int count();
	public int likeMaxCount();
}
