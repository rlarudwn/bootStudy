package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.MLikeEntity;
import com.sist.web.entity.RLikeEntity;
import com.sist.web.service.MLikeService;
import com.sist.web.service.RLikeService;
import com.sist.web.service.RecipeService;

@CrossOrigin(origins = "*")
@RestController
public class LikeRestController {
	@Autowired
	private RLikeService rService;
	@Autowired
	private MLikeService mService;
	@Autowired
	private RecipeService reService;
	@PostMapping("/like/recipeInsert")
	public void likeInsert(@RequestBody RLikeEntity e) {
		e.setLno(rService.likeMaxCount());
		rService.save(e);
		reService.recipeLikeUpdate(e.getNo());
	}
	@DeleteMapping("/like/recipeDelete/{no}/{id}")
	public void recipeLikeDelete(@PathVariable("no") int no, @PathVariable("id") String id) {
		rService.deleteLike(no, id);
		reService.recipeLikeUpdate(no);
	}
	@PostMapping("/like/meterialInsert")
	public void meterialLikeInsert(@RequestBody MLikeEntity e) {
		e.setLno(rService.likeMaxCount());
		mService.save(e);
		reService.meterialLikeUpdate(e.getMno());
	}
	@DeleteMapping("/like/meterialDelete/{mno}/{id}")
	public void meterialLikeDelete(@PathVariable("mno") int mno, @PathVariable("id") String id) {
		mService.deleteLike(mno, id);
		reService.meterialLikeUpdate(mno);
	}
}
