package ru.geekbrains.j1.lesson7.online;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;
    Map() {
        setBackground(Color.CYAN);
    };

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        System.out.printf("mode:%d, size.x:%d, size.y:%d, win:%d\n",gameMode,fieldSizeX,fieldSizeY,winLength);
    }
}
