package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.Giocatore;
import Model.Gioco;

import javax.swing.*;
import java.util.ArrayList;

public class Obbiettivo4 extends CartaObbiettivo {

    public Obbiettivo4() {
        super("Devi distruggere LE ARMATE BLU\n" +
                "Se le armate blu non sono in gioco, o se sei tu stesso il proprietario " +
                "delle armate blu, o se il giocatore proprietario è eliminato da un altro " +
                "giocatore, il tuo obiettivo diventa automaticamente " +
                "Conquistare 24 TERRITORI",null);
        super.setIcon(new ImageIcon(getClass().getResource("/obj4.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco) {
        ArrayList<Giocatore> players = gioco.getListaGiocatori();
        boolean isPresent = false;
        Giocatore temp = null;

        //controlla se esistono le armate blu
        for(Giocatore p: players){
            if(p.getColore().equals("BLU")){
                isPresent = true;
                temp = p;
            }
        }

        if(isPresent){
            //controlla se le armate blu sono ancora vive
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
