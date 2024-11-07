package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;
@Document(indexName = "react_board")
@Data
public class Board {
	@Id
	private int id;
	private String name, subject, content, pwd, regdate;
	private int hit;
}
