package Controlleur;

/**
 * @author Jude Seide aka spectro
 */
class MementoCommande {

    private Commande commande;

    public MementoCommande(Commande commande) {
        this.commande = commande;
    }

    public Commande getCommande() {
        return commande;
    }
}
