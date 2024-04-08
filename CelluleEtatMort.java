public class CelluleEtatMort implements CelluleEtat{

    protected static CelluleEtatMort  instance;
    

    private CelluleEtatMort()   
    {
    }

    public static CelluleEtatMort getInstance()
    {
        if(instance == null)
        {
            instance = new CelluleEtatMort();
        }
        return instance;
    }

    public CelluleEtat vit()
    {
        return CelluleEtatVivant.getInstance();
    }

    public CelluleEtat meurt()
    {
        return this;
    }

    public boolean estVivante()
    {
        return false;
    }

    public void accepte(Visiteur visiteur,Cellule cellule){

        visiteur.visiteCelluleMorte(cellule);

    }


}
