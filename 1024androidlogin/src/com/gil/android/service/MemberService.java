package com.gil.android.service;

import javax.servlet.http.HttpServletRequest;

import com.gil.android.domain.Member;

public interface MemberService {

	//로그인 처리를 위한  메소드
	public Member login(HttpServletRequest request);
}
