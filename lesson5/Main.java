package ru.geekbrains.j1.lesson5.homework;

public class Main {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Barseek","White",5, 100,2);
        Cat cat2 = new Cat("Murzeek","Grey",2,  200,1);
        Dog dog1 = new Dog("Druzhok","Black",3,300,50,3);
        Dog dog2 = new Dog("King","Peach",4,  500,1000,2);
        Animal[] zoo = {cat1,cat2,dog1,dog2};

        for (int i = 0; i < zoo.length; i++) {
            zoo[i].Run(120);
            zoo[i].Swim(120);
            zoo[i].Jump(3);
        }
    }
}
