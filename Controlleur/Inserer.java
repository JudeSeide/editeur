package Controlleur;

import Modele.Buffer;
import Vue.UserInterface;

/**
 * @author Jude Seide aka spectro
 */
public class Inserer implements Commande {

    private String selectionner = "";
    private int debut;
    private int fin;
    private String inseree = UserInterface.getInstance().getInsertion();
    
    public Inserer() {
    }

    @Override
    public void execute() {
        debut = UserInterface.getInstance().getDebut();
        fin = UserInterface.getInstance().getFin();
        selectionner = Buffer.getInstance().selectionner(debut, fin);
        Buffer.getInstance().inserer(debut, fin, inseree);
    }

    @Override
    public void unexecute() {
        Buffer.getInstance().inserer(debut, debut + 1, selectionner);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
