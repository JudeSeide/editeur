package Modele;

import Controlleur.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jude Seide aka spectro
 */
public class Buffer implements Subject {

    private static Buffer instance = null;
    private StringBuffer contenu = new StringBuffer(1000000); //le contenu du Buffer
    private final List<Observer> observers = new ArrayList<>();
    private int position = 0;
    private String tempon;

    /**
     * Constructeur pour eviter l'appel du constructeur par defaut
     */
    private Buffer() {
    }

    /**
     * @return Buffer instance, l'instance du Buffer
     */
    public synchronized static Buffer getInstance() {
        if (instance == null) {
            instance = new Buffer();
        }
        return instance;
    }

    /**
     * @return String contenu Retourne le contenu du Buffer
     */
    private StringBuffer getContenu() {
        return contenu;
    }

    public void vider() {
        contenu = new StringBuffer(1000000);
    }

    public void setTempon() {
        tempon = contenu.toString();
    }

    public String getTempon() {
        return tempon;
    }

    /**
     * Met dans le presse papier le contenu du Buffer entre les positions debut
     * et fin
     *
     * @param debut
     * @param fin
     */
    public void copier(int debut, int fin) {
        if (debut != fin) {
            PressePapier.getInstance().setContenu(selectionner(debut, fin));
        }
    }

    public int getPosition() {
        return position;
    }

    /**
     * Colle le contenu du PressePapier dans le Buffer
     *
     * @param debut
     * @param fin
     */
    public void coller(int debut, int fin) {
        inserer(debut, fin, PressePapier.getInstance().getContenu());
        notifier();
    }

    /**
     * Copie une chaine du Buffer dans le PressePapier et l'enleve du Buffer
     *
     * @param debut
     * @param fin
     */
    public void couper(int debut, int fin) {
        PressePapier.getInstance().setContenu(selectionner(debut, fin));
        if (debut != fin) {
            supprimer(debut, fin);
            notifier();
        }
    }

    /**
     * @param debut
     * @param fin
     * @return String la chaine entre les positions debut et fin
     */
    public String selectionner(int debut, int fin) {
        if (debut < 0) {
            debut = 0;
        }
        if (fin < 0) {
            fin = 0;
        }
        return contenu.substring(debut, fin);
    }

    /**
     * Supprime du Buffer la chaine entre les positions debut et fin
     *
     * @param debut
     * @param fin
     */
    public void supprimer(int debut, int fin) {
        if (debut == fin && debut > 0) {
            contenu.deleteCharAt(debut - 1);
            position = debut - 1;
        } else if (debut == fin) {
            if (debut > 0) {
                contenu.deleteCharAt(debut);
                position = debut;
            }
        } else if (debut >= 0 && fin >= 0) {
            contenu.delete(debut, fin);
            position = debut;
        }
        notifier();
    }

    /**
     * Insere la chaine dans le Buffer entre les position debut et fin
     *
     * @param debut
     * @param fin
     * @param chaine
     */
    public void inserer(int debut, int fin, String chaine) {
        if (debut == fin) {
            if (debut == Buffer.getInstance().getContenu().length()) {
                Buffer.getInstance().getContenu().append(chaine);
            } else {
                Buffer.getInstance().getContenu().insert(debut, chaine);
            }
        } else {
            Buffer.getInstance().getContenu().delete(debut, fin);
            inserer(debut, debut, chaine);
        }
        position = debut + chaine.length();
        notifier();
    }

    @Override
    /**
     * Ajouter un nouvel Observer
     *
     * @params Observer observer, l'Observer a ajouter
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    /**
     * Supprimer un Observer
     *
     * @params Observer observer, l'Observer a enlever
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    /**
     * Notifie aux Observateurs qu'ils doivent se mettre a jour
     */
    public void notifier() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Permet au UserInterface (ConcereteObserver) de recuperer le contenu du
     * Buffer
     * @return le contenu du Buffer
     */
    public String getState() {
        return getContenu().toString();
    }
}
