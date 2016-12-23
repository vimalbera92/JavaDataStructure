package com.vbera.util;

public class LRUMain {

	public static void main(String[] args) {
		LRU<Integer> lruCache = new LRU<Integer>(5);
		lruCache.add(5);
		lruCache.printCache();
		lruCache.add(5);
		lruCache.printCache();
		lruCache.add(5);
		lruCache.printCache();
		lruCache.add(1);
		lruCache.printCache();
		lruCache.add(2);
		lruCache.printCache();
		lruCache.add(3);
		lruCache.printCache();
		lruCache.add(4);
		lruCache.printCache();
		lruCache.add(6);
		lruCache.printCache();
		lruCache.add(7);
		lruCache.printCache();
		lruCache.add(4);
		lruCache.printCache();
		lruCache.add(5);
		lruCache.printCache();
	}
}
