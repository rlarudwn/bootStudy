package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface BoardRepository extends ElasticsearchRepository<Board, Integer> {
}