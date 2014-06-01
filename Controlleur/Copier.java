package Controlleur;

import Modele.Buffer;
import Vue.UserInterface;
import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Copier extends JMenuItem implements Commande {

    public Copier() {
    }

    @Override
    public void execute() {
        int debut = UserInterface.getInstance().getDebut();
        int fin = UserInterface.getInstance().getFin();
        Buffer.getInstance().copier(debut, fin);
    }

    @Override
    public void unexecute() {
    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
