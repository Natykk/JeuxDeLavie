public interface CelluleEtat {



    CelluleEtat vit(); /** setter de l'attribut etat vivante de la cellule */
    CelluleEtat meurt(); // setter de l'attribut etat morte de la cellule

    boolean estVivante(); //getter de l'attribut etat de la cellule

    /**
     * méthode qui accepte un visiteur qui change l'état de la cellule
     * @param visiteur visiteur qui visite la cellule
     * @param cellule cellule à visiter
     */
    void accepte(Visiteur visiteur,Cellule cellule);

}
