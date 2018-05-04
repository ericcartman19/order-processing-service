package com.valoyes.mockito.spy;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class ListTest {

	@Spy
	List<String> myList = new ArrayList<>();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
		myList.add("Sofia");
		myList.add("Regla");
		
		// mockeamos el metodo size() de la lista
		doReturn(3).when(myList).size();
		
		assertSame(3, myList.size());
	}

}
