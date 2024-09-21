package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Territorio implements Serializable {
    private final String nome;
    private final String municipalita;
    private Giocatore giocatore;
    private int carriArmati;
    private ArrayList<String> confini = new ArrayList<>();

    /**
     * costruttore del territorio con nome, municipalita e confini
     * @param nome stringa nome territorio
     * @param municipalita stringa municipalita
     * @param confini arraylist di stringhe confini
     */
    public Territorio(String nome, String municipalita, ArrayList<String> confini){
        this.nome = nome;
        this.municipalita = municipalita;
        this.confini = confini;
    }

    /**
     * setta il giocatore a cui appartiene questo territorio
     * @param g Giocatore
     */
    public void setGiocatore(Giocatore g){
        this.giocatore = g;
    }

    /**
     * ritorna il giocatore a cui appartiene questo territorio
     * @return Giocatore
     */
    public Giocatore getGiocatore(){
        return giocatore;
    }

    /**
     * aggiunge un numero di carri al territorio
     * @param carri int carri da aggiungere
     */
    public void addCarri(int carri){
        carriArmati += carri;
    }

    /**
     * rimuove un numero di carri dal territorio
     * @param carri int carri da togliere
     */
    public void togliCarri(int carri){
        carriArmati -= carri;
    }

    /**
     * ritorna il numero di carri armati nel territorio
     * @return int carri
     */
    public int getCarriArmati(){
        return carriArmati;
    }

    /**
     * ritorna il nome del territorio
     * @return string nome
     */
    public String getNome(){
        return nome;
    }

    /**
     * ritorna la municipalita del territorio
     * @return string municipalita
     */
    public String getMunicipalita(){
        return municipalita;
    }

    /**
     * toString del territorio
     * @return
     */
    @Override
    public String toString(){
        return nome + " " + municipalita;
    }

    /**
     * ritorna i confini del territorio
     * @return ArrayList di string confini
     */
    public ArrayList<String> getConfini() {
        return confini;
    }
}