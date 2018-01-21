package com.ds.lib.storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
	private Node<Item> first;
	private int n;
	
	private static class Node<Item> {
		Node<Item> next;
		Item item;
	}
	
	public Stack() {
		this.first = null;
		this.n = 0;
	}
	
	public void push(Item item) {
		Node<Item> oldFirst = first;
		first = new Node<>();
		first.item = item;
		first.next = oldFirst;
		n++;
	}
	
	public Item pop() {
		if(isEmpty())
			throw new NoSuchElementException("Empty stack!!");
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public Item peek() {
		if(isEmpty())
			throw new NoSuchElementException("Empty Stack!!");
		return first.item;
	}
	
	public int size() {
		return n;
	}

	@Override
	public Iterator<Item> iterator() {
		return new StackIterator(first);
	}
	
	private class StackIterator implements Iterator<Item> {
		private Node<Item> current;
		
		public StackIterator(Node<Item> first) {
			this.current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}

}
