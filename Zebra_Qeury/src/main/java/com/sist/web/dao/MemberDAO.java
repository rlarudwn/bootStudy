package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.MemberEntity;

@Repository
public interface MemberDAO extends JpaRepository<MemberEntity, String> {

	@Query(value = "SELECT COUNT(*) FROM member WHERE id=:id", nativeQuery = true)
	public int idCheck(@Param("id") String id);

	@Query(value = "SELECT COUNT(*) FROM member WHERE nickname=:nickname", nativeQuery = true)
	public int nicknameCheck(@Param("nickname") String nickname);
	
	@Query(value = "SELECT pwd FROM member WHERE id=:id", nativeQuery = true)
	public String getPwd(@Param("id")String id);
	
}
