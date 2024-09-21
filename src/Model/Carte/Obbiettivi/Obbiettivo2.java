package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.Giocatore;
import Model.Gioco;

import javax.swing.*;
import java.util.ArrayList;

public class Obbiettivo2 extends CartaObbiettivo {
    public Obbiettivo2() {
        super("Devi distruggere LE ARMATE ROSSE\n" +
                "Se le armate rosse non sono in gioco, o se sei tu stesso il proprietario " +
                "delle armate rosse, o se il giocatore proprietario è eliminato da un altro " +
                "giocatore, il tuo obiettivo diventa automaticamente " +
                "Conquistare 24 TERRITORI",null);
        super.setIcon(new ImageIcon(getClass().getResource("/obj2.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco) {
        ArrayList<Giocatore> players = gioco.getListaGiocatori();
        boolean isPresent = false;
        Giocatore temp = null;

        //controlla se esistono le armate rosse
        for(Giocatore p: players){
            if(p.getColore().equals("ROSSO")){
                isPresent = true;
                temp = p;
            }
        }

        if(isPresent){
            //controlla se le armate rosse sono ancora vive
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
