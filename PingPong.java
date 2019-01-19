package com.dracolih.pong;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Klasa glowna
 * odpowiedzialna za petle nieskonczona
 * w ktorej odbywa sie kod programu
 * aplikacja funkcjonuje jako Applet
 * implementuje interfejsy Runnable oraz KeyListener
 * aby obslugiwac obiekt typu Thread
 * i odczytywac dane wejsciowe z klawiatury
 *
 * @author Michal Blotko
 * @version 1.3, Styczen 2019
 */
public class PingPong extends Applet implements Runnable, KeyListener {

    public final static int wysokoscOkna = 500;
    public final static int szerokoscOkna = 500;

    private boolean startGry = false;

    private Thread thread;
    private PaletkaSterowana paletkaGracza;
    private Pilka pilka;
    private Graphics grafika;
    private Image obraz;

    /**
     * Metoda wymagana przez Applet
     * inicjujaca potrzebne obiekty
     * paletke, pilke
     *
     * obiekty typów Image i Graphics sa potrzebne
     * aby rysowanie wszystkich elementow odbywalo sie
     * poza widoczna gra, aby uniknac efektu mrugania elementow
     */

    public void init() {

        this.resize(szerokoscOkna, wysokoscOkna);
        startGry = false;
        paletkaGracza = new PaletkaSterowana();
        pilka = new Pilka();

        /* Nasłuchiwanie wejscia z klawiatury */
        this.addKeyListener(this);

        obraz = createImage(szerokoscOkna, wysokoscOkna);
        grafika = obraz.getGraphics();

        thread = new Thread(this);
        thread.start();

    }

    /**
     * Metoda wymagana przez Applet
     * jest odpowiedzialna za rysowanie elementów
     * obecnych podczas gry
     *
     * Koniec gry nastepuje gdy pilka dotknie lewej krawedzi
     *
     * @param gra obiekt klasy Graphics
     *             za pomoca ktorego uzywamy metod
     *             do rysowania w Applecie ksztaltow
     *             wyswietlania napisow itd
     */

    public void paint(Graphics gra) {
        grafika.setColor(Color.black);
        grafika.fillRect(0, 0, szerokoscOkna, wysokoscOkna);

        if (startGry) {
            if (pilka.getxWspolrzednaPilki() < -10) {
                grafika.setColor(Color.green);
                grafika.drawString("Koniec gry", 220, 220);
                grafika.drawString("Punkty zdobyte:", 210, 240);
                grafika.drawString(Integer.toString(pilka.ilePunktow()), 250, 260);
            } else {
                paletkaGracza.rysowaniePaletki(grafika);
                pilka.rysujPilke(grafika);

                grafika.setColor(Color.pink);
                grafika.drawString("Biezacy wynik:", 220, 20);
                grafika.drawString(Integer.toString(pilka.ilePunktow()), 250, 40);
            }
        }
        else {
            grafika.setColor(Color.gray);
            grafika.drawString("Aby rozpoczac nacisnij klawisz Spacji", 150, 150);
            grafika.drawString("Sterowanie odbywa sie za pomoca klawiszy  W oraz S", 110, 170);
        }
        gra.drawImage(obraz, 0, 0, this);
    }


    /**
     * Metoda wymagana przez Applet
     *
     * @param gra obiekt klasy Graphics
     *             potrzebny do zaaktualizowania obrazu
     *             w Applecie
     */

    public void update(Graphics gra) {
        paint(gra);
    }

    /**
     * Metoda wymaga przez interfejs Runnable
     * wewnatrz ktorej wykonuje sie petla nieskonczona
     * odpowiedzialna za dzialanie programu
     */

    public void run() {
        while (true) {
            if (startGry == true) {
                paletkaGracza.ruchPaletki();
                pilka.ruchPilki();
                pilka.odbicieOdPaletki(paletkaGracza);
                pilka.zwiekszPredkosc(pilka.ilePunktow());
            }

            repaint();
            try {
                Thread.sleep(10); //Czekanie 10 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Metoda wymagana przez interfejs KeyEvent
     *
     * @param event obiekt typu KeyEvent
     */

    public void keyTyped(KeyEvent event) {

    }

    /**
     * Metoda wymagana przez interfejs KeyEvent
     * odpowiada za obslugiwanie naciskania klawiszy
     * W - dla ruchu paletki w gore
     * S - dla ruchu paletki w dol
     * Spacji - dla rozpoczecia gry
     *
     * @param event obiekt typu KeyEvent
     *          wymagany do uzycia metody
     *          getKeyCode
     */

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {

            paletkaGracza.przyspieszenieGora(true);
        } else if (event.getKeyCode() == KeyEvent.VK_S) {

            paletkaGracza.przyspieszenieDol(true);
        } else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            startGry = true;
        }
    }

    /**
     * Metoda wymagana przez interfejs KeyEvent
     * odpowiada za przerwanie ruchu
     * w momencie puszczenia klawisza
     * sterujacego ruchem paletki
     *
     * @param event obiekt typu KeyEvent
     *          wymagany do uzycia metody
     *          getKeyCode
     */

    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            paletkaGracza.przyspieszenieGora(false);
        } else if (event.getKeyCode() == KeyEvent.VK_S) {
            paletkaGracza.przyspieszenieDol(false);
        }
    }

}
