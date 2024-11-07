package com.sist.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.MemberDAO;
import com.sist.web.entity.MemberEntity;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO dao;
	public void save(MemberEntity e) {
		dao.save(e);
	}
	@Override
	public int idCheck(String id) {
		return dao.idCheck(id);
	}
	@Override
	public int nicknameCheck(String nickname) {
		return dao.nicknameCheck(nickname);
	}
	@Override
	public String getPwd(String id) {
		return dao.getPwd(id);
	}
	@Override
	public Optional<MemberEntity> findById(String id) {
		return dao.findById(id);
	}
	
}
