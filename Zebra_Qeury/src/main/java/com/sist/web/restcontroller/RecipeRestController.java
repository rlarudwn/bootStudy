package com.sist.web.restcontroller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.sist.web.entity.*;
import com.sist.web.manager.NewsSearchManager;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class RecipeRestController {
	@Autowired
	private RLikeService rlService;
	@Autowired
	private MLikeService mlService;
	@Autowired
	private RecipeService rService;
	@Autowired
	private ReviewService reService;
	@Autowired
	private NewsSearchManager m;
	@Autowired
	private CommentService cService;
	@GetMapping("/recipe/home")
	public ResponseEntity<Map> recipeHome() {
		Map map = new HashMap();
		try {
			List<NewsVO> nList=m.newsFind("레시피");
			List<String> types = rService.recipeRandTypes();
			List<RecipeVO> rList = rService.recipeRandData(types);
			map.put("rList", rList);
			map.put("nList", nList);
			Map typesMap = new HashMap();
			for (int i = 0; i < types.size(); i++) {
				int cnt = 0;
				for (RecipeVO vo : rList) {
					if (vo.getType().equals(types.get(i)))
						cnt++;
				}
				if (cnt > 0) {
					typesMap.put(types.get(i), "recipe_" + (i + 1));
				} else {
					types.remove(i);
				}
			}
			map.put("typeName", types);
			map.put("types", typesMap);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/recipe/meterialList/{page}/{fd}")
	public ResponseEntity<Map> recipeMeterialList(@PathVariable("page") int page, @PathVariable("fd") String fd) {
		Map map = new HashMap();
		System.out.println(fd);
		try {
			int start = (page - 1) * 12;
			if (fd.equals("*"))
				fd = "";
			System.out.println(fd);
			List<MeterialVO> list = rService.meterialListData(start, fd);
			int count = rService.meterialTotalPage(fd);
			int totalPage = (int) (Math.ceil(count / 12.0));
			int startPage = (page - 1) / 10 * 10 + 1;
			int endPage = startPage + 10 - 1;
			if (endPage > totalPage)
				endPage = totalPage;
			map.put("list", list);
			map.put("curPage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("totalPage", totalPage);
			map.put("count", count);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/recipe/recipeList/{page}/{option}/{info1}/{info2}/{info3}/{fd}")
	public ResponseEntity<Map> recipeListData(@PathVariable("page") int page, @PathVariable("option") String option,
			@PathVariable("info1") String info1, @PathVariable("info2") String info2, @PathVariable("info3") String info3,
			@PathVariable("fd") String fd) {
		Map map = new HashMap();
		option = option.replaceAll(",", "/");
		System.out.println(option);
		System.out.println(fd);
		System.out.println(info1);
		System.out.println(info2);
		System.out.println(info3);
		if (option.equals("*"))
			option = "";
		if (info1.equals("*"))
			info1 = "";
		if (info2.equals("*"))
			info2 = "";
		if (info3.equals("*"))
			info3 = "";
		if (fd.equals("*"))
			fd = "";
		try {
			int start = (page - 1) * 12;
			map.put("option", option);
			map.put("info1", info1);
			map.put("info2", info2);
			map.put("info3", info3);
			map.put("fd", fd);
			map.put("start", start);

			List<RecipeVO> list = rService.recipeListData(map);
			List<String> type = rService.recipeTypeList();
			List<String> info1s = rService.recipeInfo1List();
			List<String> info2s = rService.recipeInfo2List();
			List<String> info3s = rService.recipeInfo3List();

			int count = rService.recipeTotalCount(map);

			int totalPage = (int) (Math.ceil(count / 12.0));
			int startPage = (page - 1) / 10 * 10 + 1;
			int endPage = startPage + 10 - 1;
			if (endPage > totalPage)
				endPage = totalPage;

			map = new HashMap();
			map.put("types", type);
			map.put("info1List", info1s);
			map.put("info2List", info2s);
			map.put("info3List", info3s);
			map.put("list", list);
			map.put("curPage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("totalPage", totalPage);
			map.put("count", count);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/recipe/makeRecipe/{page}/{item}")
	public ResponseEntity<Map> recipeMake(@PathVariable("item") String item, @PathVariable("page") int page) {
		Map map = new HashMap();
		try {
			int start = (page - 1) * 12;
			String[] itemsList = item.split(",");
			List<String> items = Arrays.asList(itemsList);
			int length = items.size();
			List<RecipeVO> rList = rService.makeRecipeList(items, length, start);
			List<MeterialVO> mList = rService.cartMeteralList(items);
			int count = rService.makeTotalCount(items, length);
			int startPage = (page - 1) / 10 * 10 + 1;
			int totalPage = (int) (Math.ceil(count / 12.0));
			int endPage = startPage + 10 - 1;
			if (endPage > totalPage)
				endPage = totalPage;
			map.put("rList", rList);
			map.put("mList", mList);
			map.put("curPage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("totalPage", totalPage);
			map.put("count", count);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/recipe/recipeDetail/{no}/{id}")
	public ResponseEntity<Map> recipeDetail(@PathVariable("no") int no, @PathVariable("id")String id) {
		if (id.contains("*")){
			id="";
		}
		Map map = new HashMap();
		try {
			RecipeEntity e = rService.findByNo(no);
			List<MeterialVO> poster = rService.recipeDetailMeterial(no);
			List<String> iList = new ArrayList<String>();
			List<String> sList = new ArrayList<String>();
			List<RecipeVO> rList=rService.recipeRelateList(no);
			List<String> items=Arrays.asList(e.getMeterial().split(","));
			List<ReviewEntity> reviewList=reService.reviewListData(no);
			int count=reService.reviewCount(no);
			int reviewCount=reService.myReviewCount(no, id);
			String[] tmps = e.getStep().split("\n");
			for (String s : tmps) {
				System.out.println(s);
				StringTokenizer st = new StringTokenizer(s, "^");
				while (st.hasMoreTokens()) {
					sList.add(st.nextToken());
					iList.add(st.nextToken());
				}
			}
			int isLike=rlService.isLike(no, id);
			map.put("isLike", isLike);
			map.put("detail", e);
			map.put("posters", poster);
			map.put("iList", iList);
			map.put("sList", sList);
			map.put("rList", rList);
			map.put("count", count);
			map.put("reviewCount", reviewCount);
			map.put("reviewList", reviewList);
			map.put("items", items);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/recipe/meterialDetail/{mno}/{id}")
	public ResponseEntity<Map> recipeMeterialDetail(@PathVariable("mno")int mno, @PathVariable("id")String id){
		Map map=new HashMap();
		try {
			MeterialEntity e=rService.findById(mno);
			List<RecipeVO> rList=rService.meterialRelateList(mno);
			int count=cService.commentCount(mno);
			int commentCount=cService.myCommentCount(mno, id);
			List<CommentEntity> cList=cService.commentListData(mno);
			int isLike=mlService.isLike(mno, id);
			map.put("detail", e);
			map.put("isLike", isLike);
			map.put("rList", rList);
			map.put("count", count);
			map.put("commentCount", commentCount);
			map.put("commentList", cList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/meterial/hitIncrement/{mno}")
	public void meterialHitIncrement(@PathVariable("mno") int mno) {
		MeterialEntity e= rService.findById(mno);
		e.setHit(e.getHit()+1);
		rService.mSave(e);
	}
	@GetMapping("/recipe/hitIncrement/{no}")
	public void recipeHitIncrement(@PathVariable("no") int no) {
		RecipeEntity e= rService.findByNo(no);
		e.setHit(e.getHit()+1);
		rService.rSave(e);
	}
}
