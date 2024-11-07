package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
@Repository
public interface BoardRepository extends ElasticsearchRepository<Board, Integer>{
     // 상세보기 
	 // 
	 // 전체 데이터 검색 findAll()
	 // 수정 , 추가 save()
	 // 삭제 => delete() 
	
}