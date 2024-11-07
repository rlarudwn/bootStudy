package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.BoardRepository;
import com.sist.web.entity.*;
import com.sist.web.service.BoardServiceImpl;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class BoardRestController {
	@Autowired
	private BoardServiceImpl bDao;

	@GetMapping("/board/listReact/{page}")
	public ResponseEntity<Map> eboard_list(@PathVariable("page")int page) {
		try {
			int rowSize = 10;
			Pageable pg = PageRequest.of(page - 1, rowSize, Sort.by(Sort.Direction.DESC, "id"));
			Page<Board> pList = bDao.findAll(pg); 
			List<Board> list = new ArrayList<Board>();
			if (pList != null && pList.hasContent()){
				list = pList.getContent();
			}

			int count = bDao.count();
			int totalpage = (int) (Math.ceil(count / 10.0));
			
			Map map = new HashMap();
			map.put("list", list);
			map.put("count", count);
			map.put("curPage", page);
			map.put("totalPage", totalpage);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}

	@PostMapping("board/insertReact")
	public void eboard_insert(@RequestBody Board vo) {
		try {
			// 1. id , 2. hit , 3. regdate
			vo.setHit(0);
			vo.setId(idMaxData());
			vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			bDao.save(vo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 시퀀스
	public int idMaxData() {
		int max = 0;
		try {
			int rowSize = 10;
			int start = 0; // (page-1)*rowSize
			Pageable pg = PageRequest.of(start, rowSize, Sort.by(Sort.Direction.DESC, "id"));
			// 페이지 나누기 => Limit (Database) => ElasticSearch는 전체 데이터를 가지고 온다
			Page<Board> pList = bDao.findAll(pg); // 정렬후에 => 데이터를 10개
			List<Board> list = new ArrayList<Board>();
			if (pList != null && pList.hasContent()) // 값 존재 확인
			{
				list = pList.getContent(); // Page => List로 변환
				max = list.get(0).getId();
			}
		} catch (Exception ex) {
			max = 0;
		}
		return max + 1;
	}

	@GetMapping("/board/hitIncrement/{id}")
	public void boardHitIncrement(@PathVariable("id")int id) {
		Board vo = bDao.findById(id);
		vo.setHit(vo.getHit() + 1);
		bDao.save(vo);
	}
	
	// 상세보기
	@GetMapping("/board/detailReact/{id}")
	public ResponseEntity<Board> eboard_detail(@PathVariable("id")int id) {
		try {
			Board vo = bDao.findById(id);
			vo = bDao.findById(id);
			return new ResponseEntity<>(vo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/board/updateReact/{id}")
	public ResponseEntity<Board> board_update(@PathVariable("id")int id) {
		try {
			Board vo = bDao.findById(id);
			vo = bDao.findById(id);
			return new ResponseEntity<>(vo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/board/updateOkReact")
	public void board_update_ok(@RequestBody Board vo) {
		Board dbvo = bDao.findById(vo.getId());
		vo.setUserid(dbvo.getUserid());
		vo.setNickname(dbvo.getNickname());
		vo.setHit(dbvo.getHit());
		vo.setRegdate(dbvo.getRegdate());
		bDao.save(vo); // 수정
	}

	@DeleteMapping("/board/deleteReact/{id}")
	public void eboard_delete(@PathVariable("id")int id) {
		Board vo = bDao.findById(id);
		bDao.delete(vo);
	}

}