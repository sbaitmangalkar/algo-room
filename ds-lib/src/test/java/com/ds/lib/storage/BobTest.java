package com.ds.lib.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BobTest {
	private Bob<String> bob;
	
	@Before
	public void init() {
		bob = new Bob<>();
	}
	
	@Test
	public void test() {
		bob.add("Shyam");
		bob.add("Krithi");
		bob.add("Jaya");
		bob.add("Akshata");
		
		System.out.println(bob);
		
		bob.delete("Jaya");
		System.out.println(bob);
		bob.delete("Krithi");
		System.out.println(bob);
	}
	
	@After
	public void tearDown() {
		if(bob != null)
			bob = null;
	}
}
