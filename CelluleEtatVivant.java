public class CelluleEtatVivant implements CelluleEtat{

    protected static CelluleEtatVivant  instance;

    private CelluleEtatVivant()   
    {
    }

    public static CelluleEtatVivant getInstance()
    {
        if(instance == null)
        {
            instance = new CelluleEtatVivant();
        }
        return instance;
        
    }

    public CelluleEtat vit()
    {
        return this;
    }

    public CelluleEtat meurt()
    {
        return CelluleEtatMort.getInstance();
    }

    public boolean estVivante()
    {
        return true;
    }

    public void accepte(Visiteur visiteur,Cellule cellule){

        visiteur.visiteCelluleVivante(cellule);
        
    }
    
}
