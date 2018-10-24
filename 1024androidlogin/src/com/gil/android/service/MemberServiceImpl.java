package com.gil.android.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gil.android.dao.MemberDao;
import com.gil.android.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	@Override
	public Member login(HttpServletRequest request) {
		//파라미터 읽기 
		String id =request.getParameter("id");
		String pw = request.getParameter("pw");
				//필요한 로직 수행
		
				//DAO 파라미터 만들고
				Member member = new Member();
				member.setId(id);
				member.setPw(pw);
				//DAO 메소드 호출
		
		
				// 리턴할 결과 만들기
				return memberDao.login(member);
	}

}
