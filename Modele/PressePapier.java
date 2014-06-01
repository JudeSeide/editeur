package Modele;

/**
 * @author Jude Seide aka spectro
 */
public class PressePapier {

	private static PressePapier instance = null;
	private String contenu = "";// le contenu du PressePapier

	/**
	*Constructeur pour eviter l'appel du constructeur par defaut
	*/
	private PressePapier(){}
	
	/**
	*@return String contenu le contenu du PressePapier	
	*/
	public String getContenu() {
		return contenu;
	}

	/**
	*@param contenu, modifie le contenu du PressePapier par la chaine contenu		
	*/
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	/**
	*@return PressePapier instance, l'instance PressePapier	
	*/
	public synchronized static PressePapier getInstance() {
		if (instance == null) {
			instance = new PressePapier();
		}
		return instance;
	}

}
