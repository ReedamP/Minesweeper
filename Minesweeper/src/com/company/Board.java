package com.company;

import javax.swing.*;
import java.util.Random;


public class Board {
    BoardSquareButton[][] buttonlist = new BoardSquareButton[Psyrp2Main.DISPLAYHEIGHT][Psyrp2Main.DISPLAYWIDTH];

    public Board() {
        return;
    }

    public BoardSquareButton initbutton(int i, int j) {
        BoardSquareButton button = new BoardSquareButton(80, 80, "GRAY", i, j);
        return button;
    }

    public void storeButton(int x, int y, BoardSquareButton button) {
        buttonlist[x][y] = button;
    }

    public BoardSquareButton getButton(int x, int y) {
        return buttonlist[x][y];
    }

    public void initaliseAll() {
        for (int i = 0; i < Psyrp2Main.DISPLAYHEIGHT; i++) {
            for (int j = 0; j < Psyrp2Main.DISPLAYWIDTH; j++) {
                buttonlist[i][j].initalise();

            }
        }
    }

    public void createMines(int NUM_MINES) {
        int mineset = 0;
        Random rand = new Random();

        while (mineset < NUM_MINES) {
            int xrand = rand.nextInt(Psyrp2Main.DISPLAYWIDTH);
            int yrand = rand.nextInt(Psyrp2Main.DISPLAYHEIGHT);
            //System.out.println("x:" + xrand + " y :" + yrand);
            if ((buttonlist[xrand][yrand].gethas_mine()) == false) {
                buttonlist[xrand][yrand].sethas_mine();
                mineset++;
            }
        }
    }

    public void lose() {
        finished();
        JOptionPane.showMessageDialog(null, "You've clicked on a mine!", "You lose", JOptionPane.ERROR_MESSAGE);
        initaliseAll();
        createMines(Psyrp2Main.NUM_MINES);
    }

    public void win() {
        JOptionPane.showMessageDialog(null, "Well Done!", "You've Won!", JOptionPane.INFORMATION_MESSAGE);
        initaliseAll();
        createMines(Psyrp2Main.NUM_MINES);
    }


    private void finished() {
        for (int i = 0; i < Psyrp2Main.DISPLAYHEIGHT; i++) {
            for (int j = 0; j < Psyrp2Main.DISPLAYWIDTH; j++) {
                if ((!buttonlist[i][j].gethas_mine())) {
                    if (!(buttonlist[i][j].getinvestigated())) {
                        buttonlist[i][j].setNewText(String.valueOf(countSurrounding(i,j)).toString());
                    }
                } else {
                    buttonlist[i][j].setColor("RED");
                    buttonlist[i][j].setNewText("X");
                }
            }
        }
    }

    public boolean hasWon() {
        for (int i = 0; i < Psyrp2Main.DISPLAYHEIGHT; i++) {
            for (int j = 0; j < Psyrp2Main.DISPLAYWIDTH; j++) {
                if (buttonlist[i][j].getinvestigated() == false && buttonlist[i][j].gethas_mine() == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillsquare(int x, int y, int mines) {
        String m = Integer.toString(mines);
        buttonlist[x][y].setNewText(m);

        if (mines == 0) {
            buttonlist[x][y].setColor("GRAY");
        }
        if (mines == 1) {
            buttonlist[x][y].setColor("BLUE");
        } else if (mines == 2) {
            buttonlist[x][y].setColor("GREEN");
        } else if (mines == 3) {
            buttonlist[x][y].setColor("PINK");
        } else if (mines == 4) {
            buttonlist[x][y].setColor("PURPLE");
        } else if (mines == 5) {
            buttonlist[x][y].setColor("MAROON");
        } else if (mines == 6) {
            buttonlist[x][y].setColor("TURQUOISE");
        } else if (mines == 7) {
            buttonlist[x][y].setColor("BLACK");
        }
    }

    public void clearZeros (int i, int j){

        for (int p=i-1; p<=i+1;p++){
            for (int q=j-1; q<=j+1;q++){
                if (p<Psyrp2Main.DISPLAYHEIGHT && p>=0 && q<Psyrp2Main.DISPLAYWIDTH && q>=0){
                    if((buttonlist[p][q] != buttonlist[i][j]) && (buttonlist[p][q].getinvestigated()==false)){
                        buttonlist[p][q].setinvestigated();
                        if (countSurrounding(p,q)==0){
                            clearZeros(p,q);
                            fillsquare(p,q,(countSurrounding(p,q)));
                        }
                        else{
                            fillsquare(p,q,(countSurrounding(p,q)));
                        }
                    }
                }
            }
        }
    }

    public int countSurrounding(int i, int j) {
        int mines = 0;

         if ((i > 0 && j > 0) && (buttonlist[i - 1][j - 1].gethas_mine() == true)) {//Up left
             mines++;
         }


        if ((i > 0) && (buttonlist[i - 1][j].gethas_mine() == true)) {//Up
            mines++;
        }

        if ((j < Psyrp2Main.DISPLAYHEIGHT - 1 && i > 0) && (buttonlist[i - 1][j + 1].gethas_mine() == true)) {//Up Right
            mines++;
        }

        if ((j < Psyrp2Main.DISPLAYHEIGHT - 1) && (buttonlist[i][j + 1].gethas_mine() == true)) { //Right
            mines++;
        }

        if ((j < Psyrp2Main.DISPLAYHEIGHT - 1 && i < Psyrp2Main.DISPLAYWIDTH - 1) && (buttonlist[i + 1][j + 1].gethas_mine() == true)) { //Bottom right
            mines++;
        }

        if ((i < Psyrp2Main.DISPLAYWIDTH - 1) && (buttonlist[i + 1][j].gethas_mine() == true)) {
            mines++;
        }

        if (j > 0 && i < Psyrp2Main.DISPLAYWIDTH - 1 && (buttonlist[i + 1][j - 1].gethas_mine() == true)) { //Bottom left
            mines++;
        }

        if (j > 0 && (buttonlist[i][j - 1].gethas_mine() == true)) { //LEFT
            mines++;
        }
        return mines;
    }
}
