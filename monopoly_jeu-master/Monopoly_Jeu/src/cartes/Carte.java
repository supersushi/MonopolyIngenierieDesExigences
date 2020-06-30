package cartes;

import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.FenetreDeJeu;

/**
 * Classe abstraite représentant une carte du Monopoly
*@author  Massourang Jugurtha Lina Emma
*/

public abstract class Carte {

	private String intitule;
	private String description;

	/**
	 * Intitule et description de la carte
	 * @param Intitule String
	 * @param description String
	 */
	public Carte(String intitule, String description) {
		this.intitule = intitule;
		this.description = description;
	}

	/**
	 * Retourne l'intitule
	 * @return Intitule
	 */
	public String getNom() {
		return this.intitule;
	}

	/**
	 * Retourne la description 
	 * @return description
	 */
	public String getDesc() {
		return this.description;
	}

	/**
	 * Action en fonction du joueur et du plateau de jeu
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fjeu FenetreDeJeu
	 */
	public abstract void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu);

	@Override
	public String toString() {
		return "Carte [Intitule=" + intitule + ", description=" + description + "]";
	}
}
