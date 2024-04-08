public class CommandeMeurt extends Commande{

    public CommandeMeurt(Cellule c){
        super(c);
    }

    @Override
    public void executer(){
        cellule.meurt();
    }
    
}
