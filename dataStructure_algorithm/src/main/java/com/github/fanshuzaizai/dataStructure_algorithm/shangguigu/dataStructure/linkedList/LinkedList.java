package com.github.fanshuzaizai.dataStructure_algorithm.shangguigu.dataStructure.linkedList;

/**
 * @author Jzy.
 * @date 2019/7/1 16:55
 */
public class LinkedList<E> {

    private Node<E> head;

    private Node<E> tail;

    public void add(E ele) {

        if (isEmpty()) {
            head = new Node<>(ele);
            tail = head;
        } else {
            tail.next = new Node<>(ele);
            tail = tail.next;
        }
    }

    public void removeFirst(E ele) {
        if (isEmpty()) {
            System.err.println("空的");
        } else {
            Node<E> current = head;
            Node<E> previous = null;
            while (current != null) {

                Node<E> next = current.next;
                if (ele == current.element) {
                    //前一个不为空
                    if (previous != null) {
                        if (next != null) {
                            previous.next = next;
                        } else {
                            previous.next = null;
                            tail = previous;
                        }
                    } else {
                        if (next != null) {
                            head = next;
                        } else {
                            head = null;
                            tail = null;
                        }
                    }
                    break;
                } else {
                    previous = current;
                    current = next;
                }
            }
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return String.format("%s，", element.toString());
        }
    }

    @Override
    public String toString() {
        Node<E> current = head;
        StringBuilder builder = new StringBuilder();
        builder.append("head:").append(head);
        builder.append("tail:").append(tail);

        builder.append("elements:[");
        while (current != null) {
            builder.append(current.toString());
            current = current.next;
        }
        builder.append("over");
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(5);
        linkedList.add(10);
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(11);
        System.out.println(linkedList);
        linkedList.removeFirst(5);
        System.out.println(linkedList);
        linkedList.add(77);
        linkedList.removeFirst(4);
        System.out.println(linkedList);
    }

}
