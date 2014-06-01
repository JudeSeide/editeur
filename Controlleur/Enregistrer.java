package Controlleur;

import Modele.Buffer;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author spectro
 */
public class Enregistrer extends JMenuItem implements Commande {

    public Enregistrer() {
    }

    private String askFileName() {
        JFrame frame = new JFrame("nomdefichier");
        String filename = JOptionPane.showInputDialog(frame, "Saisissez un nom de fichier");
        return filename;
    }

    private void errorDialog() {
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
        JOptionPane.showMessageDialog(frame,
                "Probleme de creation du fichier ou d'ecriture dans le fichier",
                "Echec d'enregistrement",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void execute() {
        String filename = askFileName();
        if (filename != null) {
            try {
                PrintWriter writer = new PrintWriter(filename, "UTF-8");
                writer.println(Buffer.getInstance().getState());
                writer.close();
            } catch (Exception e) {
                errorDialog();
            }
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
