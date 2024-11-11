package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="comment")
@Data
public class CommentEntity {
	@Id
	private int rno;
	private int mno, rating;
	private String id, nickname, content, regdate;
}
