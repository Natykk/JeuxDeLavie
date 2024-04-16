public class VisiteurHighLife  extends Visiteur{
    /**
     * Constructeur de VisiteurHighLife
     * @param jeu JeuDeLaVie
     */
    public VisiteurHighLife(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Méthode qui vérifie si une cellule morte doit vivre ou non selon les règles du jeu HighLife
     * @param c Cellule
     */
    public void visiteCelluleMorte(Cellule c){
        int nbVoisin;
        nbVoisin = c.nombreVoisinesVivantes(this.jeu);
        if(nbVoisin == 3 || nbVoisin == 6){
            this.jeu.ajouteCommande(new CommandeVit(c));
        }

    }

    /**
     * Méthode qui vérifie si une cellule vivante doit mourir ou non selon les règles du jeu HighLife
     * @param c Cellule
     */
    public void visiteCelluleVivante(Cellule c){
        int nbVoisin;
        nbVoisin = c.nombreVoisinesVivantes(this.jeu);
        if(nbVoisin < 2 || nbVoisin > 3){
            this.jeu.ajouteCommande(new CommandeMeurt(c));
        }

    }
}
