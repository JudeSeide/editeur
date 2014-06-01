package Controlleur;

import Modele.Buffer;
import Vue.UserInterface;
import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Couper extends JMenuItem implements Commande {

    private String selectionner = "";
    private int debut;
    private int fin;

    public Couper() {
    }

    @Override
    public void execute() {
        debut = UserInterface.getInstance().getDebut();
        fin = UserInterface.getInstance().getFin();
        selectionner = Buffer.getInstance().selectionner(debut, fin);
        Buffer.getInstance().couper(debut, fin);
    }

    @Override
    public void unexecute() {
        Buffer.getInstance().inserer(debut, debut, selectionner);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
