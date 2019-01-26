package org.common;


/**
 *
 *
 * Node for single linked list
 */
public class SLNode<T> extends Node<T> {
    public SLNode<T> next;

    public SLNode(T v) {
        super(v);
    }

    public SLNode(T v, SLNode<T> next) {
        super(v);
        this.next = next;
    }

    public SLNode<T> attach(SLNode<T> next) {
        this.next = next;
        return this;
    }

    public static <T> SLNode<T> createNode(T v) {
        return new SLNode(v);
    }

    public static <T> SLNode<T> createNode(T v, SLNode<T> next) {
        return new SLNode(v, next);
    }
}
