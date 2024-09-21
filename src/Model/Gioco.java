package Model;

import CLI.Frame;
import Model.Carte.CartaObbiettivo;
import Model.Carte.CartaTerritorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Gioco implements Serializable {

    private Giocatore giocatoreCorrente;
    private Giocatore giocatoreDifendente;
    private final ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
    private final ArrayList<CartaObbiettivo> carteObbiettivo = new ArrayList<>();
    private final ArrayList<CartaTerritorio> carteTerritorio = new ArrayList<>();
    private final ArrayList<Territorio> territori = new ArrayList<>();
    private Territorio territorioAttaccante, territorioSottoAttacco;
    private String gameState;
    private Frame frame;

    public Gioco(){
        gameState = "";
    }
    /**
     * ritorna il frame del gioco
     * @return frame
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * setta il frame del gioco
     * @param frame frame
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * setta la stringa per controllare il gameState
     * @param gameState stringa con stato del gioco
     */
    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    /**
     * ritorna il gameState
     * @return stringa con stato del gioco
     */
    public String getGameState() {
        return gameState;
    }

    /**
     * aggiunge un giocatore alla lista dei giocatori
     * @param g giocatore da aggiungere
     */
    public void addGiocatore(Giocatore g){
        listaGiocatori.add(g);
    }

    /**
     * aggiunge una carta territorio alla lista delle carte
     * @param t cartaTerritorio da aggiungere
     */
    public void addCartaTerritorio(CartaTerritorio t){
        carteTerritorio.add(t);
    }

    /**
     * aggiunge un territorio alla lista dei territori
     * @param t Territorio da aggiungere
     */
    public void addTerritorio(Territorio t){
        territori.add(t);
    }

    /**
     * aggiunge una CartaObbiettivo alla lista degli obbiettivi
     * @param cb CartaObbiettivo da aggiungere
     */
    public void addObbiettivo(CartaObbiettivo cb){
        carteObbiettivo.add(cb);
    }

    /**
     * assegna ad ogni giocatore nel gioco i territori dalle sue carte territorio
     */
    public void assegnaTerritori(){
        ArrayList<CartaTerritorio> temp = carteTerritorio;
        Collections.shuffle(temp);
        int cont = 0;

        while(!temp.isEmpty()) {
            //assegna ai giocatori
            listaGiocatori.get(cont).addCartaTerritorio(temp.get(temp.size() - 1)); //aggiunge al giocatore la carta territorio
            for(Territorio t: territori){   //aggiunge al giocatore il territorio
                if(t.getNome().equals(temp.get(temp.size()-1).toString())){
                    listaGiocatori.get(cont).addTerritorio(t);
                    t.setGiocatore(listaGiocatori.get(cont));
                }
            }
            temp.remove(temp.size() - 1);

            cont++;
            if (cont >= listaGiocatori.size())
                cont = 0;
        }
    }

    /**
     * assegna il numero di armate che ogni giocatore può inserire ad inizio partita
     */
    public void assegnaArmate(){
        int num = listaGiocatori.size();
        switch(num){
            case 3:
                for(int i = 0; i < num; i++){
                    listaGiocatori.get(i).setCarri(17);
                }

                break;
            case 4:
                for(int i = 0; i < num; i++){
                    listaGiocatori.get(i).setCarri(30);
                }
                break;
            case 5:
                for(int i = 0; i < num; i++){
                    listaGiocatori.get(i).setCarri(25);
                }
                break;
            case 6:
                for(int i = 0; i < num; i++){
                    listaGiocatori.get(i).setCarri(20);
                }
                break;
        }
    }

    /**
     * assegna ad ogni giocatore un obbiettivo randomizzato dalla lista obbiettivi
     */
    public void assegnaObbiettivi(){
        Random rand = new Random();
        Collections.shuffle(carteObbiettivo);

        for(int i = 0; i < listaGiocatori.size(); i++){
            int num = rand.nextInt(carteObbiettivo.size());
            listaGiocatori.get(i).setObbiettivo(carteObbiettivo.get(num));
            carteObbiettivo.remove(num);
        }
    }

    /**
     * dispone un carro per ogni territorio dei giocatori
     */
    public void disponiarmate(){
        for(Giocatore g: listaGiocatori){   //fa passare i giocatori
            for(Territorio t: g.getTerritori()){    //fa passare i territori di ogni giocatore
                t.addCarri(1);  //aggiunge un carro ad ogni territorio
                g.togliCarro(1); //toglie un carro dal numero carri disponibili del giocatore
            }
        }
    }

    /**
     * posiziona su un territorio t il numero di carri fino a 3 carri
     * @param carri numero di carri da posizionare
     * @param t nome del territorio
     * @return vero se i carri sono posizionabili, falso se non lo sono
     */
    public boolean piazzaCarri(int carri, String t){
        if(carri <= 0 || carri > 3 || carri > getCurrentPlayer().getCarri())
            return false;

        for(Territorio tp: giocatoreCorrente.getTerritori()){
            if(tp.getNome().equals(t)){
                tp.addCarri(carri);
                giocatoreCorrente.togliCarro(carri);
                return true;
            }
        }

        return false;
    }

    /**
     * posiziona su un territorio t il numero di carri
     * @param carri numero di carri da posizionare
     * @param t nome del territorio
     * @return vero se i carri sono posizionabili, falso se non lo sono
     */
    public boolean piazzaCarriTot(int carri, String t){
        for(Territorio tp: giocatoreCorrente.getTerritori()){
            if(tp.getNome().equals(t)){
                tp.addCarri(carri);
                giocatoreCorrente.togliCarro(carri);
                return true;
            }
        }

        return false;
    }

    /**
     * setta in gioco il giocatore corrente
     * @param g giocatore corrente
     */
    public void setCurrentPlayer(Giocatore g){
        giocatoreCorrente = g;
    }

    /**
     * restituisce il giocatore corrente
     * @return giocatoreCorrente
     */
    public Giocatore getCurrentPlayer(){
        return giocatoreCorrente;
    }

    /**
     * restituisce il giocatore che sta difendendo durante un attacco
     * @return giocatoreDifendente
     */
    public Giocatore getGiocatoreDifendente() {
        return giocatoreDifendente;
    }

    /**
     * setta il giocatore che sta difendendo durante un attacco
     * @param giocatoreDifendente giocatore
     */
    public void setGiocatoreDifendente(Giocatore giocatoreDifendente) {
        this.giocatoreDifendente = giocatoreDifendente;
    }

    /**
     * ritorna la lista dei giocatori
     * @return listaGiocatori
     */
    public ArrayList<Giocatore> getListaGiocatori() {
        return listaGiocatori;
    }

    /**
     * ritorna la lista di tutti i territori
     * @return territori
     */
    public ArrayList<Territorio> getTerritori() {
        return territori;
    }

    /**
     * ritorna il territorio che sta attaccando durante un attacco
     * @return territorioAttaccante
     */
    public Territorio getTerritorioAttaccante() {
        return territorioAttaccante;
    }

    /**
     * ritorna il territorio sotto attacco durante un attacco
     * @return territorioSottoAttacco
     */
    public Territorio getTerritorioSottoAttacco() {
        return territorioSottoAttacco;
    }

    /**
     * setta il territorio attaccante durante un attacco
     * @param territorioAttaccante Territorio
     */
    public void setTerritorioAttaccante(Territorio territorioAttaccante) {
        this.territorioAttaccante = territorioAttaccante;
    }

    /**
     * setta il territorio sotto attacco durante un attacco
     * @param territorioSottoAttacco Territorio
     */
    public void setTerritorioSottoAttacco(Territorio territorioSottoAttacco) {
        this.territorioSottoAttacco = territorioSottoAttacco;
    }

    /**
     * ritorna la lista di tutte le carte territorio
     * @return carteTerritorio
     */
    public ArrayList<CartaTerritorio> getCarteTerritorio() {
        return carteTerritorio;
    }

    /**
     * ritorna la lista di tutte le carte obbiettivo
     * @return carteObbiettivo
     */
    public ArrayList<CartaObbiettivo> getCarteObbiettivo() {
        return carteObbiettivo;
    }
}