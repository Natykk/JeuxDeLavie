public abstract class Commande {
    protected Cellule cellule;

    /**
     * Constructeur de Commande
     * @param cellule
     */
    public Commande(Cellule cellule) {
        this.cellule = cellule;
    }

    /**
     * Méthode qui exécute la commande
     */
    public void executer() {

    }
}
