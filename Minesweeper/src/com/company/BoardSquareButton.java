package com.company;
import javax.swing.*;
import java.awt.*;

public class BoardSquareButton extends JButton {

    private int xposition;
    private int yposition;
    private int buttonheight;
    private int buttonwidth;
    private int fontsize;

    private boolean has_mine;
    private boolean investigated;
    private boolean potential_mine;

    private String buttontext="?";
    private String colour;

    public BoardSquareButton(int buttonwidth, int buttonheight, String colour, int xposition, int yposition){

        this.buttonwidth=buttonwidth;
        this.buttonheight=buttonheight;
        this.colour=colour;
        this.xposition=xposition;
        this.yposition=yposition;


        this.setBackground(Color.GRAY);


        fontsize=buttonwidth-14;
        this.setFont(new Font("Arial", Font.PLAIN, fontsize));
        this.setPreferredSize(new Dimension(buttonwidth,buttonheight));
        this.setText(buttontext);
    }

    public void initalise(){
        this.setText("?");
        this.setBackground(Color.GRAY);
        this.investigated=false;
        this.has_mine=false;
        this.potential_mine=false;
    }

    public void setColor(String colour){
        if (colour=="GRAY"){
            this.setBackground(Color.LIGHT_GRAY);
        }
        else if (colour=="PINK"){
            this.setBackground(Color.pink);
        }
        else if (colour=="RED") {
            this.setBackground(Color.RED);
        }
        else if (colour=="ORANGE"){
            this.setBackground(Color.ORANGE);
        }
        else if (colour=="BLUE"){
            this.setBackground(Color.BLUE);
        }
        else if (colour=="GREEN"){
            this.setBackground(Color.GREEN);
        }
        else if (colour=="MAROON"){
            this.setBackground(Color.getHSBColor(0,100,50));
        }
        else if (colour=="PURPLE"){
            this.setBackground(Color.magenta);
        }
        else if (colour=="BLACK"){
            this.setBackground(Color.BLACK);
        }
        else if (colour=="TURQUOISE"){
            this.setBackground(Color.getHSBColor(174,71,88));
        }
    }

    public void setNewText(String text){
        this.setText(text);
    }

    public int getxposition(){
        return xposition;
    }

    public int getyposition(){
        return yposition;
    }

    public boolean gethas_mine(){
        return has_mine;
    }

    public void sethas_mine(){
         this.has_mine=true;
    }

    public boolean getinvestigated(){
        return investigated;
    }

    public void setinvestigated(){
        this.investigated=true;
    }

    public boolean getpotential_mine(){
        return potential_mine;
    }

    public void setpotential_mineno(){
        this.potential_mine=false;
        this.setBackground(Color.GRAY);
    }

    public void setpotential_mineyes(){
        this.potential_mine=true;
        if (this.investigated==false){
            this.setColor("ORANGE");
        }
    }
}
