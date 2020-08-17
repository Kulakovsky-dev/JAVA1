import java.util.Random;
import java.util.Scanner;

public class Homework3 {
    private static final char DOT_EMPTY = '_';
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int fieldSizeWin; // длина цепочки для победы
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    // init field
    public static void initField() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        fieldSizeWin = 4;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }
    // print field
    public static void printField() {
        System.out.print('+');
        for(int i=1; i<=fieldSizeX; i++) {
            System.out.print("-" + i);
        };
        System.out.println('-');
        for (int y=0; y<fieldSizeY; y++) {
            System.out.print((y+1) + "|");
            for (int x=0; x<fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            };
            System.out.println();
        }
    }
    // human turn
    public static void humanTurn() {
        int x, y;
        System.out.print("Введите координаты хода X и Y (от 1 до " + fieldSizeX + ") черед пробел: ");
        while(true) {
            x = SCANNER.nextInt()-1;
            y = SCANNER.nextInt()-1;
            if (isCellValid(x,y)) {
                if (isCellEmpty(x,y)) {
                    field[y][x] = DOT_HUMAN;
                    return;
                }
            };
            System.out.print("Недопустимый ход, введите заново :");
        }
    }
    // ai turn
    public static void aiTurn() {
        int x, y;
        while(true) {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
            if (isCellEmpty(x, y)) {
                field[y][x] = DOT_AI;
                return;
            }
        }
    }
    // chek valid cell
    public static boolean isCellValid(int x, int y) {
        return (x>=0 && x<fieldSizeX && y>=0 && y<fieldSizeY);
    }
    // chek empty cell
    public static boolean isCellEmpty(int x, int y) {
        return (field[y][x]==DOT_EMPTY);
    }
    // chek Draw
    public static boolean chekDraw() {
        for (int x= 0; x < fieldSizeX; x++) {
            for (int y= 0; y < fieldSizeY; y++) {
                if (field[y][x] == DOT_EMPTY)
                    return false;
            }
        };
        return true;
    }
    // chek win
    public static boolean chekWin(char dot) {
        int count1, count2;
        for (int x = 0; x < fieldSizeX; x++) {
            count1 = 0;  // подсчет символов 'dot' в горизонтальной линии 'x'
            count2 = 0;  // подсчет символов 'dot' в вертикальной линии 'y'
            for (int y = 0; y < fieldSizeY; y++) {
                if (field[y][x] == dot) count1++;
                if (field[x][y] == dot) count2++;
            }
            ;
            if (count1 == fieldSizeWin || count2 == fieldSizeWin) return true;
        }
        /*
        count1 = 0; // подсчет по прямой диагонали
        count2 = 0; // подсчет по обратной диагонали
        for (int x = 0; x < fieldSizeX; x++) {
            if (field[x][x]==dot) count1++;
            if (field[x][fieldSizeX-1-x]==dot) count2++;
        }
        if (count1==fieldSizeWin || count2==fieldSizeWin) return true;
        */
        return chekDiags(dot);
    }

    // проверка победы по произвольным диагоналям, например в случае поля 5х5 и цепочка=4,
    // в этом случае диагоналей уже не 2, а больше, например [1,1][2,2][3,3][4,4] , [1,2][2,3][3,4][4,5] и т.д
    // поэтому цикл по возможным "началам диагональных цепочек" x0 и y0
    public static boolean chekDiags (char dot) {
        int count1, count2;
        // пояснение, например для поля 5х5 и win=4, будет перебор [x0,y0] из [1,1][1,2][2,1][2,2]
        // x0,y0 - начальные координаты диагонали
        for (int x0 = 0; x0 < fieldSizeX-fieldSizeWin+1; x0++) {
            for (int y0 = 0; y0 < fieldSizeY-fieldSizeWin+1; y0++) {
                count1 = 0;
                count2 = 0;
                for (int i = 0; i < fieldSizeWin; i++) {
                    if (field[x0+i][y0+i]==dot) count1++;
                    // чтобы не плодить код, сразу проверяем "обратную" диагональ, она зеркальна, с началом [x0,fieldSizeY-1-y0]
                    if (field[x0+i][fieldSizeY-1-y0-i]==dot) count2++;
                };
                if (count1==fieldSizeWin || count2==fieldSizeWin) return true;
            }
        };
        return false;
    }

    public static void main(String[] args) {
        initField();
        printField();
        while (true) {
            humanTurn();
            printField();
            if (gameOver(DOT_HUMAN,"Human Win !")) break;
            aiTurn();
            printField();
            if (gameOver(DOT_AI,"Computer Win !")) break;
        }
    }
    private static boolean gameOver(char DOT, String msg) {
        if (chekDraw()) {
            System.out.println("Draw");
            return true;
        };            ;
        if (chekWin(DOT)) {
            System.out.println(msg);
            return true;
        };
        return false;
    }
}

