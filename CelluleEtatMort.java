public class CelluleEtatMort implements CelluleEtat{

    protected static CelluleEtatMort  instance;

    /**
     * Constructeur de CelluleEtatMort
     */
    private CelluleEtatMort()   
    {
    }

    /**
     * Méthode qui retourne l'instance de CelluleEtatMort
     * @return instance
     */
    public static CelluleEtatMort getInstance()
    {
        if(instance == null)
        {
            instance = new CelluleEtatMort();
        }
        return instance;
    }

    /**
     * Méthode qui retourne l'instance de CelluleEtatVivant
     * @return instance
     */
    public CelluleEtat vit()
    {
        return CelluleEtatVivant.getInstance();
    }

    /**
     * Méthode qui retourne l'instance de CelluleEtatMort
     * @return instance
     */
    public CelluleEtat meurt()
    {
        return this;
    }

    /**
     * Méthode qui retourne un boolean si la cellule est vivante ou non
     * @return boolean
     */
    public boolean estVivante()
    {
        return false;
    }

    /**
     * Méthode qui accepte un visiteur qui change l'état de la cellule
     * @param visiteur
     * @param cellule
     */
    public void accepte(Visiteur visiteur,Cellule cellule){

        visiteur.visiteCelluleMorte(cellule);

    }


}
