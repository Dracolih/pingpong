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
public class PingPong extends Applet implements Runnable, KeyListener {

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
        paletkaGracza = new PaletkaSterowana();
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
        grafika.setColor(Color.black);
        grafika.fillRect(0,0,SZER,WYS);


        //Koniec gry jesli pilka wypadnie z ekranu
        if(pilka.jakieX() < -10 ){
            grafika.setColor(Color.green);
            grafika.drawString("Koniec gry",220,220 );
            grafika.drawString("Punkty zdobyte:", 210,240);
            grafika.drawString(Integer.toString(pilka.ilePunktow()),250,260);
        }
        else {
            paletkaGracza.rysowaniePaletki(grafika);
            pilka.rysujPilke(grafika);
            grafika.setColor(Color.pink);
            grafika.drawString("Biezacy wynik:",220,20);
            grafika.drawString(Integer.toString(pilka.ilePunktow()),250,30);
        }

        if (startGry == false){
            grafika.setColor(Color.gray);
            grafika.drawString("Aby rozpoczac nacisnij klawisz Spacji",150,150);
            grafika.drawString("Sterowanie odbywa sie za pomoca klawiszy  W oraz S",110,170);
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

    /**Metoda wymagana przez interfejs KeyEvent
     *
     * @param e obiekt typu KeyEvent
     *
     */

    public void keyTyped(KeyEvent e){

    }

    /**Metoda wymagana przez interfejs KeyEvent
     * odpowiada za obslugiwanie naciskania klawiszy
     * W - dla ruchu paletki w gore
     * S - dla ruchu paletki w dol
     * Spacji - dla rozpoczecia gry
     *
     * @param e obiekt typu KeyEvent
     *          wymagany do uzycia metody
     *          getKeyCode
     *
     */

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_W){

            paletkaGracza.przyspieszenieGora(true);
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){

            paletkaGracza.przyspieszenieDol(true);
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            startGry = true;
        }
    }

    /**Metoda wymagana przez interfejs KeyEvent
     * odpowiada za przerwanie ruchu
     * w momencie puszczenia klawiszy
     * sterujacych ruchem paletki
     *
     * @param e obiekt typu KeyEvent
     *          wymagany do uzycia metody
     *          getKeyCode
     */

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_W){
            paletkaGracza.przyspieszenieGora(false);
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){
            paletkaGracza.przyspieszenieDol(false);
        }
    }



}
