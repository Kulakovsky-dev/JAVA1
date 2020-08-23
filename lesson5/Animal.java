package ru.geekbrains.j1.lesson5.homework;

public class Animal {
    protected String name;
    protected String color;
    protected int age;
    protected int run;
    protected int swim;
    protected int jump;

    public Animal () {};

    public Animal (String name, String color, int age, int run, int swim, int jump) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.run = run;
        this.swim = swim;
        this.jump = jump;
    };
    public void Run (int distance) {
        System.out.print(this.name + " Run(" + distance + ") ->");
        if (distance > this.run) System.out.print(" не");
        System.out.println(" пробежал");
    };
    public void Swim (int distance) {
        System.out.print(this.name + " Swim(" + distance + ") ->");
        if (distance > this.swim) System.out.print(" не");
        System.out.println(" проплыл");
    };
    public void Jump (int height) {
        System.out.print(this.name + " Jump(" + height + ") ->");
        if (height > this.jump) System.out.print(" не");
        System.out.println(" перепрыгнул");
    };
}
