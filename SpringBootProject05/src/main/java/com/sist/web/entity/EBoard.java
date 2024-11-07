package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;
@Document(indexName = "eboard")
@Data
public class EBoard {
	@Id
	private int id;
	private String name, subject, content, pwd, regdate;
	private int hit;
}
