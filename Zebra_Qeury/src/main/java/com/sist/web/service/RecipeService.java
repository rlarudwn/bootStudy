package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.MeterialEntity;
import com.sist.web.entity.MeterialVO;
import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeVO;

public interface RecipeService {
	public List<MeterialVO> meterialListData(int start, String fd);
	public MeterialEntity findById(int mno);
	public RecipeEntity findByNo(int no);
	public int meterialCount();
	public int meterialTotalPage(String fd);
	public List<RecipeVO> recipeRandData(List<String> types);
	public List<String> recipeRandTypes();
	public List<RecipeVO> makeRecipeList(List<String> items, int length, int start);
	public int makeTotalCount(List<String> items, int length);
	public List<MeterialVO> cartMeteralList(List<String> items);
	public List<RecipeVO> recipeListData(Map map);
	public int recipeTotalCount(Map map);
	public List<String> recipeTypeList();
	public List<String> recipeInfo1List();
	public List<String> recipeInfo2List();
	public List<String> recipeInfo3List();
	public List<MeterialVO> recipeDetailMeterial(int no);
	public List<RecipeVO> recipeRelateList(int no);
	public List<RecipeVO> meterialRelateList(int mno);
	public void recipeScoreUpdate(int no);
	public void meterialScoreUpdate(int mno);
	
	public void mSave(MeterialEntity e);
	public void rSave(RecipeEntity e);
	
	public void recipeLikeUpdate(int no);
	public void meterialLikeUpdate(int mno);
	
}
