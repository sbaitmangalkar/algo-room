package com.ds.lib.storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPriorityQueue<Key> implements Iterable<Key> {
	private Key[] pq;
	private int n;
	
	public MaxPriorityQueue(int initialCapacity) {
		pq = (Key[]) new Object[initialCapacity + 1];
		n = 0;
	}
	
	public MaxPriorityQueue() {
		this(1);
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public void insert(Key key) {
		if(n >= pq.length - 1)
			resize(2 * pq.length);
		pq[++n] = key;
		swim(n);
		assert isMaxHeap();
	}
	
	public Key delMax() {
		if(isEmpty())
			throw new NoSuchElementException();
		Key max = pq[1];
		swap(1, n--);
		sink(1);
		pq[n + 1] = null;
		if(n > 0 && (n == pq.length - 1 / 4))
			resize(pq.length / 2);
		assert isMaxHeap();
		return max;
	}
	
	public Key maxKey() {
		if(isEmpty())
			throw new NoSuchElementException();
		return pq[1];
	}
	
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			swap(k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2*k;
			if(j < n && less(j, j+1))
				j++;
			if(!less(k, j))
				break;
			swap(k, j);
			k = j;
		}
	}
	
	private void resize(int capacity) {
		Key[] temp = (Key[])new Object[capacity];
		for(int i = 0; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}
	
	private void swap(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	private boolean less(int i, int j) {
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
	}
	
	private boolean isMaxHeap() {
		return isMaxHeap(1);
	}
	
	private boolean isMaxHeap(int k) {
		if(k > n)
			return true;
		int left = 2*k;
		int right = 2*k + 1;
		
		if(left <= n && less(k, left))
			return false;
		if(right <= n && less(k, right))
			return false;
		return isMaxHeap(left) && isMaxHeap(right);
	}

	@Override
	public Iterator<Key> iterator() {
		
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Key> {
		private MaxPriorityQueue<Key> copy;
		
		public HeapIterator() {
			copy = new MaxPriorityQueue<>(size());
			for(int i = 1; i <= n; i++) {
				copy.insert(pq[i]);
			}
		}

		@Override
		public boolean hasNext() {
			return !copy.isEmpty();
		}

		@Override
		public Key next() {
			if(isEmpty())
				throw new NoSuchElementException();
			return copy.delMax();
		}
		
	}

}
