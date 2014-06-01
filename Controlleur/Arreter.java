package Controlleur;

import Vue.UserInterface;
import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Arreter extends JMenuItem implements Commande {

    public Arreter() {
    }

    @Override
    public void execute() {
        UserInterface.getInstance().setMacro(false);
    }

    @Override
    public void unexecute() {
    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
