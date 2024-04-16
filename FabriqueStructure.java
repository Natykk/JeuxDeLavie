
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FabriqueStructure {
    /**
     * Affiche une fenêtre permettant de choisir une structure à placer
     * @param jeu
     */
    public void afficherFenetreStructures(JeuDeLaVieUI jeu) {
        JFrame popupFrame = new JFrame("Structures");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Utilisation de BoxLayout avec orientation Y

        JButton boutonStructure1 = new JButton("Structure 1 (Glider)");
        JButton boutonStructure2 = new JButton("Structure 2 (Glider Gun)");
        JButton boutonStructure3 = new JButton("Structure 3 (clignotant)");
        JButton boutonStructure4 = new JButton("Structure 4 (Beacon)");
        JButton boutonStructure5 = new JButton("Structure 5 (Bloc)");
        JButton boutonStructure6 = new JButton("Structure 6 (Bateau)");

        boutonStructure1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.placerStructure(StructureType.GLIDER);
                popupFrame.dispose();
            }
        });

        boutonStructure2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.placerStructure(StructureType.GLIDER_GUN);
                popupFrame.dispose();
            }
        });

        boutonStructure3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.placerStructure(StructureType.CLIGNOTANT);
                popupFrame.dispose();
            }
        });


        boutonStructure4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.placerStructure(StructureType.BEACON);
                popupFrame.dispose();
            }
        });

        boutonStructure5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.placerStructure(StructureType.BLOC);
                popupFrame.dispose();
            }
        });


        boutonStructure6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.placerStructure(StructureType.BATEAU);
                popupFrame.dispose();
            }
        });



        // Ajoutez des ActionListener pour les autres boutons de structure de la même manière

        panel.add(boutonStructure1);
        panel.add(boutonStructure2);
        panel.add(boutonStructure3);
        panel.add(boutonStructure4);
        panel.add(boutonStructure5);
        panel.add(boutonStructure6);

        popupFrame.add(panel);
        popupFrame.pack(); // Ajuste automatiquement la taille de la fenêtre en fonction du contenu
        popupFrame.setVisible(true);
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Choisis une structure à placer dans la grille
     * @param type
     * @param jeu
     * @param x
     * @param y
     */
    public static void createStructure(StructureType type, JeuDeLaVie jeu, int x, int y) {
        switch (type) {
            case GLIDER:
                jeu.getGrilleXY(x, y).vit();
                jeu.getGrilleXY(x + 1, y + 1).vit();
                jeu.getGrilleXY(x + 2, y + 1).vit();
                jeu.getGrilleXY(x, y + 2).vit();
                jeu.getGrilleXY(x + 1, y + 2).vit();
                break;
            case GLIDER_GUN:
                jeu.getGrilleXY(x+1,y+5).vit();
                jeu.getGrilleXY(x+1,y+6).vit();
                jeu.getGrilleXY(x+2,y+5).vit();
                jeu.getGrilleXY(x+2,y+6).vit();
                jeu.getGrilleXY(x+11,y+5).vit();
                jeu.getGrilleXY(x+11,y+6).vit();
                jeu.getGrilleXY(x+11,y+7).vit();
                jeu.getGrilleXY(x+12,y+4).vit();
                jeu.getGrilleXY(x+12,y+8).vit();
                jeu.getGrilleXY(x+13,y+3).vit();
                jeu.getGrilleXY(x+13,y+9).vit();
                jeu.getGrilleXY(x+14,y+3).vit();
                jeu.getGrilleXY(x+14,y+9).vit();
                jeu.getGrilleXY(x+15,y+6).vit();
                jeu.getGrilleXY(x+16,y+4).vit();
                jeu.getGrilleXY(x+16,y+8).vit();
                jeu.getGrilleXY(x+17,y+5).vit();
                jeu.getGrilleXY(x+17,y+6).vit();
                jeu.getGrilleXY(x+17,y+7).vit();
                jeu.getGrilleXY(x+18,y+6).vit();
                jeu.getGrilleXY(x+21,y+3).vit();
                jeu.getGrilleXY(x+21,y+4).vit();
                jeu.getGrilleXY(x+21,y+5).vit();
                jeu.getGrilleXY(x+22,y+3).vit();
                jeu.getGrilleXY(x+22,y+4).vit();
                jeu.getGrilleXY(x+22,y+5).vit();
                jeu.getGrilleXY(x+23,y+2).vit();
                jeu.getGrilleXY(x+23,y+6).vit();
                jeu.getGrilleXY(x+25,y+1).vit();
                jeu.getGrilleXY(x+25,y+2).vit();
                jeu.getGrilleXY(x+25,y+6).vit();
                jeu.getGrilleXY(x+25,y+7).vit();
                jeu.getGrilleXY(x+35,y+3).vit();
                jeu.getGrilleXY(x+35,y+4).vit();
                jeu.getGrilleXY(x+36,y+3).vit();
                jeu.getGrilleXY(x+36,y+4).vit();
                break;
            case CLIGNOTANT:
                jeu.getGrilleXY(x,y).vit();
                jeu.getGrilleXY(x+1,y).vit();
                jeu.getGrilleXY(x+2,y).vit();
                break;
            case BEACON:
                jeu.getGrilleXY(x,y).vit();
                jeu.getGrilleXY(x+1,y).vit();
                jeu.getGrilleXY(x,y+1).vit();
                jeu.getGrilleXY(x+2,y+2).vit();
                jeu.getGrilleXY(x+1,y+2).vit();
                jeu.getGrilleXY(x+2,y+1).vit();
                break;
            case BLOC:
                jeu.getGrilleXY(x,y).vit();
                jeu.getGrilleXY(x+1,y).vit();
                jeu.getGrilleXY(x,y+1).vit();
                jeu.getGrilleXY(x+1,y+1).vit();
                break;
            case BATEAU:
                jeu.getGrilleXY(x,y).vit();
                jeu.getGrilleXY(x+1,y).vit();
                jeu.getGrilleXY(x,y+1).vit();
                jeu.getGrilleXY(x+2,y+1).vit();
                jeu.getGrilleXY(x+1,y+2).vit();
                break;
        }
    }
}