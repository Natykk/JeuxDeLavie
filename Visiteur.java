public abstract class Visiteur {
    protected JeuDeLaVie jeu;


    public Visiteur(JeuDeLaVie jeu){
        this.jeu=jeu;
    }

    /**
     * Méthode qui vérifie si une cellule morte doit vivre ou non selon les règles du jeu
     * @param cellule
     */
    public void visiteCelluleVivante(Cellule cellule){

    }

    /**
     * Méthode qui vérifie si une cellule vivante doit mourir ou non selon les règles du jeu
     * @param cellule
     */
    public void visiteCelluleMorte(Cellule cellule){

        

    }
}
