package ru.geekbrains.j1.lesson5.homework;

public class Cat extends Animal{

    public Cat(String name, String color, int age, int run, int jump) {
        super(name, color, age, run, 0, jump);
    }
    @Override
    public void Swim (int distance) {
        System.out.println(this.name + " Swim(" + distance + ") -> кот плавать не умеет");
    };
}
