package ru.geekbrains.j1.lesson6.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class main {

    public static void main(String[] args) {
        String filename = "file1.txt";
        String word;

        // склеивание 2 файлов: file1.txt + file2.txt -> file_out.txt
        try {
            FileOutputStream file_out = new FileOutputStream("file_out.txt");
            appendFile ("file1.txt", file_out);
            appendFile ("file2.txt", file_out);
            file_out.flush();
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        };

        // проверка слова в файле
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter word for search: ");
        word = scanner.next();
        System.out.print("file=\"" + filename +"\"");
        if (!findWord( word, filename)) System.out.print(" not");
        System.out.println(" contains \"" + word + '\"');

        // проверка слова в файлах папки...
        File dir = new File(".");
        File file;
        File[] lst1 = dir.listFiles();
        System.out.println("Chek word \"" + word + "\" in files of current folder:");
        for (int i = 0; i < lst1.length; i++) {
            filename = lst1[i].toString();
            file = new File(dir,filename);
            if (file.isFile()) {
                if (findWord(word,filename)) System.out.println("file \""+filename+"\" contains \""+word+"\"");
            }
        };
    }

    static void appendFile (String filename, FileOutputStream file_out) throws IOException {
        //throw new IOException("***");
        FileInputStream file_in = new FileInputStream(filename);
        int b;
        //try {
            while ((b = file_in.read()) != -1) {
                file_out.write(b);
            };
        //} catch (IOException ex) {
        //    ex.printStackTrace();
        //}
    }
    static boolean findWord (String word, String filename) {
        try {
            Scanner scanner = new Scanner( new FileInputStream(filename));
            while (scanner.hasNext()) {
                if (scanner.next().equals(word)) return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        };
        return false;
    }
}
