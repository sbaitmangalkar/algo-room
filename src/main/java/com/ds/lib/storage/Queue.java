package com.ds.lib.storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int n;
	
	private static class Node<Item> {
		Node<Item> next;
		Item item;
	}
	
	public Queue() {
		this.first = null;
		this.last = null;
		this.n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void enqueue(Item item) {
		Node<Item> oldLast = last;
		last = new Node<>();
		last.item = item;
		if(isEmpty())
			first = last;
		else
			oldLast.next = last;
		n++;
	}
	
	public Item dequeue() {
		if(isEmpty())
			throw new NoSuchElementException("Empty Queue!!");
		Item item = first.item;
		first = first.next;
		n--;
		if(isEmpty())
			last = null;
		return item;
	}
	
	public Item peek() {
		if(isEmpty())
			throw new NoSuchElementException("Empty Queue");
		return first.item;
	}
	
	public int size() {
		return n;
	}

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator(first);
	}
	
	private class QueueIterator implements Iterator<Item> {
		private Node<Item> current;
		
		public QueueIterator(Node<Item> first) {
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
