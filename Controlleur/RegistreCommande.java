package Controlleur;

/**
 * @author Jude Seide aka spectro
 */
public class RegistreCommande implements Commande {

    private Commande commande;

    public RegistreCommande(Commande commande) {
        this.commande = commande;
    }

    public void restore(MementoCommande m) {
        commande = m.getCommande();
    }

    private void save() {        
        Gardien.getInstance().clearRedo();
        Gardien.getInstance().addCommand(new MementoCommande(commande));
    }

    @Override
    public void execute() {
        commande.execute();
        save();
    }

    @Override
    public void unexecute() {
    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
