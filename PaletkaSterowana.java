package com.dracolih.pong;

import java.awt.*;

/**
 * Klasa odpowiedialna za zachowanie
 * paletki sterowanej przez gracza
 */
public class PaletkaSterowana {

    private double yWspolrzednaPaletki;
    private double yPredkosc;

    private boolean przysGora;
    private boolean przysDol;

    private int xWspolrzednaPaletki;
    private int wysPaletki = 80;
    private int szerPaletki = 20;
    private final int maxPredkosc = 5;

    private final double czuloscSterowania = 0.5;
    private final double hamowaniePaletki = 0.95;


    /**
     * Konstruktor klasy PaletkaSterowana
     * definuje predkosc poczatkowa paletki
     * oraz miejsce w ktorym paletka zaczyna gre
     */
    public PaletkaSterowana() {
        przysGora = false;
        przysDol = false;

        yWspolrzednaPaletki = 210;
        xWspolrzednaPaletki = 20;

        yPredkosc = 0;

    }

    /**
     * Metoda odpowiedzialna za zwiekszenie predkosci ruchu
     * w gore Paletki
     * wywolywana w metodzie KeyPressed i KeyReleased
     *
     * @param czyPrzyspiesza zmienna typu boolean
     *            przypisywana do zmiennej przysGora
     */
    public void przyspieszenieGora(boolean czyPrzyspiesza) {
        przysGora = czyPrzyspiesza;
    }

    /**
     * Metoda odpowiedzialna za zwiekszenie predkosci ruchu
     * w dol Paletki
     * wywolywana w metodzie KeyPressed i KeyReleased
     *
     * @param czyZwalnia zmienna typu boolean
     *            przypisywana do zmiennej przysDol
     */
    public void przyspieszenieDol(boolean czyZwalnia) {
        przysDol = czyZwalnia;
    }


    /**
     * Metoda odpowiedzialna za rysowanie ksztaltu paletki
     * czerwony prostokat o wymiarach zdediniowanych na poczatku klasy
     *
     * @param game obiekt typu Graphics potrzebny do
     *             wywolania metod ktore rysuja ksztalt prostokata
     *             i wypelniaja go kolorem
     */
    public void rysowaniePaletki(Graphics game) {//Rysowanie paletki
        game.setColor(Color.red);   //Kolor paletki
        game.fillRect(xWspolrzednaPaletki, (int) yWspolrzednaPaletki, szerPaletki, wysPaletki); //Rozmiar paletki

    }

    /**
     * Metoda odpowiedzialna za ruch w gore i dol
     * im wieksza wartosc czuloscSterowania
     * tym szybciej porusza sie paletka
     * im wieksza wartosc hamowaniePaletki
     * tym szybciej paletka zatrzymuje sie w miejscu
     * predkosc paletki jest ograniczona do wartosci maxPredkosc
     * Dodatkowo metoda sprawdza
     * czy paletka nie osiagnela skraju ekranu gry
     *
     * wymnozenie zmiennej yPredkosc z hamowaniePaletki
     * wyhamowuje ruch paletki po puszczeniu klawisza
     * odpowiedzialnego za ruch
     */

    public void ruchPaletki() {

        if (przysGora) {
            yPredkosc -= czuloscSterowania;
        }
        else if (przysDol) {
            yPredkosc += czuloscSterowania;
        }
        else if (!przysDol && !przysGora) {
            yPredkosc *= hamowaniePaletki;
        }
        if (yPredkosc >= maxPredkosc) {
            yPredkosc = maxPredkosc;
        }
        else if (yPredkosc <= -maxPredkosc) {
            yPredkosc = -maxPredkosc;
        }

        yWspolrzednaPaletki += yPredkosc;


        if (yWspolrzednaPaletki < 0) {
            yWspolrzednaPaletki = 0;
        }

        if (yWspolrzednaPaletki > PingPong.wysokoscOkna - wysPaletki) {
            yWspolrzednaPaletki = PingPong.wysokoscOkna - wysPaletki;
        }
    }


    /**
     * Metoda pozwala odczytac wartosc yWspolrzednaPaletki
     * poza klasa PaletkaSterowana
     *
     * @return zwraca wartosc yWspolrzednaPaletki
     * w postaci zmiennej typu int
     */
    public int znajdzY() {
        return (int) yWspolrzednaPaletki;
    }
}
