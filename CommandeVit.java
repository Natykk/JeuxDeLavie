public class CommandeVit extends Commande{
    
    public CommandeVit(Cellule c) {
        super(c);
    }

    @Override
    public void executer() {

        cellule.vit();
    }
}
