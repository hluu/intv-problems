package org.common;


/***
 *
 * To represent a node in binary tree
 */
public class BNode<T> {
    public T value;
    public BNode left;
    public BNode right;

    public BNode(T v) {
        this.value = v;
    }

    public BNode(T v, BNode<T> left, BNode<T> right) {
        this.value = v;
        this.left = left;
        this.right = right;
    }

    public String toString() {

        return (value != null) ? value.toString() : "null";
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public static <T> BNode create(T v) {

        return new BNode(v);
    }
}

