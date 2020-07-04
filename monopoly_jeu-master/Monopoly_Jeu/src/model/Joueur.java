package model;

/**
 * Cette classe represente un joueur
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public abstract class Joueur {

	private String nom;
	private int id;
	private int position = 0;

	/**
	 * Définition d'un joueur
	 * 
	 * @param nom String
	 * @param id  int
	 */
	public Joueur(String nom, int id) {
		setNom(nom);
		setID(id);
	}

	/**
	 * Retourne le nom du joueur
	 * 
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Définit le nom d'un joueur, il ne peut pas être null.
	 * 
	 * @param nom String
	 */
	public void setNom(String nom) {
		if (nom == null || nom.isEmpty())
			throw new IllegalArgumentException("Le nom du joueur est obligatoire!");
		this.nom = nom;
	}

	/**
	 * Retourne l'id du joueur
	 * 
	 * @return id
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Définit l'id d'un joueur
	 * 
	 * @param id int
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Retourne la position du pion d'un joueur
	 * 
	 * @return position
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * Définit la position du pion d'un joueur
	 * 
	 * @param pos int
	 */
	public void setPosition(int pos) {
		this.position = pos;
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", id=" + id + ", position=" + position + "]";
	}

}
