package com.ds.lib.storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
	private Node<Item> first;
	private int n;
	
	private static class Node<Item> {
		Node<Item> next;
		Item item;
	}
	
	public Bag() {
		this.first = null;
		this.n = 0;
	}
	
	public void add(Item item) {
		Node<Item> oldFirst = first;
		first = new Node<>();
		first.item = item;
		first.next = oldFirst;
		n++;
	}
	
	public int size() {
		return n;
	}

	@Override
	public Iterator<Item> iterator() {
		return new BagIterator(first);
	}
	
	private class BagIterator implements Iterator<Item> {
		private Node<Item> current;
		
		public BagIterator(Node<Item> first) {
			this.current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException("Empty Bag!!");
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
}
