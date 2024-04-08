import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class JeuDeLaVie implements Observable{
    private Cellule[][] grille;
    private int xMax;
    private int yMax;
    private List<Observateur> observateurs;
    private List<Commande> commandes;

    public JeuDeLaVie() {
        this.xMax=600;
        this.yMax=600;
        this.grille = new Cellule[xMax][yMax];
        this.observateurs = new ArrayList<>();
        this.commandes = new ArrayList<>();
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
    }

    public void distribueVisiteur(){
        Visiteur v = new VisiteurClassique(this);
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
    }

    public static void main(String[] args) throws InterruptedException{

        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieUI jeuUI = new JeuDeLaVieUI(jeu);
        jeu.attacheObservateur(jeuUI);



        JFrame Frame = new JFrame();
        Frame.setSize(600,600);
        Frame.add(jeuUI);

        Frame.setVisible(true);

        while(true){
            jeu.calculerGenerationSuivante();
            Thread.sleep(100);
            
        }
        

    }
}
