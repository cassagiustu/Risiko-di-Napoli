package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.Giocatore;
import Model.Gioco;

import javax.swing.*;
import java.util.ArrayList;

public class Obbiettivo3 extends CartaObbiettivo {

    public Obbiettivo3() {
        super("Devi distruggere LE ARMATE GIALLE\n" +
                "Se le armate gialle non sono in gioco, o se sei tu stesso il proprietario " +
                "delle armate gialle, o se il giocatore proprietario è eliminato da un altro " +
                "giocatore, il tuo obiettivo diventa automaticamente " +
                "Conquistare 24 TERRITORI",null);
        super.setIcon(new ImageIcon(getClass().getResource("/obj3.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco) {
        ArrayList<Giocatore> players = gioco.getListaGiocatori();
        boolean isPresent = false;
        Giocatore temp = null;

        //controlla se esistono le armate gialle
        for(Giocatore p: players){
            if(p.getColore().equals("GIALLO")){
                isPresent = true;
                temp = p;
            }
        }

        if(isPresent){
            //controlla se le armate gialle sono ancora vive
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
