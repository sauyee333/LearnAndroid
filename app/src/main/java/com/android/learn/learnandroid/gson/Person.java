package com.android.learn.learnandroid.gson;

/**
 * Created by sauyee.wong on 18/2/2016.
 */
public class Person {
  private String name;
  private String surname;
  private Car[] cars;
  private int contact;
  private transient int age;

  public Person(String name, String surname, int phone, int age, Car[] cars) {
    this.name = name;
    this.surname = surname;
    this.cars = cars;
    this.contact = phone;
    this.age = age;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name: " + name + " " + surname + "\n");
    sb.append("Phone: " + contact + "\n");
    sb.append("Age: " + age + "\n");

    int i = 0;
    for (Car item : cars) {
      i++;
      sb.append("Car " + i + ": " + item + "\n");
    }
    return sb.toString();
  }
}
