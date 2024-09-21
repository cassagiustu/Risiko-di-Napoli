package Model.Carte;
import Model.Gioco;

import javax.swing.*;
import java.io.Serializable;

public abstract class CartaObbiettivo extends Carta implements Serializable {
    public CartaObbiettivo(String stringa,  ImageIcon icon) {
        super(stringa, icon);
    }

    /**
     * viene chiamato a fine turno di ogni giocatore, prende la situazione del gioco
     * in base all'obbiettivo dato e controlla se l'obbiettivo è stato raggiunto
     * @param gioco Gioco
     * @return true se l'obbiettivo è stato raggiunto, false se non è stato raggiunto
     */
    public abstract boolean ControllaVittoria(Gioco gioco);

}
