package com.kh.jaba.client.cart.model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.jaba.client.cart.model.dao.CartNoDao;
import com.kh.jaba.client.cart.model.domain.CartNo;
import com.kh.jaba.client.order.model.domain.Order;

@Service("cartNoService")
public class CartNoServiceImpl implements CartNoService {

	@Autowired
	private CartNoDao cartNoDao;
	
	@Override
	public int insertCartNo(CartNo cn) {
		return cartNoDao.insertCartNo(cn);
	}

	@Override
	public CartNo currentCartId() {
		return cartNoDao.currentCartId();
	}

	@Override
	public Order selectOneCartNo(String cartno_id) {
		return cartNoDao.selectOneCartNo(cartno_id);
	}

}