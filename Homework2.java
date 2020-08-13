import java.util.Arrays;

public class Homework2 {

    public static void invertArray() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 - arr[i];
        };
        System.out.println(Arrays.toString(arr));
    }

    public static void fillArray() {
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3 + 1;
        };
        System.out.println(Arrays.toString(arr));
    }

    public static void changeArray() {
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        };
        System.out.println(Arrays.toString(arr));
    }

    public static void findMinMax() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) min = arr[i];
            if (max < arr[i]) max = arr[i];
        };
        System.out.println(Arrays.toString(arr) + " min=" + min + " max=" + max);
    }

    public static void fillDiags() {
        int[][] arr = new int[6][6];
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            arr[i][i] = 1;
            arr[i][len-i-1] = 1;
        };
        printArr2 (arr);
    }

    public static void printArr2 (int [][] arr ) {
        for ( int i = 0 ; i < arr.length ; i ++) {
            for ( int j = 0 ; j < arr[i].length ; j ++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean chekBalance(int[] arr) {
        int summ = 0, partial = 0;
        for (int i=0; i<arr.length; i++) {
            summ += arr[i];
        };
        for (int i=0; i<arr.length; i++) {
            partial += arr[i];
            if (partial * 2 == summ) return(true);
        };
        return(false);
    }

    public static void shiftArray(int[] arr, int shift) {
        int lastCell;
        System.out.print(Arrays.toString(arr) + " shift=" + shift + " ");
        while (shift < 0) {
            shift += arr.length;
        };
        shift = shift % arr.length;
        for (int j = 1; j <= shift; j++) {
            lastCell = arr[arr.length-1];
            for (int i = arr.length-1; i > 0; i--) {
                arr[i] = arr[i-1];
            };
            arr[0] = lastCell;
        };
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        invertArray();  // 1
        fillArray();    // 2
        changeArray();  // 3
        findMinMax();   // 4
        fillDiags();    // 5

        // задание 6
        int[] arr1 = { 1, 2, 3, 4, 5, 5};
        System.out.println ( chekBalance (arr1) );
        int[] arr2 = { 1, 2, 3, 4, 5, 6};
        System.out.println ( chekBalance (arr2) );

        // задание 7.8
        shiftArray (arr2, 2);
    }
}