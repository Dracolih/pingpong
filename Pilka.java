package com.dracolih.pong;

import java.awt.*;
import java.awt.Graphics;
import java.util.Random;


public class Pilka {
    Random rand = new Random();
    double x,y,xPredkosc,yPredkosc;
    int szerPilki=20,wysPilki=20;
    int punkty;


    /**
     * Konstruktor dla klasyxp
     */
    //Konstruktor
    public Pilka(){
        //Pilka zaczyna na srodku ekranu
        x=350;
        y=250;
        punkty=0;

        //Kierunek w ktorym pilka zaczyna leciec
        xPredkosc=(double)rand.nextInt(10)-5;
        yPredkosc=(double)rand.nextInt(10)-5;
        if (xPredkosc==0){
            xPredkosc=1;
        }
        if (yPredkosc==0){
            yPredkosc=1;
        }


    }
    public void zwiekszPredkosc(int x)
    {

    }

    public void rysujPilke(Graphics ball){
        ball.setColor(Color.cyan);
        ball.fillOval((int)x-(szerPilki/2),(int)y-(wysPilki/2),szerPilki,wysPilki);
    }


    /**
     *
     */
    public void ruchPilki(){
        x+=xPredkosc;
        y+=yPredkosc;

        //Odbicie pilki od gornej, dolnej i lewej krawedzi
        if (y<(wysPilki/2)){
            yPredkosc=-yPredkosc;
        }
        if (y>(500-(wysPilki/2))){
            yPredkosc=-yPredkosc;
        }
        //Prawa krawedz
        if (x > 500){
            xPredkosc=-xPredkosc;
        }
    }

    public int ilePunktow(){
return punkty;
    }

    /**
     * @param p1
     */
    //Jeśli pilka trafi w paletkę, odbij sie od niej
    public void odbicieOdPaletki(Paletka p1){
        if (x <= 50 && x >= 30){
            if (y >= p1.znajdzY() && y <= p1.znajdzY()+80){
                xPredkosc=-xPredkosc;
                punkty+=1;

            }
        }
    }

    public int jakieX(){

        return (int)x;
    }

    public int jakieY(){

        return (int)y;
    }
}
