package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.LikeEntity;
import com.sist.web.service.LikeService;

@CrossOrigin(origins = "*")
@RestController
public class LikeRestController {
	@Autowired
	private LikeService lService;
	@GetMapping("/like/insert")
	public void likeInsert(LikeEntity e) {
		e.setLno(lService.likeMaxCount());
		lService.save(e);
	}
	@GetMapping("/like/delete")
	public void likeDelete(String fno, String id) {
		lService.deleteLike(fno, id);
	}
}
