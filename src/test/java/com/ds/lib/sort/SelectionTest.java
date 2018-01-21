package com.ds.lib.sort;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SelectionTest {
	private static Integer[] a;
	
	@Before
	public void init() {
		a = new Integer[] {10,2,1,4,3,8,6,9,5,7};
	}
	
	@Test
	public void test() {
		Selection.sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	@After
	public void tearDown() {
		
	}
}
