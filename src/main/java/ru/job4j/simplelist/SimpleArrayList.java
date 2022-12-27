package ru.job4j.simplelist;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }
    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        this.container[index] = newValue;
        return newValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T rsl = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return this.container[index];
    }

    @Override
    public int size() {
        return size;
    }

    public void grow() {
        if (size == 0) {
            container =  (T[]) new Object[10];
        }
        container = Arrays.copyOf(container, container.length * 2);
    }



    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            int expectedModCount = modCount;
            private int pointer = 0;


            @Override
            public boolean hasNext() {
              checkModification();
              return pointer < size;
            }

            @Override
            public T next() {
                checkModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
              return container[pointer++];
            }

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

        };
    }
}
