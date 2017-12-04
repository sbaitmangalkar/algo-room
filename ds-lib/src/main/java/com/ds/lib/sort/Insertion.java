package com.ds.lib.sort;

public class Insertion {
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int n = a.length;
		for(int i = 0; i < n; i++) {
			for(int j = i; j > 0; j--) {
				if(less(a[j], a[j - 1]))
					swap(a, j, j - 1);
				else
					break;
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a, int lo, int hi) {
		for(int i = lo; i <= hi; i++) {
			for(int j = i; j > lo; j++) {
				if(less(a[j], a[j - 1]))
					swap(a, j, j - 1);
				else
					break;
			}
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
