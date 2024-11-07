package com.sist.web.restcontroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.ReviewEntity;
import com.sist.web.service.RecipeService;
import com.sist.web.service.ReviewService;

@RestController
@CrossOrigin(origins = "*")
public class ReviewRestController {
	@Autowired
	private ReviewService rService;
	@Autowired
	private RecipeService reService;
	
	@PostMapping("/review/insertReact")
	public void reviewInsertReact(@RequestBody ReviewEntity e) {
		e.setRno(rService.reviewMaxRno());
		e.setRegdate(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
		rService.save(e);
		reService.recipeScoreUpdate(e.getNo());
	}
	
	@DeleteMapping("/review/deleteReact/{rno}")
	public void reviewDeleteReact(@PathVariable("rno")int rno) {
		ReviewEntity e=rService.findById(rno);
		rService.reviewDelete(rno);
		reService.recipeScoreUpdate(e.getNo());
	}
}