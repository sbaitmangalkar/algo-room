package com.ds.lib.storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int n;
	
	private static class Node<Item> {
		Node<Item> next;
		Node<Item> previous;
		Item item;
	}
	
	public Deque() {
		this.first = null;
		this.last = null;
		this.n = 0;
	}
	
	public void addFirst(Item item) {
		if(item == null)
			throw new NullPointerException("Null items are not allowed!!");
		
		Node<Item> oldFirst = first;
		first = new Node<>();
		first.item = item;
		n++;
		if(isEmpty())
			first = last;
		else {
			first.next = oldFirst;
			oldFirst.previous = first;
		}
	}
	
	public void addLast(Item item) {
		if(item == null)
			throw new NullPointerException("Null items are not allowed!!");
		Node<Item> oldLast = last;
		last = new Node<>();
		last.item = item;
		n++;
		if(isEmpty())
			first = last;
		else {
			last.previous = oldLast;
			oldLast.next = last;
		}
	}
	
	public Item removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException("Empty Deque!!");
		Item item = first.item;
		first = first.next;
		n--;
		if(isEmpty())
			last = null;
		return item;
	}
	
	public Item removeLast() {
		if(isEmpty())
			throw new NoSuchElementException("Empty Deque!!");
		Item item = last.item;
		last = last.previous;
		n--;
		if(isEmpty())
			first = null;
		return item;
	}
	
	public Item peekFront() {
		if(isEmpty())
			throw new NoSuchElementException("Empty Deque!!");
		return first.item;
	}
	
	public Item peekBack() {
		if(isEmpty())
			throw new NoSuchElementException("Empty Deque!!");
		return last.item;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator(first);
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current;
		
		public DequeIterator(Node<Item> first) {
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
