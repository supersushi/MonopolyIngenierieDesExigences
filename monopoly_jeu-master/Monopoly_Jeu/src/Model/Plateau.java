package jeu;

import java.util.ArrayList;

import cases.Case;

/**
 * Cette classe représente le plateau du jeu Monopoly
*@author  Massourang Jugurtha Lina Emma
*/

public abstract class Plateau {

	private int JoueurActifID = 0;
	private int nombreDeJoueurs = 3;
	private int nombreDeCases = 20;
	private int nombreDeTours = 1;

	private ArrayList<Case> cases = new ArrayList<Case>();
	
	public Des des = new Des();

	/**
	 * Création d'un plateau en fonction du nombre de joueur et nombre de cases
	 * @param nombreDeJoueurs int
	 * @param nbCases int
	 */
	public Plateau(int nombreDeJoueurs, int nbCases) {
		this.nombreDeJoueurs = nombreDeJoueurs;
		this.nombreDeCases = nbCases;
		for(int i=0; i<nombreDeCases; i++) {
			cases.add(null);
		}
	}
	
	/* PARTIE CONCERNANT LA CASE */
	
	/**
	 * Retourne une case en fonction d'un index i
	 * @param i int
	 * @return case
	 */
	public Case getCase(int i) {
		return this.cases.get(i);
	}

	/**
	 * Definit une case en fonction de l'index et de la case
	 * @param i int
	 * @param cells Case
	 */
	public void setCase(int i, Case cells) {
		this.cases.set(i, cells);
		cases.get(i).setId(i);
	}

	/**
	 * Retourne le nombre de cases
	 * @return nombreDeCases
	 */
	public int getNbCases() {
		return this.nombreDeCases;
	}

	/* PARTIE CONCERNANT LE JOUEUR */

	/**
	 * renvoie le nombre de joueurs
	 * @return nombreDeJoueurs
	 */
	public int getNbJoueurs() {
		return this.nombreDeJoueurs;
	}

	/**
	 * Renvoie le joueur actif
	 * @return JoueurActifID
	 */
	public int getJoueurActifID() {
		return this.JoueurActifID;
	}

	/**
	 * Sélectionne le joueur suivant
	 */
	public void setJoueurSuivant() {
		this.JoueurActifID++;
		if(this.JoueurActifID >= this.nombreDeJoueurs) {
			this.JoueurActifID = 0;
			this.nombreDeTours++;
		}
	}

/* PARTIE CONCERNANT LE PLATEAU*/

	/**
	 * Renvoie le nombre de tours
	 * @return nombreDeTours
	 */
	public int getNbTours() {
		return this.nombreDeTours;
	}

	/* FIN DE PARTIE */

	/**
	 * Retourne TRUE si la partie est finie
	 * @return boolean
	 */
	public abstract boolean finPartie();
	/**
	 * Retourne le gagnant du jeu
	 * @return joueur
	 */
	public abstract Joueur estVainqueur();

}
