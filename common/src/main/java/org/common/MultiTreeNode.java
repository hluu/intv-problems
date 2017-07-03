package org.common;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hluu on 6/28/17.
 */
public class MultiTreeNode<T> extends Node<T>  {
    private List<MultiTreeNode<T>> childList = new LinkedList<>();

    public MultiTreeNode(T t) {
        super(t);
    }

    public Collection<MultiTreeNode<T>> getChildNodes() {
        return new LinkedList<MultiTreeNode<T>>(childList);
    }

    public void addChild(MultiTreeNode<T>... tArray) {
        for (MultiTreeNode<T> t : tArray ) {
            childList.add(t);
        }
    }

}
