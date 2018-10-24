package com.gil.android.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gil.android.domain.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;
	
	//로그인 처리 메소드
	public Member login(Member member) {
		return sqlSession.selectOne("member.login", member);
	}
	
}
