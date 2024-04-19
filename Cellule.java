import java.awt.Color;
import java.util.Random;

public class Cellule {
    private CelluleEtat etat; // état de la cellule
    private int x ; // coordonnées de la cellule dans la grille
    private int y ;
    public static Color aliveColor = Color.BLACK;

    /**
     * Constructeur de la classe Cellule
     * @param x coordonnée x de la cellule
     * @param y coordonnée y de la cellule
     * @param etat état de la cellule
     */
    public Cellule(int x ,int y,CelluleEtat etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    /**
     * setter de l'attribut etat vivante de la cellule
     *
     */
    public void vit() {
        etat = etat.vit();
    }

    /**
     * setter de l'attribut etat morte de la cellule
     */
    public void meurt() {
        etat = etat.meurt();
    }

    /**
     * getter de l'attribut etat de la cellule
     * @return boolean true si la cellule est vivante, false sinon
     */
    public boolean estVivante() {
        return etat.estVivante();
    }

    public Color getColor(){
        return aliveColor;
    }

    public void setColor(Color c){
        this.aliveColor = c;
    }

    /**
     * getter du nombre de voisines vivantes de la cellule
     * @param jeu jeu de la vie dans lequel se trouve la cellule
     * @return int nombre de voisines vivantes de la cellule
     */
    public int nombreVoisinesVivantes(JeuDeLaVie jeu) {
        int nbVoisinesVivantes = 0;
        for(int i = this.x-1; i <= this.x+1; i++) {
            for(int j = this.y-1; j <= this.y+1; j++) {
                if(i >= 0 && i < jeu.getxMax() && j >= 0 && j < jeu.getyMax() && !(i == this.x && j == this.y) && jeu.getGrilleXY(i, j).estVivante()) {
                    nbVoisinesVivantes++;
                }
            }
        }
        return nbVoisinesVivantes;
    }

    /**
     * méthode qui accepte un visiteur qui change l'état de la cellulea
     * @param visiteur visiteur qui visite la cellule
     */
    public void accepte(Visiteur visiteur){
        etat.accepte(visiteur,this);        
    }



}