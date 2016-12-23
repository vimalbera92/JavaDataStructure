package com.vbera.util;

import java.util.HashMap;
import java.util.Map;

public class LRU<E> {

	private int size;
	private int occupiedBlocks = 0;

	private Node<E> leastRecentlyUsedElement;
	private Node<E> mostRecentlyUsedElement;

	private Map<E, Node<E>> cache = new HashMap<E, Node<E>>();

	public LRU(int size) {
		this.size = size;
	}

	public void add(E element) {
		if (leastRecentlyUsedElement == null) {
			Node<E> node = createNode(element);
			leastRecentlyUsedElement = node;
			mostRecentlyUsedElement = node;
			cache.put(element, node);
			occupiedBlocks++;
		} else {
			if (cache.containsKey(element)) {
				Node<E> elementNode = cache.get(element);
				addElementToFront(elementNode);
			} else {
				Node<E> node = createNode(element, mostRecentlyUsedElement, null);
				if (occupiedBlocks >= size) {
					cache.remove(leastRecentlyUsedElement.value);
					leastRecentlyUsedElement = leastRecentlyUsedElement.next;
					leastRecentlyUsedElement.prev = null;
					occupiedBlocks--;
				}
				mostRecentlyUsedElement.next = node;
				mostRecentlyUsedElement = node;
				cache.put(element, node);
				occupiedBlocks++;
			}
		}
	}

	private void addElementToFront(Node<E> elementNode) {
		if (elementNode.next != null) {
			if (elementNode.prev == null) {
				mostRecentlyUsedElement.next = leastRecentlyUsedElement;
				leastRecentlyUsedElement = leastRecentlyUsedElement.next;
				mostRecentlyUsedElement.next = null;
			} else {
				elementNode.prev.next = elementNode.next;
				elementNode.next.prev = elementNode.prev;
				mostRecentlyUsedElement.next = elementNode;
				elementNode.prev = mostRecentlyUsedElement;
				mostRecentlyUsedElement = mostRecentlyUsedElement.next;
			}
		}
	}

	private LRU<E>.Node<E> createNode(E element, Node<E> prev, Node<E> next) {
		Node<E> node = new Node<E>(element, prev, next);
		return node;
	}

	private Node<E> createNode(E element) {
		return createNode(element, null, null);
	}

	public void printCache() {
		Node<E> temp = mostRecentlyUsedElement;
		System.out.println("==============");
		do {
			System.out.println(temp.value);
			temp = temp.prev;
		} while (temp != null);
		System.out.println("==============");
	}

	public class Node<E> {
		private Node<E> next;
		private Node<E> prev;
		private E value;

		public Node(E element, Node<E> prev, Node<E> next) {
			this.value = element;
			this.prev = prev;
			this.next = next;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [next=" + next + ", prev=" + prev + ", value=" + value + "]";
		}

	}

	@Override
	public String toString() {
		return "LRU [size=" + size + ", occupiedBlocks=" + occupiedBlocks + ", leastRecentlyUsedElement=" + leastRecentlyUsedElement + ", mostRecentlyUsedElement=" + mostRecentlyUsedElement
				+ ", cache=" + cache + "]";
	}

}
