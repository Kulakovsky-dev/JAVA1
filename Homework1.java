public class Homework1 {
//git
    public static double calculate(int a, int b, int c, int d) {
        float rezult = c;
        rezult /= d;
        rezult += b;
        return (rezult *= a);
        //a * (b + (c / d));
    }

    public static boolean task10and20 (int a, int b) {
        return ((a+b)>=10 && (a+b)<=20);
    }

    public static void isPositiveOrNegative(int x) {
        if(x >= 0) {
            System.out.println("Число " + x + " положительное");
        } else {
            System.out.println("Число " + x + " отрицательное");
        }
    }

    public static boolean isNegative(int x) {
        if(x < 0) {
            return true;
        }
        return false;
    }

    public static void greetings(String name) {
        System.out.println("Привет, " + name);
    }

    public static void yearIsLeap(int year) {
        if (year%400 == 0) {
            System.out.println(year + " високосный");
        } else if (year%100 == 0) {
            System.out.println(year + " не високосный");
        } else if (year%4 == 0) {
            System.out.println(year + " високосный");
        } else {
            System.out.println(year + " не високосный");
        }
    }

    public static void main(String[] args) {
        byte a1 = 1;
        short a2 = 200;
        int a3 = 4000;
        long a4 = 100000000000L;
        float a5 = 56.7890f;
        double a6 = 12.345678;
        char a7 = 'S';
        boolean a8 = true;
        System.out.println(calculate( 2 , 2 , 2 , 2 ));
        System.out.println(task10and20( 5 , 6 ));
        isPositiveOrNegative(- 30 );
        System.out.println(isNegative(-4 ));
        greetings("Иван");
        yearIsLeap(2019);
        yearIsLeap(2020);
        yearIsLeap(2000);
        yearIsLeap(2100);
    }
}
