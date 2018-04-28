package org.common;

public class Tuple<T,V> {
	public T first;
	public V second;
	
	public Tuple(T first, V second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		Tuple<String, Integer> other = (Tuple<String, Integer>) o;

		return this.first.equals(other.first) && this.second.equals(other.second);
	}

	@Override
	public String toString() {
		return "(" + first + "," + second + ")";
	}
	
	public static <T,V> Tuple<T,V> createTuple( T first, V second) {
		return new Tuple<T,V>(first, second);
	}
}
