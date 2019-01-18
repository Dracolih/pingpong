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
 *
 *
 * @author Michal Blotko
 * @version 1.0, 17 Styczen 2019
 */
public class Ping_Pong extends Applet implements Runnable, KeyListener {

    final int WYS=500, SZER=500;
    Thread thread;
    PaletkaSterowana paletkaGracza;
    Pilka pilka;
    boolean startGry =false;
    Graphics grafika;
    Image obraz;

    /**
     * Metoda wymagana przez Applet
     * inicjujaca potrzebne obiekty
     * paletke, pilke
     * thread poniewaz ping pong jest obiektem Runnable
     *
     */

    public void init(){

        this.resize(SZER,WYS);
        startGry=false;
        paletkaGracza = new PaletkaSterowana(1);
        pilka = new Pilka();

        this.addKeyListener(this);

        obraz = createImage(SZER,WYS);
        grafika = obraz.getGraphics();

        thread = new Thread(this);
        thread.start();

    }

    /**
     * Metoda wymagana przez Applet
     * jest odpowiedzialna za
     * @param game obiekt klasy Graphics
     *             za pomoca ktorego uzywamy metod
     *             do rysowania w Applecie ksztaltow
     *             wyswietlania napisow itd
     *
     */

    public void paint(Graphics game){
        grafika.setColor(Color.black); //Tło czarne
        grafika.fillRect(0,0,SZER,WYS); //Wypelnij cale tlo


        //Koniec gry jesli pilka wypadnie z ekranu
        if(pilka.jakieX() < -10 /*|| pilka.jakieX() > (SZER+10)*/ ){
            grafika.setColor(Color.green);
            grafika.drawString("Koniec gry",220,230 );
            grafika.drawString("Punkty zdobyte:", 210,240);
            grafika.drawString(Integer.toString(pilka.ilePunktow()),250,250);
        }
        else {

            paletkaGracza.rysowaniePaletki(grafika); //Narysuj paletke
            pilka.rysujPilke(grafika); //Narysuj pilke
            grafika.setColor(Color.pink);
            grafika.drawString("Biezacy wynik:",230,20);
            grafika.drawString(Integer.toString(pilka.ilePunktow()),250,30);
        }
        if (startGry == false){
            grafika.setColor(Color.gray);
            grafika.drawString("Aby rozpoczac nacisnij spacje",180,150);
            grafika.drawString("Sterowanie odbywa sie za pomoca klawiszy W oraz S",100,170);
        }
        game.drawImage(obraz,0,0,this);
    }



    /** Metoda wymagana przez Applet
     * @param game obiekt klasy Graphics
     *             potrzebny do zaaktualizowania obrazu
     *             w Applecie
     */

    public void update(Graphics game){
        paint(game);
    }

    /** Metoda wymaga przez interfejs Runnable
     * wewnatrz ktorej wykonuje sie petla nieskonczona
     * odpowiedzialna za dzialanie programu
     *
     */

    public void run() {



                while(true){
                    if(startGry == true){
                    paletkaGracza.ruchPaletki();
                    pilka.ruchPilki();
                    pilka.odbicieOdPaletki(paletkaGracza);
                    }

                    repaint();
                    try {
                        Thread.sleep(10); //Czekanie 10 ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

    }

    /**Metoda wymagana przez interfejs KeyEvent
     *
     * @param e obiekt typu KeyEvent
     *          odpowiedzialny
     */
    public void keyTyped(KeyEvent e){

    }

    /**
     *
     * @param e
     */

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_W){ //If wcisniete w gore

            paletkaGracza.przyspieszenieGora(true); //Jedz w gore
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){ //If wcisniete w dol

            paletkaGracza.przyspieszenieDol(true); //Jedz w dol
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            startGry = true;
        }
    }

    /**
     *
     * @param e
     */

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_W){ //If wcisniete w gore
            paletkaGracza.przyspieszenieGora(false);
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){
            paletkaGracza.przyspieszenieDol(false);
        }
    }



}
