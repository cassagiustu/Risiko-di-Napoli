package Controllore;
import Model.Carte.*;
import Model.*;
import CLI.*;
import Model.Carte.Obbiettivi.*;
import CLI.Frame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Controllore {

    private Gioco g = new Gioco();
    private Interfaccia i;
    private boolean endGame = false, endStart = false, hasConquered = false, guiB = false;
    private Frame frame;

    /**
     * costruttore del controllore che in base al parametro che riceve apre CLI o GUI
     * @param scelta scelta CLI o GUI fatta nel main
     */
    public Controllore(int scelta){
        switch(scelta){
            case 1: i = new CLI(this, g);
                break;
            case 2:
                i = new GUI(this,g);
                guiB = true;
                break;
        }
    }

    /**
     * setter del frame
     * @param frame frame
     */
    public void setFrame(Frame frame){
        this.frame = frame;
    }

    /**
     * chiama la funzione showMenu che fa iniziare il gioco chiedendo i dati iniziali
     */
    public void start(){
        i.showMenu();
        //faccio startgame dentro la cli e gui così sono sicuro di assegnare i territori solo quando i giocatori sono stati inseriti
    }

    /**
     *ritorna il numero di pixel corrispondente alla x
     * @param percentuale valore percentuale dello schermo x
     * @return intero nello schermo attuale
     */
    public int getPixelX(double percentuale){
        //ritorna il numero di pixel corrispondente alla x
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int larghezza = (int) screenSize.getWidth();//larghezza

        return (int) (larghezza*percentuale/100);
    }

    /**
     * ritorna il numero di pixel corrispondente alla y
     * @param percentuale valore percentuale dello schermo y
     * @return intero nello schermo attuale
     */
    public int getPixelY(double percentuale){
        //ritorna il numero di pixel corrispondente alla y
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int altezza = (int) screenSize.getHeight();//altezza
        return (int) (altezza*percentuale/100);
    }
    /**
     * posiziona 1 carro in tutti i territori della mappa
     */
    public void posizionaCarri(){
        frame.addPosCarri(new PosCarri(getPixelX(30),getPixelY(28.5) , "Arenella Est"));
        frame.addPosCarri(new PosCarri(getPixelX(23),getPixelY(34.7), "Arenella Ovest"));
        frame.addPosCarri(new PosCarri(getPixelX(42.2),getPixelY(36.3), "Avvocata"));
        frame.addPosCarri(new PosCarri(getPixelX(6.3),getPixelY(56.4), "Bagnoli Nord"));
        frame.addPosCarri(new PosCarri(getPixelX(15),getPixelY(65), "Bagnoli Sud"));
        frame.addPosCarri(new PosCarri(getPixelX(84.4),getPixelY(40), "Barra Nord"));
        frame.addPosCarri(new PosCarri(getPixelX(91),getPixelY(51.7), "Barra Sud"));
        frame.addPosCarri(new PosCarri(getPixelX(36),getPixelY(55.8), "Chiaia Est"));
        frame.addPosCarri(new PosCarri(getPixelX(31.1),getPixelY(67.2), "Chiaia Ovest"));
        frame.addPosCarri(new PosCarri(getPixelX(29.7),getPixelY(12.6), "Chiaiano Est"));
        frame.addPosCarri(new PosCarri(getPixelX(20),getPixelY(10), "Chiaiano Ovest"));
        frame.addPosCarri(new PosCarri(getPixelX(23),getPixelY(67), "Fuorigrotta"));
        frame.addPosCarri(new PosCarri(getPixelX(67.5),getPixelY(41.7), "Mercato"));
        frame.addPosCarri(new PosCarri(getPixelX(55),getPixelY(12.8), "Miano"));
        frame.addPosCarri(new PosCarri(getPixelX(42.2),getPixelY(43.6), "MonteCalvario"));
        frame.addPosCarri(new PosCarri(getPixelX(60.7),getPixelY(41.7), "Pendino"));
        frame.addPosCarri(new PosCarri(getPixelX(14.5),getPixelY(34.2), "Pianura Est"));
        frame.addPosCarri(new PosCarri(getPixelX(6.2),getPixelY(32.5), "Pianura Ovest"));
        frame.addPosCarri(new PosCarri(getPixelX(34.2),getPixelY(4.1), "Piscinola Nord"));
        frame.addPosCarri(new PosCarri(getPixelX(41.2),getPixelY(7.5), "Piscinola Sud"));
        frame.addPosCarri(new PosCarri(getPixelX(76.3),getPixelY(25), "Poggioreale"));
        frame.addPosCarri(new PosCarri(getPixelX(86),getPixelY(20), "Ponticelli Nord"));
        frame.addPosCarri(new PosCarri(getPixelX(91.5),getPixelY(32), "Ponticelli Sud"));
        frame.addPosCarri(new PosCarri(getPixelX(50.6),getPixelY(50.3), "Porto"));
        frame.addPosCarri(new PosCarri(getPixelX(27),getPixelY(80.4), "Posillipo"));
        frame.addPosCarri(new PosCarri(getPixelX(54.5),getPixelY(21.7), "San Carlo all'Arena Centro"));
        frame.addPosCarri(new PosCarri(getPixelX(66.7),getPixelY(13.5), "San Carlo all'Arena Est"));
        frame.addPosCarri(new PosCarri(getPixelX(43.5),getPixelY(17.1), "San Carlo all'Arena Ovest"));
        frame.addPosCarri(new PosCarri(getPixelX(82),getPixelY(54.6), "San Giovanni a Teduccio"));
        frame.addPosCarri(new PosCarri(getPixelX(50.5),getPixelY(35.3), "San Giuseppe"));
        frame.addPosCarri(new PosCarri(getPixelX(59.2),getPixelY(33.3), "San Lorenzo"));
        frame.addPosCarri(new PosCarri(getPixelX(68.6),getPixelY(4), "San Pietro a Piatierno Nord"));
        frame.addPosCarri(new PosCarri(getPixelX(76.3),getPixelY(9.8), "San Pietro a Piatierno Sud"));
        frame.addPosCarri(new PosCarri(getPixelX(42.5),getPixelY(52.9), "San Ferdinando"));
        frame.addPosCarri(new PosCarri(getPixelX(45.4),getPixelY(1.5), "Scampia"));
        frame.addPosCarri(new PosCarri(getPixelX(60),getPixelY(3.8), "Secondigliano"));
        frame.addPosCarri(new PosCarri(getPixelX(21.3),getPixelY(44.8), "Soccavo"));
        frame.addPosCarri(new PosCarri(getPixelX(42.2),getPixelY(26.1), "Stella Rione Sanita"));
        frame.addPosCarri(new PosCarri(getPixelX(67.4),getPixelY(27.5), "Vicaria"));
        frame.addPosCarri(new PosCarri(getPixelX(32.2),getPixelY(41.6), "Vomero Est"));
        frame.addPosCarri(new PosCarri(getPixelX(28.4),getPixelY(50.4), "Vomero Ovest"));
        frame.addPosCarri(new PosCarri(getPixelX(74.8),getPixelY(41.8), "Zona Industriale"));

    }

    //chiama questo metodo una volta che ha preso in input i giocatori e ha chiamto startGame
    /**
     * gestisce i turni del gioco
     */
    public void game(boolean caricando){
        //turni disposizione 3 carri armati a player
        if(g.getGameState().isEmpty()){
            for (Giocatore p : g.getListaGiocatori()) {
                p.getCarte().clear();
            }

            Collections.shuffle(g.getListaGiocatori());
        }

        if(g.getGameState().equals("start") || g.getGameState().isEmpty()) {

            int j = 0;
            if(caricando){
                for(int k = 0; k < g.getListaGiocatori().size(); k++){
                    if(g.getListaGiocatori().get(k).getNickname().equals(g.getCurrentPlayer().getNickname())){
                        j = k;
                    }
                }
            }
            do {
                g.setGameState("start");
                for (j = j;  j < g.getListaGiocatori().size(); j++) {
                    i.showGreenMessage("\nTURNO GIOCATORE " + (j + 1) + ": " + g.getListaGiocatori().get(j).getNickname());
                    g.setCurrentPlayer(g.getListaGiocatori().get(j));
                    i.showPosizioneCarri();
                }

                int cont = 0;
                for (Giocatore p : g.getListaGiocatori()) {
                    System.out.println(p.getNickname() + " " + p.getCarri());
                    if (p.getCarri() <= 0) {
                        p.setCarri(0);
                        cont++;
                    }
                }
                j = 0;

                if (cont == g.getListaGiocatori().size()) {
                    endStart = true;
                }

            } while (!endStart);
            g.setGameState("turnigame");
        }


        if(g.getGameState().equals("turnigame")) {
            //inizio turni normali
            i.showGreenMessage("-- Inizio Turni --");
            int j = 0;
            if(caricando){
                for(int k = 0; k < g.getListaGiocatori().size(); k++){
                    if(g.getListaGiocatori().get(k).getNickname().equals(g.getCurrentPlayer().getNickname())){
                        j = k;
                    }
                }
            }

            int cont = 1;
            do {
                g.setGameState("turnigame");
                g.setCurrentPlayer(g.getListaGiocatori().get(j));
                i.showGreenMessage("\nTURNO " + cont + ": " + g.getCurrentPlayer().getNickname());
                i.showGame();

                if (hasConquered) {
                    hasConquered = false;
                    g.getCurrentPlayer().addCartaTerritorio(endTurn());
                } else {
                    i.showGreenMessage("\nFINE TURNO");
                }

                if (j == g.getListaGiocatori().size() - 1)     //PASSAGGIO GIOCATORE SUCCESSIVO
                    j = 0;
                else
                    j++;

                if (g.getCurrentPlayer().getTerritori().isEmpty()) {
                    g.getListaGiocatori().remove(g.getCurrentPlayer());
                    i.showMessage("GIOCATORE " + g.getCurrentPlayer().getNickname() + " ha perso tutti i territori ed è stato eliminato.");
                }

                cont++;

                if (g.getCurrentPlayer().getObbiettivo().ControllaVittoria(g))   //GIOCATORE VINCE
                    endGame();

            } while (!endGame);
        }

    }
    /**
     * chiama tutte le funzioni per poter iniziare il gioco fino al posizionamento dei carri
     */
    //assegnazione obbiettivi, territori, carri nei territori, precede l'inizio dei turni
    public void startGame(){
        //creazione carteterritori
        creazioneCarteTerritorio();
        //creazione territori
        creazioneTerritori();
        //assegnazione numero carri
        g.assegnaArmate();
        //assegnazione casuale carteterritori
        g.assegnaTerritori();
        creazioneCarteTerritorio();
        //randomizza obbiettivi
        creazioneObbiettivi();
        g.assegnaObbiettivi();
        //disposizione dei carriarmati sui territori
        g.disponiarmate();
        //inizio turni disposizione 3 carri x turno, stampa di tutte le informazioni di ogni player fin'ora
        i.stampaGiocatori();
        if(guiB){
            posizionaCarri();
            frame.Mappa();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    game(false);
                }
            }).start();
        }
        else{
            game(false);
        }
    }

    /**
     * crea tutte le carte territorio e le memorizza nell' arralist carteTerritori dentro Gioco g
     */
    public void creazioneCarteTerritorio(){
        //MUNICIPALITA 8
        g.addCartaTerritorio(new CartaTerritorio("Chiaiano Ovest",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Chiaiano Ovest.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("Chiaiano Est",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Chiaiano Est.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Piscinola Nord",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Piscinola Nord.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Piscinola Sud",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Piscinola Sud.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("Scampia",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Scampia.png"))), "MOTORINO"));

        //MUNICIPALITA 5 E 9
        g.addCartaTerritorio(new CartaTerritorio("Pianura Ovest",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Pianura Ovest.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Pianura Est",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Pianura Est.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Arenella Est",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Arenella Est.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("Arenella Ovest",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Arenella Ovest.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Soccavo",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Soccavo.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("Vomero Est",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Vomero Est.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("Vomero Ovest",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Vomero Ovest.png"))), "CANNONE"));

        //MUNICIPALITA 1 E 10
        g.addCartaTerritorio(new CartaTerritorio("Bagnoli Nord",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Bagnoli Nord.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Bagnoli Sud",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Bagnoli Sud.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Fuorigrotta",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Fuorigrotta.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Posillipo",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Posillipo.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Chiaia Ovest",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Chiaia Ovest.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Chiaia Est",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Chiaia Est.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("San Ferdinando",new ImageIcon(Objects.requireNonNull(getClass().getResource("/SanFerdinando.png"))), "MOTORINO"));

        //MUNICIPALITA 2 E 4
        g.addCartaTerritorio(new CartaTerritorio("Avvocata",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Avvocata.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("MonteCalvario",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Montecalvario.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("San Giuseppe",new ImageIcon(Objects.requireNonNull(getClass().getResource("/San Giuseppe.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("Porto",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Porto.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("San Lorenzo",new ImageIcon(Objects.requireNonNull(getClass().getResource("/San Lorenzo.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Pendino",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Pendino.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Vicaria",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Vicaria.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Mercato",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Mercato.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("Zona Industriale",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Zona Industriale.png"))), "MOTORINO"));

        //MUNICIPALITA 6
        g.addCartaTerritorio(new CartaTerritorio("Ponticelli Nord",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Ponticelli Nord.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("Ponticelli Sud",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Ponticelli Sud.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Barra Nord",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Barra Nord.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Barra Sud",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Barra Sud.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("San Giovanni a Teduccio",new ImageIcon(Objects.requireNonNull(getClass().getResource("/San Giovanni a Teduccio.png"))), "CANNONE"));

        //MUNICIPALITA 3 E 7
        g.addCartaTerritorio(new CartaTerritorio("San Carlo all'Arena Ovest",new ImageIcon(Objects.requireNonNull(getClass().getResource("/San Carlo all_Arena Ovest.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("San Carlo all'Arena Centro",new ImageIcon(Objects.requireNonNull(getClass().getResource("/San Carlo all_Arena Centro.png"))), "MAFIOSO"));
        g.addCartaTerritorio(new CartaTerritorio("Stella Rione Sanita",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Stella.png"))), "MOTORINO"));
        g.addCartaTerritorio(new CartaTerritorio("San Carlo all'Arena Est",new ImageIcon(Objects.requireNonNull(getClass().getResource("/San Carlo all_Arena Est.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Miano",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Miano.png"))), "CANNONE"));
        g.addCartaTerritorio(new CartaTerritorio("Secondigliano",new ImageIcon(Objects.requireNonNull(getClass().getResource("/Secondigliano.png"))), "MAFIOSO"));
        for (CartaTerritorio cartaTerritorio : Arrays.asList(new CartaTerritorio("San Pietro a Piatierno Nord", new ImageIcon(getClass().getResource("/San Pietro a Patierno Nord.png")), "CANNONE"), new CartaTerritorio("San Pietro a Piatierno Sud", new ImageIcon(getClass().getResource("/San Pietro a Patierno Sud.png")), "MAFIOSO"), new CartaTerritorio("Poggioreale", new ImageIcon(getClass().getResource("/Poggioreale.png")), "MAFIOSO"))) {
            g.addCartaTerritorio(cartaTerritorio);
        }
    }


    /**
     * crea tutti i territori e li memorizza nell' arralist territori dentro Gioco g
     */
    public void creazioneTerritori(){
        ArrayList<String> confini = new ArrayList<>();
        //MUNICIPALITA 8
        confini.add("Chiaiano Est");
        confini.add("Arenella Ovest");
        g.addTerritorio(new Territorio("Chiaiano Ovest","MUNICIPALITA 8", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Chiaiano Ovest");
        confini.add("Piscinola Nord");
        confini.add("Piscinola Sud");
        confini.add("San Carlo all'Arena Ovest");
        g.addTerritorio(new Territorio("Chiaiano Est","MUNICIPALITA 8", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Chiaiano Est");
        confini.add("Scampia");
        confini.add("Piscinola Sud");
        g.addTerritorio(new Territorio("Piscinola Nord","MUNICIPALITA 8", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Chiaiano Est");
        confini.add("Scampia");
        confini.add("Piscinola Nord");
        confini.add("San Carlo all'Arena Ovest");
        g.addTerritorio(new Territorio("Piscinola Sud","MUNICIPALITA 8", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Secondigliano");
        confini.add("Piscinola Sud");
        confini.add("Piscinola Nord");
        g.addTerritorio(new Territorio("Scampia","MUNICIPALITA 8", new ArrayList<>(confini)));

        //MUNICIPALITA 5 E 9
        confini.clear();
        confini.add("Pianura Est");
        confini.add("Bagnoli Nord");
        g.addTerritorio(new Territorio("Pianura Ovest", "MUNICIPALITA 5 E 9", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Pianura Ovest");
        confini.add("Soccavo");
        confini.add("Arenella Ovest");
        g.addTerritorio(new Territorio("Pianura Est", "MUNICIPALITA 5 E 9", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Vomero Est");
        confini.add("Stella Rione Sanita");
        confini.add("Arenella Ovest");
        g.addTerritorio(new Territorio("Arenella Est", "MUNICIPALITA 5 E 9", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Arenella Est");
        confini.add("Vomero Est");
        confini.add("Soccavo");
        confini.add("Pianura Est");
        confini.add("Vomero Ovest");
        confini.add("Chiaiano Ovest");
        g.addTerritorio(new Territorio("Arenella Ovest", "MUNICIPALITA 5 E 9", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Pianura Est");
        confini.add("Arenella Ovest");
        confini.add("Vomero Ovest");
        confini.add("Bagnoli Sud");
        g.addTerritorio(new Territorio("Soccavo", "MUNICIPALITA 5 E 9", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Stella Rione Sanita");
        confini.add("Arenella Est");
        confini.add("Arenella Ovest");
        confini.add("Vomero Ovest");
        confini.add("Chiaia Est");
        g.addTerritorio(new Territorio("Vomero Est", "MUNICIPALITA 5 E 9", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Soccavo");
        confini.add("Vomero Est");
        confini.add("Arenella Ovest");
        g.addTerritorio(new Territorio("Vomero Ovest", "MUNICIPALITA 5 E 9", new ArrayList<>(confini)));

        //MUNICIPALITA 1 E 10
        confini.clear();
        confini.add("Bagnoli Sud");
        confini.add("Pianura Ovest");
        g.addTerritorio(new Territorio("Bagnoli Nord", "MUNICIPALITA 1 E 10", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Bagnoli Nord");
        confini.add("Fuorigrotta");
        confini.add("Soccavo");
        confini.add("Posillipo");
        g.addTerritorio(new Territorio("Bagnoli Sud", "MUNICIPALITA 1 E 10", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Bagnoli Sud");
        confini.add("Posillipo");
        confini.add("Chiaia Ovest");
        g.addTerritorio(new Territorio("Fuorigrotta", "MUNICIPALITA 1 E 10", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Bagnoli Sud");
        confini.add("Fuorigrotta");
        confini.add("Chiaia Ovest");
        g.addTerritorio(new Territorio("Posillipo", "MUNICIPALITA 1 E 10", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Chiaia Est");
        confini.add("Fuorigrotta");
        confini.add("Posillipo");
        g.addTerritorio(new Territorio("Chiaia Ovest", "MUNICIPALITA 1 E 10", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Vomero Est");
        confini.add("Chiaia Ovest");
        confini.add("San Ferdinando");
        g.addTerritorio(new Territorio("Chiaia Est", "MUNICIPALITA 1 E 10", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Chiaia Est");
        confini.add("Porto");
        g.addTerritorio(new Territorio("San Ferdinando", "MUNICIPALITA 1 E 10", new ArrayList<>(confini)));

        //MUNICIPALITA 2 E 4
        confini.clear();
        confini.add("Vomero Est");
        confini.add("MonteCalvario");
        confini.add("San Giuseppe");
        confini.add("Stella Rione Sanita");
        g.addTerritorio(new Territorio("Avvocata", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Avvocata");
        confini.add("San Giuseppe");
        g.addTerritorio(new Territorio("MonteCalvario", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));
        confini.clear();
        confini.add("San Lorenzo");
        confini.add("MonteCalvario");
        confini.add("Avvocata");
        confini.add("Porto");
        g.addTerritorio(new Territorio("San Giuseppe", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));
        confini.clear();
        confini.add("San Ferdinando");
        confini.add("San Lorenzo");
        confini.add("San Giuseppe");
        confini.add("Pendino");
        g.addTerritorio(new Territorio("Porto", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Porto");
        confini.add("Pendino");
        confini.add("San Giuseppe");
        confini.add("Mercato");
        confini.add("Vicaria");
        confini.add("Zona Industriale");
        g.addTerritorio(new Territorio("San Lorenzo", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Mercato");
        confini.add("San Lorenzo");
        confini.add("Porto");
        g.addTerritorio(new Territorio("Pendino", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Zona Industriale");
        confini.add("San Lorenzo");
        confini.add("Poggioreale");
        g.addTerritorio(new Territorio("Vicaria", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Pendino");
        confini.add("San Lorenzo");
        confini.add("Zona Industriale");
        g.addTerritorio(new Territorio("Mercato", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Mercato");
        confini.add("San Lorenzo");
        confini.add("Vicaria");
        confini.add("Barra Nord");
        g.addTerritorio(new Territorio("Zona Industriale", "MUNICIPALITA 2 E 4", new ArrayList<>(confini)));

        //MUNICIPALITA 6
        confini.clear();
        confini.add("Ponticelli Sud");
        confini.add("Barra Nord");
        confini.add("Poggioreale");
        confini.add("San Pietro a Piatierno Sud");
        g.addTerritorio(new Territorio("Ponticelli Nord", "MUNICIPALITA 6", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Ponticelli Nord");
        confini.add("Barra Nord");
        confini.add("Barra Sud");
        g.addTerritorio(new Territorio("Ponticelli Sud", "MUNICIPALITA 6", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Ponticelli Sud");
        confini.add("Ponticelli Nord");
        confini.add("Zona Industriale");
        confini.add("Barra Sud");
        confini.add("San Giovanni a Teduccio");
        g.addTerritorio(new Territorio("Barra Nord", "MUNICIPALITA 6", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Ponticelli Sud");
        confini.add("Barra Nord");
        confini.add("San Giovanni a Teduccio");
        g.addTerritorio(new Territorio("Barra Sud", "MUNICIPALITA 6", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Porto");
        confini.add("Barra Nord");
        confini.add("Barra Sud");
        g.addTerritorio(new Territorio("San Giovanni a Teduccio", "MUNICIPALITA 6", new ArrayList<>(confini)));

        //MUNICIPALITA 3 E 7
        confini.clear();
        confini.add("Piscinola Sud");
        confini.add("Chiaiano Est");
        confini.add("Stella Rione Sanita");
        confini.add("San Carlo all'Arena Centro");
        confini.add("Miano");
        g.addTerritorio(new Territorio("San Carlo all'Arena Ovest", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
        confini.clear();
        confini.add("San Carlo all'Arena Est");
        confini.add("Chiaiano Est");
        confini.add("Stella Rione Sanita");
        confini.add("San Carlo all'Arena Ovest");
        g.addTerritorio(new Territorio("San Carlo all'Arena Centro", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
        confini.clear();
        confini.add("San Carlo all'Arena Centro");
        confini.add("Avvocata");
        confini.add("Arenella Est");
        confini.add("San Carlo all'Arena Ovest");
        g.addTerritorio(new Territorio("Stella Rione Sanita", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
        confini.clear();
        confini.add("San Carlo all'Arena Centro");
        confini.add("Secondigliano");
        confini.add("Miano");
        confini.add("San Pietro a Piatierno Nord");
        confini.add("San Pietro a Piatierno Sud");
        confini.add("Poggioreale");
        g.addTerritorio(new Territorio("San Carlo all'Arena Est", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
        confini.clear();
        confini.add("San Carlo all'Arena Centro");
        confini.add("San Carlo all'Arena Ovest");
        confini.add("San Carlo all'Arena Est");
        confini.add("Secondigliano");
        g.addTerritorio(new Territorio("Miano", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Scampia");
        confini.add("San Pietro a Piatierno Nord");
        confini.add("San Carlo all'Arena Est");
        confini.add("Miano");
        g.addTerritorio(new Territorio("Secondigliano", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Secondigliano");
        confini.add("San Pietro a Piatierno Sud");
        confini.add("San Carlo all'Arena Est");
        g.addTerritorio(new Territorio("San Pietro a Piatierno Nord", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Ponticelli Nord");
        confini.add("San Pietro a Piatierno Nord");
        confini.add("San Carlo all'Arena Est");
        confini.add("Poggioreale");
        g.addTerritorio(new Territorio("San Pietro a Piatierno Sud", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
        confini.clear();
        confini.add("Ponticelli Nord");
        confini.add("San Pietro a Piatierno Sud");
        confini.add("San Carlo all'Arena Est");
        confini.add("Vicaria");
        g.addTerritorio(new Territorio("Poggioreale", "MUNICIPALITA 3 E 7", new ArrayList<>(confini)));
    }

    /**
     * crea tutte le carte obbiettivo e le memorizza nell' arraylist carteTerritorio dentro Gioco g
     */
    public void creazioneObbiettivi(){
        g.addObbiettivo(new Obbiettivo1());
        g.addObbiettivo(new Obbiettivo2());
        g.addObbiettivo(new Obbiettivo3());
        g.addObbiettivo(new Obbiettivo4());
        g.addObbiettivo(new Obbiettivo5());
        g.addObbiettivo(new Obbiettivo6());
        g.addObbiettivo(new Obbiettivo7());
        g.addObbiettivo(new Obbiettivo8());
        g.addObbiettivo(new Obbiettivo9());
        g.addObbiettivo(new Obbiettivo10());
        g.addObbiettivo(new Obbiettivo11());
        g.addObbiettivo(new Obbiettivo12());
        g.addObbiettivo(new Obbiettivo13());
    }

    /**
     * chiamata quando qualcuno vince, chiama l'interfaccia per mostrare chi ha vinto e pone il flag endGame a true
     */
    public void endGame(){
        i.showVictory();
        endGame = true;
    }


    /**
     * chiamata quando l' utente termina un turno e ha conquistato almeno un territorio
     * fa pescare una carta territorio all' utente per i power up
     * @return cartaterritorio randomica
     */
    public CartaTerritorio endTurn(){
        Collections.shuffle(g.getCarteTerritorio());
        Random rand = new Random();
        int num = rand.nextInt(g.getCarteTerritorio().size());
        if(!guiB){
            i.showGreenMessage("\nFine turno, avendo conquistato territori peschi una carta territorio");
            i.showMessage("Carta pescata: " + g.getCarteTerritorio().get(num).toStringPowerUp());
        }
        else{
            //chiamare funzione che fa vedere carta pescata in jdialog chiudibile
            frame.showCartaPescata(g.getCarteTerritorio().get(num));
        }
        return g.getCarteTerritorio().get(num);
    }

    /**
     * chiamata ogni volta che un utente inizia ad attaccare
     * ritorna l'arraylist con tutti i territori attaccabili dall' utente corrente, ovvero i vicini dei suoi territori sui quali ha più di un caroarmato
     * @return arraylist di stringhe di nomi territori disponibili da attaccare
     */
    public ArrayList<String> attacchiDisponibili(){
        ArrayList<String> attaccabili = new ArrayList<>();

        for (Territorio t : g.getCurrentPlayer().getTerritori()) {
            if(t.getCarriArmati() > 1)
                attaccabili.addAll(t.getConfini());
        }

        ArrayList<String> copia = attaccabili.stream().distinct().collect(Collectors.toCollection(ArrayList::new));

        Iterator<String> it = copia.iterator();
        while (it.hasNext()) {
            String territorio = it.next();
            for (Territorio t : g.getCurrentPlayer().getTerritori()) {
                if (t.getNome().equals(territorio)) {
                    it.remove();
                    break;
                }
            }
        }
        return copia;
    }


    /**
     * chiamata quando l'attaccante deve scegliere da quale territorio attaccare
     * restituisce l'arraylist con tutti i territori dell' attaccante da cui può attaccare il territorio passato come stringa nei parametri
     * @param difensore, territorio che si vuole attaccare
     * @return arraylist territori dai quali si puo attaccare
     */
    public ArrayList<Territorio> territoriAttaccanti(String difensore){
        ArrayList<Territorio> attaccanti = new ArrayList<>();

        for(Territorio t: g.getCurrentPlayer().getTerritori()){
            for(String s: t.getConfini()){
                if(s.equals(difensore) && t.getCarriArmati() > 1){
                    attaccanti.add(t);
                }
            }
        }
        return attaccanti;
    }

    /**
     * controlla la validita del territorio in input
     * @param attaccante stringa nome territorio
     * @return 0 se territorio non trovato, numero di carri del territorio se trovato
     */
    public int findTerritorioAttaccante(String attaccante){
        for(Territorio t: g.getCurrentPlayer().getTerritori()){
            if(t.getNome().equals(attaccante)){
                g.setTerritorioAttaccante(t);
                return t.getCarriArmati();
            }
        }

        i.showError("Territorio non valido");
        return 0;
    }

    /**
     * genera un numero random da 1 a 6 inclusi per un dado
     * @param dif boolean che controlla se sta difendendo o attaccando
     * @return numero intero random da 1 a 6
     */
    public int tiraDadi(boolean dif){
        Random rand = new Random();
        int num = rand.nextInt(6) + 1;
        if(!guiB){
            //CLI
            if(dif)
                i.showMessage("Giocatore " + g.getGiocatoreDifendente().getNickname() + " ha tirato " + num);
            else
                i.showMessage("Giocatore " + g.getCurrentPlayer().getNickname() + " ha tirato " + num);
        }

        return num;
    }

    /**
     * controlla i valori dei dadi tirati da attaccante e difensore
     * e stabilisce le sconfitte e le vittorie e l'eventuale conquista di territori
     * @param difesaR dadi della difesa
     * @param attaccoR dadi dell'attacco
     */
    public void esitoAttacchi(ArrayList<Integer> difesaR, ArrayList<Integer> attaccoR){
        int numI = attaccoR.size();
        int def = difesaR.size();
        int att = attaccoR.size();
        do {
            int maxD = 0, maxA = 0;
            if(!difesaR.isEmpty()) {
                for (int num : difesaR) {
                    if (maxD < num) {
                        maxD = num;
                    }
                }
                difesaR.remove((Integer) maxD);
                def--;
            }

            if(!attaccoR.isEmpty()) {
                for (int num : attaccoR) {
                    if (maxA < num) {
                        maxA = num;
                    }
                }
                attaccoR.remove((Integer) maxA);
                att--;
            }

            if (maxA > maxD) {
                //if(!guiB)
                i.showMessage(g.getCurrentPlayer().getNickname() + " vince un attacco contro " + g.getGiocatoreDifendente().getNickname());

                g.getTerritorioSottoAttacco().togliCarri(1);
                if(g.getTerritorioSottoAttacco().getCarriArmati() == 0){
                    //ha conquistato il territorio, prende il dominio e aggiunge carri
                    hasConquered = true;
                    g.getTerritorioSottoAttacco().setGiocatore(g.getCurrentPlayer());
                    g.getCurrentPlayer().addTerritorio(g.getTerritorioSottoAttacco());
                    g.getGiocatoreDifendente().removeTerritorio(g.getTerritorioSottoAttacco());
                    g.getTerritorioSottoAttacco().addCarri(numI);
                    g.getTerritorioAttaccante().togliCarri(numI);
                    difesaR.clear();
                    attaccoR.clear();

                    if(!guiB){
                        i.showMessage(g.getTerritorioSottoAttacco() + " è stato conquistato da " + g.getCurrentPlayer().getNickname());
                        i.showMessage((numI) + " armate si spostano da " + g.getTerritorioAttaccante() + " a " + g.getTerritorioSottoAttacco());
                    }
                    else{
                        i.showMessage(g.getTerritorioSottoAttacco() + " è stato conquistato da " + g.getCurrentPlayer().getNickname() + "\n" + (numI) + " armate si spostano da " + g.getTerritorioAttaccante() + " a " + g.getTerritorioSottoAttacco());
                    }

                }
                else if(!guiB){
                    i.showMessage("Non sei riuscito a conquistare");
                }
            }
            else{
                //if(!guiB)
                i.showMessage(g.getGiocatoreDifendente().getNickname() + " vince difendendosi contro " + g.getCurrentPlayer().getNickname());

                g.getTerritorioAttaccante().togliCarri(1);
            }

        }while(!difesaR.isEmpty() && !attaccoR.isEmpty());
    }

    /**
     * permette di tirare i dadi in base al numero di armate e dopo chiama l'esito
     * @param def numero di dadi della difesa
     * @param att numero di dadi dell'attacco
     */
    public void difendiAndattacca(int def, int att){
        ArrayList<Integer> difesaR = new ArrayList<>();
        ArrayList<Integer> attaccoR = new ArrayList<>();
        switch(def){
            case 1:
                difesaR.add(tiraDadi(true));
                break;

            case 2:
                difesaR.add(tiraDadi(true));
                difesaR.add(tiraDadi(true));
                break;

            case 3:
                difesaR.add(tiraDadi(true));
                difesaR.add(tiraDadi(true));
                difesaR.add(tiraDadi(true));
                break;
        }

        switch(att){
            case 1:
                attaccoR.add(tiraDadi(false));
                break;

            case 2:
                attaccoR.add(tiraDadi(false));
                attaccoR.add(tiraDadi(false));
                break;

            case 3:
                attaccoR.add(tiraDadi(false));
                attaccoR.add(tiraDadi(false));
                attaccoR.add(tiraDadi(false));
                break;
        }

        esitoAttacchi(difesaR, attaccoR);
    }


    /**
     * controlla la validità dell'attacco
     * @param attaccante nome territorio attaccante
     * @param carri numero carri con cui si svolge l'attacco
     * @return vero se può attaccare, falso se non può
     */
    public boolean canAttack(String attaccante, int carri){
        boolean arm = false;
        Giocatore temp = g.getCurrentPlayer();

        for(Territorio t: temp.getTerritori()){
            if(attaccante.equals(t.getNome())){
                g.setTerritorioAttaccante(t);
                if(carri >= 1 && carri < t.getCarriArmati() && t.getCarriArmati() > 1 && carri < 4){
                    arm = true;
                    break;
                }
            }
        }

        if(arm) {
            return true;
        }
        else{
            i.showError("Numero armate non valido");
            return false;
        }
    }

    /**
     * getter del gioco
     * @return Gioco
     */
    public Gioco getGioco(){
        return g;
    }

    /**
     * controlla se qualcuno possiede una municipalità e in caso affermativo mostra a schermo il messaggio con le armate corrispondenti
     * @return numero di carri disponibili
     */
    public int getCarriTurn(){
        Giocatore p = g.getCurrentPlayer();
        int var = p.getTerritori().size()/3;
        int m8 = 0, m5 = 0, m1 = 0, m2 = 0, m6 = 0, m3 = 0;

        for(Territorio t: p.getTerritori()){
            String string = t.getMunicipalita();
            switch (string) {
                case "MUNICIPALITA 8" -> m8++;
                case "MUNICIPALITA 5 E 9" -> m5++;
                case "MUNICIPALITA 1 E 10" -> m1++;
                case "MUNICIPALITA 2 E 4" -> m2++;
                case "MUNICIPALITA 6" -> m6++;
                case "MUNICIPALITA 3 E 7" -> m3++;
            }
        }

        //CARRI SE POSSIEDE INTERE MUNICIPALITA
        if(m8 == 5){
            i.showMessage("Guadagni 2 armate perchè possiedi l'intera Municipalità 8");
            var += 2;
        }
        if(m5 == 7){
            i.showMessage("Guadagni 5 armate perchè possiedi l'intera Municipalità 5 e 9");
            var += 5;
        }
        if(m1 == 7){
            i.showMessage("Guadagni 3 armate perchè possiedi l'intera Municipalità 1 e 10");
            var += 3;
        }
        if(m2 == 9){
            i.showMessage("Guadagni 5 armate perchè possiedi l'intera Municipalità 2 e 4");
            var += 5;
        }
        if(m6 == 5){
            i.showMessage("Guadagni 2 armate perchè possiedi l'intera Municipalità 6");
            var += 2;
        }
        if(m3 == 9){
            i.showMessage("Guadagni 7 armate perchè possiedi l'intera Municipalità 3 e 7");
            var += 7;
        }

        //CARRI PER POWER UPS
        int can3 = 0, mot3 = 0, maf3 = 0;
        int maf = 0, mot = 0, can = 0;
        for(CartaTerritorio t: p.getCarte()){
            switch(t.getPowerUp()){
                case "MAFIOSO":
                    maf3++;
                    if(maf == 0)
                        maf++;
                    break;
                case "MOTORINO":
                    mot3++;
                    if(mot == 0)
                        mot++;
                    break;
                case "CANNONE":
                    can3++;
                    if(can == 0)
                        can++;
                    break;
            }
        }

        ArrayList<CartaTerritorio> cards = new ArrayList<>(p.getCarte());
        if(maf3 == 3){
            //6 armate
            for(CartaTerritorio t: cards){
                if(t.getPowerUp().equals("MAFIOSO")){
                    p.getCarte().remove(t);
                }
            }
            i.showMessage("Usi 3 carte pescate e ottieni un bonus di 6 carri armati extra");
            var += 6;
        }
        if(can3 == 3){
            //3 armate
            for(CartaTerritorio t: cards){
                if(t.getPowerUp().equals("CANNONE")){
                    p.getCarte().remove(t);
                }
            }
            i.showMessage("Usi 3 carte pescate e ottieni un bonus di 3 carri armati extra");
            var += 3;
        }
        if(mot3 == 3){
            //8 armate
            for(CartaTerritorio t: cards){
                if(t.getPowerUp().equals("MOTORINO")){
                    p.getCarte().remove(t);
                }
            }
            i.showMessage("Usi 3 carte pescate e ottieni un bonus di 8 carri armati extra");
            var += 8;
        }
        if(maf == 1 && mot == 1 && can == 1){
            //10 armate
            boolean cannone = false, mafioso = false, motorino = false;
            for(CartaTerritorio t: cards){
                if(t.getPowerUp().equals("CANNONE") && !cannone){
                    p.getCarte().remove(t);
                    cannone = true;
                }
                if(t.getPowerUp().equals("MAFIOSO") && !mafioso){
                    p.getCarte().remove(t);
                    mafioso = true;
                }
                if(t.getPowerUp().equals("MOTORINO") && !motorino){
                    p.getCarte().remove(t);
                    motorino = true;
                }
            }
            i.showMessage("Usi 3 carte pescate e ottieni un bonus di 10 carri armati extra");
            var += 10;
        }

        return var;
    }

    /**
     * salvataggio serializzato della partita in corso
     */
    public void salvaPartita(){
        g.setFrame(frame);
        File f = new File("salvataggio.ser");
        try {
            FileOutputStream fo = new FileOutputStream(f);
            ObjectOutputStream o = new ObjectOutputStream(fo);
            o.writeObject(g);
            o.flush();
            o.close();
        } catch (IOException e) {
            System.out.println("\033[0;31m" + "ERRORE NELLA SERIALIZZAZIONE DEL FILE" + "\033[0m");
        }
    }

    /**
     * caricamento del salvataggio serializzato della partita in corso
     */
    public void caricaPartita(){
        File f = new File("salvataggio.ser");
        try {
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream i = new ObjectInputStream(fi);
            g = (Gioco) i.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\033[0;31m" + "ERRORE NELLA LETTURA DEL FILE" + "\033[0m");
        }

        String state = g.getGameState();
        frame = g.getFrame();
        frame.setControllore(this);
        i.setFrame(frame);
        frame.setGioco(g);
        frame.Mappa();
        switch(state){
            case "start", "turnigame":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        game(true);
                    }
                }).start();
                break;
        }
    }
}