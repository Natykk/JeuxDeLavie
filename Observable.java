public interface Observable {


    /**
     *
     * @param o
     */
    void attacheObservateur(Observateur o);

    /**
     *  Détache un observateur de la liste des observateurs
     * @param o
     */
    void detacheObservateur(Observateur o);

    /**
     * Notifie tous les observateurs contenu dans la liste des observateurs
     */
    void notifieObservateurs();
}
