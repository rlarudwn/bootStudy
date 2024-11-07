package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.web.dao.BoardRepository;
import com.sist.web.entity.Board;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardRepository dao;
	@Override
	public Page<Board> findAll(Pageable pg) {
		return dao.findAll(pg);
	}
	@Override
	public Board findById(int id) {
		return dao.findById(id).get();
	}
	@Override
	public int count() {
		return (int)dao.count();
	}
	@Override
	public void save(Board b) {
		dao.save(b);
	}
	@Override
	public void delete(Board b) {
		dao.delete(b);
	}
}
