package my.epi.tree;

/**
 * Created by hluu on 12/11/15.
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

    public static BNode create(int v) {
        return new BNode(v);
    }
}
