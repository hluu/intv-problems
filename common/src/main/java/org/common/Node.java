package org.common;

/**
 *
 */
public class Node<T> {
    public T value;

    public Node(T v) {
        this.value = v;
    }

    public String toString() {
        return (value != null) ? value.toString() : "";
    }
}
