package ru.job4j.simplelinkedlist;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;
    private Node<E> tail;


public SimpleLinkedList() {

}

    @Override
    public void add(E value) {

      if (head == null) {
          Node<E> newNode = new Node<>(value);
          head = newNode;
          tail = newNode;
      } else {
          Node<E> newNode = new Node<>(value);
          newNode.prev = tail;
          tail.next = newNode;
          tail = newNode;

      }

      size++;
      modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }



    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            SimpleLinkedList.Node<E> currentNode = head;
            private E result;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = currentNode.item;
                currentNode = currentNode.next;
                return result;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;


        Node(E element) {
            this.item = element;


        }
    }
}

