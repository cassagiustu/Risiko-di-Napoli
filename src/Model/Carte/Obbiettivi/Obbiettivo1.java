package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.*;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Obbiettivo1 extends CartaObbiettivo {

    public Obbiettivo1(){
        super("Devi distruggere le armate NERE\n" + "Se le armate nere non sono in gioco, o se sei tu stesso il proprietario " +
                "delle armate nere, o se il giocatore proprietario è eliminato da un altro giocatore, il tuo obiettivo diventa automaticamente " +
                "Conquistare 24 TERRITORI", null);

        super.setIcon(new ImageIcon(getClass().getResource("/obj1.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco) {
        ArrayList<Giocatore> players = gioco.getListaGiocatori();
        boolean isPresent = false;
        Giocatore temp = null;

        //controlla se esistono le armate nere
        for(Giocatore p: players){
            if(p.getColore().equals("NERO")){
                isPresent = true;
                temp = p;
            }
        }

        if(isPresent){
            //controlla se le armate nere sono ancora vive
            if(temp.getTerritori().isEmpty())
                return true;
            else
                return false;
        }
        else{
            //controlla se possiedi almeno 24 territori
            if(gioco.getCurrentPlayer().getTerritori().size() >= 24)
                return true;
            else
                return false;
        }
    }
}