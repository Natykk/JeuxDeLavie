import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class JeuDeLaVieUI extends JPanel implements Observateur {
    private JeuDeLaVie jeu;
    private JButton boutonStart = new JButton("Start");
    private JButton boutonStop = new JButton("Stop");
    private JButton boutonSkipGen = new JButton("Skip 1 Generation");
    private JButton boutonStructures = new JButton("Structures");
    private JButton boutonModeClassique = new JButton("Mode Classique");
    private JButton boutonModeDayNight = new JButton("Mode Day and Night");
    private JButton boutonModeHighLife = new JButton("Mode High Life");
    private JButton boutonGenAleatoire = new JButton("Génération aléatoire");
    private JButton boutonClearGrille = new JButton("Reset la Grille");
    private JButton boutonChoisirCouleur = new JButton("Choisir une Couleur");

    private Thread startThread=null;
    private JSlider sliderVitesse = new JSlider(JSlider.VERTICAL, 0, 100, 50);
    private int vitesse = 100;
    private int cellSize = 20; // Taille de chaque cellule
    private Color deadColor = Color.WHITE; // Couleur des cellules mortes


    /**
     * Constructeur de la classe JeuDeLaVieUI
     * @param jeu JeuDeLaVie
     */
    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        this.jeu = jeu;
        setPreferredSize(new Dimension(jeu.getxMax() * cellSize, jeu.getyMax() * cellSize));
    }

    /**
     * Méthode pour actualiser l'affichage
     *
     */
    @Override
    public void actualise() {
        repaint();
    }

    /**
     * Méthode pour dessiner la grille du jeu de la vie
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if (jeu.getGrilleXY(x, y).estVivante()) {

                    g.setColor(jeu.getGrilleXY(x, y).getColor());
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                } else {
                    g.setColor(deadColor);
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
                // Dessiner une bordure autour de chaque cellule
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }

    /**
     * Méthode pour positionner la fenêtre et les boutons
     * @param frame
     */
    public void PositionneFenetreEtBouton(JFrame frame) {
        frame.setLayout(new BorderLayout());
        //La Taille de la fenetre doit être de 1182 x 1050 pour que la grille soit bien affichée




        JPanel buttonsPanel = new JPanel(new GridLayout(8, 1)); // 3 lignes, 1 colonne
        buttonsPanel.add(boutonSkipGen);
        buttonsPanel.add(boutonStructures);
        buttonsPanel.add(boutonModeClassique);
        buttonsPanel.add(boutonModeDayNight);
        buttonsPanel.add(boutonModeHighLife);
        buttonsPanel.add(boutonGenAleatoire);
        buttonsPanel.add(boutonClearGrille);
        buttonsPanel.add(boutonChoisirCouleur);
        // La Taille de la frame doit être de 1182 x 1050 pour que la grille soit bien affichée




        frame.add(this, BorderLayout.CENTER);
        frame.add(boutonStart, BorderLayout.NORTH);
        frame.add(boutonStop, BorderLayout.SOUTH);
        frame.add(sliderVitesse, BorderLayout.WEST);
        frame.add(buttonsPanel, BorderLayout.EAST);

        // Calcul de la taille de la fenêtre en fonction de la grille et de la taille des cellules et des boutons
        int windowWidth = jeu.getxMax() * cellSize ;
        int windowHeight = jeu.getyMax() * cellSize ;


        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Positionne les boutons collé a la grille

        boutonStart.setBounds(0, 0, 100, 50);
        boutonStop.setBounds(0, 50, 100, 50);
        sliderVitesse.setBounds(0, 150, 100, 100);
        boutonSkipGen.setBounds(0, 0, 40, 50);
        boutonStructures.setBounds(0, 0, 40, 50);
        boutonModeClassique.setBounds(0, 0, 40, 50);
        boutonModeDayNight.setBounds(0, 0, 40, 50);
        boutonModeHighLife.setBounds(0, 0, 40, 50);
        boutonGenAleatoire.setBounds(0, 0, 40, 50);
        boutonClearGrille.setBounds(0, 0, 40, 50);
        boutonChoisirCouleur.setBounds(0, 0, 40, 50);

        // Ajouter des actions aux boutons
        boutonStart.addActionListener(e -> {
            if(startThread == null){
                startThread = new Thread(() -> {
                    while (true) {
                        jeu.calculerGenerationSuivante();
                        try {
                            Thread.sleep(JeuDeLaVieUI.this.vitesse);
                        } catch (InterruptedException ex) {
                            break;
                        }
                    }
                });
                startThread.start();
            }
        });

        boutonStop.addActionListener(e -> {
            // Arrêter le thread de boutonStart
            if (startThread != null){
                startThread.interrupt();
                startThread = null;
            }
        });

        boutonSkipGen.addActionListener(e -> jeu.calculerGenerationSuivante());

        sliderVitesse.addChangeListener(e -> this.vitesse = sliderVitesse.getValue());


        boutonStructures.addActionListener(e -> {
            // Afficher la fenêtre pop-up pour sélectionner une structure
            FabriqueStructure fabrique = new FabriqueStructure();
            fabrique.afficherFenetreStructures(this);
        });

        boutonModeClassique.addActionListener(e -> {
            // Change le visiteur pour le mode classique
            jeu.setVisiteur(new VisiteurClassique(this.jeu));
        });

        boutonModeDayNight.addActionListener(e -> {
            // Change le visiteur pour le mode Day and Night
            jeu.setVisiteur(new VisiteurDayNight(this.jeu));
        });

        boutonModeHighLife.addActionListener(e -> {
            // Change le visiteur pour le mode High Life
            jeu.setVisiteur(new VisiteurHighLife(this.jeu));
        });

        boutonGenAleatoire.addActionListener(e -> {
            // Générer une grille aléatoire
            jeu.initaliseGrilleRandom();
            actualise();
        });


        boutonChoisirCouleur.addActionListener(e -> {
            Color selectedColor = choisirCouleur();
            if (selectedColor != null) {
                // appliquer la couleur choisie à toutes les cellules
                for (int x = 0; x < jeu.getxMax(); x++) {
                    for (int y = 0; y < jeu.getyMax(); y++) {
                        jeu.getGrilleXY(x, y).setColor(selectedColor);
                    }
                }
                actualise();
            }
        });


        boutonClearGrille.addActionListener(e -> {
            // Réinitialiser la grille
            jeu.initialiseGrilleVide();
            actualise();
        });



        // Si l'utilisateur clique sur une cellule, elle devient vivante si elle était morte et vice versa
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    // Obtention des coordonnées x et y du clic de souris
                    int mouseX = e.getX() / cellSize;
                    int mouseY = e.getY() / cellSize;

                    if(jeu.getGrilleXY(mouseX, mouseY).estVivante()){
                        jeu.getGrilleXY(mouseX, mouseY).meurt();
                        //new CommandeMeurt(jeu.getGrilleXY(mouseX, mouseY));
                    }else{
                        jeu.getGrilleXY(mouseX, mouseY).vit();
                        //new CommandeVit(jeu.getGrilleXY(mouseX, mouseY));
                    }

                    // Mise à jour de l'affichage
                    actualise();

            }
        });

    }

    /**
     * Méthode pour choisir une couleur
     * @return Color couleur choisie
     */
    private Color choisirCouleur() {
        return JColorChooser.showDialog(this, "Choisir une couleur", Color.WHITE);
    }



    /**
     * Méthode pour placer une structure sur la grille
     * @param type
     */
    public void placerStructure(StructureType type) {
        // Création d'un nouveau MouseAdapter pour écouter les clics de souris
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtention des coordonnées x et y du clic de souris
                int mouseX = e.getX() / 20;
                int mouseY = e.getY() / 20;

                // Création de la structure à l'emplacement de la souris
                FabriqueStructure.createStructure(type, jeu, mouseX, mouseY);

                // Mise à jour de l'affichage
                actualise();

                // Retrait du MouseListener après avoir placé la structure
                removeMouseListener(this);
            }
        };

        // Ajout du MouseListener à l'instance de JeuDeLaVieUI
        addMouseListener(mouseAdapter);
    }
}




