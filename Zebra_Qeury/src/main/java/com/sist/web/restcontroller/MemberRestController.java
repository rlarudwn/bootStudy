package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.MemberEntity;
import com.sist.web.service.MemberService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
public class MemberRestController {
	@Autowired
	private MemberService mService;

	@PostMapping("/member/join")
	public void memberJoin(MemberEntity e) {
		System.out.println(e);
		mService.save(e);
	}

	@GetMapping("/member/login/{id}/{pwd}")
	public ResponseEntity<Map> memberLogin(@PathVariable("id") String id,@PathVariable("pwd") String pwd) {
		Map map=new HashMap();
		try {
			String result = "";
			MemberEntity e = new MemberEntity();
			int count = mService.idCheck(id);
			if (count == 0) {
				result = "NOID";
			} else {
				String tmp = mService.getPwd(id);
				if (tmp.equals(pwd)) {
					result = "OK";
					e = mService.findById(id).get();
				} else {
					result = "NO";
				}
			}
			map.put("state", result);
			map.put("info", e);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/member/idCheck")
	public int memberIdCheck(String id) {
		int count = mService.idCheck(id);
		return count;
	}

	@GetMapping("/member/nicknameCheck")
	public int memberNicknameCheck(String nickname) {
		int count = mService.nicknameCheck(nickname);
		return count;
	}
}
