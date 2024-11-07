package com.sist.web.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sist.web.entity.Board;

public interface BoardRepository extends ElasticsearchRepository<Board, Integer>{
	public List<Board> findByName(String name);
}
