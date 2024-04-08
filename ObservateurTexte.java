public class ObservateurTexte implements Observateur{
    private JeuDeLaVie monJeu;
    

    public ObservateurTexte(JeuDeLaVie jeu) {
        this.monJeu = jeu;

        
    }

    public void actualise(){
        System.out.println("Generation : "+this.monJeu.getGeneration());
        System.out.println("Nombre de cellule vivante -> "+this.monJeu.NbCelluleVivante());
    }
    
}
