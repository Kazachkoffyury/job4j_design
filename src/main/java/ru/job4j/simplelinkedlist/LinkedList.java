package ru.job4j.simplelinkedlist;

public interface LinkedList<E>  extends Iterable<E> {
    void add(E value);
    E get(int index);
}
