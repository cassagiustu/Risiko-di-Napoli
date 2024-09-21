package CLI;
import Controllore.Controllore;
import Model.Giocatore;
import Model.Gioco;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GUI implements Interfaccia{

    private static Controllore c;
    private final Gioco g;
    private Frame frame;

    /**
     * costruttore della GUI
     * @param c controllore
     * @param g gioco
     */
    public GUI(Controllore c, Gioco g) {
        this.c = c;
        this.g=g;
        frame=new Frame(g,c, this);
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * chiama la funzione Menu() del frame
     */
    @Override
    public void showMenu() {
        frame.Menu();
    }

    /**
     * crea i giocatori assegnando nickname e colore presi dal frame
     * @param num numero dei giocatori da creare
     */
    @Override
    public void creaGiocatori(int num) {
        ArrayList<String> players = frame.getNomiGiocatori();

        for(int i = 0; i < num; i++){
            if((Color) Arrays.stream(frame.getColoriGiocatori()).toList().get(i).getSelectedItem() == Color.RED){
                Giocatore p = new Giocatore(players.get(i), "ROSSO");
                g.addGiocatore(p);
            }
            else if((Color) Arrays.stream(frame.getColoriGiocatori()).toList().get(i).getSelectedItem() == Color.YELLOW){
                Giocatore p = new Giocatore(players.get(i), "GIALLO");
                g.addGiocatore(p);
            }
            else if((Color) Arrays.stream(frame.getColoriGiocatori()).toList().get(i).getSelectedItem() == Color.GREEN){
                Giocatore p = new Giocatore(players.get(i), "VERDE");
                g.addGiocatore(p);
            }
            else if((Color) Arrays.stream(frame.getColoriGiocatori()).toList().get(i).getSelectedItem() == Color.BLACK){
                Giocatore p = new Giocatore(players.get(i), "NERO");
                g.addGiocatore(p);
            }
            else if((Color) Arrays.stream(frame.getColoriGiocatori()).toList().get(i).getSelectedItem() == Color.BLUE){
                Giocatore p = new Giocatore(players.get(i), "BLU");
                g.addGiocatore(p);
            }
            else if((Color) Arrays.stream(frame.getColoriGiocatori()).toList().get(i).getSelectedItem() == Color.MAGENTA){
                Giocatore p = new Giocatore(players.get(i), "VIOLA");
                g.addGiocatore(p);
            }
        }
    }

    /**
     * chiama la funziona showGame() nel frame per i turni dei giocatori
     */
    @Override
    public void showGame() {
        frame.showGame();
        g.setGameState("turnigame");
    }

    /**
     * chiama la funzione showPosizioneCarri() nel frame per far inserire i carri iniziali ai giocatori
     */
    @Override
    public void showPosizioneCarri(){
        frame.showPosizioneCarri(-1);
        g.setGameState("start");
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void stampaGiocatori(){

    }

    /**
     * chiama la funzione showMessage() nel frame
     * @param stringa stringa da passare a showMessage()
     */
    @Override
    public void showMessage(String stringa) {
        frame.showMessage(stringa);
    }

    /**
     * chiama la funzione showGreenMessage() nel frame
     * @param stringa stringa da passare a showGreenMessage()
     */
    @Override
    public void showGreenMessage(String stringa) {
        frame.showGreenMessage(stringa);
    }

    /**
     * chiama la funzione showVictory() nel frame per terminare la partita
     */
    @Override
    public void showVictory() {
        frame.showVictory();
    }
}
