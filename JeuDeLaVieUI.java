import javax.swing.*;
import java.awt.*;
public class JeuDeLaVieUI extends JPanel implements Observateur{
    private JeuDeLaVie jeu;


    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actualise() {
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);

        for(int x=0; x<jeu.getxMax();x++){
            for(int y=0; y<jeu.getyMax();y++){
                if(jeu.getGrilleXY(x,y).estVivante()){
                    g.fillOval(x*3, y*3, 3, 3);
                }
               
            }
        }
    }

    
}
