package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity(name = "meterial")
@Data
public class MeterialEntity {
	@Id
	private int mno;
	private int hit, likecount;
	private String name, content, poster, season, temp, kcal, prep, storage, cook, checklist, matchs;
}
