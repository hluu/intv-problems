package org.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Each node can have n-children
 * @param <T>
 */
public class NChildrenNode<T> extends Node<T> {
    private List<NChildrenNode> children;
    public NChildrenNode(T value) {
        super(value);
        children = new ArrayList<>();
    }

    public void addChil(NChildrenNode<T> child) {
        if (child != null) {
            children.add(child);
        }
    }

    public List<NChildrenNode>getChildren() {
        return children;
    }

    public static <T> NChildrenNode<T> createNode(T value) {
        return new NChildrenNode<T>(value);
    }


}
