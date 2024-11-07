package com.sist.web.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.MemberEntity;

public interface MemberService {
	public void save(MemberEntity e);
	public int idCheck(String id);
	public int nicknameCheck(String nickname);
	public String getPwd(String id);
	public Optional<MemberEntity> findById(String id);
}
