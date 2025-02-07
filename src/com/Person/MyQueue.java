package com.Person;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

public class MyQueue<T> {

    private Node<T> front; // Points to the front of the queue
    private Node<T> rear; // Points to the rear of the queue
    private int size; // Tracks the number of elements in the queue

    // Inner class to represent a node in the queue
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor to initialize an empty queue
    public MyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Adds an element to the rear of the queue
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    // Removes and returns the element at the front of the queue
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        T data = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return data;
    }

    // Returns the element at the front of the queue without removing it
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return front.data;
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of elements in the queue
    public int size() {
        return size;
    }
    
    // Method to print the contents of the queue
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("The queue is empty.");
            return;
        }
        
        Node<T> current = front;
        while (current != null) {
            Person person = (Person) current.data;
            System.out.printf("\nName: %s %s, Age: %d", person.getFirstName(), person.getLastName(), person.getAge());
            current = current.next;
        }
        System.out.println();
    }

    // Sort queue by last name in descending order
    public void sortQueueByLastNameDescending() {
        if (size <= 1) return; // Already sorted

        List<Person> personList = extractToList();
        quickSort(personList, 0, personList.size() - 1, "lastName");
        restoreFromList(personList);
    }

    // Sort queue by age in descending order
    public void sortQueueByAgeDescending() {
        if (size <= 1) return; // Already sorted

        List<Person> personList = extractToList();
        quickSort(personList, 0, personList.size() - 1, "age");
        restoreFromList(personList);
    }

    // Extract queue contents to a list
    private List<Person> extractToList() {
        List<Person> list = new ArrayList<>();
        while (!isEmpty()) {
            list.add((Person) dequeue());
        }
        return list;
    }

    // Restore list contents back into queue
    private void restoreFromList(List<Person> list) {
        for (Person p : list) {
            enqueue((T) p);
        }
    }

    // QuickSort implementation for sorting the list
    private void quickSort(List<Person> list, int low, int high, String criteria) {
        if (low < high) {
            int pi = partition(list, low, high, criteria);
            quickSort(list, low, pi - 1, criteria);
            quickSort(list, pi + 1, high, criteria);
        }
    }

    // Partition function for QuickSort
    private int partition(List<Person> list, int low, int high, String criteria) {
        Person pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(list.get(j), pivot, criteria) > 0) { // Descending order
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    // Comparison method for sorting
    private int compare(Person a, Person b, String criteria) {
        if (criteria.equals("lastName")) {
            return b.getLastName().compareTo(a.getLastName()); // Descending order
        } else {
            return Integer.compare(b.getAge(), a.getAge()); // Descending age order
        }
    }

    // Swap two elements in the list
    private void swap(List<Person> list, int i, int j) {
        Person temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
