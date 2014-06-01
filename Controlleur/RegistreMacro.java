package Controlleur;

/**
 * @author Jude Seide aka spectro
 */
public class RegistreMacro implements Commande {

    private Commande commande;

    public RegistreMacro(Commande commande) {
        this.commande = commande;
    }

    private void save() {
        Gardien.getInstance().addMacro(new MementoMacro(commande));
    }

    public void restore(MementoMacro m) {
        commande = m.getCommande();
    }

    @Override
    public void execute() {
        commande.execute();
        save();
    }

    @Override
    public void unexecute() {
    }

    public boolean isReversible() {
        return false;
    }
}
