package org.common;



/**
 * Created by hluu on 1/10/16.
 *
 * Node for double linked list
 */
public class DLNode<T> extends Node<T> {
    public DLNode<T> prev;
    public DLNode<T> next;

    public DLNode(T value) {
        super(value);
    }

    public DLNode<T> add(T v) {
        DLNode<T>  newNode = new DLNode<T>(v) ;
        add(newNode);

        return newNode;
    }


    public DLNode<T> add(DLNode<T> other) {

        this.next = other;
        other.prev = this;
        return other;
    }

    public void moveUp() {
        DLNode<T> tmp = this.prev;
        this.prev = this;
        this.prev.next = this;
    }

    public DLNode<T> delete() {

        DLNode<T> prev = this.prev;
        // previous node
        if (this.prev != null) {
            this.prev.next = this.next;
        } else {
            prev = this.next;
        }
        // next node
        if (this.next != null) {
            this.next.prev = this.prev;
        }

        this.prev = null;
        this.next = null;

        return prev;
    }

    public String toString() {
        return (value != null) ? value.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("DLNode.main");

        DLNode<Integer> root = new DLNode<>(1);

        DLNode<Integer> two = new DLNode<>(2);
        DLNode<Integer> tail = root.add(two).add(3).add(4);

        LinkedListUtil.printLinkedList(root);
        LinkedListUtil.printLinkedListBackward(tail);

        System.out.println("*** after removing tail ****");
        tail = tail.delete();

        LinkedListUtil.printLinkedList(root);
        LinkedListUtil.printLinkedListBackward(tail);

        System.out.println("*** after removing " + two);
        two.delete();

        LinkedListUtil.printLinkedList(root);
        LinkedListUtil.printLinkedListBackward(tail);

        System.out.println("test deleting head");
        // testing delete head
        DLNode<Integer> head2 = new DLNode<>(1);

        DLNode<Integer> tail2 = new DLNode<>(2);

        head2.add(tail2);
        LinkedListUtil.printLinkedList(head2);
        DLNode<Integer> newHead = head2.delete();
        LinkedListUtil.printLinkedList(newHead);

    }
}
