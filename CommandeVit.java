public class CommandeVit extends Commande{

    /**
     * Constructeur de CommandeVit
     * @param c
     */
    public CommandeVit(Cellule c) {
        super(c);
    }

    /**
     * Méthode qui exécute la commande qui fait vivre la cellule
     */
    @Override
    public void executer() {
        cellule.vit();
    }
}
