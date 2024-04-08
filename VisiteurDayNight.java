public class VisiteurDayNight extends Visiteur {
        
        
        public VisiteurDayNight(JeuDeLaVie jeu){
            super(jeu);
        }
    
        public void visiteCelluleMorte(Cellule c){
            int nbVoisin;
            nbVoisin = c.nombreVoisinesVivantes(this.jeu);
            if(nbVoisin == 3 || nbVoisin >= 6){
                this.jeu.ajouteCommande(new CommandeVit(c));
            }
    
        }
    
        public void visiteCelluleVivante(Cellule c){
            int nbVoisin;
            nbVoisin = c.nombreVoisinesVivantes(this.jeu);
            if(nbVoisin < 3 || nbVoisin == 5){
                this.jeu.ajouteCommande(new CommandeMeurt(c));
            }
    
        }
}
