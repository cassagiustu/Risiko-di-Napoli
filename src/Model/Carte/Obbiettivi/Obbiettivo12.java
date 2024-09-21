package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.Giocatore;
import Model.Gioco;

import javax.swing.*;

public class Obbiettivo12 extends CartaObbiettivo {

    public Obbiettivo12(){
        super("Devi conquistare 24 TERRITORI a tua scelta.",null);
        super.setIcon(new ImageIcon(getClass().getResource("/obj13.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco) {
        Giocatore temp = gioco.getCurrentPlayer();

        if(temp.getTerritori().size()>=24)
            return true;
        else
            return false;
    }
}
