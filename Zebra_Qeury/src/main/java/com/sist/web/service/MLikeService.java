package com.sist.web.service;


import com.sist.web.entity.MLikeEntity;

public interface MLikeService {
	public void save(MLikeEntity e);
	public void deleteById(int lno);
	public MLikeEntity findById(int lno);
	public int isLike(int mno, String id);
	public void deleteLike(int mno, String id);
	public int count();
	public int likeMaxCount();
}
