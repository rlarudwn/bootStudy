package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "meterial_like")
@Data
public class MLikeEntity {
	@Id
	private int lno;
	private int mno;
	private String id;
}
