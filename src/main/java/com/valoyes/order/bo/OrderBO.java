package com.valoyes.order.bo;

import com.valoyes.order.bo.exception.BOException;
import com.valoyes.order.dao.dto.Order;

public interface OrderBO {

	boolean placeOrder(Order order) throws BOException;
	
	boolean cancelOrder(int id) throws BOException;
	
	boolean deleteOrder(int id) throws BOException;
}
