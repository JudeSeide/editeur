package Controlleur;

import Modele.Buffer;
import Modele.PressePapier;
import Vue.UserInterface;
import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Coller extends JMenuItem implements Commande {

    private String selectionner = "";
    private int debut;
    private int fin;

    public Coller() {
    }

    @Override
    public void execute() {
        debut = UserInterface.getInstance().getDebut();
        fin = UserInterface.getInstance().getFin();
        selectionner = Buffer.getInstance().selectionner(debut, fin);
        Buffer.getInstance().coller(debut, fin);
    }

    @Override
    public void unexecute() {
        fin = debut + PressePapier.getInstance().getContenu().length();
        Buffer.getInstance().inserer(debut, fin, selectionner);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}