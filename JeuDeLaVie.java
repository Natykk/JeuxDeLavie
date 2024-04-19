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
        // La Taille de la fenêtre doit être de 1182 x 1050 pour que la grille soit bien affichée
        Frame.setSize(1182, 1050);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Frame.add(jeuUI);
        Frame.setResizable(false);
        Frame.setVisible(true);
        jeuUI.PositionneFenetreEtBouton(Frame);
        

    }
}
