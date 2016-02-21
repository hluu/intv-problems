package org.common;



/**
 * Created by hluu on 1/17/16.
 */
public class TreeNode<T> extends Node<T> {
    public enum VisitState { NOT_VISITED, VISITING, VISITED };

    public TreeNode<T> parent;
    public TreeNode<T> left;
    public TreeNode<T> right;
    public VisitState state = VisitState.NOT_VISITED;

    public TreeNode(T t) {
        super(t);
    }

    public TreeNode(T t, TreeNode left) {
        this(t);
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
    }

    public TreeNode(T t, TreeNode<T> left, TreeNode<T> right) {
        this(t);
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
        this.right = right;
        if (right != null) {
            right.parent = this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof TreeNode)) {
            return false;
        }

        TreeNode<T> other = (TreeNode<T>)o;
        return this.value.equals(((TreeNode<T>) o).value);
    }

    public boolean isNotVisited() {
        return (this.state == VisitState.NOT_VISITED);
    }
    public void visiting() {
        this.state = VisitState.VISITING;
    }

    public boolean isVisiting() {
        return (this.state == VisitState.VISITING);
    }

    public void visited() {
        this.state = VisitState.VISITED;
    }

    public boolean  isVisited() {
        return (this.state == VisitState.VISITED);
    }

    public void resetState() {
        this.state = VisitState.NOT_VISITED;
    }

    public String toString() {
        return super.toString() + " left: " +
                ((left != null) ? left.toString() : "null") +
                " right: " + ((right != null) ? right.toString() : "null");
    }

    public static <T> TreeNode<T> createTreeNode(T t) {
        return new TreeNode<T>(t);
    }

    public static <T> TreeNode<T> createTreeNode(T t, TreeNode left) {

        return new TreeNode<T>(t, left);
    }

    public static <T> TreeNode<T> createTreeNode(T t, T left) {

        return new TreeNode<T>(t, new TreeNode(left));
    }

    public static <T> TreeNode<T> createTreeNode(T t, TreeNode left, TreeNode<T> right ) {
        return new TreeNode<T>(t, left, right);
    }

    /*
    public static <T> TreeNode<T> createTreeNode(T t, Integer left, TreeNode<T> right ) {
        return new TreeNode<T>(t, new TreeNode(left), right);
    }

    public static <T> TreeNode<T> createTreeNode(T t, TreeNode<T> left, Integer right ) {
        return new TreeNode<T>(t, left, new TreeNode(right) );
    } */

    public static TreeNode<Integer> createTreeNode(Integer n, Integer left, Integer right ) {
        if (n == null) {
            throw new NullPointerException("value can't be null");
        }
        TreeNode<Integer> result = new TreeNode<Integer>(n);
        if (left != null) {
            result.left = createTreeNode(left);
            result.left.parent = result;
        }

        if (right != null) {
            result.right = createTreeNode(right);
            result.right.parent = result;
        }
        return result;
    }

    public static <T> void resetState(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        if (root.isNotVisited()) {
            return;
        }
        root.resetState();


        resetState(root.left);
        resetState(root.right);
    }

}
