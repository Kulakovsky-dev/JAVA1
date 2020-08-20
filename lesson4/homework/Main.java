package ru.geekbrains.j1.lesson4.homework;

public class Main {
    public static Employee[] emps = new Employee[5];

    public static void main(String[] args) {
        emps[0] = new Employee("Иванов А.А.",20000,25);
        emps[1] = new Employee("Петров Б.Б.",30000,30);
        emps[2] = new Employee("Сидоров В.В.",40000,40);
        emps[3] = new Employee("Пупкин Г.Г.",50000,50);
        emps[4] = new Employee("Васькин Д.Д.",60000,55);

        // вывести список сотрудников старше 40 лет
        System.out.println("Список сотрудников старше 40 лет");
        for (int i = 0; i < emps.length; i++) {
            if(emps[i].getAge() > 40)
                System.out.println("таб.номер="+ emps[i].getTabNum() + ' ' +emps[i].getName() + " возраст=" + emps[i].getAge());
        }

        // увеличить зп на 5000 для сотрудников старше 45
        salaryUp(45, 5000);
        System.out.println("Средняя зп = " + averageSalary());
        System.out.println("Средний возраст = " + averageAge());
    }

    // увеличение зп
    public static void salaryUp(int ageUp, int salUp) {
        int salNew;
        for (int i = 0; i < emps.length; i++) {
            if (emps[i].getAge() > ageUp) {
                salNew = emps[i].getSalary() + salUp;
                emps[i].setSalary(salNew);
            }
        }
    }
    // среднее арифметическое зп
    public static int averageSalary () {
        int sum = 0;
        for (int i = 0; i < emps.length; i++) {
            sum += emps[i].getSalary();
        };
        if (emps.length == 0) return 0;
        return sum / emps.length;
    }
    // среднее арифметическое возраста
    public static int averageAge () {
        int sum = 0;
        for (int i = 0; i < emps.length; i++) {
            sum += emps[i].getAge();
        };
        if (emps.length == 0) return 0;
        return sum / emps.length;
    }
}
