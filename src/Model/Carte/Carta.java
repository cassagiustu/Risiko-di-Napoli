package Model.Carte;

import javax.swing.*;
import java.io.Serializable;

public class Carta implements Serializable {
    private String stringa;
    private ImageIcon icon;

    /**
     * costruttore dell'oggetto carta
      * @param stringa nome carta
     * @param icon immagine
     */
    public Carta(String stringa, ImageIcon icon){
        this.stringa = stringa;
        this.icon = icon;
    }

    /**
     * metodo toString della carta ritorna il nome della carta
     * @return
     */
    @Override
    public String toString(){
        return stringa;
    }

    /**
     * setta l'immagine della carta per la GUI
     * @param icon
     */
    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    /**
     * ritorna l'immagine della carta
     * @return
     */
    public ImageIcon getIcon(){
        return icon;
    }

}
