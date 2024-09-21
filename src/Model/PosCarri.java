package Model;

import javax.swing.*;
import java.io.Serializable;

public class PosCarri implements Serializable {

    private int x, y;
    private String nome;
    private Giocatore giocatore;
    private ImageIcon icon;
    private JLabel labelNum = null;
    private JLabel tank = null;
    private int num;

    /**
     * costruttore di PosCarri, setta x, y e nome del territorio del carro
     * @param x
     * @param y
     * @param nome
     */
    public PosCarri(int x, int y, String nome){
        this.x = x;
        this.y = y;
        this.nome = nome;
    }

    /**
     * ritorna la x
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * ritorna la y
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * ritorna il nome assegnato all'oggetto
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * setta l'immagine del carro
     * @param icon
     */
    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    /**
     * ritorna l'immagine del carro
     * @return
     */
    public ImageIcon getIcon(){
        return icon;
    }

    /**
     * ritorna il numero del PosCarri
     * @return
     */
    public int getNum(){
        return num;
    }

    /**
     * setta il numero del PosCarri
     * @param num
     */
    public void setNum(int num){
        this.num = num;
    }

    /**
     * ritorna la JLabel numero
     * @return
     */
    public JLabel getLabelNum() {
        return labelNum;
    }

    /**
     * setta la JLabel numero
     * @param labelNum
     */
    public void setLabelNum(JLabel labelNum) {
        this.labelNum = labelNum;
    }

    /**
     * ritorna la JLabel con il tank
     * @return
     */
    public JLabel getTank() {
        return tank;
    }

    /**
     * setta la JLabel con il tank
     * @param tank
     */
    public void setTank(JLabel tank) {
        this.tank = tank;
    }
}
