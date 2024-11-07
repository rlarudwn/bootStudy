package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="food_like")
@Data
public class LikeEntity {
	@Id
	private int lno;
	private String id, fno;
}
