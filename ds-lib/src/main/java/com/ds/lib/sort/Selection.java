package com.ds.lib.sort;

public class Selection {
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			int min = i;
			for(int j = i + 1; j < a.length; j++) {
				if(less(a[j], a[min]))
					min = j;
			}
			swap(a, i, min);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable a, Comparable b) {
		if(a.compareTo(b) < 0)
			return true;
		else
			return false;
	}
	
	@SuppressWarnings("rawtypes")
	private static Comparable[] swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}
}
