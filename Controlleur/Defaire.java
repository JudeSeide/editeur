package Controlleur;

import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Defaire extends JMenuItem implements Commande {

    public Defaire() {
    }

    @Override
    public void execute() {
        try {
            Commande temp = Gardien.getInstance().undo().getCommande();
            if (temp != null) {
                temp.unexecute();
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
