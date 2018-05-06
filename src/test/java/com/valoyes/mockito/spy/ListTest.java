package com.valoyes.mockito.spy;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class ListTest {

	@Spy
	List<String> myList = new ArrayList<>();
	
	@Mock
	List<String> myList2 = new ArrayList<>();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
//		myList.add("Sofia");
//		myList.add("Regla");
		
		// mockeamos el metodo size() de la lista
		doReturn(3).when(myList).size();
		
		assertSame(3, myList.size());
	}
	
	@Test
	public void test_MockWith_CallRealMethod() {
		
		when(myList2.get(0)).thenReturn("Rambo");
		// when(myList2.size()).thenCallRealMethod();
		assertSame(0, myList2.size());
	}

}
