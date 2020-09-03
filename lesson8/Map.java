package ru.geekbrains.j1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;
    public static final int DOT_EMPTY = 0;
    public static final int DOT_HUMAN = 1;
    public static final int DOT_AI = 2;
    public static final int PADDING = 25;

    private int[][] field;
    private int fieldSizeX = 3;
    private int fieldSizeY = 3;
    private int winLength;
    private int cellWidth = 0;
    private int cellHeight = 0;

    private int stateGameOver;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_AI = 1;
    private static final int STATE_WIN_HUMAN = 2;

    private static final String MSG_WIN_HUMAN = "Победил игрок !";
    private static final String MSG_WIN_AI = "Победил компьютер !";
    private static final String MSG_DRAW = "Ничья !";

    private static final Random RANDOM = new Random();
    private boolean isGameOver;
    private boolean isInitialized;

    Map() {
        setBackground(Color.CYAN);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        isInitialized = false;
    };
    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        //System.out.printf("mode:%d, size.x:%d, size.y:%d, win:%d\n",gameMode,fieldSizeX,fieldSizeY,winLength);
        this.fieldSizeY = fieldSizeY;
        this.fieldSizeX = fieldSizeX;
        this.winLength = winLength;
        field = new int[fieldSizeY][fieldSizeX];
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    private void update (MouseEvent e) {
        if (isGameOver) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        //System.out.println("x="+cellX+" y="+cellY);
        if (!isValidCell(cellX,cellY) || !isEmptyCell(cellX,cellY)) return;
        field[cellY][cellX] = DOT_HUMAN;

        chekEndGame(DOT_HUMAN, STATE_WIN_HUMAN);
        if (isGameOver) return;
        aiTurn();
        chekEndGame(DOT_AI, STATE_WIN_AI);
    }
    private boolean chekEndGame(int dot, int stateGameOver) {
        repaint();
        if (chekWin(dot)) {
            this.stateGameOver = stateGameOver;
            isGameOver = true;
            return true;
        }
        if (isMapFull()) {
            this.stateGameOver = STATE_DRAW;
            isGameOver = true;
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render (Graphics g) {
        //if (isGameOver) return;
        if (!isInitialized) return;
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        };
        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x,y)) continue;
                if (field[y][x] == DOT_HUMAN) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x*cellWidth+PADDING,y*cellHeight+PADDING,cellWidth-PADDING*2,cellHeight-PADDING*2);
                } else if (field[y][x] == DOT_AI) {
                    g.setColor(Color.RED);
                    g.fillRect(x*cellWidth+PADDING,y*cellHeight+PADDING,cellWidth-PADDING*2,cellHeight-PADDING*2);
                } else {
                    throw new RuntimeException(
                            String.format("Can't recognize cell field[%d][%d] = %d",y,x,field[y][x] ));
                }
            }
        }
        if (isGameOver) {
            showMessageGameOver(g);
        }
    }
    private void showMessageGameOver (Graphics g) {
        int w = getWidth();
        g.setColor(Color.BLUE);
        g.fillRect(w/10,220, w-w/5,50);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 24));
        switch (stateGameOver) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW , w*2/5, getHeight()/2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI , w*3/10, getHeight()/2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN , w*3/10, getHeight()/2);
                break;
            default:
                throw new RuntimeException("Unexpected Game Over state: "+stateGameOver);
        }
    }

    // ход компьютера (нолик)
    private void aiTurn() {
        if (turnAIWinCell()) return; // проверка может ли выиграть комп след.ходом
        if (turnHumanWinCell()) return; // проверка может ли выиграть игрок след.ходом...
        int x, y;   // или комп ходит случайно
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x,y));
        field[y][x] = DOT_AI;
    }
    // проверка может ли выиграть комп след.ходом
    private boolean turnAIWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j,i)) {
                    field[i][j] = DOT_AI; // поставим нолик, если выигрыш то все
                    if (chekWin(DOT_AI)) return true;
                    field[i][j] = DOT_EMPTY; // возвращаем пустышку
                }
            }
        };
        return false;
    }
    // проверка может ли выиграть игрок след.ходом
    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j,i)) {
                    field[i][j] = DOT_HUMAN; // поставим крестик, вдруг это выигрыш для Human...
                    if (chekWin(DOT_HUMAN)) { // Human выигрывает, значит поставим сюда нолик (Comp)
                        field[i][j] = DOT_AI;  // значит поставим сюда нолик (Comp)
                        return true;
                    }
                    field[i][j] = DOT_EMPTY; // возвращаем пустышку
                }
            }
        };
        return false;
    }
    // chek Win
    private boolean chekWin(int c) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (chekLine(i,j,1,0,winLength,c)) return true;
                if (chekLine(i,j,0,1,winLength,c)) return true;
                if (chekLine(i,j,1,1,winLength,c)) return true;
                if (chekLine(i,j,1,-1,winLength,c)) return true;
            }
        }
        return false;
    }
    // chek Line
    private boolean chekLine(int x, int y, int vx, int vy, int len, int c) {
        if (x+vx*(len-1)<0) return false;
        if (x+vx*(len-1)>=fieldSizeX) return false;
        if (y+vy*(len-1)<0) return false;
        if (y+vy*(len-1)>=fieldSizeY) return false;
        for (int i = 0; i < len; i++) {
            if (field[y+i*vy][x+i*vx]!=c) return false;
        };
        return true;
    }
    // chek valid cell
    private boolean isValidCell(int x, int y) {
        return (x>=0 && x<fieldSizeX && y>=0 && y<fieldSizeY);
    }
    // chek empty cell
    private boolean isEmptyCell(int x, int y) {
        return (field[y][x]==DOT_EMPTY);
    }
    // chek Draw
    private boolean isMapFull() {
        for (int x= 0; x < fieldSizeX; x++) {
            for (int y= 0; y < fieldSizeY; y++) {
                if (field[y][x] == DOT_EMPTY)
                    return false;
            }
        };
        return true;
    }
}
