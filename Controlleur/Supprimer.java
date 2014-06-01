package Controlleur;

import Modele.Buffer;
import Vue.UserInterface;
import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Supprimer extends JMenuItem implements Commande {

    private String selectionner = "";
    private int debut;
    private int fin;

    public Supprimer() {
    }

    @Override
    public void execute() {
        debut = UserInterface.getInstance().getDebut();
        fin = UserInterface.getInstance().getFin();
        selectionner = Buffer.getInstance().selectionner(debut, fin);
        if (debut == fin) {
            selectionner = Buffer.getInstance().selectionner(debut - 1, fin);
        }
        Buffer.getInstance().supprimer(debut, fin);
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
