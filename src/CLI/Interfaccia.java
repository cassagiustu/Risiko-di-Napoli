package CLI;

public interface Interfaccia{

    void showMenu();
    void creaGiocatori(int num);
    void showGame();
    void setFrame(Frame frame);
    void stampaGiocatori();
    void showMessage(String stringa);
    void showGreenMessage(String stringa);
    void showPosizioneCarri();
    void showError(String error);
    void showVictory();
}
