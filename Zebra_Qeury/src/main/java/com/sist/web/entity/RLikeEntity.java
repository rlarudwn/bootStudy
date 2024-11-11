package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "recipe_like")
@Data
public class RLikeEntity {
	@Id
	private int lno;
	private int no;
	private String id;
}
