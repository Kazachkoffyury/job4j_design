package ru.job4j.simplelinkedlist;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();



    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);

    }

    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }
}
