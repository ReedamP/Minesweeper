package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

////  GREEN = NOT A MINE  ////////////////////
////  ORANGE = USER SUGGESTED IT IS A MINE /////////////////////
////  RED = IT IS A MINE //////////////////

public class Psyrp2Main {
    final static int DISPLAYWIDTH=9; //The board dimensions can only be edited with the same dimensions for the height and width
    final static int DISPLAYHEIGHT=9; //E.g. 6X6, 9X9, 12X12 etc..
    final static int NUM_MINES=18;
    private Board board = new Board();

    public static void main(String[] args) {

        Psyrp2Main x=new Psyrp2Main();
        x.Minesweeper();
    }

    private void Minesweeper(){

        BoardSquareButton button;

        JFrame frame = new JFrame("Minesweeper");
        frame.setLocationRelativeTo(null);
        GridLayout grid=new GridLayout(DISPLAYHEIGHT,DISPLAYWIDTH);
        frame.getContentPane().setLayout(grid);
        for (int i=0;i<DISPLAYHEIGHT;i++){
            for (int j=0; j<DISPLAYWIDTH; j++){
                button=board.initbutton(i,j);
                button.addMouseListener(new mouse());
                board.storeButton(i,j,button);
                frame.add(board.getButton(i,j));
            }
        }

        board.createMines(NUM_MINES);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public class mouse extends MouseAdapter{
        public void mousePressed(MouseEvent e) {

            for (int i=0;i<DISPLAYHEIGHT;i++){
                    for(int j=0;j<DISPLAYWIDTH;j++){ //Loops through whole board...

                        if (board.getButton(i,j)==e.getSource()){ //..if the e.getSource object type (mouse click) matches the co-ord of the board square..
                            if(SwingUtilities.isLeftMouseButton(e)){

                                BoardSquareButton clickedbutton=board.getButton(i,j); //..Then that is the button clicked

                                if ((clickedbutton.gethas_mine())){
                                    board.lose();
                                }
                                else{
                                    clickedbutton.setinvestigated();
                                    if(board.countSurrounding(i,j)==0){
                                        board.clearZeros(i,j);
                                        board.fillsquare(clickedbutton.getxposition(),clickedbutton.getyposition(),0);
                                    }
                                    else{
                                        int mines=board.countSurrounding(i,j);
                                        board.fillsquare(i,j,mines);
                                    }

                                    if (board.hasWon()){
                                        board.win();
                                    }
                                }
                            }

                            else if(SwingUtilities.isRightMouseButton(e)){
                                BoardSquareButton clickedbutton =board.getButton(i,j); //..Then that is the button clicked

                                if(!clickedbutton.getpotential_mine()){
                                    clickedbutton.setpotential_mineyes();
                                }
                                else{
                                    clickedbutton.setpotential_mineno();
                                }
                            }
                        }
                    }
                }
            }
        }
    }



