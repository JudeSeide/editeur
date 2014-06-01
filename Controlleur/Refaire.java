package Controlleur;

import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Refaire extends JMenuItem implements Commande {

    public Refaire() {
    }

    @Override
    public void execute() {
        try {
            Commande temp = Gardien.getInstance().redo().getCommande();
            if (temp != null) {
                temp.execute();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void unexecute() {
    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
