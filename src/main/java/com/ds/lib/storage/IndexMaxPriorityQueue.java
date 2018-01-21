package com.ds.lib.storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMaxPriorityQueue<Key extends Comparable<Key>> implements Iterable<Integer>{
	private int[] pq;
	private int[] qp;
	private Key[] keys;
	private int n;
	
	@SuppressWarnings("unchecked")
	public IndexMaxPriorityQueue(int maxN) {
		if(maxN <= 0)
			throw new IllegalArgumentException("Don't f*ck around!! Pass on a valid queue size!!");
		n = 0;
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		keys = (Key[]) new Object[maxN + 1];
		for(int i = 0; i < qp.length; i++)
			qp[i] = -1;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public void insert(int i, Key key) {
		if(contains(i))
			throw new IllegalArgumentException("Index " + i + " is already present in the Queue!!");
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = key;
		swim(n);
	}
	
	public int delMax() {
		int max = pq[1];
		swap(1, n--);
		sink(1);
		
		qp[max] = -1;
		keys[max] = null;
		pq[n + 1] = -1;
		return max;
	}
	
	public void delete(int i) {
		if(!contains(i))
			throw new NoSuchElementException();
		int index = qp[i];
		swap(index, n--);
		sink(index);
		keys[i] = null;
		qp[i] = -1;
	}
	
	public void decreaseKey(int i, Key key) {
		if(!contains(i))
			throw new IllegalArgumentException("Index is not present in Queue!!");
		if(keys[i].compareTo(key) <= 0)
			throw new IllegalArgumentException("calling decreaseKey() with given argument would strictely decrease the key");
		keys[i] = key;
		sink(qp[i]);
	}
	
	public void increaseKey(int i, Key key) {
		if(!contains(i))
			throw new IllegalArgumentException("Index is not present in Queue!!");
		if(keys[i].compareTo(key) >= 0)
			throw new IllegalArgumentException("calling increaseKey() with given argument would strictely increase the key");
		keys[i] = key;
		swim(qp[i]);
	}
	
	public void changeKey(int i, Key key) {
		if(!contains(i))
			throw new NoSuchElementException();
		keys[i] = key;
		swim(qp[i]);
		sink(qp[i]);
	}
	
	public Key maxKey() {
		if(n == 0)
			throw new NoSuchElementException("Empty Queue!!");
		return keys[pq[1]];
	}
	
	public int maxIndex() {
		if(n == 0)
			throw new NoSuchElementException("Empty Queue!!");
		return pq[1];
	}
	
	public boolean contains(int i) {
		return qp[i] != -1;
	}
	
	private void swap(int i, int j) {
		int temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
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
	
	private boolean less(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new IndexPQIterator();
	}
	
	private class IndexPQIterator implements Iterator<Integer> {
		private IndexMaxPriorityQueue<Key> copy;
		
		public IndexPQIterator() {
			copy = new IndexMaxPriorityQueue<>(pq.length - 1);
			for(int i = 1; i <= n; i++) {
				copy.insert(pq[i], keys[pq[i]]);
			}
		}
		
		@Override
		public boolean hasNext() {
			return !copy.isEmpty();
		}

		@Override
		public Integer next() {
			if(!hasNext())
				throw new NoSuchElementException();
			
			return copy.delMax();
		}
		
	}
}
