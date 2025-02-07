package com.Person;

import java.util.Scanner;

public class Person {
    
    private String firstName;
    private String lastName;
    private int age;
    
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public int getAge() {
        return age;
    }
    
    public static void main(String[] args) {
        MyQueue<Person> personQueue = new MyQueue<>();
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();
            
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            
            Person person = new Person(firstName, lastName, age);
            personQueue.enqueue(person);
            
            System.out.printf("Added %s to the queue!\n", person.getFirstName());
        }
        
        System.out.println("\nQueue before sorting:");
        personQueue.printQueue();

        // Sort by last name in descending order
        personQueue.sortQueueByLastNameDescending();
        System.out.println("\nQueue sorted by last name (descending):");
        personQueue.printQueue();

        // Sort by age in descending order
        personQueue.sortQueueByAgeDescending();
        System.out.println("\nQueue sorted by age (descending):");
        personQueue.printQueue();

        scanner.close();
    }
}
