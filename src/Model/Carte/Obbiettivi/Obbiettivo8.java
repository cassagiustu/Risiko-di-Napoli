package Model.Carte.Obbiettivi;

import Model.Carte.CartaObbiettivo;
import Model.Giocatore;
import Model.Gioco;
import Model.Territorio;

import javax.swing.*;
import java.util.ArrayList;

public class Obbiettivo8 extends CartaObbiettivo {

    public Obbiettivo8(){
        super("Devi conquistare la totalità delle Municipalità 8, 3 e 7",null);
        super.setIcon(new ImageIcon(getClass().getResource("/obj9.png")));
    }

    @Override
    public boolean ControllaVittoria(Gioco gioco) {
        Giocatore temp = gioco.getCurrentPlayer();
        ArrayList<Territorio> playerterritori = temp.getTerritori();

        int cont1 = 0, cont2 = 0;

        for(Territorio t: playerterritori){
            if(t.getMunicipalita().equals("MUNICIPALITA 8")){
                cont1++;
            }

            if(t.getMunicipalita().equals("MUNICIPALITA 3 E 7")){
                cont2++;
            }
        }

        if(cont1 == 5 && cont2 == 9)
            return true;
        else
            return false;
    }
}
