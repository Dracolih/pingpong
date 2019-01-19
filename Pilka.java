package com.dracolih.pong;

import java.awt.*;
import java.awt.Graphics;
import java.util.Random;


/**
 * Klasa Pilka
 * odpowiedzialna za rozmiar
 * predkosc oraz zachowanie pilki
 * w grze
 */
public class Pilka {
    private Random rand = new Random();

    private int xWspolrzednaPilki;
    private int yWspolrzednaPilki;
    private int punkty;
    private final static int szerPilki = 20;
    private final static int wysPilki = 20;

    private double xPredkosc;
    private double yPredkosc;
    private final double zmianaPredkosci;

    private boolean zwiekszonoPredkoscX;


    /**
     * Konstruktor dla klasy Pilka
     * definiuje rozmiar
     * oraz predkosc poczatkowa Pilki
     * Pilka rozpoczyna posrodku ekranu
     * i zaczyna swoj ruch w kierunku prawej sciany
     * aby gracz mogl dostosowac swoje zachowanie
     * mozliwe jest tez pseudolosowe ustawienie kierunkow
     * w jakich porusza sie pilka
     */

    public Pilka() {

        zmianaPredkosci = 0.4;
        xWspolrzednaPilki = 350;
        yWspolrzednaPilki = 250;

        punkty = 0;
        zwiekszonoPredkoscX = false;

        xPredkosc = 2;
        yPredkosc = 3;
    }

    /**
     * Metoda ktora zwieksza predkosc w zaleznosci od
     * ilosci punktow jakie zdobyl gracz
     * w trakcie rozgrywki
     *
     * @param punktyGracza zmienna w ktorej przekazujemy
     *                     punkty zdobyte przez gracza
     */
    public void zwiekszPredkosc(int punktyGracza) {
        if (punktyGracza % 3 == 0 && !zwiekszonoPredkoscX) {
            xPredkosc += zmianaPredkosci;
            zwiekszonoPredkoscX = true;
        } else if (punktyGracza % 3 != 0) {
            zwiekszonoPredkoscX = false;
        }
    }

    /**
     * Metoda rysujaca ksztalt pilki i nadajaca jej kolor
     *
     * @param ball obiekt typu graphics
     *             potrzebny do wywolania metod
     *             rysujacej owal pilki
     *             i wypelniajacej go kolorem turkusowym
     */
    public void rysujPilke(Graphics ball) {
        ball.setColor(Color.cyan);
        ball.fillOval(xWspolrzednaPilki - (szerPilki / 2), yWspolrzednaPilki - (wysPilki / 2), szerPilki, wysPilki);
    }


    /**
     * Metoda odpowiedzialna za
     * przemieszczanie sie po polu gry oraz
     * odbicie pilki od gornej, dolnej i prawej krawedzi
     */
    public void ruchPilki() {
        xWspolrzednaPilki += xPredkosc;
        yWspolrzednaPilki += yPredkosc;


        if (yWspolrzednaPilki < (wysPilki / 2)) {
            yPredkosc = -yPredkosc;
        }
        if (yWspolrzednaPilki > (PingPong.wysokoscOkna - (wysPilki / 2))) {
            yPredkosc = -yPredkosc;
        }
        if (xWspolrzednaPilki > PingPong.wysokoscOkna) {
            xPredkosc = -xPredkosc;
        }
    }

    /**
     * Metoda zwracajaca obecna liczbe punktow
     *
     * @return zwraca wartosc punktow jako int
     */
    public int ilePunktow() {
        return punkty;
    }

    /**
     * Metoda ktora w momencie
     * zetkniecie paletki z pilka
     * zmienia wektor predkosci pilki na przeciwny
     * i zwieksza liczbe punktow o 1
     * warunek xPredkosc < 0
     * sluzy zapobieganiu zaciecia sie pilki w paletce
     *
     * @param paletkaPierwsza obiekt na ktorym wywolywane sa metody
     *                        zluzace sprawdzeniu polozenia paletki
     *                        i porownania jej wzgledem pilki
     */

    public void odbicieOdPaletki(PaletkaSterowana paletkaPierwsza) {
        if (xWspolrzednaPilki <= 50 && xWspolrzednaPilki >= 30) {
            if (yWspolrzednaPilki >= paletkaPierwsza.znajdzY() && yWspolrzednaPilki <= paletkaPierwsza.znajdzY() + 80) {
                if (xPredkosc < 0) {
                    xPredkosc = -xPredkosc;
                    punkty += 1;
                }
            }
        }
    }

    /**
     * Funkcja zwracaja wspolrzedna x pilki
     *
     * @return int zwracajacy wspolrzedna x
     */

    public int getxWspolrzednaPilki() {
        return xWspolrzednaPilki;
    }

}
