public class Cellule {
    private CelluleEtat etat;
    private int x ;
    private int y ;

    public Cellule(int x ,int y,CelluleEtat etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    public void vit() {
        etat = etat.vit();
    }

    public void meurt() {
        etat = etat.meurt();
    }

    public boolean estVivante() {
        return etat.estVivante();
    }

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

    public void accepte(Visiteur visiteur){
        etat.accepte(visiteur,this);        
    }



}