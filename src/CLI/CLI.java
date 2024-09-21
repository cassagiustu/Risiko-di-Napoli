package CLI;
import java.util.*;
import Controllore.Controllore;
import Model.Giocatore;
import Model.Gioco;
import Model.Territorio;

public class CLI implements Interfaccia{

    private static Controllore c;
    private final Gioco g;
    private final Scanner s = new Scanner(System.in);
    private final ArrayList<String> colori = new ArrayList<>();

    /**
     * costruttore della CLI, prende come parametri il controllore e il gioco, li memorizza e aggiunge i colori delle possibili armate
     * @param c controllore
     * @param g gioco
     */
    public CLI(Controllore c, Gioco g){
        this.c = c;
        this.g = g;
        colori.add("GIALLO");   //(0)
        colori.add("NERO");     //(1)
        colori.add("VERDE");    //(2)
        colori.add("BLU");      //(3)
        colori.add("ROSSO");    //(4)
        colori.add("VIOLA");    //(5)
    }

    /**
     * prende in input dall'utente il numero di giocatori che vogliono giocare e chiama la funzione che prende in input i nomi
     */
    @Override
    public void showMenu() {
        showGreenMessage("----- RISIKO Napoli -----\n");
        String numPlayers;
        do{
            System.out.println("Quanti player vogliono giocare (3 - 6) --> ");
            numPlayers = s.next();
            if(!numPlayers.matches("\\d+")){
                showError("Inserimento non valido");
                numPlayers = "0";
            }
            else if(Integer.parseInt(numPlayers) < 3 || Integer.parseInt(numPlayers) > 6){
                showError("Numero di giocatori non valido, inserire un numero tra 3 e 6 giocatori");
            }
        } while(Integer.parseInt(numPlayers) <= 2 || Integer.parseInt(numPlayers) >= 7);
        creaGiocatori(Integer.parseInt(numPlayers));
    }

    /**
     * prende in input le informazioni dei giocatori, nickname e colore carri e crea il Giocatore
     * @param num numero di giocatori da creare
     */
    @Override
    public void creaGiocatori(int num){
        System.out.print("\n");
        String nickname;
        boolean exists;
        for(int i = 0; i < num; i++){
            if(i == 0) {
                System.out.println("Giocatore " + (i + 1) + ":");
                System.out.println("Inserire nickname: ");
                nickname = s.next();
                System.out.println("Scegliere colore delle armate: ");
                Giocatore p = new Giocatore(nickname, coloriDisponibili());
                g.addGiocatore(p);
            }
            else{
                do{
                    System.out.println("Giocatore " + (i + 1) + ":");
                    System.out.println("Inserire nickname: ");
                    nickname = s.next();
                    System.out.println("Scegliere colore delle armate: ");

                    exists = false;
                    for(Giocatore s: g.getListaGiocatori()){
                        if(s.getNickname().equals(nickname)){
                            showError("Impossibile inserire 2 giocatori con lo stesso nickname");
                            exists = true;
                            break;
                        }
                    }
                }while(exists);
                Giocatore p = new Giocatore(nickname, coloriDisponibili());
                g.addGiocatore(p);
            }
        }
        c.startGame();
    }

    /**
     * chiamata quando un utente deve scegliere il colore dei carri armati tra quelli ancora disponibili
     * @return ritorna la stringa col colore scelto
     */
    public String coloriDisponibili(){
        String scelta;
        do{
            for(int i = 0; i < colori.size(); i++){
                System.out.println((i + 1) + ": " + colori.get(i));
            }
            //scelta = s.nextInt();
            scelta = s.next();
            if(!scelta.matches("\\d+")){
                showError("Scelta non valida");
                scelta = "0";
            }
            if(Integer.parseInt(scelta) <= 0 || Integer.parseInt(scelta) > colori.size())
                showError("Scelta non valida");

        } while(Integer.parseInt(scelta) <= 0 || Integer.parseInt(scelta) > colori.size());
        String temp = colori.get(Integer.parseInt(scelta) - 1);
        colori.remove(Integer.parseInt(scelta) - 1);
        return temp;
    }

    /**
     * stampa la lista dei giocatori presenti nel gioco
     */
    @Override
    public void stampaGiocatori(){
        for(Giocatore p: g.getListaGiocatori()){
            System.out.println(p.toString());
        }
    }

    /**
     * gestisce il turno di ogni giocatore, permette di posizionare carri, attaccare, spostare i carri e concludere
     */
    @Override
    public void showGame() {
        //carri di inizio turno da posizionare
        int carri = c.getCarriTurn();
        if(carri > 0){
            showPosizioneCarri(carri);
        }
        else{
            showMessage("Non hai armate extra da sistemare");
        }

        boolean endAttack = false;
        do{

            showMessage("Attaccare (1) o Concludere (2) -->");
            String scelta = s.next();

            switch(scelta){
                case "1":
                    ArrayList<String> disponibili = c.attacchiDisponibili();
                    System.out.println("Attacchi disponibili -->");
                    for(String s: disponibili){
                        System.out.println(s);
                    }
                    if(disponibili.isEmpty()){
                        showError("Non puoi attaccare nessun territorio");
                        break;
                    }
                    //attacca
                    String difensore, attaccante;
                    String numC;
                    boolean defenderOK = false;
                    do {
                        showYellowMessage("Quale territorio vuoi attaccare? ");
                        s.nextLine();
                        difensore = s.nextLine();
                        for(String s: disponibili){
                            if(s.equals(difensore)){
                                defenderOK = true;
                                break;
                            }
                        }

                        if(!defenderOK){
                            showError("Territorio non valido");
                        }

                    }while(!defenderOK);

                    do {
                        showYellowMessage("Da dove vuoi attaccare " + difensore + "? Territori attaccanti disponibili --> " + c.territoriAttaccanti(difensore));
                        attaccante = s.nextLine();
                    }while(c.findTerritorioAttaccante(attaccante) == 0 || c.findTerritorioAttaccante(attaccante) == 1);

                    do {
                        showYellowMessage("Quante armate vuoi usare? (Armate disponibili per l'attacco: " + (c.findTerritorioAttaccante(attaccante) - 1)+ ") (Armate nel territorio: " + g.getTerritorioAttaccante().getCarriArmati() + ")");
                        numC = s.next();
                        if(!numC.matches("\\d+")){
                            showError("Inserimento non valido");
                        }
                    }while(!numC.matches("\\d+") || !c.canAttack(attaccante, Integer.parseInt(numC)));


                    boolean attaccato = false;
                    for(Giocatore p: g.getListaGiocatori()){
                        for(Territorio t: p.getTerritori()){
                            if(t.getNome().equals(difensore)){
                                showMessage(g.getCurrentPlayer().getNickname() + " attacca con " + numC + " armate");
                                g.setGiocatoreDifendente(p);
                                g.setTerritorioSottoAttacco(t);
                                String difCarri;
                                if(t.getCarriArmati() == 1){
                                    showMessage(p.getNickname() + " difende con 1 armata");
                                    c.difendiAndattacca(1, Integer.parseInt(numC));
                                }
                                else{
                                    do{
                                        showYellowMessage(p.getNickname() + " quante armate vuoi utilizzare per difendere? (Armate disponibili " + g.getTerritorioSottoAttacco().getCarriArmati() + ")");
                                        difCarri = s.next();
                                        if(!difCarri.matches("\\d+")){
                                            showError("Inserimento non valido");
                                        }
                                        else if(Integer.parseInt(difCarri) > t.getCarriArmati()){
                                            showError("Non hai abbastanza armate");
                                        }
                                        else if(Integer.parseInt(difCarri) > 3){
                                            showError("Non puoi difendere con piu di 3 armate");
                                        }

                                    }while(!difCarri.matches("\\d+") || Integer.parseInt(difCarri) > t.getCarriArmati() || Integer.parseInt(difCarri) > 3);
                                    c.difendiAndattacca(Integer.parseInt(difCarri), Integer.parseInt(numC));
                                }
                                attaccato = true;
                                break;
                            }
                        }
                        if(attaccato)
                            break;
                    }
                    break;
                case "2":
                    endAttack = true;
                    break;
                default:
                    showError("Scelta non valida");
                    break;
            }

        }while(!endAttack);

        String scelta;
        do {
            System.out.println(g.getCurrentPlayer().getNickname() + " vuoi spostare armate? \nSi (1) \nNo (2)");
            scelta = s.next();
            switch (scelta) {
                case "1":
                    //chiedere da quale territorio si vogliono spostare, controllare se si ha piu di un carro, chiedere quanti carri vogliono essere
                    //spostati, controllare se il numero è valido, chiedere a quale territorio si vogliono spostare, controllare se il territorio
                    // è confinante e dello stesso giocatore e spostare i carri togliendoli dal territorio precedente e aggiungendoli a quello nuovo
                    String terr1, terr2;
                    boolean exists = false;
                    ArrayList<Territorio> spostabili = new ArrayList<>();
                    Territorio spostante = null, destinazione = null;
                    System.out.println("Da quale territorio vuoi spostare le tue armate? Territori con armate spostabili -->");
                    for(Territorio t: g.getCurrentPlayer().getTerritori()){
                        if(t.getCarriArmati() > 1){
                            spostabili.add(t);
                            System.out.println(t.getNome());
                        }
                    }

                    if(spostabili.isEmpty()){
                        showError("Non possiedi territori dai quali puoi spostare armate.");
                        break;
                    }

                    do{
                        s.nextLine();
                        terr1 = s.nextLine();
                        for(Territorio t: spostabili){
                            if(t.getNome().equals(terr1)){
                                exists = true;
                                spostante = t;
                            }
                        }

                        if(!exists)
                            showError("Territorio non valido!");
                    }while(!exists);

                    String num;
                    System.out.println("Quante armate vuoi spostare da " + terr1 + "? (Armate disponibili per lo spostamento --> " + (spostante.getCarriArmati()-1) + ")");
                    do{
                        num = s.next();
                        if(!num.matches("\\d+") || Integer.parseInt(num) < 1 || Integer.parseInt(num) > spostante.getCarriArmati()-1)
                            showError("Numero di armate non valido. Puoi spostare minimo 1 armata e massimo " + (spostante.getCarriArmati()-1));
                    }while(!num.matches("\\d+") || Integer.parseInt(num) < 1 || Integer.parseInt(num) > spostante.getCarriArmati()-1);

                    spostabili.clear();
                    exists = false;
                    System.out.println("Dove vuoi spostare le armate? Territori disponibili -->");
                    for(String s: spostante.getConfini()){
                        for(Territorio t: g.getTerritori()){
                            if(s.equals(t.getNome())){
                                if(t.getGiocatore() == g.getCurrentPlayer()){
                                    spostabili.add(t);
                                    System.out.println(t.getNome());
                                }
                            }
                        }
                    }

                    if(spostabili.isEmpty()){
                        showError("Non possiedi territori confinanti a " + spostante.getNome() + ", impossibile spostare armate.");
                        break;
                    }

                    do{
                        s.nextLine();
                        terr2 = s.nextLine();
                        for(Territorio t: spostabili){
                            if(terr2.equals(t.getNome())){
                                exists = true;
                                destinazione = t;
                            }
                        }

                        if(!exists)
                            showError("Territorio non valido!");
                    }while(!exists);

                    spostante.togliCarri(Integer.parseInt(num));
                    destinazione.addCarri(Integer.parseInt(num));
                    showMessage("Armate spostate con successo!");
                    break;

                case "2":
                    break;

                default:
                    showError("Scelta non valida");
                    break;
            }

        }while(!scelta.equals("1") && !scelta.equals("2"));
    }

    @Override
    public void setFrame(Frame frame) {

    }

    /**
     * gestisce un turno degli inserimenti iniziali dei carri armati, con massimo 3 carri alla volta
     */
    @Override
    public void showPosizioneCarri(){
        int carri = g.getCurrentPlayer().getCarri();
        if(carri >= 3)
            carri = 3;

        for(Territorio t: g.getCurrentPlayer().getTerritori()){
            System.out.println(t.getNome() + " " + t.getMunicipalita());
        }

        do{
            String var;
            boolean result;
            do{
                System.out.println("Scegliere un territorio dove posizione le armate -->");
                s.nextLine();
                String terr = s.nextLine();
                System.out.println("Scegliere quante armate posizionare (Armate disponibili " + carri + ") --> ");
                var = s.next();
                if(var.matches("\\d+")){
                    result = g.piazzaCarri(Integer.parseInt(var), terr);
                }
                else{
                    result = false;
                }

                if(!result)
                    showError("Impossibile posizionare le armate! Puoi posizionare massimo 3 armate e solo in un territorio di tuo possesso!");
            }while(!result);

            carri -= Integer.parseInt(var) ;
        }while(carri > 0);
    }

    /**
     * gestisce l' inserimento delle armate (senza limiti) extra a inizio turno
     * @param carri numero di carri disponibili
     */
    public void showPosizioneCarri(int carri){
        System.out.println("Armate disponibili --> " + carri);
        System.out.println("I tuoi territori -->");
        for(Territorio t: g.getCurrentPlayer().getTerritori()){
            System.out.println(t.getNome() + " " + t.getMunicipalita());
        }
        g.getCurrentPlayer().setCarri(carri);
        do{
            String var;
            boolean result;
            do{
                System.out.println("Scegliere un territorio dove posizione le armate --> ");
                s.nextLine();
                String terr = s.nextLine();
                do{
                    System.out.println("Scegliere quante armate posizionare (Armate disponibili " + carri + ") --> ");
                    var = s.next();
                    if(!var.matches("\\d+") || Integer.parseInt(var) > carri || carri < 1){
                        showError("Numero di armate non valido!");
                    }

                }while(!var.matches("\\d+") || Integer.parseInt(var) > carri || carri < 1);

                if(var.matches("\\d+")){
                    result = g.piazzaCarriTot(Integer.parseInt(var), terr);
                }
                else{
                    result = false;
                }

                if(!result)
                    showError("Impossibile trovare il territorio!");

            }while(!result);

            carri -= Integer.parseInt(var);
        }while(carri > 0);
    }

    /**
     * stampa a schermo un messaggio
     * @param  stringa stringa stampata a schermo
     */
    @Override
    public void showMessage(String stringa) {
        System.out.println(stringa);
    }

    /**
     * stampa a schermo un messaggio in giallo
     * @param stringa stringa stampata a schermo
     */
    public void showYellowMessage(String stringa) {
        System.out.println("\033[0;33m" + stringa + "\033[0m");
    }

    /**
     * stampa a schermo un messaggio in verde
     * @param stringa stringa stampata a schermo
     */
    @Override
    public void showGreenMessage(String stringa) {
        System.out.println("\033[0;32m" + stringa + "\033[0m");
    }

    /**
     * stampa a schermo un messaggio di errore in rosso
     * @param error stringa stampata a schermo
     */
    @Override
    public void showError(String error) {
        System.out.println("\033[0;31m" + error + "\033[0m");
    }

    /**
     * stampa a schermo la vittoria di un giocatore e il suo nome
     */
    @Override
    public void showVictory() {
        System.out.println("\n-----VITTORIA-----");
        System.out.println(g.getCurrentPlayer().getNickname() + " ha raggiunto il suo obbiettivo e vinto la partita!");
    }


}
