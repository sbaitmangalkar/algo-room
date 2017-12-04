package com.ds.lib.sort;

import java.util.Random;

public class Quick {
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	@SuppressWarnings("rawtypes")
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int partition = partition(a, lo, hi);
		sort(a, lo, partition - 1);
		sort(a, partition + 1, hi);
	}
	
	@SuppressWarnings("rawtypes")
	public static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		
		while(true) {
			while(less(a[++i], a[lo]))
				if(i == hi)
					break;
			while(less(a[lo], a[--j]))
				if(j == lo)
					break;
			
			if(i >= j)
				break;
			swap(a, i, j);
			
		}
		swap(a, j, lo);
		return j;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	@SuppressWarnings("rawtypes")
	private static Comparable[] swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
		return a;
	}
	
	private static void shuffle(Object[] a) {
		if(a == null)
			throw new IllegalArgumentException();
		Random rand = new Random();
		int n = a.length;
		for(int i = 0; i < n; i++) {
			int r = i + rand.nextInt(n - i);
			Object temp = a[r];
			a[r] = a[i];
			a[i] = temp;
		}
	}
}
