public class VisiteurClassique extends Visiteur {
    
    
    public VisiteurClassique(JeuDeLaVie jeu){
        super(jeu);
    }

    public void visiteCelluleMorte(Cellule c){
        int nbVoisin;
        nbVoisin = c.nombreVoisinesVivantes(this.jeu);
        if(nbVoisin == 3){
            this.jeu.ajouteCommande(new CommandeVit(c));
        }


    }

    public void visiteCelluleVivante(Cellule c){
        int nbVoisin;
        nbVoisin = c.nombreVoisinesVivantes(this.jeu);
        if(nbVoisin < 2 || nbVoisin > 3){
            this.jeu.ajouteCommande(new CommandeMeurt(c));
        }
    
    }
}
