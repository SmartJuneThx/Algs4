package assignments.queues;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class Deque<Item> implements Iterable<Item> {
	private ListNode first, last; 
	private int sz;
	
	private class ListNode {
		Item item;
		ListNode prev, next;
		
		public ListNode(Item item, ListNode prev, ListNode next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	
		public String toString() {
			return item.toString();
		}
	}

	// Construct an empty deque
	public Deque() {
		first = new ListNode(null, null, null);  // dummy node
		last = first;
		sz = 0;
	}
	
	// Is the deque empty
	public boolean isEmpty() {
		return first == last;
	}
	
	// Return the number of items on the queue
	public int size() {
		return sz;
	}
	
	// Add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		
		ListNode next = first.next;
		ListNode inserted = new ListNode(item, first, next);
		first.next = inserted;
		if (next != null) {
			next.prev = inserted;
		} else {
			last = inserted;
		}
		
		sz++;
	}
	
	// Add the item to the end
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		
		ListNode oldlast = last;
		last = new ListNode(item, oldlast, null);
		oldlast.next = last;
		
		sz++;
	}
	
	// Remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		
		ListNode node = first.next;
		first.next = first.next.next;
		if (first.next == null) {
			last = first;
		}
		sz--;
		
		return node.item;
	}
	
	// Remove and return the item from the end
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		
		ListNode node = last;
		last = last.prev;
		last.next = null;  // avoid memory loitering
		sz--;
		
		return node.item;
	}
	
	// Return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<Item> {
		private ListNode head;
		
		public MyIterator() {
			head = first;
		}
		
		public boolean hasNext() {
			return head.next != null;
		}
		
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			
			Item item = head.next.item;
			head = head.next;
			return item;
			
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}
	
	// Unit testing
	public static void main(String[] args) {
		Deque<String> deque = new Deque<>();
		add(deque);
		display(deque);
		
		remove(deque);
		display(deque);
	}
	
	static private void add(Deque<String> deque) {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			char ch = (char) ('A' + cnt++);
			String item = ch + "";
			deque.addFirst(item);
		}
		for (int i = 0; i < 4; i++) {
			char ch = (char) ('A' + cnt++);
			String item = ch + "";
			deque.addLast(item);
		}		
	}
	
	private static void remove(Deque<String> deque) {
		System.out.println("removed: " + deque.removeFirst());
		System.out.println("removed: " + deque.removeLast());
	}
	
	private static void display(Deque<String> deque) {
		System.out.print("======deque: ");
		for (String item : deque) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
}
