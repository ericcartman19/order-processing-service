package com.valoyes.order.bo;

import java.sql.SQLException;

import com.valoyes.order.bo.exception.BOException;
import com.valoyes.order.dao.OrderDAO;
import com.valoyes.order.dao.dto.Order;

public class OrderBOImpl implements OrderBO {

	private OrderDAO dao;
	
	@Override
	public boolean placeOrder(Order order) throws BOException {
		try {
			if(dao.create(order) == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		
		return true;
	}

	@Override
	public boolean cancelOrder(int id) throws BOException {
		try {
			Order order = dao.read(id);
			if(dao.update(order)==0) {
				return false;
			}
			
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean deleteOrder(int id) throws BOException {
		try {
			if(dao.delete(id) == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
			
		return true;
	}

	public OrderDAO getDao() {
		return dao;
	}

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}
}
