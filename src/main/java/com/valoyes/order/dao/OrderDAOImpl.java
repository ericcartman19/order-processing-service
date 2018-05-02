package com.valoyes.order.dao;

import java.sql.SQLException;

import com.valoyes.order.dao.dto.Order;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public int create(Order order) throws SQLException {
		// estos metodos no los vamos a implementar para
		// demostrar el power de mockito
		// estos metodos los vamos a mockear
		return 0;
	}

	@Override
	public Order read(int id) throws SQLException {
		// estos metodos no los vamos a implementar para
		// demostrar el power de mockito
		// estos metodos los vamos a mockear
		return null;
	}

	@Override
	public int update(Order order) throws SQLException {
		// estos metodos no los vamos a implementar para
		// demostrar el power de mockito
		// estos metodos los vamos a mockear
		return 0;
	}

	@Override
	public int delete(int id) throws SQLException {
		// estos metodos no los vamos a implementar para
		// demostrar el power de mockito
		// estos metodos los vamos a mockear
		return 0;
	}

}
