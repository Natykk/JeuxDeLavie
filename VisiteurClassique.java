public class VisiteurClassique extends Visiteur {

    /**
     * Constructeur de VisiteurClassique
     * @param jeu
     */
    public VisiteurClassique(JeuDeLaVie jeu){
        super(jeu);
    }

    /**
     * Méthode qui vérifie si une cellule morte doit vivre ou non selon les règles du jeu classique
     * @param c Cellule
     */
    public void visiteCelluleMorte(Cellule c){
        int nbVoisin;
        nbVoisin = c.nombreVoisinesVivantes(this.jeu);
        if(nbVoisin == 3){
            this.jeu.ajouteCommande(new CommandeVit(c));
        }

    }

    /**
     * Méthode qui vérifie si une cellule vivante doit mourir ou non selon les règles du jeu classique
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
