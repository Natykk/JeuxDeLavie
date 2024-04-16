public class ObservateurTexte implements Observateur{
    private JeuDeLaVie monJeu;

    /**
     * Constructeur de la classe ObservateurTexte
     * @param jeu
     */
    public ObservateurTexte(JeuDeLaVie jeu) {
        this.monJeu = jeu;

        
    }

    /**
     * Méthode pour actualiser l'affichage textuel

     */
    public void actualise(){
        System.out.println("Generation : "+this.monJeu.getGeneration());
        System.out.println("Nombre de cellule vivante -> "+this.monJeu.NbCelluleVivante());
    }
    
}
