package com.ds.lib.storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPriorityQueue<Key> implements Iterable<Key> {
	private Key[] pq;
	private int n;
	
	public MinPriorityQueue(int initialCapacity) {
		this.pq = (Key[]) new Object[initialCapacity  + 1];
		this.n = 0;
	}
	
	public MinPriorityQueue() {
		this(1);
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public void insert(Key key) {
		if(n >= pq.length)
			resize(2 * pq.length);
		pq[++n] = key;
		swim(n);
		assert isMinHeap();
	}
	
	public Key delMin() {
		if(isEmpty())
			throw new NoSuchElementException();
		Key min = pq[1];
		swap(1, n--);
		sink(1);
		pq[n+1] = null;
		if(n > 0 && (n == pq.length - 1 / 4))
			resize(pq.length / 2);
		assert isMinHeap();
		return min;
	}
	
	public Key minKey() {
		if(isEmpty())
			throw new NoSuchElementException();
		return pq[1];
	}
	
	private boolean isMinHeap() {
		return isMinHeap(1);
	}
	
	private boolean isMinHeap(int k) {
		if(k > n)
			return true;
		int left = 2*k;
		int right = 2*k + 1;
		
		if(left <= n && greater(k ,left))
			return false;
		if(right <= n && greater(k, right))
			return false;
		return isMinHeap(left) && isMinHeap(right);
	}

	private void resize(int capacity) {
		Key[] temp = (Key[]) new Object[capacity];
		for(int i = 0; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	private void swim(int k) {
		while(k > 1 && greater(k/2, k)) {
			swap(k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2*k;
			if(j < n && greater(j, j+1))
				j++;
			if(!greater(k, j))
				break;
			swap(k, j);
			k = j;
		}
	}
	
	private boolean greater(int i, int j) {
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
	}
	
	private void swap(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	@Override
	public Iterator<Key> iterator() {
		return new MinPriorityQueueIterator();
	}
	
	private class MinPriorityQueueIterator implements Iterator<Key> {
		private MinPriorityQueue<Key> copy;
		
		public MinPriorityQueueIterator() {
			copy = new MinPriorityQueue<>(size());
			for(int i = 1; i <= n; i++)
				copy.insert(pq[i]);
		}

		@Override
		public boolean hasNext() {
			return !copy.isEmpty();
		}

		@Override
		public Key next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return copy.delMin();
		}
		
	}

}
