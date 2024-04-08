import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class JeuDeLaVie implements Observable{
    private Cellule[][] grille;
    private int xMax;
    private int yMax;
    private List<Observateur> observateurs;
    private List<Commande> commandes;
    private int Generation;

    public JeuDeLaVie() {
        this.xMax=50;
        this.yMax=50;
        this.grille = new Cellule[xMax][yMax];
        this.observateurs = new ArrayList<>();
        this.commandes = new ArrayList<>();
        this.Generation=0;
        initialiseGrille();
    }

    public void initialiseGrille(){
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

    public void StructureDepart(int numeroConfig){
        
        // Ajoute les différentes structures de départ dans la grille du jeux de la vie selon le design pattern Factory
        


    }

    public Cellule getGrilleXY(int x, int y){
        return grille[x][y];
    }

    public int getxMax(){
        return xMax;
    }

    public int getyMax(){
        return yMax;
    }

    public void attacheObservateur(Observateur o){
        observateurs.add(o);
    }

    public void detacheObservateur(Observateur o){
        observateurs.remove(o);
    }

    public void notifieObservateurs(){
        for(Observateur o : observateurs){
            o.actualise();
        }
    }

    public void ajouteCommande(Commande c ){
        commandes.add(c);
    }

    public void executeCommandes(){
        for(Commande c : commandes){
            c.executer();
        }
        //commandes.clear();
    }

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

    public void distribueVisiteur(){
        Visiteur v = new VisiteurClassique(this);
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                grille[i][j].accepte(v);
            }
        }
    
    }

    public void distribueVisiteurHighLife(){
        Visiteur v = new VisiteurHighLife(this);
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                grille[i][j].accepte(v);
            }
        }
    
    }

    public void distribueVisiteurDayNight(){
        Visiteur v = new VisiteurDayNight(this);
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                grille[i][j].accepte(v);
            }
        }
    
    }

    public void calculerGenerationSuivante(){
        distribueVisiteur();
        executeCommandes();
        notifieObservateurs();
        this.Generation++;
    }

    public int getGeneration(){
        return this.Generation;
    }

    public static void main(String[] args) throws InterruptedException{

        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieUI jeuUI = new JeuDeLaVieUI(jeu);
        ObservateurTexte ObsText = new ObservateurTexte(jeu);

        jeu.attacheObservateur(jeuUI);
        jeu.attacheObservateur(ObsText);
        JFrame Frame = new JFrame();
        Frame.setSize(600,600);
        Frame.add(jeuUI);

        Frame.setVisible(true);
        jeuUI.PositionneFenetreEtBouton(Frame);
        

    }
}
