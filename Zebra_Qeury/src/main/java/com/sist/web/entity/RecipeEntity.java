package com.sist.web.entity;
/*
NO       NOT NULL NUMBER         
TITLE             VARCHAR2(4000) 
CONTENT           VARCHAR2(4000) 
CHEF              VARCHAR2(2000) 
POSTER            VARCHAR2(4000) 
METERIAL          VARCHAR2(4000) 
INFO1             VARCHAR2(4000) 
INFO2             VARCHAR2(4000) 
INFO3             VARCHAR2(4000) 
STEP              VARCHAR2(4000) 
TYPE              VARCHAR2(2000) 
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "recipe")
@Data
public class RecipeEntity {
	@Id
	private int no;
	private int hit, likecount;
	private double score;
	private String title, content, chef, poster, meterial, info1, info2, info3, step, type;
}
