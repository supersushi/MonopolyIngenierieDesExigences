package model;

import java.util.ArrayList;

import javafx.scene.shape.Polygon;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.MonopolyView;

/**
 * Classe abstraite représentant une case du Monopoly
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public abstract class Case {

	private String nom;
	private int id = 0;
	private int valeur = 0;
	private Polygon signet = new Polygon();
	public ArrayList<Polygon> Salaries = new ArrayList<Polygon>();

	/**
	 * Définit le nom de la case
	 * 
	 * @param nom    String
	 * @param valeur int
	 */
	public Case(String nom, int valeur) {
		this.nom = nom;
		this.valeur = valeur;

		for (int i = 0; i < 6; i++) {
			Polygon Salarie = new Polygon();
			Salaries.add(Salarie);
		}
	}

	/**
	 * Retourne le nom
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne l'id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Actualisation de l'id
	 * 
	 * @param id int
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Renvoie salaire du salarie
	 * 
	 * @return prix
	 */
	public int getPrix() {
		return valeur;
	}

	/**
	 * Définit le salaire du salarie
	 * 
	 * @param valeur int
	 */
	public void setPrix(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * Marque la case en fonction de la couleur du joueur
	 * 
	 * @return Signet
	 */
	public Polygon getSignetPatron() {
		return this.signet;
	}

	/**
	 * Définit un signet de possession d'un salarié
	 * 
	 * @param r Polygon
	 */
	public void setSignet(Polygon r) {
		this.signet = r;
	}

	/**
	 * Renvoie le directeur d'un salarié
	 * 
	 * @return Patron
	 */
	public abstract JoueurMonopoly getPatron();

	/**
	 * Renvoie la couleur de la case
	 * 
	 * @return couleur
	 */
	public abstract String getCouleur();

	/**
	 * Renvoie le salaire en fonction du nombre de compétences du salarié
	 * 
	 * @return apayer
	 */
	public abstract int getSalaire();

	/**
	 * Renvoie le prix d'un salarié
	 * 
	 * @return prixSalarie
	 */
	public abstract int getPrixCompetence();

	/**
	 * Retourne le nombre de competence d'un salarié
	 * 
	 * @return nbSalarie
	 */
	public abstract int getNbCompetence();

	/**
	 * Retourne la réponse à la question : la question diffère selon les classes.
	 * 
	 * @return rep
	 */
	public abstract boolean getRep();

	/**
	 * Détermine si un joueur peut monter en competence. Possible lorsque: - Le
	 * joueur possède toutes les salaries d'une même couleur - Le nombre de
	 * competence sur chaque case doit être identique pour en rajouter (ex : 1
	 * Salarie sur chaque case d'une couleur pour en poser une deuxième)
	 * 
	 * @return boolean
	 * @see JoueurMonopoly
	 */
	public abstract boolean getPeutMonterEnCompetence();

	/**
	 * Définition du directeur de la case.
	 * 
	 * @param j : joueur
	 */
	public abstract void setPatron(JoueurMonopoly j);

	/**
	 * Définition de la réponse du joueur via les boutons de l'interface. La réponse
	 * est définiz alatoirement si la partie est en mode automatique.
	 * 
	 * @param b : boolean
	 */
	public abstract void setRep(boolean b);

	/**
	 * Appelle la fenêtre d'action associé à la case (acquisition d'une compétence,
	 * d'un grade, tirage d'une carte)
	 * 
	 * @param fjeu : fenetre de la partie en cours
	 */
	public abstract void fenetreAction(MonopolyView fjeu);

	/**
	 * Action de la case lorsqu'un joueur atteri dessus
	 * 
	 * @param joueur  JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fjeu    FenetreDeJeu
	 */
	public abstract void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView fjeu);

	@Override
	public String toString() {
		return "Case [nom=" + nom + ", id=" + id + ", valeur=" + valeur + "]";
	}

	public abstract String descriptionPoste();

	public abstract ArrayList<String> getCompetences();

}
