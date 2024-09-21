package CLI;

import CLI.GUIObjects.RoundedLabel;
import Controllore.Controllore;
import Model.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Model.Carte.CartaTerritorio;
import Model.PosCarri;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("ALL")
public class Frame extends JFrame implements Serializable{
    private transient Clip clip;
    public JButton nuovaPartita;
    private JPanel panel;
    private transient Gioco gioco;
    private transient Controllore controllore;
    private final ArrayList<String> nomiGiocatori = new ArrayList<>();
    private JLabel backgroundLabel;
    private final transient GUI i;
    private JComboBox<Object>[] coloriGiocatori;
    private final ArrayList<PosCarri> posizioniCarri = new ArrayList<>();
    private boolean done = false;
    private int val;
    private JLabel obb;
    private JLabel carLabelNum;
    private RoundedLabel greenMessageLabel = new RoundedLabel("");
    private JButton posizionaCarri;
    private JLabel dado1 = new JLabel(new ImageIcon(getClass().getResource("/dado1.png")));
    private JLabel dado2 = new JLabel(new ImageIcon(getClass().getResource("/dado1.png")));
    private JLabel dado3 = new JLabel(new ImageIcon(getClass().getResource("/dado1.png")));
    private JLabel dado1D = new JLabel(new ImageIcon(getClass().getResource("/dado1.png")));
    private JLabel dado2D = new JLabel(new ImageIcon(getClass().getResource("/dado1.png")));
    private JLabel dado3D = new JLabel(new ImageIcon(getClass().getResource("/dado1.png")));
    private ArrayList<JLabel> dadi = new ArrayList<>();
    private JButton attacca;
    private boolean serializing = false;

    /**
     * costruttore del frame, passaandogli come parametri GIOCO,CONTROLLORE e la GUI
     *
     * @param gioco
     * @param controllore
     * @param i
     */
    public Frame(Gioco gioco, Controllore controllore, GUI i) {
        super("RISIKO");
        this.gioco = gioco;
        this.controllore = controllore;
        this.i = i;
        setUndecorated(true);
        setResizable(false);
    }

    /**
     * setta il controllore in caso di caricamento partita
     * @param c
     */
    public void setControllore(Controllore c){
        controllore = c;
    }

    /**
     * setta il gioco
     * @param gioco
     */
    public void setGioco(Gioco gioco) {
        this.gioco = gioco;
    }

    /**
     * apre la schermata iniziale del gioco, dove puoi scegliere se iniziare una nuova partita, continuare una partita già iniziata oppure terminare il programma
     */
    public void Menu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();


        // Carica l'immagine
        ImageIcon icon = new ImageIcon(getClass().getResource("/risikata3.png"));
        Image image = icon.getImage();


        // Ridimensiona l'immagine alle dimensioni dello schermo
        Image scaledImage = image.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);


        // Crea un'icona ridimensionata
        ImageIcon scaledIcon = new ImageIcon(scaledImage);


        // Utilizza l'icona ridimensionata per creare il JLabel di sfondo
        JLabel backgroundLabel = new JLabel(scaledIcon);


        // Imposta il layout, le dimensioni e aggiungi il backgroundLabel al frame
        backgroundLabel.setLayout(new OverlayLayout(backgroundLabel));
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);


        // Aggiungi il backgroundLabel al frame
        add(backgroundLabel);

        Musica("/sound1.wav");
        //musicPlayer.play();


        panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);


        JLabel risikoLabel = new JLabel("RISIKO!");
        risikoLabel.setFont(new Font("Open Sans", Font.BOLD, 200));
        risikoLabel.setForeground(Color.RED);
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.insets = new Insets(20, 0, 20, 0);
        panel.add(risikoLabel, gbcTitle);


        nuovaPartita = new JButton("Nuova Partita");
        nuovaPartita.setFont(new Font("Arial", Font.PLAIN, 35));
        nuovaPartita.setBackground(new Color(56, 142, 60));
        nuovaPartita.setForeground(Color.WHITE);
        nuovaPartita.setFocusPainted(false);


        nuovaPartita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clip.stop();
                Inserimento();
            }
        });
        GridBagConstraints gbcNuovaPartita = new GridBagConstraints();
        gbcNuovaPartita.gridx = 0;
        gbcNuovaPartita.gridy = 1;
        gbcNuovaPartita.insets = new Insets(10, 0, 10, 270);
        panel.add(nuovaPartita, gbcNuovaPartita);


        // Aggiungi il pulsante "Carica Partita"
        JButton caricaPartita = new JButton("Carica Partita");
        caricaPartita.setFont(new Font("Arial", Font.PLAIN, 35));
        caricaPartita.setBackground(new Color(221, 167, 7));
        caricaPartita.setForeground(Color.WHITE);
        caricaPartita.setFocusPainted(false);
        caricaPartita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logica per caricare una partita esistente
                clip.stop();
                serializing = true;
                controllore.caricaPartita();
            }
        });
        GridBagConstraints gbcCaricaPartita = new GridBagConstraints();
        gbcCaricaPartita.gridx = 0;
        gbcCaricaPartita.gridy = 1;
        gbcCaricaPartita.insets = new Insets(10, 270, 10, 0);
        panel.add(caricaPartita, gbcCaricaPartita);


        JButton esciButton = new JButton("Esci");
        esciButton.setFont(new Font("Arial", Font.PLAIN, 35));
        esciButton.setBackground(new Color(183, 28, 28));
        esciButton.setForeground(Color.WHITE);
        esciButton.setFocusPainted(false);
        esciButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        GridBagConstraints gbcEsci = new GridBagConstraints();
        gbcEsci.gridx = 0;
        gbcEsci.gridy = 2;
        gbcEsci.insets = new Insets(10, 0, 10, 0);
        panel.add(esciButton, gbcEsci);
        backgroundLabel.add(panel);
        add(backgroundLabel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * chiamata quando l'utente preme "avanti" nel menu, fa inserire il nome e il colore corrisponde dei giocatori
     */
    public void Inserimento() {
        controllore.setFrame(this);
        // Avvia la nuova musica
        clip.stop();
        Musica("/osimhen.wav");
        getContentPane().removeAll();
        revalidate();


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        ImageIcon icon = new ImageIcon(getClass().getResource("/maradona4.png"));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel playerBackgroundLabel = new JLabel(scaledIcon);
        playerBackgroundLabel.setLayout(new BorderLayout());


        // Pannello per gli input dei giocatori
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.anchor = GridBagConstraints.CENTER;


        // Etichetta di inserimento
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 10, 10, 150);
        gbc.insets.right = 250;
        JLabel inserisci = new RoundedLabel("INSERISCI GIOCATORI:");
        inserisci.setFont(new Font("Open Sans", Font.BOLD, 50));
        inserisci.setForeground(Color.BLACK);
        inputPanel.add(inserisci, gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;


        // Crea un array di JTextField e JComboBox per l'inserimento dei nomi e dei colori dei giocatori
        JTextField[] inputGiocatori = new JTextField[6];
        coloriGiocatori = new JComboBox[6];
        Object[] coloriDisponibili = {"Colori:", Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.BLACK};
        gbc.insets = new Insets(50, 10, 10, 100);


        // Ciclo per aggiungere i JTextField e i JComboBox affiancati
        for (int i = 0; i < 6; i++) {
            gbc.gridx = 0;
            inputGiocatori[i] = new JTextField(20);
            inputGiocatori[i].setFont(new Font("Arial", Font.PLAIN, 30));
            inputPanel.add(inputGiocatori[i], gbc);
            gbc.gridx = 1;
            coloriGiocatori[i] = new JComboBox<>(coloriDisponibili);
            customizeComboBox(coloriGiocatori[i]);
            inputPanel.add(coloriGiocatori[i], gbc);
            gbc.gridy++;
        }


        gbc.gridx = 0;
        gbc.gridwidth = 2;


        JButton avanti = new JButton("Avanti");
        avanti.setFont(new Font("Arial", Font.PLAIN, 35));
        avanti.setBackground(new Color(56, 142, 60));
        avanti.setForeground(Color.WHITE);
        avanti.setFocusPainted(false);
        avanti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Controlla se ci sono almeno tre giocatori
                int j = 0;
                int giocatoriInseriti = 0;
                for (int i = 0; i < 6; i++) {
                    if (!inputGiocatori[i].getText().isEmpty()) {
                        giocatoriInseriti++;

                    }
                }
                for (int i = 0; i < giocatoriInseriti; i++) {
                    if (inputGiocatori[i].getText().equals(" ")) {
                        JOptionPane.showMessageDialog(null, "Inserisci i nomi dei giocatori.");
                        return;
                    }
                }
                if (giocatoriInseriti < 3) {
                    JOptionPane.showMessageDialog(null, "Devi inserire almeno tre giocatori per iniziare la partita.");
                    return;
                }


                // Controlla se ci sono duplicati nei nomi
                ArrayList<String> nomiUnici = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    String nomeGiocatore = inputGiocatori[i].getText().trim();
                    if (!nomeGiocatore.isEmpty()) {
                        if (nomiUnici.contains(nomeGiocatore)) {
                            JOptionPane.showMessageDialog(null, "Non puoi inserire due giocatori con lo stesso nome.");
                            return;
                        } else {
                            nomiUnici.add(nomeGiocatore);
                            nomiGiocatori.add(nomeGiocatore);
                        }
                    }
                }


                // Controlla se ci sono duplicati nei colori
                ArrayList<Color> coloriUnici = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    if (!inputGiocatori[i].getText().isEmpty()) {
                        Object coloreSelezionato = coloriGiocatori[i].getSelectedItem();
                        if (coloreSelezionato instanceof Color) {
                            if (coloriUnici.contains(coloreSelezionato)) {
                                JOptionPane.showMessageDialog(null, "Non puoi scegliere lo stesso colore per due giocatori.");
                                return;
                            } else {
                                coloriUnici.add((Color) coloreSelezionato);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ogni giocatore deve selezionare un colore.");
                            return;
                        }
                    }

                }

                //CREAZIONE GIOCATORI
                i.creaGiocatori(giocatoriInseriti);
                // Passa alla mappa
                controllore.startGame();
            }
        });

        gbc.gridy++;
        inputPanel.add(avanti, gbc);


        JButton indietroButton = new JButton("Indietro");
        indietroButton.setFont(new Font("Arial", Font.PLAIN, 35));
        indietroButton.setBackground(new Color(183, 28, 28));
        indietroButton.setForeground(Color.WHITE);
        indietroButton.setFocusPainted(false);
        indietroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Torna al menu principale
                clip.stop();
                getContentPane().removeAll();
                Menu();
                revalidate();
                repaint();
            }
        });

        gbc.gridy = -1;

        inputPanel.add(avanti, gbc);

        // Sposta il cursore alla colonna successiva per il pulsante "Indietro"
        inputPanel.add(indietroButton, gbc);


        // Pannello per gestire la posizione del inputPanel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(inputPanel, BorderLayout.NORTH);


        // Aggiungi il pannello destro al playerBackgroundLabel
        playerBackgroundLabel.add(rightPanel, BorderLayout.EAST);
        getContentPane().add(playerBackgroundLabel);
        revalidate();
        repaint();
    }

    public ArrayList<String> getNomiGiocatori() {
        return nomiGiocatori;
    }

    public JComboBox<Object>[] getColoriGiocatori() {
        return coloriGiocatori;
    }

    private void customizeComboBox(JComboBox<Object> comboBox) {
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel comp = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Color) {
                    comp.setBackground((Color) value);
                    comp.setText(" "); // Imposta un singolo spazio per mantenere la dimensione
                    comp.setPreferredSize(new Dimension(100, 30)); // Imposta la dimensione preferita per mantenere le dimensioni uguali
                    comp.setOpaque(true);
                    if (isSelected) {
                        comp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    }
                } else {
                    comp.setText(value.toString());
                    comp.setHorizontalAlignment(SwingConstants.CENTER);
                }
                return comp;
            }
        });


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ottieni il colore selezionato
                Object selectedColor = comboBox.getSelectedItem();
                if (selectedColor instanceof Color) {
                    // Cambia il colore di sfondo della tendina stessa
                    comboBox.setBackground((Color) selectedColor);
                }
            }
        });
    }

    /**
     * chiamata quando sono stati inseriti correttamente i giocatori e i colori ad essi assegnati
     * fa vedere la mappa
     */
    public void Mappa() {
        if(!serializing)
            clip.stop();
        if(serializing){
            Menu();
            clip.stop();
        }

        Musica("/soundgame.wav");
        getContentPane().removeAll();
        revalidate();

        // Ottieni le dimensioni dello schermo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Carica l'immagine
        ImageIcon icon = new ImageIcon(getClass().getResource("/mappa6.png"));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setLayout(null);
        add(backgroundLabel, BorderLayout.CENTER);

        // Crea il pulsante audio
        ImageIcon audioOnIcon = new ImageIcon(getClass().getResource("/audioon.png"));
        ImageIcon audioOffIcon = new ImageIcon(getClass().getResource("/audiooff.png"));
        final boolean[] isSoundOn = {true};
        JButton audioButton = new JButton(audioOnIcon);
        audioButton.setBorderPainted(false);
        audioButton.setContentAreaFilled(false);
        audioButton.setFocusPainted(false);
        int buttonWidth = 50;
        int buttonHeight = 50;
        int buttonMargin = 10;
        int x = 20;
        int y = screenHeight - buttonHeight - buttonMargin;
        audioButton.setBounds(x, y, buttonWidth, buttonHeight);

        audioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSoundOn[0] = !isSoundOn[0];
                if (isSoundOn[0]) {
                    audioButton.setIcon(audioOnIcon);
                    clip.start();
                } else {
                    audioButton.setIcon(audioOffIcon);
                    clip.stop();
                }
                revalidate();
                repaint();
            }
        });

        // Crea il pulsante impostazioni
        ImageIcon settingsIcon = new ImageIcon(getClass().getResource("/setting4.png"));
        JButton settingsButton = new JButton(settingsIcon);
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setBounds(x + buttonWidth + buttonMargin, y, buttonWidth, buttonHeight);

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog settingsDialog = new JDialog();
                settingsDialog.setTitle("Settings");
                settingsDialog.setModal(true);
                settingsDialog.setSize(350, 200);
                settingsDialog.setLocationRelativeTo(null);

                JPanel settingsPanel = new JPanel();
                settingsPanel.setLayout(new BorderLayout());
                settingsPanel.setBackground(Color.WHITE);

                JLabel titleLabel = new JLabel("Impostazioni");
                titleLabel.setForeground(Color.BLACK);
                titleLabel.setBackground(Color.WHITE);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                settingsPanel.add(titleLabel, BorderLayout.NORTH);

                JButton saveButton = new JButton("Salva ed esci");
                saveButton.setFont(new Font("Arial", Font.BOLD, 16));
                saveButton.setBackground(Color.WHITE);
                saveButton.setForeground(Color.BLACK);
                saveButton.setFocusPainted(false);
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //SALVARE LA PARTITA
                        serializing = true;
                        controllore.salvaPartita();
                        JOptionPane.showMessageDialog(null, "Partita salvata con successo");
                        System.exit(0);
                    }
                });

                JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
                buttonPanel.setBackground(new Color(150, 75, 0));
                buttonPanel.add(saveButton);

                JButton backButton = new JButton("Indietro");
                backButton.setFont(new Font("Arial", Font.BOLD, 16));
                backButton.setBackground(Color.WHITE);
                backButton.setForeground(Color.BLACK);
                backButton.setFocusPainted(false);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        settingsDialog.dispose();
                    }
                });
                buttonPanel.add(backButton);

                settingsPanel.add(buttonPanel, BorderLayout.CENTER);
                settingsDialog.getContentPane().add(settingsPanel);
                settingsDialog.setVisible(true);
            }
        });

        final boolean[] objectiveIsOpen = {false};
        JButton cartaObbiettivo = new JButton("Obbiettivo");
        cartaObbiettivo.setBounds(150, y, 100, buttonHeight);
        cartaObbiettivo.setBackground(Color.YELLOW);
        cartaObbiettivo.setForeground(Color.BLACK);
        cartaObbiettivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!objectiveIsOpen[0]) {
                    ImageIcon obbIcon = gioco.getCurrentPlayer().getObbiettivo().getIcon();
                    obb = new JLabel(obbIcon);
                    obb.setBounds(controllore.getPixelX(40), controllore.getPixelY(69), 150, 237);
                    backgroundLabel.add(obb);
                    obb.setVisible(true);
                    objectiveIsOpen[0] = true;
                }
            }
        });

        JButton chiudiObb = new JButton("Chiudi Obbiettivo");
        chiudiObb.setBounds(270, y, 100, buttonHeight);
        chiudiObb.setBackground(Color.YELLOW);
        chiudiObb.setForeground(Color.BLACK);
        chiudiObb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (objectiveIsOpen[0]) {
                    obb.setVisible(false);
                    objectiveIsOpen[0] = false;
                }

            }
        });

        backgroundLabel.add(audioButton);
        backgroundLabel.add(settingsButton);
        backgroundLabel.add(cartaObbiettivo);
        backgroundLabel.add(chiudiObb);

        for (int j = 0; j < gioco.getListaGiocatori().size(); j++) {
            setCarriToPlayers(gioco.getListaGiocatori().get(j));
        }
        posizionaCarriIniziali();

        revalidate();
        repaint();
    }

    /**
     * metodo che gestissce il funzionamento della musica
     * riceve come parametro il nome del file della musica da mettere come sottofondo
     *
     * @param filePath
     */
    private void Musica(String filePath) {
        try {
            InputStream audio = getClass().getResourceAsStream(filePath);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audio));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * funzione che fa vedere a schermo tramite un JDialog il messaggio passato come parametro
     *
     * @param message stringa da mostrare a schermo
     */
    public void showGreenMessage(String message) {
        // Creazione del JLabel per il messaggio
        greenMessageLabel.setText("   " + message);
        greenMessageLabel.setFont(new Font("Arial", Font.PLAIN, 60)); // Modifica la dimensione del carattere
        greenMessageLabel.setForeground(Color.BLACK); // Imposta il colore del testo
        greenMessageLabel.setBounds(480, 50, 960, 75);
        greenMessageLabel.setVisible(true);

        //aggiunta della scritta alla mappa
        backgroundLabel.add(greenMessageLabel);
        revalidate();
        repaint();
    }

    /**
     * gestisce l' inserimento dei carri armati da parte dei giocatori sui propri territori
     */
    public void showPosizioneCarri(int carri) {
        // Reset delle variabili di stato all'inizio del metodo
        final boolean[] confermaPossible = {false};
        final boolean[] alreadyDone = {false};
        final boolean[] clicked = {false};
        final boolean[] attacking = {true};
        done = false;
        final int[] carriC = {carri};
        gioco.getCurrentPlayer().setDone(true);

        if (carri == -1) {
            gioco.getCurrentPlayer().setDone(false);
            carriC[0] = gioco.getCurrentPlayer().getCarri();
            if (carriC[0] >= 3)
                carriC[0] = 3;

            attacking[0] = false;
        }

        JDialog pos = new JDialog();
        //pos.setUndecorated(true);
        pos.setResizable(false);
        pos.setSize(450, 700);
        pos.setBounds(controllore.getPixelX(40), controllore.getPixelY(20), 300, 700);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        JPanel grdTerr = new JPanel(new GridLayout(gioco.getCurrentPlayer().getTerritori().size(), 3));
        grdTerr.setBounds(controllore.getPixelX(40), controllore.getPixelY(20), 300, 700);

        for (Territorio t : gioco.getCurrentPlayer().getTerritori()) {
            JButton b = new JButton(t.getNome());
            switch (t.getMunicipalita()) {
                case "MUNICIPALITA 8":
                    b.setBackground(new Color(0, 175, 237));
                    break;
                case "MUNICIPALITA 5 E 9":
                    b.setBackground(new Color(14, 193, 124));
                    break;
                case "MUNICIPALITA 1 E 10":
                    b.setBackground(new Color(232, 93, 70));
                    break;
                case "MUNICIPALITA 2 E 4":
                    b.setBackground(new Color(135, 1, 213));
                    break;
                case "MUNICIPALITA 6":
                    b.setBackground(new Color(220, 208, 76));
                    break;
                case "MUNICIPALITA 3 E 7":
                    b.setBackground(new Color(163, 18, 18));
                    break;
            }

            b.setForeground(Color.BLACK);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String terr = b.getText();
                    pos.dispose();
                    JDialog conf = new JDialog();
                    conf.setLayout(new GridLayout(3, 1));
                    conf.setUndecorated(true);
                    conf.setResizable(false);
                    conf.setBounds(controllore.getPixelX(40), controllore.getPixelY(20), 300, 200);
                    JPanel flow = new JPanel();
                    flow.setLayout(new FlowLayout());
                    flow.add(new JLabel("Quante armate vuoi posizionare? (" + carriC[0] + ")"));
                    conf.add(flow);

                    final JPanel[] armate = {new JPanel(new GridLayout(1, carriC[0]))};
                    for (int i = 0; i < carriC[0]; i++) {
                        JButton b1 = new JButton(String.valueOf(i + 1));
                        switch (t.getMunicipalita()) {
                            case "MUNICIPALITA 8":
                                b1.setBackground(new Color(0, 175, 237));
                                break;
                            case "MUNICIPALITA 5 E 9":
                                b1.setBackground(new Color(14, 193, 124));
                                break;
                            case "MUNICIPALITA 1 E 10":
                                b1.setBackground(new Color(232, 93, 70));
                                break;
                            case "MUNICIPALITA 2 E 4":
                                b1.setBackground(new Color(135, 1, 213));
                                break;
                            case "MUNICIPALITA 6":
                                b1.setBackground(new Color(220, 208, 76));
                                break;
                            case "MUNICIPALITA 3 E 7":
                                b1.setBackground(new Color(163, 18, 18));
                                break;
                        }
                        b1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                val = Integer.parseInt(b1.getText());
                                confermaPossible[0] = true;
                            }
                        });
                        armate[0].add(b1);
                    }

                    conf.add(armate[0]);
                    JButton conferma = new JButton("CONFERMA");
                    conferma.setBackground(Color.cyan);
                    JPanel confFlow = new JPanel();
                    confFlow.setLayout(new FlowLayout());
                    confFlow.add(conferma);
                    conf.add(confFlow);
                    conferma.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (confermaPossible[0]) {
                                carriC[0] -= val;
                                if (!attacking[0])
                                    gioco.piazzaCarri(val, terr);
                                else
                                    gioco.piazzaCarriTot(val, terr);

                                posizionaCarriIniziali();

                                if (carriC[0] == 0) {
                                    backgroundLabel.remove(posizionaCarri);
                                    done = true;
                                    armate[0] = null;
                                    conf.dispose();
                                } else {
                                    confermaPossible[0] = false;
                                    alreadyDone[0] = true;
                                    conf.dispose();
                                }
                            }
                        }
                    });
                    conf.setVisible(true);
                }
            });
            grdTerr.add(b);
        }
        pos.add(grdTerr);
        pos.repaint();

        posizionaCarri = new JButton("POSIZIONA CARRI");
        posizionaCarri.setBackground(Color.cyan);
        posizionaCarri.setForeground(Color.BLACK);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        posizionaCarri.setBorder(compound);
        posizionaCarri.setBounds(screenWidth - 220, screenHeight - 70, 200, 50);
        posizionaCarri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostra la matrice di territori disponibili per posizionare i carri
                if (!clicked[0]) {
                    pos.repaint();
                    pos.setVisible(true);
                    if (!alreadyDone[0]) {
                        backgroundLabel.revalidate();
                        backgroundLabel.repaint();
                    } else {
                        backgroundLabel.revalidate();
                        backgroundLabel.repaint();
                    }
                }
            }
        });
        backgroundLabel.add(posizionaCarri);

        while (!done) {
            backgroundLabel.revalidate();  // Revalidate e repaint su backgroundLabel
            backgroundLabel.repaint();
        }
    }

    /**
     * gestisce tutto ciò che si può fare in un turno, attacchi, spostamenti e conclusione
     */
    public void showGame() {
        final boolean[] end = {false};
        final boolean[] confermabile = {false};
        final boolean[] confermabile1 = {false};
        final boolean[] tiraDadiMade = {false};
        ArrayList<String> attaccanti = new ArrayList<>();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int carri = controllore.getCarriTurn();
        //posiziona carri iniziali
        if(!gioco.getCurrentPlayer().isDone()){
            if (carri > 0) {
                showPosizioneCarri(carri);
            }
        }

        //inizio attacchi
        attacca = new JButton("ATTACCA");
        attacca.setBackground(Color.RED);
        attacca.setForeground(Color.BLACK);
        attacca.setBounds(screenWidth - 220, screenHeight - 70, 200, 50);
        attacca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundLabel.remove(attacca);
                final int[] att = new int[1];
                final int[] def = new int[1];
                final boolean[] attackB = {false};
                final boolean[] defense = {false};

                //ATTACCA
                final String[] attaccante = new String[1];
                final String[] difensore = new String[1];

                final JDialog[] attack = {new JDialog()};
                attack[0].setLayout(new GridLayout(2, 1));
                attack[0].setUndecorated(true);
                attack[0].setResizable(false);
                attack[0].setBounds(controllore.getPixelX(40), controllore.getPixelY(20), 300, 500);
                JPanel grdTerr = new JPanel();
                grdTerr.setLayout(new GridLayout(controllore.attacchiDisponibili().size(), 1));

                for (String t : controllore.attacchiDisponibili()) {
                    Territorio temp = null;
                    for (Territorio t1 : gioco.getTerritori()) {
                        if (t1.getNome().equals(t))
                            temp = t1;
                    }
                    attaccanti.add(t);
                    JButton b = new JButton(t);
                    if(temp != null){
                        switch (temp.getMunicipalita()) {
                            case "MUNICIPALITA 8":
                                b.setBackground(new Color(0, 175, 237));
                                break;
                            case "MUNICIPALITA 5 E 9":
                                b.setBackground(new Color(14, 193, 124));
                                break;
                            case "MUNICIPALITA 1 E 10":
                                b.setBackground(new Color(232, 93, 70));
                                break;
                            case "MUNICIPALITA 2 E 4":
                                b.setBackground(new Color(135, 1, 213));
                                break;
                            case "MUNICIPALITA 6":
                                b.setBackground(new Color(220, 208, 76));
                                break;
                            case "MUNICIPALITA 3 E 7":
                                b.setBackground(new Color(163, 18, 18));
                                break;
                        }
                    }
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //ACTION PERFORMED DEL BOTTONE TERRITORIO DA ATTACCARE
                            JButton b = (JButton) e.getSource();
                            difensore[0] = b.getText();

                            attack[0].dispose();

                            //creo attack1 che ha i territori da cui si può attaccare
                            JDialog attack1 = new JDialog();
                            attack1.setLayout(new GridLayout(2, 1));
                            attack1.setUndecorated(true);
                            attack1.setResizable(false);
                            attack1.setBounds(controllore.getPixelX(40), controllore.getPixelY(20), 300, 500);
                            JPanel grdTerr1 = new JPanel();
                            grdTerr1.setLayout(new GridLayout(controllore.attacchiDisponibili().size(), 1));
                            for (Territorio t : controllore.territoriAttaccanti(difensore[0])) {
                                JButton b1 = new JButton(t.getNome());
                                switch (t.getMunicipalita()) {
                                    case "MUNICIPALITA 8":
                                        b1.setBackground(new Color(0, 175, 237));
                                        break;
                                    case "MUNICIPALITA 5 E 9":
                                        b1.setBackground(new Color(14, 193, 124));
                                        break;
                                    case "MUNICIPALITA 1 E 10":
                                        b1.setBackground(new Color(232, 93, 70));
                                        break;
                                    case "MUNICIPALITA 2 E 4":
                                        b1.setBackground(new Color(135, 1, 213));
                                        break;
                                    case "MUNICIPALITA 6":
                                        b1.setBackground(new Color(220, 208, 76));
                                        break;
                                    case "MUNICIPALITA 3 E 7":
                                        b1.setBackground(new Color(163, 18, 18));
                                        break;
                                }
                                b1.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JButton b2 = (JButton) e.getSource();
                                        attaccante[0] = b2.getText();

                                        attack1.dispose();
                                        for (Giocatore g : gioco.getListaGiocatori()) {
                                            for (Territorio t : g.getTerritori()) {
                                                if (difensore[0].equals(t.getNome())) {
                                                    gioco.setTerritorioSottoAttacco(t);
                                                    gioco.setGiocatoreDifendente(g);
                                                }
                                            }
                                        }

                                        //CARRI
                                        //ATTACCO
                                        JDialog numeroAtt = new JDialog();
                                        numeroAtt.setUndecorated(true);
                                        numeroAtt.setResizable(false);
                                        numeroAtt.setBounds(controllore.getPixelX(30), controllore.getPixelY(20), 300, 200);
                                        numeroAtt.setLayout(new GridLayout(2, 1));
                                        JPanel flow = new JPanel();
                                        flow.setLayout(new FlowLayout());
                                        int valFor = controllore.findTerritorioAttaccante(attaccante[0]);
                                        flow.add(new JLabel("Quante armate vuoi usare per attaccare? (" + valFor + ")"));
                                        JPanel grid = new JPanel();
                                        grid.setLayout(new GridLayout(1, valFor));

                                        for (int i = 0; i < valFor; i++) {
                                            if (i < valFor - 1) {
                                                if (i < 3) {
                                                    JButton b1 = new JButton(String.valueOf(i + 1));
                                                    switch (gioco.getTerritorioAttaccante().getMunicipalita()) {
                                                        case "MUNICIPALITA 8":
                                                            b1.setBackground(new Color(0, 175, 237));
                                                            break;
                                                        case "MUNICIPALITA 5 E 9":
                                                            b1.setBackground(new Color(14, 193, 124));
                                                            break;
                                                        case "MUNICIPALITA 1 E 10":
                                                            b1.setBackground(new Color(232, 93, 70));
                                                            break;
                                                        case "MUNICIPALITA 2 E 4":
                                                            b1.setBackground(new Color(135, 1, 213));
                                                            break;
                                                        case "MUNICIPALITA 6":
                                                            b1.setBackground(new Color(220, 208, 76));
                                                            break;
                                                        case "MUNICIPALITA 3 E 7":
                                                            b1.setBackground(new Color(163, 18, 18));
                                                            break;
                                                    }
                                                    b1.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            JButton num = (JButton) e.getSource();
                                                            att[0] = Integer.parseInt(num.getText());
                                                            numeroAtt.dispose();
                                                            attackB[0] = true;

                                                            //TIRA DADI
                                                            if (defense[0] && attackB[0] && !tiraDadiMade[0]) {
                                                                tiraDadiMade[0] = true;
                                                                tiraDadi(att[0], def[0]);
                                                                tiraDadiMade[0] = false;
                                                            }
                                                        }
                                                    });
                                                    grid.add(b1);
                                                }
                                            }
                                        }
                                        numeroAtt.add(flow);
                                        numeroAtt.add(grid);

                                        //DIFESA
                                        JDialog numeroDif = new JDialog();
                                        numeroDif.setUndecorated(true);
                                        numeroDif.setResizable(false);
                                        numeroDif.setBounds(controllore.getPixelX(60), controllore.getPixelY(20), 300, 200);
                                        numeroDif.setLayout(new GridLayout(2, 1));
                                        JPanel flow1 = new JPanel();
                                        flow1.setLayout(new FlowLayout());
                                        flow1.add(new JLabel(gioco.getGiocatoreDifendente().getNickname() + " quante armate vuoi usare per difendere? (" + gioco.getTerritorioSottoAttacco().getCarriArmati() + ")"));
                                        JPanel grid1 = new JPanel();
                                        grid1.setLayout(new GridLayout(1, gioco.getTerritorioSottoAttacco().getCarriArmati()));

                                        for (int i = 0; i < gioco.getTerritorioSottoAttacco().getCarriArmati(); i++) {
                                            if (i < 3) {
                                                JButton b = new JButton(String.valueOf(i + 1));
                                                switch (gioco.getTerritorioSottoAttacco().getMunicipalita()) {
                                                    case "MUNICIPALITA 8":
                                                        b.setBackground(new Color(0, 175, 237));
                                                        break;
                                                    case "MUNICIPALITA 5 E 9":
                                                        b.setBackground(new Color(14, 193, 124));
                                                        break;
                                                    case "MUNICIPALITA 1 E 10":
                                                        b.setBackground(new Color(232, 93, 70));
                                                        break;
                                                    case "MUNICIPALITA 2 E 4":
                                                        b.setBackground(new Color(135, 1, 213));
                                                        break;
                                                    case "MUNICIPALITA 6":
                                                        b.setBackground(new Color(220, 208, 76));
                                                        break;
                                                    case "MUNICIPALITA 3 E 7":
                                                        b.setBackground(new Color(163, 18, 18));
                                                        break;
                                                }
                                                b.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        JButton num = (JButton) e.getSource();
                                                        def[0] = Integer.parseInt(num.getText());
                                                        numeroDif.dispose();
                                                        defense[0] = true;
                                                        backgroundLabel.remove(attacca);

                                                        //TIRA DADI
                                                        if (defense[0] && attackB[0] && !tiraDadiMade[0]) {
                                                            tiraDadiMade[0] = true;
                                                            tiraDadi(att[0], def[0]);
                                                            tiraDadiMade[0] = false;
                                                        }
                                                    }
                                                });
                                                grid1.add(b);
                                            }
                                        }
                                        numeroDif.add(flow1);
                                        numeroDif.add(grid1);

                                        numeroAtt.setVisible(true);
                                        numeroDif.setVisible(true);

                                    }
                                });
                                grdTerr1.add(b1);
                            }

                            JPanel flow1 = new JPanel();
                            flow1.setLayout(new FlowLayout());
                            flow1.add(new JLabel("Territori da dove puoi attaccare"));
                            attack1.add(flow1);
                            attack1.add(grdTerr1);
                            attack1.setVisible(true);
                        }
                    });
                    grdTerr.add(b);
                }
                JPanel flow = new JPanel();
                flow.setLayout(new FlowLayout());
                flow.add(new JLabel("Territori attaccabili"));
                attack[0].add(flow);
                attack[0].add(grdTerr);
                attack[0].setVisible(true);

                if(controllore.attacchiDisponibili().isEmpty()){
                    attack[0].dispose();
                }
            }
        });

        backgroundLabel.add(attacca);

        JButton concludi = new JButton("CONCLUDI TURNO");
        concludi.setBackground(Color.GREEN);
        concludi.setForeground(Color.BLACK);
        concludi.setBounds(screenWidth - 420, screenHeight - 70, 200, 50);
        concludi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MUOVI CARRI
                JDialog muovi = new JDialog();
                muovi.setResizable(false);
                muovi.setUndecorated(true);
                muovi.setBounds(controllore.getPixelX(47), controllore.getPixelY(62), 300, 200);
                muovi.setLayout(new GridLayout(2, 1));
                JPanel flow = new JPanel();
                flow.setLayout(new FlowLayout());
                flow.add(new JLabel("Vuoi spostare armate?"));
                muovi.add(flow);
                JButton si = new JButton("SI");
                si.setBackground(Color.GREEN);
                si.setForeground(Color.BLACK);
                si.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<Territorio> spostabili = new ArrayList<>();
                        boolean spostabile = true;

                        //SPOSTA
                        muovi.dispose();
                        JDialog muovi1 = new JDialog();
                        muovi1.setResizable(false);
                        muovi1.setUndecorated(true);
                        muovi1.setBounds(controllore.getPixelX(45), controllore.getPixelY(45), 300, 200);
                        muovi1.setLayout(new GridLayout(2, 1));

                        for (Territorio t : gioco.getCurrentPlayer().getTerritori()) {
                            if (t.getCarriArmati() > 1) {
                                spostabili.add(t);
                            }
                        }

                        if (spostabili.isEmpty()) {
                            showMessage("Nessun territorio da cui si possono spostare armate");
                            muovi1.dispose();
                            muovi.dispose();
                            spostabile = false;
                        }

                        if (spostabile) {
                            JPanel grid = new JPanel();
                            grid.setLayout(new GridLayout(spostabili.size(), 1));
                            for (Territorio t : spostabili) {
                                JButton b = new JButton(t.getNome());
                                switch (t.getMunicipalita()) {
                                    case "MUNICIPALITA 8":
                                        b.setBackground(new Color(0, 175, 237));
                                        break;
                                    case "MUNICIPALITA 5 E 9":
                                        b.setBackground(new Color(14, 193, 124));
                                        break;
                                    case "MUNICIPALITA 1 E 10":
                                        b.setBackground(new Color(232, 93, 70));
                                        break;
                                    case "MUNICIPALITA 2 E 4":
                                        b.setBackground(new Color(135, 1, 213));
                                        break;
                                    case "MUNICIPALITA 6":
                                        b.setBackground(new Color(220, 208, 76));
                                        break;
                                    case "MUNICIPALITA 3 E 7":
                                        b.setBackground(new Color(163, 18, 18));
                                        break;
                                }
                                b.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Territorio spostante = null;
                                        int numCarri = 0;
                                        JButton b = (JButton) e.getSource();
                                        for (Territorio t : spostabili) {
                                            if (t.getNome().equals(b.getText())) {
                                                spostante = t;
                                                numCarri = spostante.getCarriArmati();
                                            }
                                        }
                                        spostabili.clear();
                                        muovi1.dispose();
                                        JDialog muovi2 = new JDialog();
                                        muovi2.setResizable(false);
                                        muovi2.setUndecorated(true);
                                        muovi2.setBounds(controllore.getPixelX(45), controllore.getPixelY(45), 300, 200);
                                        muovi2.setLayout(new GridLayout(2, 1));
                                        boolean spostabile2 = true;
                                        for (String s : spostante.getConfini()) {
                                            for (Territorio t : gioco.getTerritori()) {
                                                if (s.equals(t.getNome())) {
                                                    if (t.getGiocatore() == gioco.getCurrentPlayer()) {
                                                        spostabili.add(t);
                                                    }
                                                }
                                            }
                                        }

                                        if (spostabili.isEmpty()) {
                                            showMessage("Non possiedi territori confinanti a " + spostante.getNome() + ", impossibile spostare armate.");
                                            muovi2.dispose();
                                            spostabile2 = false;
                                        }

                                        if (spostabile2) {
                                            JPanel grid = new JPanel();
                                            grid.setLayout(new GridLayout(spostabili.size(), 1));
                                            for (Territorio t : spostabili) {
                                                JButton b1 = new JButton(t.getNome());
                                                switch (t.getMunicipalita()) {
                                                    case "MUNICIPALITA 8":
                                                        b1.setBackground(new Color(0, 175, 237));
                                                        break;
                                                    case "MUNICIPALITA 5 E 9":
                                                        b1.setBackground(new Color(14, 193, 124));
                                                        break;
                                                    case "MUNICIPALITA 1 E 10":
                                                        b1.setBackground(new Color(232, 93, 70));
                                                        break;
                                                    case "MUNICIPALITA 2 E 4":
                                                        b1.setBackground(new Color(135, 1, 213));
                                                        break;
                                                    case "MUNICIPALITA 6":
                                                        b1.setBackground(new Color(220, 208, 76));
                                                        break;
                                                    case "MUNICIPALITA 3 E 7":
                                                        b1.setBackground(new Color(163, 18, 18));
                                                        break;
                                                }
                                                int finalNumCarri = numCarri;
                                                Territorio finalSpostante = spostante;
                                                b1.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        JButton b = (JButton) e.getSource();
                                                        Territorio destinazione = null;
                                                        for (Territorio t : spostabili) {
                                                            if (t.getNome().equals(b.getText())) {
                                                                destinazione = t;
                                                            }
                                                        }

                                                        muovi2.dispose();
                                                        JDialog muovi3 = new JDialog();
                                                        muovi3.setResizable(false);
                                                        muovi3.setUndecorated(true);
                                                        muovi3.setBounds(controllore.getPixelX(45), controllore.getPixelY(45), 300, 200);
                                                        muovi3.setLayout(new GridLayout(2, 1));
                                                        JPanel flow = new JPanel();
                                                        flow.setLayout(new FlowLayout());
                                                        flow.add(new JLabel("Quanti carri vuoi spostare"));

                                                        muovi3.add(flow);
                                                        JPanel grid = new JPanel();
                                                        grid.setLayout(new GridLayout(finalNumCarri, 1));
                                                        for (int i = 0; i < finalNumCarri - 1; i++) {
                                                            JButton bC = new JButton(String.valueOf(i + 1));
                                                            Territorio finalDestinazione = destinazione;
                                                            bC.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    muovi3.dispose();
                                                                    JButton b = (JButton) e.getSource();
                                                                    finalSpostante.togliCarri(Integer.parseInt(b.getText()));
                                                                    finalDestinazione.addCarri(Integer.parseInt(b.getText()));

                                                                    for (Giocatore g : gioco.getListaGiocatori()) {
                                                                        setCarriToPlayers(g);
                                                                    }
                                                                    posizionaCarriIniziali();

                                                                    end[0] = true;
                                                                    muovi.dispose();
                                                                    muovi1.dispose();
                                                                    muovi2.dispose();
                                                                    muovi3.dispose();
                                                                }
                                                            });
                                                            grid.add(bC);
                                                        }
                                                        muovi3.add(grid);
                                                        muovi3.setVisible(true);
                                                    }
                                                });
                                                grid.add(b1);
                                            }
                                            JPanel flow = new JPanel();
                                            flow.setLayout(new FlowLayout());
                                            flow.add(new JLabel("Seleziona territorio dove spostare le armate"));
                                            muovi2.add(flow);
                                            muovi2.add(grid);
                                        }

                                        muovi2.setVisible(true);
                                    }
                                });
                                grid.add(b);
                            }

                            JPanel flow = new JPanel();
                            flow.setLayout(new FlowLayout());
                            flow.add(new JLabel("Seleziona territorio dal quale spostare le armate"));
                            muovi1.add(flow);
                            muovi1.add(grid);
                            muovi1.setVisible(true);
                        }

                    }
                });

                JButton no = new JButton("NO");
                no.setBackground(Color.RED);
                no.setForeground(Color.BLACK);
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //ESCI
                        end[0] = true;
                        muovi.dispose();
                    }
                });

                JPanel flowB = new JPanel();
                flowB.setLayout(new FlowLayout());
                flowB.add(si);
                flowB.add(no);
                muovi.add(flowB);
                muovi.setVisible(true);
                //CONCLUDI TURNO E PESCA CARTA SE CONQUISTATO
            }
        });
        backgroundLabel.add(concludi);

        while (!end[0]) {
            backgroundLabel.revalidate();
            backgroundLabel.repaint();
        }

        gioco.getCurrentPlayer().setDone(false);
        backgroundLabel.remove(concludi);
        backgroundLabel.remove(attacca);

    }

    /**
     * mostra a schermo un JFrame quando un giocatore vince la partita
     */
    public void showVictory() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        backgroundLabel.removeAll();

        JFrame win = new JFrame();
        win.setBackground(Color.GREEN);
        win.setResizable(false);
        win.setLayout(null);
        win.setBounds(100, screenHeight / 4, screenWidth - 300, 500);
        JLabel victory = new JLabel("GIOCATORE " + gioco.getCurrentPlayer().getNickname() + " VINCE LA PARTITA!");
        Font f = new Font("Arial", Font.CENTER_BASELINE, 36);
        victory.setFont(f);
        win.add(victory);
        victory.setBounds(100, 50, screenWidth - 300, 50);
        JButton chiudi = new JButton("ESCI");
        chiudi.setBackground(Color.RED);
        chiudi.setForeground(Color.BLACK);
        win.add(chiudi);
        chiudi.setBounds(screenWidth / 3, screenHeight / 3, 400, 100);
        chiudi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        win.setVisible(true);
        win.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * mostra a schermo la carta pescata randomicamente dall'utente
     * @param c carta territorio pescata randomicamente e mostrata a schermo
     */
    public void showCartaPescata(CartaTerritorio c) {
        JDialog pescata = new JDialog();
        JLabel carta = new JLabel(c.getIcon());
        pescata.setResizable(false);
        pescata.setBounds(controllore.getPixelX(60), controllore.getPixelY(60), 150, 220);
        pescata.add(carta);
        pescata.setVisible(true);
    }

    /**
     * gestisce il numero di dadi da usare e li mostra a schermo
     *
     * @param num numero di dadi che il giocatore vuole usare
     * @param att se vero significa che il metodo è chiamato dal giocatore attaccante, se falso dal giocatore in difesa
     */
    public void showDadi(int num, boolean att) {
        backgroundLabel.add(dado1);
        backgroundLabel.add(dado2);
        backgroundLabel.add(dado3);
        backgroundLabel.add(dado1D);
        backgroundLabel.add(dado2D);
        backgroundLabel.add(dado3D);

        //POSIZIONI DADI
        if (att) {
            dado1.setBounds(controllore.getPixelX(30), controllore.getPixelY(20), 168, 166);
            dado2.setBounds(controllore.getPixelX(30), controllore.getPixelY(40), 168, 166);
            dado3.setBounds(controllore.getPixelX(30), controllore.getPixelY(60), 168, 166);
        } else {
            dado1D.setBounds(controllore.getPixelX(60), controllore.getPixelY(20), 168, 166);
            dado2D.setBounds(controllore.getPixelX(60), controllore.getPixelY(40), 168, 166);
            dado3D.setBounds(controllore.getPixelX(60), controllore.getPixelY(60), 168, 166);
        }


        switch (num) {
            case 1:
                if (att) {
                    dado1.setVisible(true);
                    dadi.add(dado1);
                } else {
                    dado1D.setVisible(true);
                    dadi.add(dado1D);
                }
                break;

            case 2:
                if (att) {
                    dado1.setVisible(true);
                    dado2.setVisible(true);
                    dadi.add(dado1);
                    dadi.add(dado2);
                } else {
                    dado1D.setVisible(true);
                    dado2D.setVisible(true);
                    dadi.add(dado1D);
                    dadi.add(dado2D);
                }

                break;

            case 3:
                if (att) {
                    dado1.setVisible(true);
                    dado2.setVisible(true);
                    dado3.setVisible(true);
                    dadi.add(dado1);
                    dadi.add(dado2);
                    dadi.add(dado3);
                } else {
                    dado1D.setVisible(true);
                    dado2D.setVisible(true);
                    dado3D.setVisible(true);
                    dadi.add(dado1D);
                    dadi.add(dado2D);
                    dadi.add(dado3D);
                }

                break;
        }
    }

    /**
     * tira i dadi in base al numero di armate con cui attacchi e difendi
     *
     * @param att numero di dadi dell'attaccante da tirare
     * @param def numero di dadi del difensore da tirare
     */
    public void tiraDadi(int att, int def) {
        dadi.clear();
        dado1.setVisible(false);
        dado2.setVisible(false);
        dado3.setVisible(false);
        dado1D.setVisible(false);
        dado2D.setVisible(false);
        dado3D.setVisible(false);
        ArrayList<Integer> attack = new ArrayList<>();
        ArrayList<Integer> defense = new ArrayList<>();
        final boolean[] tirato = {false};

        int tot = att + def;

        switch (att) {
            case 1:
                showDadi(1, true);
                break;

            case 2:
                showDadi(2, true);
                break;

            case 3:
                showDadi(3, true);
                break;
        }

        switch (def) {
            case 1:
                showDadi(1, false);
                break;

            case 2:
                showDadi(2, false);
                break;

            case 3:
                showDadi(3, false);
                break;
        }

        //BUTTON TIRA DADI
        JButton tira = new JButton("TIRA DADI");
        tira.setBackground(Color.YELLOW);
        tira.setForeground(Color.BLACK);
        tira.setBounds(controllore.getPixelX(45), controllore.getPixelY(42), 150, 75);
        backgroundLabel.add(tira);
        tira.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TIRA DADI
                if (!tirato[0]) {
                    for (int i = 0; i < tot; i++) {
                        int num = controllore.tiraDadi(false);
                        switch (num) {
                            case 1:
                                dadi.get(i).setIcon(new ImageIcon(getClass().getResource("/dado1.png")));
                                break;
                            case 2:
                                dadi.get(i).setIcon(new ImageIcon(getClass().getResource("/dado2.png")));
                                break;
                            case 3:
                                dadi.get(i).setIcon(new ImageIcon(getClass().getResource("/dado3.png")));
                                break;
                            case 4:
                                dadi.get(i).setIcon(new ImageIcon(getClass().getResource("/dado4.png")));
                                break;
                            case 5:
                                dadi.get(i).setIcon(new ImageIcon(getClass().getResource("/dado5.png")));
                                break;
                            case 6:
                                dadi.get(i).setIcon(new ImageIcon(getClass().getResource("/dado6.png")));
                                break;
                        }

                        if (i < att) {
                            attack.add(num);
                        } else {
                            defense.add(num);
                        }
                    }
                    tirato[0] = true;

                    //ESITO ATTACCHI
                    controllore.esitoAttacchi(defense, attack);
                    for (Giocatore g : gioco.getListaGiocatori()) {
                        setCarriToPlayers(g);
                    }
                    posizionaCarriIniziali();

                    JButton chiudi = new JButton("CHIUDI");
                    chiudi.setBackground(Color.RED);
                    chiudi.setForeground(Color.BLACK);
                    chiudi.setBounds(controllore.getPixelX(45), controllore.getPixelY(50), 150, 75);
                    backgroundLabel.add(chiudi);
                    chiudi.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            backgroundLabel.remove(tira);
                            for (int i = 0; i < dadi.size(); i++) {
                                dadi.get(i).setVisible(false);
                            }
                            backgroundLabel.remove(chiudi);
                            backgroundLabel.add(attacca);
                        }
                    });
                }
            }
        });
    }

    /**
     * mostra a schermo un JDialog il messaggio passato come stringa
     *
     * @param stringa stringa da mostrare a schermo
     */
    public void showMessage(String stringa) {
        JDialog message = new JDialog();
        message.setBounds(controllore.getPixelX(45), controllore.getPixelY(48), 400, 200);
        message.setResizable(false);
        message.add(new JLabel(stringa));
        message.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        message.setVisible(true);
    }

    /**
     * aggiunge un oggetto PosCarri all'arraylist posizionicarri
     *
     * @param pc oggetto PosCarri
     */
    public void addPosCarri(PosCarri pc) {
        posizioniCarri.add(pc);
    }

    private ArrayList<JLabel> arraynumeri = new ArrayList<>();
    private ArrayList<JLabel> arrayTank = new ArrayList<>();

    /**
     * mostra a schermo i carri armati e il numero di essi su ciascun territorio
     */
    public void posizionaCarriIniziali() {

        //rende invisibili tutti i numeri precedentemente salvati nell'arraylist
        for (int i = 0; i < arraynumeri.size(); i++) {
            arraynumeri.get(i).setVisible(false);
        }
        //svuota l'arraylist
        arraynumeri.clear();

        for (int i = 0; i < arrayTank.size(); i++) {
            arrayTank.get(i).setVisible(false);
        }

        arrayTank.clear();

        for (Giocatore p : gioco.getListaGiocatori()) {
            for (Territorio t : p.getTerritori()) {
                for (PosCarri c : posizioniCarri) {
                    if (c.getNome().equals(t.getNome())) {
                        //crea la label dove mettere l'immagine del carro
                        JLabel button;
                        button = new JLabel(c.getIcon());
                        int labelWidth = c.getIcon().getIconWidth();
                        int labelHeight = c.getIcon().getIconHeight();
                        button.setBounds(c.getX(), c.getY(), labelWidth, labelHeight);
                        arrayTank.add(button);

                        //crea label per il numero di carri nel territorio
                        c.setNum(t.getCarriArmati());
                        JLabel num;
                        num = new JLabel(String.valueOf(c.getNum()));

                        num.setForeground(Color.BLACK);
                        num.setBackground(Color.WHITE);
                        num.setFont(new Font("Arial", Font.BOLD, 20));
                        num.setBounds(c.getX() + 50, c.getY(), 20, 20);
                        arraynumeri.add(num);
                        c.setLabelNum(num);
                        c.getLabelNum().setVisible(true);
                        c.setTank(button);
                        c.getTank().setVisible(true);

                        //aggiunge le label alla mappa
                        backgroundLabel.add(c.getLabelNum());
                        backgroundLabel.add(c.getTank());
                    }
                }
            }
        }

    }

    /**
     * setta l'immagine del carro armato del giocatore
     *
     * @param giocatore
     */
    public void setCarriToPlayers(Giocatore giocatore) {
        ArrayList<String> territori = new ArrayList<>();

        for (Territorio t : giocatore.getTerritori()) {
            territori.add(t.getNome());
        }

        for (PosCarri p : posizioniCarri) {
            for (String s : territori) {
                if (p.getNome().equals(s)) {
                    switch (giocatore.getColore()) {
                        case "ROSSO":
                            p.setIcon(new ImageIcon(requireNonNull(getClass().getResource("/carrorosso.png"))));
                            break;

                        case "GIALLO":
                            p.setIcon(new ImageIcon(getClass().getResource("/carrogiallo.png")));
                            break;

                        case "VERDE":
                            p.setIcon(new ImageIcon(getClass().getResource("/carroverde.png")));
                            break;

                        case "BLU":
                            p.setIcon(new ImageIcon(getClass().getResource("/carroblu.png")));
                            break;

                        case "VIOLA":
                            p.setIcon(new ImageIcon(getClass().getResource("/carroviola.png")));
                            break;

                        case "NERO":
                            p.setIcon(new ImageIcon(getClass().getResource("/carronero.png")));
                            break;
                    }
                }
            }

            backgroundLabel.repaint();
            backgroundLabel.revalidate();
        }


    }
}
