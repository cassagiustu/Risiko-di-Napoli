package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.Giocatore;
import Model.Gioco;
import Model.Territorio;

import javax.swing.*;
import java.util.ArrayList;

public class Obbiettivo13 extends CartaObbiettivo {

    public Obbiettivo13(){
        super("Devi conquistare 18 TERRITORI ed occupare ciascuno di essi con almeno " +
                "2 armate.",null);
        super.setIcon(new ImageIcon(getClass().getResource("/obj14.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco){
        Giocatore temp = gioco.getCurrentPlayer();
        ArrayList<Territorio> playerterritori = temp.getTerritori();
        int cont = 0;

        if(temp.getTerritori().size() >= 18){
            //controllo carri armati nei territori
            for(Territorio t: playerterritori){
                if(t.getCarriArmati() >= 2){
                    cont++;
                }
            }
        }

        if(cont >= 18)
            return true;
        else
            return false;
    }
}
