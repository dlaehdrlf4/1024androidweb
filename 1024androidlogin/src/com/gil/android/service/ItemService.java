package com.gil.android.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gil.android.domain.Item;

public interface ItemService {
	
	
	//리턴 타입은 보통의 경우는 Dao 메소드와 일치
	//매개변수는 제일쉽게 하는 방법은 HttpServletRequest
	//파일이 있을 때는 앞에 Multipart
	public List<Item> listItem(HttpServletRequest request);
	public Item getItem(HttpServletRequest request);
}
