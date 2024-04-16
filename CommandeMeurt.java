public class CommandeMeurt extends Commande{
    /**
     * Constructeur de CommandeMeurt
     * @param c
     */
    public CommandeMeurt(Cellule c){
        super(c);
    }

    /**
     * Méthode qui exécute la commande qui tue la cellule
     */
    @Override
    public void executer(){
        cellule.meurt();
    }
}
