package Vue;

import Controlleur.Coller;
import Controlleur.Commande;
import Controlleur.Copier;
import Controlleur.Couper;
import Controlleur.Defaire;
import Controlleur.Enregistrer;
import Controlleur.Gardien;
import Controlleur.Inserer;
import Controlleur.Refaire;
import Controlleur.RegistreCommande;
import Controlleur.RegistreMacro;
import Controlleur.Selectionner;
import Controlleur.Supprimer;
import java.awt.event.*;

public class Listener implements KeyListener, ActionListener {

    public Listener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Commande commande = (Commande) e.getSource();
        try {
            executeCommand(commande.getClass().newInstance());
        } catch (InstantiationException | IllegalAccessException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Character car = e.getKeyChar();
        if (Character.isLetterOrDigit(car) || car.equals('\t') || car.equals('\n') || car.equals(' ')) {
            String s = Character.toString(car);
            UserInterface.getInstance().setInsertion(s);
            executeCommand(new Inserer());
        }

    }

    private void executeCommand(Commande commande) {
        if (commande.isReversible()) {
            commande = new RegistreCommande(commande);
        }

        if (UserInterface.getInstance().isMacro()) {
            commande = new RegistreMacro(commande);
        }

        commande.execute();
        UserInterface.getInstance().displayCursor();
        UserInterface.getInstance().setRedo(Gardien.getInstance().hasRedo());
        UserInterface.getInstance().setUndo(Gardien.getInstance().hasUndo());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_C:
                    executeCommand(new Copier());
                    break;
                case KeyEvent.VK_V:
                    executeCommand(new Coller());
                    break;
                case KeyEvent.VK_X:
                    executeCommand(new Couper());
                    break;
                case KeyEvent.VK_Z:
                    executeCommand(new Defaire());
                    break;
                case KeyEvent.VK_Y:
                    executeCommand(new Refaire());
                    break;
                case KeyEvent.VK_S:
                    executeCommand(new Enregistrer());
                    break;
            }
        } else if (e.isShiftDown()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    executeCommand(new Selectionner());
                    break;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            executeCommand(new Supprimer());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
