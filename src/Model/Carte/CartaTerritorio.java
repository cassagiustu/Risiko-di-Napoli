package Model.Carte;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class CartaTerritorio extends Carta implements Serializable {
    private String powerUp;
    public CartaTerritorio(String stringa, ImageIcon icon, String powerUp) {
        super(stringa, icon);
        this.powerUp = powerUp;
    }

    /**
     * toString con power up
      * @return
     */
    public String toStringPowerUp(){
        return "PowerUp" + powerUp;
    }

    /**
     * ritorna il power up della carta
     * @return
     */
    public String getPowerUp() {
        return powerUp;
    }
}
