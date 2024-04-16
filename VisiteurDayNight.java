public class VisiteurDayNight extends Visiteur {

    /**
     * Constructeur de VisiteurDayNight
     * @param jeu
     */
    public VisiteurDayNight(JeuDeLaVie jeu){
            super(jeu);
        }

    /**
     * Méthode qui vérifie si une cellule morte doit vivre ou non selon les règles du jeu DayNight
     * @param c Cellule
     */
    public void visiteCelluleMorte(Cellule c){
            int nbVoisin;
            nbVoisin = c.nombreVoisinesVivantes(this.jeu);
            if(nbVoisin == 3 || nbVoisin >= 6){
                this.jeu.ajouteCommande(new CommandeVit(c));
            }
    
        }

    /**
     * Méthode qui vérifie si une cellule vivante doit mourir ou non selon les règles du jeu DayNight
     * @param c Cellule
     */
    public void visiteCelluleVivante(Cellule c){
            int nbVoisin;
            nbVoisin = c.nombreVoisinesVivantes(this.jeu);
            if(nbVoisin < 3 || nbVoisin == 5){
                this.jeu.ajouteCommande(new CommandeMeurt(c));
            }
    
        }
}
