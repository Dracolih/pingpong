package com.dracolih.pong;

import java.awt.*;

public class PaletkaSterowana implements Paletka {

    double y,yPredkosc;
    boolean przysGora,przysDol;
    int gracz, x,wysPaletki=80,szerPaletki=20;
    double czuloscSterowania = 1;

    final double GRAVITY = 0.95; //Z duzych bo stala

    public PaletkaSterowana(int gracz){ //Konstruktor
        przysGora = false;
        przysDol = false;
        y = 210; // Zaczynamy na srodku
        yPredkosc = 0; //Zaczynamy stojac w miejscu

        if (gracz == 1){
            x=20; //Lewa strona
        }
        else {
            x = 660; // Prawa strona
        }

    }

    public void przyspieszenieGora(boolean wej){
        przysGora = wej;
    }

    public void przyspieszenieDol(boolean wej){
        przysDol = wej;
    }


    public void rysowaniePaletki(Graphics game) {//Rysowanie paletki
        game.setColor(Color.red);   //Kolor paletki
        game.fillRect(x, (int) y, szerPaletki,wysPaletki); //Rozmiar paletki

    }

    //Metoda odpowiedzialna za ruch w gore i dol
    public void ruchPaletki() {

        if (przysGora){
            yPredkosc -=czuloscSterowania;

        }
        else if (przysDol){
            yPredkosc +=czuloscSterowania;
        }
        else if (!przysDol && !przysGora){
            yPredkosc *= GRAVITY; // * <1 = zmiejsza sie
        }
    //Ograniczenie predkosci paletki
        if (yPredkosc >= 5){
            yPredkosc = 5;
        }

        else if (yPredkosc <= -5){
            yPredkosc = -5;
        }


        y+=yPredkosc;

        //Jesli paletka dotknie gory
        if (y<0){
            y=0;
        }


        //Jesli paletka dotknie dolu
        if (y>500-wysPaletki)
        {
            y=500-wysPaletki;
        }
    }


    public int znajdzY() {


        return (int)y;
    }
}
