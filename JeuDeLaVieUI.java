import javax.swing.*;
import java.awt.*;
public class JeuDeLaVieUI extends JPanel implements Observateur{
    private JeuDeLaVie jeu;
    private JButton boutonStart = new JButton("Start");
    private JButton boutonStop = new JButton("Stop");
    private JButton boutonSkipGen = new JButton("Skip 1 Generation");
    private Thread startThread;
    private JSlider sliderVitesse = new JSlider(JSlider.VERTICAL,0,100,50);
    private int vitesse = 100;
    private int Height = 30;
    private int Width = 30;

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
                    g.fillOval(x*Width, y*Height, Width, Height);
                }
               
            }
        }
    }

    public void PositionneFenetreEtBouton(JFrame frame){
        frame.add(this);
        frame.add(boutonStart, BorderLayout.NORTH);
        frame.add(boutonStop, BorderLayout.SOUTH);
        frame.add(boutonSkipGen, BorderLayout.EAST);
        frame.add(sliderVitesse, BorderLayout.WEST);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //Positionne les boutons
        boutonStart.setBounds(0,0,100,50);
        boutonStop.setBounds(0,50,100,50);
        boutonSkipGen.setBounds(0,100,100,50);
        sliderVitesse.setBounds(0,150,100,100);

        //Ajoute les actions aux boutons

        boutonStart.addActionListener(e -> {
            startThread = new Thread(){

                public void run(){
                    while(true){
                        jeu.calculerGenerationSuivante();
                        try{
                            Thread.sleep(JeuDeLaVieUI.this.vitesse);
                        }catch(InterruptedException e){
                            break;
                        }
                    }
                }
            };
            startThread.start();
        });

        boutonStop.addActionListener(e -> {
            //Stop le thread de boutonStart
            startThread.interrupt();
        });

        boutonSkipGen.addActionListener(e -> {
            jeu.calculerGenerationSuivante();
        });

        sliderVitesse.addChangeListener(e ->{
            //Change la vitesse du jeu
            this.vitesse = sliderVitesse.getValue();
        });

        //quand l'utilisateur clique sur une case du jeu, la cellule correspondante change d'état
        this.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent e){
                int x = e.getX()/Width;
                int y = e.getY()/Height;
                if(jeu.getGrilleXY(x,y).estVivante()){
                    jeu.getGrilleXY(x,y).meurt();
                }else{
                    jeu.getGrilleXY(x,y).vit();
                }
                repaint();
            }
        });

    }

    
}
