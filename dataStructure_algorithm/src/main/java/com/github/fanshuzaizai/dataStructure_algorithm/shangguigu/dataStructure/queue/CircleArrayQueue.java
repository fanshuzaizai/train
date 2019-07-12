package com.github.fanshuzaizai.dataStructure_algorithm.shangguigu.dataStructure.queue;

import java.util.Arrays;

/**
 * 队列
 * 1.先入先出
 *
 * @author Jzy.
 * @date 2019/6/28 17:49
 */
public class CircleArrayQueue {

    private Integer headIndex = 0;

    private Integer tailIndex = 0;

    private int maxSize = 5;

    private Integer[] _arr = new Integer[maxSize];

    public boolean isEmpty() {
        return _arr[tailIndex] == null;
    }

    public void push(int element) {
        if (isFull()) {
            System.err.println("队列已经满了");
        } else if (isEmpty()) {
            _arr[tailIndex] = element;
        } else {
            tailIndex = (tailIndex + 1) % maxSize;
            _arr[tailIndex] = element;
        }
    }

    public Integer pop() {
        if (isEmpty()) {
            System.err.println("空的");
            return null;
        } else {
            int element = _arr[headIndex];
            _arr[headIndex] = null;
            if (!isEmpty()) {
                headIndex = (headIndex + 1) % maxSize;
            }
            return element;
        }
    }

    public boolean isFull() {
        return (tailIndex + 1) % maxSize == headIndex;
    }

    @Override
    public String toString() {
        return "head:" + headIndex + ",tail:" + tailIndex + ",element:" + Arrays.toString(_arr);
    }

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue();
        queue.push(3);
        queue.push(7);
        queue.push(6);
        queue.push(-2);
        queue.push(99);
        System.out.println(queue);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue);
        queue.push(1);
        queue.push(11);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.push(111);
        queue.push(111);
        System.out.println(queue);

    }
}
