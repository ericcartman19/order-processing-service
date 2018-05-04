package com.valoyes.order.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.valoyes.order.bo.exception.BOException;
import com.valoyes.order.dao.OrderDAO;
import com.valoyes.order.dao.dto.Order;

public class OrderBOImplTest {

	private static final int ORDER_ID = 123;
	
	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;

	@Before
	public void setep() {
		// Initializes objects annotated with Mockito annotations for given testClass
		MockitoAnnotations.initMocks(this);

		bo = new OrderBOImpl();
		bo.setDao(dao);
	}

	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
		// given
		OrderBOImpl bo = new OrderBOImpl();
		bo.setDao(dao);

		// when
		// si no estamos testeando por excepciones, simplemente las lanzamos
		Order order = new Order();
		when(dao.create(any())).thenReturn(new Integer(1));
		when(dao.create(any(Order.class))).thenReturn(new Integer(1));
		boolean resultado = bo.placeOrder(order);

		// then : comprobamos valos y verificamos la llamada del metodo
		assertTrue(resultado);
		
		verify(dao).create(order);
	}

	@Test
	public void placeOrder_Should_not_Create_An_Order() throws SQLException, BOException {

		// when
		// si no estamos testeando por excepciones, simplemente las lanzamos
		Order order = new Order();
		when(dao.create(order)).thenReturn(0);
		boolean resultado = bo.placeOrder(order);

		// then : comprobamos valos y verificamos la llamada del metodo
		assertFalse(resultado);
		
		verify(dao).create(order);
	}
	
	@Test(expected=BOException.class)
	public void placeOrder_Should_BOException() throws SQLException, BOException {

		// when
		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);
		boolean resultado = bo.placeOrder(order);
	}
	
	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
		// given
		Order order = new Order();
		
		// when : stubbing
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean resultado = bo.cancelOrder(123);
		
		// then
		assertTrue(resultado);
		
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);
	}
	
	@Test
	public void cancelOrder_Should_Cancel_not_The_Order() throws SQLException, BOException {
		// given
		Order order = new Order();
		
		// when : stubbing
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean resultado = bo.cancelOrder(123);
		
		// then
		assertFalse(resultado);
		
		verify(dao).read(ORDER_ID);
		verify(dao, times(1)).update(order);
	}
	
	@Test(expected=BOException.class)
	public void cancelOrder_Should_ThrowBOException_OnRead() throws SQLException, BOException {
		
		// when
		when(dao.read(ORDER_ID)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDER_ID);
		
		// este test verifica que la exception lanzada por el dao es wrapped en un BOException
	}
	
	@Test(expected=BOException.class)
	public void cancelOrder_Should_ThrowBOException_OnUpdate() throws SQLException, BOException {
		// given
		Order order = new Order();
		
		// when : stubbing
		when(dao.read(anyInt())).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(123);
	}
	
	@Test
	public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
		
		// when : stubbing
		when(dao.delete(ORDER_ID)).thenReturn(1);
		boolean resultado = bo.deleteOrder(ORDER_ID);
		
		// then
		assertTrue(resultado);
		
		verify(dao, atLeast(1)).delete(ORDER_ID);
	}
	
	@Test
	public void deleteOrder_do_not_Deletes_The_Order() throws SQLException, BOException {
		
		// when : stubbing
		when(dao.delete(ORDER_ID)).thenReturn(0);
		boolean resultado = bo.deleteOrder(ORDER_ID);
		
		// then
		assertFalse(resultado);
		
		verify(dao).delete(ORDER_ID);
	}
	
	
	@Test(expected=BOException.class)
	public void deleteOrder_ThrowsBOException() throws SQLException, BOException {
		
		// when
		when(dao.delete(ORDER_ID)).thenThrow(SQLException.class);
		bo.deleteOrder(ORDER_ID);
	}

}
