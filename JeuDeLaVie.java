import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class JeuDeLaVie implements Observable{
    private Cellule[][] grille;
    private int xMax;
    private int yMax;
    private List<Observateur> observateurs;
    private List<Commande> commandes;
    private int Generation;
    private Visiteur visiteur;

    /**
     * Constructeur de la classe JeuDeLaVie
     *
     *
     */
    public JeuDeLaVie() {
        this.xMax=50;
        this.yMax=50;
        this.grille = new Cellule[xMax][yMax];
        this.observateurs = new ArrayList<>();
        this.commandes = new ArrayList<>();
        this.Generation=0;
        initialiseGrilleVide();
        
        this.visiteur = new VisiteurClassique(this);
    }

    /**
     * initialise la grille avec des uniquement des cellules mortes
     *
     */
    public void initialiseGrilleVide(){
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                this.grille[i][j] = new Cellule(i,j,CelluleEtatMort.getInstance());
            }
        }
        setColorGrille();
    }

    /**
     * Met la couleur des cellules de la grille 
     *
     */
    public void setColorGrille(){
        // Créer un couleur en hexadécimal
        Color couleur = new Color(255, 0, 0);
        int nouvRed = couleur.getRed();
        int nouvGreen = 0;
        int nouvBlue = 0;
        System.out.println(couleur.toString());

        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){

                // on diminue le rouge et on augmente le vert puis on diminue le vert pour augmenter le bleu et on recommence pour faire un dégradé
                if(nouvRed > 0 && nouvGreen < 255 && nouvBlue == 0){
                    nouvRed--;
                    nouvGreen++;
                }
                else if(nouvRed == 0 && nouvGreen > 0 && nouvBlue < 255){
                    nouvGreen--;
                    nouvBlue++;
                }
                else if(nouvRed < 255 && nouvGreen == 0 && nouvBlue > 0){
                    nouvRed++;
                    nouvBlue--;
                }

                couleur = new Color(nouvRed, nouvGreen, nouvBlue);

                grille[i][j].setColor(couleur);



            

            }
        }
    }

    /**
     * initialise la grille avec des cellules vivantes et mortes de manière aléatoire
     *
     */
    public void initaliseGrilleRandom(){
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                if(Math.random() < 0.5){
                    this.grille[i][j] = new Cellule(i, j, CelluleEtatVivant.getInstance());
                }
                else{
                    this.grille[i][j] = new Cellule(i,j,CelluleEtatMort.getInstance());
                }
            }
        }
        setColorGrille();
    }

    /**
     * Recupère la cellule à la position x,y
     * @param x
     * @param y
     * @return
     */
    public Cellule getGrilleXY(int x, int y){
        return grille[x][y];
    }

    /**
     * getter qui retourne la largeur de la grille
     * @return
     */
    public int getxMax(){
        return xMax;
    }

    /**
     * getter qui retourne la hauteur de la grille
     * @return
     */
    public int getyMax(){
        return yMax;
    }

    /**
     * attache un observateur à la liste des observateurs
     * @param o
     */
    public void attacheObservateur(Observateur o){
        observateurs.add(o);
    }

    /**
     * détache un observateur de la liste des observateurs
     * @param o
     */
    public void detacheObservateur(Observateur o){
        observateurs.remove(o);
    }

    /**
     * notifie tous les observateurs contenu dans la liste des observateurs
     */
    public void notifieObservateurs(){
        for(Observateur o : observateurs){
            o.actualise();
        }
    }

    /**
     * ajoute une commande à la liste des commandes
     * @param c
     */
    public void ajouteCommande(Commande c ){
        commandes.add(c);
    }
    /**
     * execute toutes les commandes de la liste des commandes
     */
    public void executeCommandes(){
        for(Commande c : commandes){
            c.executer();
        }
        //commandes.clear();
    }

    /**
     * getter qui retourne le nombre de cellules vivantes dans la grille
     * @return le nombre de cellules vivantes
     */
    public int NbCelluleVivante(){
        int nbVivante=0;
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                if(grille[i][j].estVivante()){
                    nbVivante++;
                }
            }
        }
        return nbVivante;

    }

    /**
     * distribue un visiteur sur toutes les cellules de la grille
     */
    public void distribueVisiteur(){
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                grille[i][j].accepte(this.visiteur);
            }
        }
    }


    /**
     * calcule la génération suivante du jeu
     */
    public void calculerGenerationSuivante(){

        distribueVisiteur();
        executeCommandes();
        notifieObservateurs();
        this.Generation++;
    }

    /**
     * méthode qui set le visiteur du jeu
     * @param v
     */
    public void setVisiteur(Visiteur v){
        this.visiteur = v;
    }

    /**
     * Retourne le numero de la génération actuelle
     * @return
     */
    public int getGeneration(){
        return this.Generation;
    }

    /**
     * Main du programme qui initialise le jeu , les observateurs et la fenêtre
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{

        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieUI jeuUI = new JeuDeLaVieUI(jeu);
        ObservateurTexte ObsText = new ObservateurTexte(jeu);

        jeu.attacheObservateur(jeuUI);
        jeu.attacheObservateur(ObsText);
        JFrame Frame = new JFrame();
        Frame.setSize(600,600);
        Frame.add(jeuUI);
        Frame.setResizable(true);
        Frame.setVisible(true);
        jeuUI.PositionneFenetreEtBouton(Frame);
        

    }
}
