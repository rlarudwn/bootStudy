package com.sist.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sist.web.entity.Board;

public interface BoardService {
	public Page<Board> findAll(Pageable pg);
	public Board findById(int id);
	public int count();
	public void save(Board b);
	public void delete(Board b);
}
