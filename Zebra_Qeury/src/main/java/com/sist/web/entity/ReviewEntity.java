package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="review")
@Data
public class ReviewEntity {
	@Id
	private int rno;
	private int no, rating;
	private String id, nickname, content, regdate;
}
