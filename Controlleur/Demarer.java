package Controlleur;

import Modele.Buffer;
import Vue.UserInterface;
import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Demarer extends JMenuItem implements Commande {

    public Demarer() {
    }

    @Override
    public void execute() {
        Buffer.getInstance().setTempon();
        Gardien.getInstance().clearListeMacros();
        UserInterface.getInstance().setMacro(true);
    }

    @Override
    public void unexecute() {
    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
