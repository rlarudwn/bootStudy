package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
// DB => @Entity , Elasticsearch => @Docuement 
@Document(indexName = "board")
@Data
public class Board {
  @Id // Primary Key => 검색 
  private int id;
  @Column(insertable = true , updatable = false)
  private int hit;
  @Column(insertable = true , updatable = false)
  private String userid;
  @Column(insertable = true , updatable = false)
  private String nickname;
  private String subject;
  private String content;
  @Column(insertable = true , updatable = false)
  private String regdate;
}