package ru.geekbrains.j1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int WIN_WIDTH = 507;
    private static final int WIN_HEIGHT = 555;
    private static final int WIN_POSX = 650;
    private static final int WIN_POSY = 250;
    private Map map;
    SettingsWindow settings;

    GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH,WIN_HEIGHT);
        setLocation(WIN_POSX,WIN_POSY);
        setTitle("TicTacToe");
        setResizable(false);
        //setLayout(new GridLayout(5,3));
        //setLayout(new FlowLayout());
        JButton btnStart = new JButton("Start New Game");
        JButton btnExit = new JButton("<html><body><b>Exit</b></body></html>");
        JPanel panelButton = new JPanel(new GridLayout(1,2));
        //add(btnStart, BorderLayout.NORTH);
        //add(btnExit, BorderLayout.NORTH);
        panelButton.add(btnStart);
        panelButton.add(btnExit);
        map = new Map();
        settings = new SettingsWindow(this);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(panelButton, BorderLayout.SOUTH);
        add(map, BorderLayout.CENTER);
        setVisible(true);
    }
    public void applySettings(int mode, int size, int len) {
        map.startNewGame(mode, size, size, len);
    }
}
