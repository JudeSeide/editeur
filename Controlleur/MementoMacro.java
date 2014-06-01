package Controlleur;

/**
 * @author Jude Seide aka spectro
 */
class MementoMacro {

    private final Commande commande;

    public MementoMacro(Commande commande) {
        this.commande = commande;
    }

    public Commande getCommande() {
        return commande;
    }
}
