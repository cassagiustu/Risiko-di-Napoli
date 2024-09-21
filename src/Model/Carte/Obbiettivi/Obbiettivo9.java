package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.Giocatore;
import Model.Gioco;
import Model.Territorio;

import javax.swing.*;
import java.util.ArrayList;

public class Obbiettivo9 extends CartaObbiettivo {

    public Obbiettivo9(){
        super("Devi conquistare la totalità delle Municipalità 2 e 4, 1 e 10.",null);
        super.setIcon(new ImageIcon(getClass().getResource("/obj10.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco) {
        Giocatore temp = gioco.getCurrentPlayer();
        ArrayList<Territorio> playerterritori = temp.getTerritori();

        int cont1 = 0, cont2 = 0;

        for(Territorio t: playerterritori){
            if(t.getMunicipalita().equals("MUNICIPALITA 2 E 4")){
                cont1++;
            }

            if(t.getMunicipalita().equals("MUNICIPALITA 1 E 10")){
                cont2++;
            }
        }

        if(cont1 == 9 && cont2 == 7)
            return true;
        else
            return false;
    }
}
