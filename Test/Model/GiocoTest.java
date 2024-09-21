package Model;

import Controllore.Controllore;
import Model.Carte.CartaObbiettivo;
import Model.Carte.CartaTerritorio;
import Model.Carte.Obbiettivi.Obbiettivo2;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GiocoTest {

    Controllore controllore=new Controllore(1);
    int numGiocatori=4; // da testare com 3,4,5,6 che sono il numero possibile dei giocatori


    @org.junit.jupiter.api.Test
    void assegnaTerritori() {
        /*
            il test controlla se in base al numero di giocatori memorizzati gli assegna il numero giusto di territori e di carte territorio, perchè quella funzione assegna agli arreylist ancora vuoti tutti i territori e tutte le carteTerritorio
            il numero dei giocatori è variabile, lo switch controllo tutti i possibili casi (nella variabile numGiocatori), 3,4,5,6 i possibili casi
        */

        //assegnazione dei giocatori in lista giocatori
        for (int i=0;i<numGiocatori;i++){  //assegnazione all'arraylist di numGiocatori giocatori
            controllore.getGioco().getListaGiocatori().add(new Giocatore("Giocatore : "+i,"Giallo"));
        }

        //creazione delle cpse che servono per chiamare la funzione da testare
        controllore.creazioneCarteTerritorio();//prima deve creare le carte territorio
        controllore.creazioneTerritori();// e i territori

        //chiama la funzione da testare
        controllore.getGioco().assegnaTerritori();//la chiama perche i territori e le carte territorio sono state memorizzate dentro controllore


        //switch che controlla tutte le possibilita di grandezza di arraylist giocatori
        switch (controllore.getGioco().getListaGiocatori().size()){ //numero di giocatori
            case 3: //14 territori a testa
                //controlla tutte le size dell arraylist territori e carte di giocatore con assertequals
                //controllo dei territori
                for (int i=0;i<3;i++){
                    assertEquals(14,controllore.getGioco().getListaGiocatori().get(i).getTerritori().size()); //confronta la grandezza dell arraylist dei territori con il numero che mi aspetto in base al numero di giocatori
                }
                //controllo delle carte territorio
                for (int i=0;i<3;i++){
                    assertEquals(14,controllore.getGioco().getListaGiocatori().get(i).getCarte().size()); //confronta la grandezza dell arraylist delle carteTerritorio con il numero che mi aspetto in base al numero di giocatori
                }
                break;
            case 4:
                //controllo dei territori
                for (int i=0;i<2;i++){
                    assertEquals(11,controllore.getGioco().getListaGiocatori().get(i).getTerritori().size()); //ai primi 2 giocatori mi aspetto 11 territori
                }
                for (int i=2;i<4;i++){
                    assertEquals(10,controllore.getGioco().getListaGiocatori().get(i).getTerritori().size()); //agli atri 2 giocatori mi aspetto 10 territori
                }
                //controllo delle carte territorio
                for (int i=0;i<2;i++){
                    assertEquals(11,controllore.getGioco().getListaGiocatori().get(i).getCarte().size()); //ai primi 2 giocatori mi aspetto 11 carteTerritorio
                }
                for (int i=2;i<4;i++){
                    assertEquals(10,controllore.getGioco().getListaGiocatori().get(i).getCarte().size()); //agli atri 2 giocatori mi aspetto 10 carteTerritorio
                }
                break;
            case 5:
                //controllo dei territori
                for (int i=0;i<2;i++){
                    assertEquals(9,controllore.getGioco().getListaGiocatori().get(i).getTerritori().size()); //ai primi 2 giocatori mi aspetto 9 territori
                }
                for (int i=2;i<5;i++){
                    assertEquals(8,controllore.getGioco().getListaGiocatori().get(i).getTerritori().size()); //agli atri 3 giocatori mi aspetto 8 territori
                }
                //controllo delle carte territorio
                for (int i=0;i<2;i++){
                    assertEquals(9,controllore.getGioco().getListaGiocatori().get(i).getCarte().size()); //ai primi 2 giocatori mi aspetto 9 carteTerritorio
                }
                for (int i=2;i<5;i++){
                    assertEquals(8,controllore.getGioco().getListaGiocatori().get(i).getCarte().size()); //agli atri 3 giocatori mi aspetto 8 carteTerritorio
                }
                break;
            case 6:
                //controllo dei territori
                for (int i=0;i<6;i++){
                    assertEquals(7,controllore.getGioco().getListaGiocatori().get(i).getTerritori().size()); //si aspetta 7 territori per tutti i giocatori
                }
                //controllo delle carte territorio
                for (int i=0;i<6;i++){
                    assertEquals(7,controllore.getGioco().getListaGiocatori().get(i).getCarte().size()); //si aspetta 7 carteTerritorio per tutti i giocatori
                }
                break;
        }

        //test per vedere se carteterritorio si suota dentro gioco (carteTerritorio.clear()
        assertEquals(0,controllore.getGioco().getCarteTerritorio().size());
    }

    @org.junit.jupiter.api.Test
    void assegnaArmate() {

        /*
        il test controlla che venga assegnato il numero corretto di carriarmati ai giocatori
         */

        //assegnazione dei giocatori in lista giocatori
        for (int i=0;i<numGiocatori;i++){  //assegnazione all'arraylist di numGiocatori giocatori
            controllore.getGioco().getListaGiocatori().add(new Giocatore("Giocatore : "+i,"Giallo"));
        }

        //chiama la funzione da testare
        controllore.getGioco().assegnaArmate();

        //fa i test per tutti i casi possibili
        switch (controllore.getGioco().getListaGiocatori().size()){
            case 3: //3 giocatori
                for(int i = 0; i < 3; i++){ //fa passare i 3 giocatori
                    assertEquals(35,controllore.getGioco().getListaGiocatori().get(i).getCarri());//si aspetta che ogni giocatore abbia 35 armate disponibili
                }
                break;
            case 4:
                for(int i = 0; i < 4; i++){//fa passare i 4 giocatori
                    assertEquals(30,controllore.getGioco().getListaGiocatori().get(i).getCarri());//si aspetta che ogni giocatore abbia 30 armate disponibili
                }
                break;
            case 5:
                for(int i = 0; i < 5; i++){ //fa passare i 5 giocatori
                    assertEquals(25,controllore.getGioco().getListaGiocatori().get(i).getCarri());//si aspetta che ogni giocatore abbia 25 armate disponibili
                }
                break;
            case 6:
                for(int i = 0; i < 6; i++){//fa passare i 6 giocatori
                    assertEquals(20,controllore.getGioco().getListaGiocatori().get(i).getCarri());//si aspetta che ogni giocatore abbia 20 armate disponibili
                }
                break;
        }
    }

    @org.junit.jupiter.api.Test
    void assegnaObbiettivi() {
        /*
        testa se sono stati tolti correttamente gli obbiettivi già assegnati
         */

        //assegnazione dei giocatori in lista giocatori
        for (int i=0;i<numGiocatori;i++){  //assegnazione all'arraylist di numGiocatori giocatori
            controllore.getGioco().getListaGiocatori().add(new Giocatore("Giocatore : "+i,"Giallo"));
        }

        //chiama la funzione da testare
        controllore.creazioneObbiettivi();
        controllore.getGioco().assegnaObbiettivi();

        assertEquals(13-controllore.getGioco().getListaGiocatori().size(),controllore.getGioco().getCarteObbiettivo().size()); // si aspetta che ci siano tot obbiettivi in meno in base a quanti giocatori ci sono e quindi a quanti obbiettivi sono stati assegnati

    }

    @Test
    void disponiarmate() {
        /*
        testa se su ogni territorio cè un caroarmato e se ad ogni giocatore sono rimasti il numero giusto di carriarmati
         */

        //assegnazione dei giocatori in lista giocatori
        for (int i=0;i<numGiocatori;i++){  //assegnazione all'arraylist di numGiocatori giocatori
            controllore.getGioco().getListaGiocatori().add(new Giocatore("Giocatore : "+i,"Giallo"));
        }

        //creazione delle cose che servono per testare
        controllore.creazioneTerritori();
        controllore.getGioco().assegnaTerritori();
        controllore.getGioco().assegnaArmate();

        int carriperGiocatore=controllore.getGioco().getListaGiocatori().get(0).getCarri();//memorizza il numero di armate iniziali disponibili per l utente
        ArrayList <Integer> numTerritori=new ArrayList<>();
        for (Giocatore g:controllore.getGioco().getListaGiocatori()){
            numTerritori.add(g.getTerritori().size());
        }


        //chiama la funzione da testare
        controllore.getGioco().disponiarmate();


        for(Giocatore g: controllore.getGioco().getListaGiocatori()){   //fa passare i giocatori
            for(Territorio t: g.getTerritori()){    //fa passare tutti i territori
                assertEquals(1,t.getCarriArmati());//si aspetta che su ogni territorio ci sia un carroarmato
            }
        }
        int i=0;
        //controlla che ad ogni giocatore sia rimasto il numero corrispindente di carri disponibili
        for(Giocatore g: controllore.getGioco().getListaGiocatori()){   //fa passare i giocatori
            assertEquals(carriperGiocatore- numTerritori.get(i),g.getCarri()); //si aspetta il numero dei carri iniziale meno il numero dei suoi territori perchè ci e stato messo un carroarmato per territorio
            i++;
        }

    }

    @Test
    void addGiocatore() {
        /*
        testo se è stato aggiunto il giocatore con il nome e il colore desiderato dentro larraylist listagiocatori
         */

        //creo il giocatore che voglio aggiungere all arraylist
        Giocatore giocatore=new Giocatore("orsey","Nero");

        //chiamo la funzione che voglio testare passandogli il giocatore che voglio aggiungergli
        controllore.getGioco().addGiocatore(giocatore);

        //verifico se quello che mi aspetto, ovvero il nome e il colore del giocatore che ho creato sono stati memorizzatti correttamente all'interno dell'array
        //controllo la posizione 0 perchè l'arraylist è vuoto e se la funzione lo aggiunge correttamente una volta allora lo aggiunge correttamente sempre
        assertEquals(giocatore,controllore.getGioco().getListaGiocatori().get(0));
    }

    @Test
    void addCartaTerritorio() {
        /*
        testo se è stata aggiunta la cartaTerritorio con il i parametri desiderati dentro l'arraylist carteTerritorio
         */

        //creo la cartaterritorio che voglio aggiungere all arraylist
        CartaTerritorio cartaTerritorio=new CartaTerritorio("Ardenno",new ImageIcon(),"re del java");

        //chiamo la funzione che voglio testare passandogli la cartaTerritorio che voglio aggiungere all'arraylist
        controllore.getGioco().addCartaTerritorio(cartaTerritorio);

        //verifico se quello che mi aspetto, ovvero quello che ho salvato dentro cartaTerritorio è stato memorizzato correttamente all'interno dell'array
        //controllo la posizione 0 perchè l'arraylist è vuoto e se la funzione lo aggiunge correttamente una volta allora lo aggiunge correttamente sempre
        assertEquals(cartaTerritorio,controllore.getGioco().getCarteTerritorio().get(0));
    }

    @Test
    void addTerritorio() {
        /*
        testo se è stato aggiunto il territorio desiderato all'arraylist territori
         */

        //creo il territorio che voglio aggiungere all arraylist
        Territorio territorio=new Territorio("Ardenno","City",new ArrayList<>());

        //chiamo la funzione che voglio testare passandogli il territorio che voglio aggiungergli
        controllore.getGioco().addTerritorio(territorio);

        //verifico se quello che mi aspetto, ovvero il territorio che ho creato sono stati memorizzatti correttamente all'interno dell'array
        //controllo la posizione 0 perchè l'arraylist è vuoto e se la funzione lo aggiunge correttamente una volta allora lo aggiunge correttamente sempre
        assertEquals(territorio,controllore.getGioco().getTerritori().get(0));
    }

    @Test
    void addObbiettivo() {
        /*
        testo se è stato aggiunto il'obbiettivo desiderato
         */

        //creo l'obbiettivo che voglio aggiungere all arraylist
        Obbiettivo2 obbiettivo2=new Obbiettivo2();

        //chiamo la funzione che voglio testare passandogli l'obbiettico che voglio aggiungergli
        controllore.getGioco().addObbiettivo(obbiettivo2);

        //verifico se quello che mi aspetto, l'obbiettivo che ho creato sono stati memorizzatti correttamente all'interno dell'array
        //controllo la posizione 0 perchè l'arraylist è vuoto e se la funzione lo aggiunge correttamente una volta allora lo aggiunge correttamente sempre
        assertEquals(obbiettivo2,controllore.getGioco().getCarteObbiettivo().get(0));
    }

    @Test
    void piazzaCarri() {
        /*
        testo se ritorna il boolean giusto in base al numero di caroarmati che gli passo, da 1 a 3
        e se ritorna true testo che abbia aggiunto il numero corretto di carri e che li abbia tolti dal numero dei carridisponibili dell utente
         */

        //assegnazione dei giocatori in lista giocatori
        for (int i=0;i<numGiocatori;i++){  //assegnazione all'arraylist di numGiocatori giocatori
            controllore.getGioco().getListaGiocatori().add(new Giocatore("Giocatore : "+i,"Giallo"));
        }

        //crea le cose necessarie
        controllore.creazioneTerritori();

        Territorio territorio=new Territorio("Secondigliano", "MUNICIPALITA 3 E 7", new ArrayList<>());
        controllore.getGioco().getListaGiocatori().get(0).addTerritorio(territorio);
        controllore.getGioco().assegnaArmate();//mette 1 armata sul territorio
        controllore.getGioco().disponiarmate(); //mettera 1 armata su secondigliano
        controllore.getGioco().setCurrentPlayer(controllore.getGioco().getListaGiocatori().get(0));
        //System.out.println(controllore.getGioco().getCurrentPlayer().getTerritori().get(0).getCarriArmati());

        //crea le variabili del carro e del territorio al quale voglio aggiungerlo
        int carriinizialiDisponibili=controllore.getGioco().getCurrentPlayer().getCarri();
        int carriInizialinelTerritorio=controllore.getGioco().getCurrentPlayer().getTerritori().get(0).getCarriArmati();//memprizza i carriarmati iniziali

        //variabile per decidere quanti carri mettere sul territorio
        int carri=1; //mettere un valore diverso da 1,2,3
        String t="Secondigliano";

        //chiama la funzione da testare controllando il valore di risposta in base al numero di carri passato
        assertTrue(controllore.getGioco().piazzaCarri(carri,t)); //con 3,2,1 carri testerà giusto, con altri numeri no
        // assertFalse(controllore.getGioco().piazzaCarri(carri,t)); //con tutti i numeri tranne 3,2,1 testerà giusto



        //da fare solo se ritorna trua, ovvero se in carri ho 1,2,3
        //controlla che siano stati aggiunti correttamente i carri
        assertEquals(carriInizialinelTerritorio+carri,controllore.getGioco().getCurrentPlayer().getTerritori().get(0).getCarriArmati());//si aspetta quelli iniziali piu quelli che passa alla funzione per aggiungerli
        //stampa il numero di carri adesso in quel territorio dell utente
        //System.out.println(controllore.getGioco().getCurrentPlayer().getTerritori().get(0).getCarriArmati());

        //controlla che siano stati tolti correttamente i carri dal giocatore corrente
        assertEquals(carriinizialiDisponibili-carri,controllore.getGioco().getCurrentPlayer().getCarri());//si aspetta quelli iniziali piu quelli che passa alla funzione per aggiungerli
        //stampa il numero di carri adesso disponibili per l'utente
        //System.out.println(controllore.getGioco().getCurrentPlayer().getCarri());

    }

    @Test
    void piazzaCarriTot() {
        /*
        come la funzione orecedente ma non ha limiti sul numero di carri da poter aggiungere
        testo che abbia aggiunto il numero corretto di carri sul territorio e che li abbia tolti dal numero dei carridisponibili dell utente
         */

        //assegnazione dei giocatori in lista giocatori
        for (int i=0;i<numGiocatori;i++){  //assegnazione all'arraylist di numGiocatori giocatori
            controllore.getGioco().getListaGiocatori().add(new Giocatore("Giocatore : "+i,"Giallo"));
        }

        //crea le cose necessarie
        controllore.creazioneTerritori();

        Territorio territorio=new Territorio("Secondigliano", "MUNICIPALITA 3 E 7", new ArrayList<>());
        controllore.getGioco().getListaGiocatori().get(0).addTerritorio(territorio);
        controllore.getGioco().assegnaArmate();//mette 1 armata sul territorio
        controllore.getGioco().disponiarmate(); //mettera 1 armata su secondigliano
        controllore.getGioco().setCurrentPlayer(controllore.getGioco().getListaGiocatori().get(0));
        //System.out.println(controllore.getGioco().getCurrentPlayer().getTerritori().get(0).getCarriArmati());

        //crea le variabili del carro e del territorio al quale voglio aggiungerlo
        int carriinizialiDisponibili=controllore.getGioco().getCurrentPlayer().getCarri();
        int carriInizialinelTerritorio=controllore.getGioco().getCurrentPlayer().getTerritori().get(0).getCarriArmati();//memprizza i carriarmati iniziali

        //variabile per decidere quanti carri mettere sul territorio
        int carri=7; //mettere un valore diverso da 1,2,3
        String t="Secondigliano";

        //chiama la funzione da testare
        controllore.getGioco().piazzaCarriTot(carri,t);

        //controlla che siano stati aggiunti correttamente i carri
        assertEquals(carriInizialinelTerritorio+carri,controllore.getGioco().getCurrentPlayer().getTerritori().get(0).getCarriArmati());//si aspetta quelli iniziali piu quelli che passa alla funzione per aggiungerli
        //stampa il numero di carri adesso in quel territorio dell utente
        // System.out.println(controllore.getGioco().getCurrentPlayer().getTerritori().get(0).getCarriArmati());

        //controlla che siano stati tolti correttamente i carri dal giocatore corrente
        assertEquals(carriinizialiDisponibili-carri,controllore.getGioco().getCurrentPlayer().getCarri());//si aspetta quelli iniziali piu quelli che passa alla funzione per aggiungerli
        //stampa il numero di carri adesso disponibili per l'utente
        // System.out.println(controllore.getGioco().getCurrentPlayer().getCarri());
    }

    @Test
    void setCurrentPlayer() {
        /*
        testa se è stato settato correttamente il giocatore corrente
         */
        //crea il giocatore che voglio settare come giocatore corrente
        Giocatore giocatore=new Giocatore("orsey","Bianco");

        //chiamo la funzione che voglio testare
        controllore.getGioco().setCurrentPlayer(giocatore);
        //testa se il giocatore corrente memorizzato dentro gioco corrisponde a quello che volevo io
        assertEquals(giocatore,controllore.getGioco().getCurrentPlayer());
    }

    @Test
    void setTerritorioAttaccante() {
        /*
        testa se è stato settato correttamente il territorio attaccante
         */
        //crea il territorio che voglio settare come territorio attaccante
        Territorio territorio=new Territorio("Ardenno","City",new ArrayList<>());

        //chiamo la funzione che voglio testare
        controllore.getGioco().setTerritorioAttaccante(territorio);
        //testa se il territorioAttaccante memorizzato dentro gioco corrisponde a quello che volevo io
        assertEquals(territorio,controllore.getGioco().getTerritorioAttaccante());
    }

    @Test
    void setTerritorioSottoAttacco() {
        /*
        testa se è stato settato correttamente il territorioSottoAttacco
         */
        //crea il territorio che voglio settare come territorioSottoAttacco
        Territorio territorio=new Territorio("Ardenno","City",new ArrayList<>());

        //chiamo la funzione che voglio testare
        controllore.getGioco().setTerritorioSottoAttacco(territorio);
        //testa se il territorioSottoAttacco memorizzato dentro gioco corrisponde a quello che volevo io
        assertEquals(territorio,controllore.getGioco().getTerritorioSottoAttacco());
    }

    @Test
    void setGiocatoreDifendente() {
        /*
        testa se è stato settato correttamente il giocatoreDifendente
         */
        //crea il giocatore che voglio settare come giocatoreDifendente
        Giocatore giocatore=new Giocatore("orsey","Bianco");

        //chiamo la funzione che voglio testare
        controllore.getGioco().setGiocatoreDifendente(giocatore);
        //testa se il giocatoreDifendente memorizzato dentro gioco corrisponde a quello che volevo io
        assertEquals(giocatore,controllore.getGioco().getGiocatoreDifendente());
    }


    @Test
    void getCurrentPlayer() {
        /*
        testo se prende il giocatoreCorrente giusto, per farlo lo setto prima con un giocatore che creo io e poi faccio il test per vedere se corrispondono
         */

        //creo e setto a giocatoreCorrente un giocatore che scelgo io
        Giocatore giocatore=new Giocatore("andriei","fucsia");
        controllore.getGioco().setCurrentPlayer(giocatore);

        //chiamo e testo la funzione che voglio testare
        assertEquals(giocatore,controllore.getGioco().getCurrentPlayer());
    }

    @Test
    void getGiocatoreDifendente() {
        /*
        testo se prende il giocatoreDifendente giusto, per farlo lo setto prima con un giocatore che creo io e poi faccio il test per vedere se corrispondono
         */

        //creo e setto a giocatoreDifendente un giocatore che scelgo io
        Giocatore giocatore=new Giocatore("andriei","fucsia");
        controllore.getGioco().setGiocatoreDifendente(giocatore);

        //chiamo e testo la funzione che voglio testare
        assertEquals(giocatore,controllore.getGioco().getGiocatoreDifendente());
    }

    @Test
    void getCarteObbiettivo() {
        /*
        testo se prende l'arraylist di carteobbiettivo giusto, per farlo lo setto prima con un arraylist che creo io e poi faccio il test per vedere se corrispondono
         */

        //creo quello che mi serve per chiamare la funzione
        ArrayList <CartaObbiettivo> carteObbiettivo = new ArrayList<>();
        carteObbiettivo.add(new CartaObbiettivo("conquista cosio", new ImageIcon()) {
            @Override
            public boolean ControllaVittoria(Gioco gioco) {
                return false;
            }
        });
        controllore.getGioco().addObbiettivo(carteObbiettivo.get(0));

        //chiamo e testo la funzione che voglio testare
        assertEquals(carteObbiettivo,controllore.getGioco().getCarteObbiettivo());
    }

    @Test
    void getListaGiocatori() {
        /*
        testo se prende la listaGiocatori giusta, per farlo lo setto prima con un arraylist di giocatori che creo io e poi faccio il test per vedere se corrispondono
         */

        //creo quello che mi serve per eseguire la funzione che voglio testare
        ArrayList <Giocatore> listaGiocarori=new ArrayList<>();
        listaGiocarori.add(new Giocatore("orsey","Giallo"));
        controllore.getGioco().addGiocatore(listaGiocarori.get(0));

        //chiamo e testo la funzione che voglio testare
        assertEquals(listaGiocarori,controllore.getGioco().getListaGiocatori());
    }

    @Test
    void getTerritori() {
        /*
        testo se prende l' arraylist di territori giusto, per farlo lo setto prima con uno che creo io e poi faccio il test per vedere se corrispondono
         */

        //creo quello che mi serve per eseguire la funzione
        ArrayList <Territorio> territori=new ArrayList<>();
        territori.add(new Territorio("coso","",new ArrayList<>()));
        controllore.getGioco().addTerritorio(territori.get(0));

        //chiamo e testo la funzione che voglio testare
        assertEquals(territori,controllore.getGioco().getTerritori());
    }

    @Test
    void getTerritorioAttaccante() {
        /*
        testo se prende il territorioAttaccante giusto, per farlo lo setto prima con un TerritorioSottoAttacco che creo io e poi faccio il test per vedere se corrispondono
         */

        //creo quello che mi serve per chiamare la funzione
        Territorio territorioAttaccante=new Territorio("Ardenno","City",new ArrayList<>());
        controllore.getGioco().setTerritorioAttaccante(territorioAttaccante);

        //chiamo e testo la funzione che voglio testare
        assertEquals(territorioAttaccante,controllore.getGioco().getTerritorioAttaccante());
    }

    @Test
    void getTerritorioSottoAttacco() {
        /*
        testo se prende il TerritorioSottoAttacco giusto, per farlo lo setto prima con un TerritorioSottoAttacco che creo io e poi faccio il test per vedere se corrispondono
         */

        //creo quello che mi serve per chiamare la funzione
        Territorio territorioSottoAttacco=new Territorio("Ardenno","City",new ArrayList<>());
        controllore.getGioco().setTerritorioSottoAttacco(territorioSottoAttacco);

        //chiamo e testo la funzione che voglio testare
        assertEquals(territorioSottoAttacco,controllore.getGioco().getTerritorioSottoAttacco());
    }

    @Test
    void getCarteTerritorio() {
        /*
        testo se prende l'arraylist di carte territorio giusta, per farlo lo setto prima con un arraylist di carto territorio che creo io e poi faccio il test per vedere se corrispondono
         */

        //creo quello che mi serve per eseguire la funzione che voglio testare
        ArrayList <CartaTerritorio> carteTerritorio=new ArrayList<>();
        carteTerritorio.add(new CartaTerritorio("Ardenno",new ImageIcon(),""));
        controllore.getGioco().addCartaTerritorio(carteTerritorio.get(0));

        //chiamo e testo la funzione che voglio testare
        assertEquals(carteTerritorio,controllore.getGioco().getCarteTerritorio());
    }
}