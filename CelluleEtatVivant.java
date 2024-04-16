public class CelluleEtatVivant implements CelluleEtat{

    protected static CelluleEtatVivant  instance;

    /**
     * Constructeur de CelluleEtatVivant
     */
    private CelluleEtatVivant()   
    {
    }

    /**
     * Méthode qui retourne l'instance de CelluleEtatVivant
     * @return
     */
    public static CelluleEtatVivant getInstance()
    {
        if(instance == null)
        {
            instance = new CelluleEtatVivant();
        }
        return instance;
        
    }

    /**
     * Méthode qui retourne l'instance de CelluleEtatVivant
     * @return instance
     */
    public CelluleEtat vit()
    {
        return this;
    }

    /**
     * Méthode qui retourne l'instance de CelluleEtatMort
     * @return instance
     */
    public CelluleEtat meurt()
    {
        return CelluleEtatMort.getInstance();
    }

    /**
     * Méthode qui retourne un boolean si la cellule est vivante ou non
     * @return
     */
    public boolean estVivante()
    {
        return true;
    }

    /**
     * Méthode qui accepte un visiteur qui change l'état de la cellule
     * @param visiteur
     * @param cellule
     */
    public void accepte(Visiteur visiteur,Cellule cellule){

        visiteur.visiteCelluleVivante(cellule);
        
    }
    
}
