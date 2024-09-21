package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.Giocatore;
import Model.Gioco;

import javax.swing.*;
import java.util.ArrayList;

public class Obbiettivo5 extends CartaObbiettivo {

    public Obbiettivo5() {
        super(" Devi distruggere LE ARMATE VERDI\n" +
                "Se le armate verdi non sono in gioco, o se sei tu stesso il proprietario " +
                "delle armate verdi, o se il giocatore proprietario è eliminato da un altro " +
                "giocatore, il tuo obiettivo diventa automaticamente " +
                "Conquistare 24 TERRITORI",null);

        super.setIcon(new ImageIcon(getClass().getResource("/obj5.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco) {
        ArrayList<Giocatore> players = gioco.getListaGiocatori();
        boolean isPresent = false;
        Giocatore temp = null;

        //controlla se esistono le armate verdi
        for(Giocatore p: players){
            if(p.getColore().equals("VERDE")){
                isPresent = true;
                temp = p;
            }
        }

        if(isPresent){
            //controlla se le armate verdi sono ancora vive
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
