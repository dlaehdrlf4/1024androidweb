package com.gil.android.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gil.android.dao.ItemDao;
import com.gil.android.domain.Item;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemdao;
	
	@Override
	public List<Item> listItem(HttpServletRequest request) {
		return itemdao.listItem();
	}

	@Override
	public Item getItem(HttpServletRequest request) {
		//파라미터를 가져오기
		String itemid = request.getParameter("itemid");
		//파라미터를 정수로 변환해서 Dao 메소드에게 전달
		return itemdao.getItem(Integer.parseInt(itemid));
	}

}
