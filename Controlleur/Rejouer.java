package Controlleur;

import Modele.Buffer;
import Vue.UserInterface;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JMenuItem;

/**
 * @author Jude Seide aka spectro
 */
public class Rejouer extends JMenuItem implements Commande {
    
    private Iterator<MementoMacro> iterator = Gardien.getInstance().listeMacros().listIterator(0);

    public Rejouer() {
    }
    

    @Override
    public void execute() {
        UserInterface.getInstance().setText("");
        Buffer.getInstance().vider();
        String tempon = Buffer.getInstance().getTempon();
        Buffer.getInstance().inserer(0, 0, tempon);

        final Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                if (iterator.hasNext()) {
                    iterator.next().getCommande().execute();
                }
            }
        }, 0, 100);

    }

    @Override
    public void unexecute() {
    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
