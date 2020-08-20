package ru.geekbrains.j1.lesson4.homework;

public class Employee {
    private int tabNum;
    private String name;
    private int salary;
    private int age;

    public static int TABNUM = 0;

    Employee() {
        TABNUM ++;
        this.tabNum = TABNUM;
    }

    Employee(String name,int salary,int age) {
        TABNUM ++;
        this.tabNum = TABNUM;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
    int getTabNum() {
        return tabNum;
    }
    String getName () {
        return name;
    }
    int getSalary() {
        return salary;
    }
    int getAge() {
        return age;
    }
    void setSalary(int slr) {
        salary = slr;
    }
}
