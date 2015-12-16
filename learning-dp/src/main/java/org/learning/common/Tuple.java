package org.learning.common;

public class Tuple<T,V> {
	public T first;
	public V second;
	
	public Tuple(T first, V second) {
		this.first = first;
		this.second = second;
	}
	
	public String toString() {
		return "(" + first + "," + second + ")";
	}
	
	public static <T,V> Tuple<T,V> createTuple( T first, V second) {
		return new Tuple<T,V>(first, second);
	}
}
