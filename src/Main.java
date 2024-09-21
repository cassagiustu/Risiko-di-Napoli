import CLI.Frame;
import CLI.GUI;
import Controllore.Controllore;
import Model.Gioco;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String scelta;
        System.out.println("Quale interfaccia vuoi utilizzare?\nCLI (1) \nGUI (2)\n");
        do{
            Scanner s = new Scanner(System.in);
            scelta = s.next();
            if(!scelta.equals("1") && !scelta.equals("2")){
                System.out.println("\033[0;31m" + "Inserimento non valido" + "\033[0m");
            }
        }while(!scelta.equals("1") && !scelta.equals("2"));

        Controllore c = new Controllore(Integer.parseInt(scelta));
        c.start();
    }
}