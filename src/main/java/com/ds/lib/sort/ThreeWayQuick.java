package com.ds.lib.sort;

public class ThreeWayQuick {
	public static void sort(Comparable[] a) {
		
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int lt = lo;
		int gt = hi;
		
		Comparable v = a[lo];
		int i = lo;
		
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			
			if(cmp < 0) 		swap(a, i++, lt++);
			else if(cmp > 0) 	swap(a, i, gt--);
			else 				i++;
		}
		
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}
	
	private static Comparable[] swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
		return a;
	}
}
