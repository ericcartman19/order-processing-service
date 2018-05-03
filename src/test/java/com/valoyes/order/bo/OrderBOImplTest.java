package com.valoyes.order.bo;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.valoyes.order.bo.exception.BOException;
import com.valoyes.order.dao.OrderDAO;
import com.valoyes.order.dao.dto.Order;

public class OrderBOImplTest {

	@Mock
	OrderDAO dao;
	
	@Before
	public void setep() {
		// Initializes objects annotated with Mockito annotations for given testClass
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
		// given
		OrderBOImpl bo = new OrderBOImpl();
		bo.setDao(dao);
		
		// when
		// si no estamos testeando por exceptiones, simplemente las lanzamos
		Order order = new Order();
		when(dao.create(order)).thenReturn(1);
		boolean resultado = bo.placeOrder(order);
		
		// then : comprobamos valos y verificamos la llamada del metodo
		assertTrue(resultado);
		verify(dao).create(order);
	}

}
