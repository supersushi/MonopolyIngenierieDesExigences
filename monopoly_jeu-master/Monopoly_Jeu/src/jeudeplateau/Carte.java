package jeudeplateau;

import fenetres.FenetreDeJeu;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Définit les cartes du Monopoly
*@author  Massourang Jugurtha Lina Emma
*/

public abstract class Carte {

	private String intitule;
	private String description;

	/**
	 * Définition d'une carte : intitule et description
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
	 * @see cartes.CarteDeplacement
	 * @see cartes.CartePayerArgent
	 * @see cartes.CarteRecevoirArgent
	 * @see cartes.CarteSortirArretMaladie
	 */
	public abstract void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu);

	@Override
	public String toString() {
		return "Carte [Intitule=" + intitule + ", description=" + description + "]";
	}
}
