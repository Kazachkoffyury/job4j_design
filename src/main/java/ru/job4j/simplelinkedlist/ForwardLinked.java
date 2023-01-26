package ru.job4j.simplelinkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;

        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    public void addFirst(T value) {
        Node<T> current = head;
        head = new Node<>(value);
        head.next = current;
        size++;
        modCount++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> currentNode = head;
        T result = currentNode.item;
        head = head.next;
        currentNode.next = null;
        currentNode.item = null;
        size--;
        modCount++;
        return result;


    }

    public boolean revert() {
        boolean result;
        if (head == null || head.next == null) {
            result = false;
        } else {
            Node<T> previous = null;
            Node<T> current = head;
            Node<T> next;
            while (current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
            result = true;
        }
        return result;
    }



    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            Node<T> currentNode = head;
            private T result;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = currentNode.item;
                currentNode = currentNode.next;
                return result;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;


        Node(T element) {
            this.item = element;
        }
    }
}
