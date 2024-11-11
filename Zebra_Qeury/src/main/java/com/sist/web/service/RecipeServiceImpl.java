package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.MeterialDAO;
import com.sist.web.dao.RecipeDAO;
import com.sist.web.entity.MeterialEntity;
import com.sist.web.entity.MeterialVO;
import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeVO;
@Service
public class RecipeServiceImpl implements RecipeService{
	@Autowired
	private MeterialDAO mDao;
	@Autowired
	private RecipeDAO rDao;
	@Override
	public List<MeterialVO> meterialListData(int start, String fd) {
		return mDao.meterialListData(start, fd);
	}

	@Override
	public MeterialEntity findById(int mno) {
		return mDao.findById(mno).get();
	}

	@Override
	public int meterialCount() {
		return (int)mDao.count();
	}
	@Override
	public int meterialTotalPage(String fd) {
		return mDao.meterialTotalPage(fd);
	}

	@Override
	public List<RecipeVO> recipeRandData(List<String> types) {
		return rDao.recipeRandData(types);
	}

	@Override
	public List<String> recipeRandTypes() {
		return rDao.recipeRandTypes();
	}
	
	@Override
	public List<RecipeVO> makeRecipeList(List<String> items, int length, int start) {
		return rDao.makeRecipeList(items, length, start);
	}
	
	@Override
	public int makeTotalCount(List<String> items, int length) {
		return rDao.makeTotalCount(items, length);
	}
	
	@Override
	public List<MeterialVO> cartMeteralList(List<String> items) {
		return mDao.cartMeteralList(items);
	}
	
	@Override
	public List<RecipeVO> recipeListData(Map map) {
		return rDao.recipeListData(map);
	}
	
	@Override
	public int recipeTotalCount(Map map) {
		return rDao.recipeTotalCount(map);
	}

	@Override
	public List<String> recipeTypeList() {
		return rDao.recipeTypeList();
	}

	@Override
	public List<String> recipeInfo1List() {
		return rDao.recipeInfo1List();
	}

	@Override
	public List<String> recipeInfo2List() {
		return rDao.recipeInfo2List();
	}

	@Override
	public List<String> recipeInfo3List() {
		return rDao.recipeInfo3List();
	}

	@Override
	public RecipeEntity findByNo(int no) {
		return rDao.findById(no).get();
	}
	
	@Override
	public List<MeterialVO> recipeDetailMeterial(int no) {
		return rDao.recipeDetailMeterial(no);
	}
	
	@Override
	public List<RecipeVO> recipeRelateList(int no) {
		return rDao.recipeRelateList(no);
	}
	
	@Override
	public List<RecipeVO> meterialRelateList(int mno) {
		return mDao.meterialRelateList(mno);
	}
	
	@Override
	public void recipeScoreUpdate(int no) {
		rDao.recipeScoreUpdate(no);
	}
	
	@Override
	public void meterialScoreUpdate(int mno) {
		mDao.meterialScoreUpdate(mno);
	}

	@Override
	public void mSave(MeterialEntity e) {
		mDao.save(e);
	}

	@Override
	public void rSave(RecipeEntity e) {
		rDao.save(e);
	}
	
	@Override
	public void recipeLikeUpdate(int no) {
		rDao.recipeLikeUpdate(no);
	}
	
	@Override
	public void meterialLikeUpdate(int mno) {
		mDao.meterialLikeUpdate(mno);
	}
}
