package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int instanceCount;

  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    instanceCount++;
    ssn = "";
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if (age < 0) 
      throw new IllegalArgumentException("Age must be at least 0");
    int old = age;
    this.age = age;

    pcs.firePropertyChange(new PropertyChangeEvent(this, "age", old, age));
    propertyChangeFired = true;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null) 
      throw new IllegalArgumentException("no name passed");
    String old = name;
    this.name = name;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "name", old, name));
    propertyChangeFired = true;

  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    double old = salary;
    this.salary = salary;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "salary", old, salary));
    propertyChangeFired = true;
  }

  public int count() {
    return instanceCount;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public boolean equals(Object other) {
    if(other instanceof Person) {
        Person p = (Person) other;
        return (this.name.equals(p.name) && this.age == p.age);
    }
    return false;
  }

  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public int compareTo(Person other) {
    return (int)(other.getSalary() - this.getSalary());
  }

  // Ted, age 41, salary 250000; Charlotte, age 43, salary 150000; Michael,
  // age 22, salary 10000; and Matthew, age 15, salary 0.
  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> family = new ArrayList<Person>();
    family.add(new Person("Ted", 41, 250000.0));
    family.add(new Person("Charlotte", 43, 150000.0));
    family.add(new Person("Michael", 22, 10000.0));
    family.add(new Person("Matthew", 15, 0.0));
    return family;
  }

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  };

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
