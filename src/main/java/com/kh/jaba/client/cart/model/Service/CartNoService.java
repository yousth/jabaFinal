package com.kh.jaba.client.cart.model.Service;

import com.kh.jaba.client.cart.model.domain.CartNo;

public interface CartNoService {
	public int insertCartNo();

	public CartNo currentCartId();

	public CartNo selectOneCartNo(String cartno_id);
}
