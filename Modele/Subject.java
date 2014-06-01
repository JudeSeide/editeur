package Modele;

import Controlleur.Observer;

/**
 * @author Jude Seide aka spectro
 */
public interface Subject{
        /**
	 * Ajouter un nouvel observateur
	 * @param observer,l'observateur a ajouter
	 */
	public void attach(Observer observer);

	/**
	 * Supprimer un observateur
	 * @param observer,l'observateur a retirer
	 */
	public void detach(Observer observer);

	/**
	 * Notifier aux observateurs que le contenu a été modifié
	 */
	public void notifier();
}
