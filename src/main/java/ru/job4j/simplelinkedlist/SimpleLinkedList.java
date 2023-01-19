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


public SimpleLinkedList() {

}

    @Override
    public void add(E value) {


      if (size == 0) {
          head = new Node<>(value);
      } else {
          Node<E> nodeTemp = this.head;
          int index = size - 1;
          if (Objects.checkIndex(index, size) == index) {
              for (int i = 0; i < index; i++) {
                  nodeTemp = nodeTemp.next;
              }
          }
          nodeTemp.next = new Node<>(value);


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

    private Node<E> getLastNode(int index) {
        Node<E> nodeTemp = this.head;
        if (Objects.checkIndex(index, size) == index) {
            for (int i = 0; i < index; i++) {
                nodeTemp = nodeTemp.next;
            }
        }
        return nodeTemp;
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


        Node(E element) {
            this.item = element;


        }
    }
}

