package com.gil.android;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gil.android.domain.Member;
import com.gil.android.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public Map<String, String>  login(HttpServletRequest request){
		
		Map<String , String> map = new HashMap<String, String>();
		
		//Service 메소드 호출
		Member member = memberService.login(request);
		//로그인 실패
		if(member == null) {
			map.put("id", "null");
		}
		//로그인 성공
		else {
			map.put("id", member.getId());
			map.put("nicname", member.getNicname());
		}
		return map;
	}
}
