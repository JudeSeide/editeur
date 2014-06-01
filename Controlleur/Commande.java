package Controlleur;

/**
 * @author Jude Seide aka spectro
 */
public interface Commande {

    void execute();

    void unexecute();

    boolean isReversible();
}
