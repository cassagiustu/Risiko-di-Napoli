package Model;

import Model.Carte.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Giocatore implements Serializable {

    private final String nickname;
    private CartaObbiettivo obbiettivo;
    private int carri;
    private final ArrayList<Territorio> territori = new ArrayList<>();
    private final ArrayList<CartaTerritorio> carte = new ArrayList<>();
    private ColoreCarri coloreCarri;
    private boolean done = false;

    /**
     * setta il valore di done
     * @param done boolean done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * ritorna il valore di done
     * @return boolean done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * costruttore del giocatore, assegna il nickname e il colore dei carri
      * @param nickname nickname
     * @param colore colore
     */
    public Giocatore(String nickname, String colore){
        this.nickname = nickname;
        colore = colore.toUpperCase();
        switch(colore){
            case "GIALLO": coloreCarri = ColoreCarri.GIALLO; break;
            case "VERDE": coloreCarri = ColoreCarri.VERDE; break;
            case "ROSSO": coloreCarri = ColoreCarri.ROSSO; break;
            case "BLU": coloreCarri = ColoreCarri.BLU; break;
            case "VIOLA": coloreCarri = ColoreCarri.VIOLA; break;
            case "NERO": coloreCarri = ColoreCarri.NERO; break;
        }
    }

    /**
     * ritorna l'obbiettivo del giocatore
     * @return obbiettivo
     */
    public CartaObbiettivo getObbiettivo() {
        return obbiettivo;
    }

    /**
     * setta l'obbiettivo del giocatore
     * @param obbiettivo CartaObbiettivo
     */
    public void setObbiettivo(CartaObbiettivo obbiettivo) {
        this.obbiettivo = obbiettivo;
    }

    /**
     * ritorna il nickname del giocatore
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * setta il numero di carri attuali del giocatore per il posizionamento
     * @param num carri
     */
    public void setCarri(int num){
        carri = num;
    }

    /**
     * ritorna il numero di carri del giocatore per il posizionamento
     * @return carri
     */
    public int getCarri() {
        return carri;
    }

    /**
     * toglie ai carri attuali del giocatore un numero di carri
     * @param carri carri tolti dal numero attuale
     */
    public void togliCarro(int carri){
        this.carri -= carri;
    }

    /**
     * aggiunge un Territorio alla lista territori del giocatore
     * @param t Territorio da aggiungere
     */
    public void addTerritorio(Territorio t){
        territori.add(t);
    }

    /**
     * rimuove un Territorio dalla lista territori del giocatore
     * @param t Territorio da rimuovere
     */
    public void removeTerritorio(Territorio t){
        territori.remove(t);
    }

    /**
     * aggiunge una CartaTerritorio alla lista di carte del giocatore
     * @param ct CartaTerritorio da aggiungere
     */
    public void addCartaTerritorio(CartaTerritorio ct){
        carte.add(ct);
    }

    /**
     * toString del giocatore
     * @return stringa
     */
    @Override
    public String toString(){
        return "\nNickname: " + nickname + " Colore: " + coloreCarri.toString() + "\nTerritori:\n" + territori.toString() + "\nObbiettivo: " + obbiettivo.toString() + "\n";
    }

    /**
     * ritorna il colore dei carri del giocatore
     * @return Color
     */
    public String getColore(){
        return coloreCarri.toString();
    }

    /**
     * ritorna la lista dei territori posseduti dal giocatore
     * @return territori
     */
    public ArrayList<Territorio> getTerritori() {
        return territori;
    }

    /**
     * ritorna la lista di carte territorio possedute dal giocatore
     * @return carte
     */
    public ArrayList<CartaTerritorio> getCarte() {
        return carte;
    }
}