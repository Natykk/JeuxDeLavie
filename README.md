# Jeu de la Vie - Projet Utilisant Différents Design Patterns

Ce projet est une implémentation du célèbre "Jeu de la Vie" de John Conway en Java, mettant en œuvre plusieurs design patterns pour une conception modulaire et extensible.
## Introduction

Le "Jeu de la Vie" est un automate cellulaire conçu pour simuler l'évolution de populations basées sur des règles simples. Ce projet propose une implémentation du Jeu de la Vie avec une interface utilisateur graphique (GUI) permettant aux utilisateurs d'interagir avec la simulation.
Fonctionnalités

  - Interface utilisateur graphique (GUI) permettant de visualiser et d'interagir avec la grille du jeu.
  - Possibilité de démarrer, arrêter et avancer d'une génération à la fois.
  - Différents modes de jeu : Classique, Day and Night, High Life.
  - Génération aléatoire de la grille.
  - Placement de structures prédéfinies sur la grille.
  - Choix de couleur pour les cellules vivantes.

## Design Patterns Utilisés

  - Pattern Fabrique (Factory Pattern) : Utilisé pour créer différentes structures préconfigurées sur la grille, comme des planeurs, des oscillateurs, etc. La classe FabriqueStructure implémente ce pattern.
  - Pattern Visiteur (Visitor Pattern) : Utilisé pour distribuer un visiteur à toutes les cellules de la grille lors du calcul de la génération suivante. Trois visiteurs sont implémentés pour différents modes de jeu : Classique, Day and Night, High Life.
  - Pattern Commande (Command Pattern) : Utilisé pour encapsuler les actions effectuées sur les cellules de la grille, comme la naissance ou la mort d'une cellule, ce qui permet de les annuler ou de les rétablir facilement. Les classes CommandeVit et CommandeMeurt implémentent ce pattern.
  - Pattern État (State Pattern) : Bien que non explicitement mentionné, le pattern État est utilisé pour représenter l'état d'une cellule (vivante ou morte) de manière modulaire et extensible.
  - Pattern Observateur (Observer Pattern) : Utilisé pour informer l'interface utilisateur graphique (GUI) des changements dans la grille du jeu, afin de mettre à jour l'affichage en conséquence. Les classes Observateur et JeuDeLaVieUI implémentent ce pattern.

## Installation et Utilisation

    Clonez ce dépôt sur votre machine locale.
    Ouvrez le projet dans votre environnement de développement Java.
    Compilez avec javac JeuDeLaVie.java et exécutez java JeuDeLaVie
    Interagissez avec l'interface utilisateur graphique pour contrôler la simulation du Jeu de la Vie.

## Exemple d'utilisation
  

    public static void main(String[] args) {
        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieUI jeuUI = new JeuDeLaVieUI(jeu);
        ObservateurTexte ObsText = new ObservateurTexte(jeu);
    
        jeu.attacheObservateur(jeuUI);
        jeu.attacheObservateur(ObsText);
        JFrame Frame = new JFrame();
        Frame.setSize(1182, 1050);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        Frame.add(jeuUI);
        Frame.setResizable(false);
        Frame.setVisible(true);
        jeuUI.PositionneFenetreEtBouton(Frame);
    }
