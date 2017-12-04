package com.ds.lib.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class Bob<Item> {
	private List<Item> lookUp;
	private Map<Item, Integer> hashTable;
	
	public Bob() {
		lookUp = new ArrayList<>();
		hashTable = new HashMap<>();
	}
	
	public void add(Item item) {
		if(hashTable.containsKey(item))
			throw new IllegalArgumentException("Item " + item + " already exists in Bob!!");
		lookUp.add(item);
		hashTable.put(item, lookUp.size() - 1);
	}
	
	public Item delete(Item item) {
		if(!hashTable.containsKey(item))
			throw new NoSuchElementException("Item " + item + " not present in Bob!!");
		int lastIndex = lookUp.size() - 1;
		Item lastItem = lookUp.get(lastIndex);
		int itemPos = hashTable.get(item);
		
		hashTable.remove(item);
		
		Collections.swap(lookUp, itemPos, lastIndex);
		
		Item delItem = lookUp.get(lastIndex);
		
		lookUp.remove(lastIndex);
		hashTable.put(lastItem, itemPos);
		return delItem;
	}
	
	public Item getRandom() {
		Random rand = new Random();
		int index = rand.nextInt(lookUp.size());
		return lookUp.get(index);
	}
	
	public boolean search(Item item) {
		if(hashTable.containsKey(item))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Bob [lookUp=" + lookUp + ", hashTable=" + hashTable + "]";
	}
}
