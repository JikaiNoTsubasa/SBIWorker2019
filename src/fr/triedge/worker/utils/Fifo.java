package fr.triedge.worker.utils;

import java.util.LinkedList;
import java.util.Queue;

public class Fifo<T>{

	private Queue<T> queue = new LinkedList<>();
	private int maxSize;
	
	
	public Fifo(int maxSize) {
		super();
		this.maxSize = maxSize;
	}

	public void add(T element) {
		for (T el : queue) {
			if (el.equals(element))
				return;
		}
		queue.add(element);
		if (queue.size() > maxSize)
			queue.remove();
	}

	public Queue<T> getQueue(){
		return queue;
	}

	public int getMaxSize() {
		return maxSize;
	}


	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
}
