package com.dracolih.pong;

import java.awt.*;

/**Klasa odpowiedzialna za zachowanie
 * paletki sterowanej przez gracza
 *
 *
 */
public class PaletkaSterowana implements Paletka {

    private double yWspolrzednaPaletki,yPredkosc;
    private boolean przysGora,przysDol;
    private int gracz, xWspolrzednaPaletki,wysPaletki=80,szerPaletki=20;
    private double czuloscSterowania = 0.5;
    private int maxPredkosc = 5;
    private final double HAMOWANIE = 0.95;

    /**Konstruktor klasy PaletkaSterowana
     * definuje predkosc poczatkowa paletki
     * oraz miejsce w ktorym paletka zaczyna gre
     *
     */
    public PaletkaSterowana(){
        przysGora = false;
        przysDol = false;

        yWspolrzednaPaletki = 210;
        xWspolrzednaPaletki =20;

        yPredkosc = 0;

    }

    /**Metoda odpowiedzialna za zwiekszenie predkosci ruchu
     * w gore Paletki
     * wywolywana w metodzie KeyPressed i KeyReleased
     * @param wej zmienna typu boolean
     *            przypisywana do zmiennej przysGora
     */
    public void przyspieszenieGora(boolean wej){
        przysGora = wej;
    }

    /**Metoda odpowiedzialna za zwiekszenie predkosci ruchu
     * w dol Paletki
     * wywolywana w metodzie KeyPressed i KeyReleased
     * @param wej zmienna typu boolean
     *            przypisywana do zmiennej przysDol
     */
    public void przyspieszenieDol(boolean wej){
        przysDol = wej;
    }


    /** Metoda odpowiedzialna za rysowanie ksztaltu paletki
     * czerwony prostokat o wymiarach zdediniowanych na poczatku klasy
     * @param game obiekt typu Graphics potrzebny do
     *             wywolania metod ktore rysuja ksztalt prostokata
     *             i wypelniaja go kolorem
     */
    public void rysowaniePaletki(Graphics game) {//Rysowanie paletki
        game.setColor(Color.red);   //Kolor paletki
        game.fillRect(xWspolrzednaPaletki, (int) yWspolrzednaPaletki, szerPaletki,wysPaletki); //Rozmiar paletki

    }

    /**Metoda odpowiedzialna za ruch w gore i dol
     * im wieksza wartosc czuloscSterowania
     * tym szybciej porusza sie paletka
     * im wieksza wartosc HAMOWANIE
     * tym szybciej paletka zatrzymuje sie w miejscu
     * predkosc paletki jest ograniczona do wartosci maxPredkosc
     * Dodatkowo metoda sprawdza
     * czy paletka nie osiagnela skraju ekranu gry
     *
     */

    public void ruchPaletki() {

        if (przysGora){
            yPredkosc -=czuloscSterowania;
        }
        else if (przysDol){
            yPredkosc +=czuloscSterowania;
        }
        else if (!przysDol && !przysGora){
            yPredkosc *= HAMOWANIE; // * <1 = zmiejsza sie
        }
        if (yPredkosc >= maxPredkosc){
            yPredkosc = maxPredkosc;
        }
        else if (yPredkosc <= -maxPredkosc){
            yPredkosc = -maxPredkosc;
        }

        yWspolrzednaPaletki +=yPredkosc;

        //Jesli paletka dotknie gory
        if (yWspolrzednaPaletki <0){
            yWspolrzednaPaletki =0;
        }
        //Jesli paletka dotknie dolu
        if (yWspolrzednaPaletki >500-wysPaletki)
        {
            yWspolrzednaPaletki =500-wysPaletki;
        }
    }


    /**Metoda pozwala odczytac wartosc yWspolrzednaPaletki
     * poza klasa PaletkaSterowana
     * @return zwraca wartosc yWspolrzednaPaletki
     * w postaci zmiennej typu int
     * */
    public int znajdzY() {
        return (int) yWspolrzednaPaletki;
    }
}
