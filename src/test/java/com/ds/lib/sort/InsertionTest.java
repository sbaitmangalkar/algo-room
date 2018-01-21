package com.ds.lib.sort;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InsertionTest {
	private String[] a;
	
	@Before
	public void init() {
		a = new String[] {"G","A","D","B","C","F","E"};
	}
	
	@Test
	public void test() {
		Insertion.sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	@After
	public void tearDown() {
		
	}
}
