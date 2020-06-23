package jeumonopoly;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

/**
 * Définit un joueur et ses données dans le jeu du Monopoly : compétences, entreprises possédées etc
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

public class JoueurMonopoly extends Joueur {

	private int argent = 1000;
	private boolean estFauche = false;
	private boolean estMalade = false;
	private int nbtoursArret = 1;
	private boolean possedeCarteSortieArretMaladie = false;
	private int nombreEntreprisesPossedees = 0;
	private int nombreServicesPossedes = 0;
	private ArrayList<Case> Competences = new ArrayList<Case>();
	private ArrayList<String> couleurs = new ArrayList<String>();

	/* CONSTRUCTEUR */

	
	public JoueurMonopoly(String nom, int id, int argent) {
		super(nom, id);
		this.argent = argent;
	}

	/* PARTIE ARRET MALADIE */

	/**
	 * Renvoie le nombre de tours passé dans la case arret maladie
	 * 
	 * @return toursEnArretMaladie
	 */
	public int getToursEnArretMaladie() {
		return nbtoursArret;
	}

	/**
	 * Met à jour le nombre de tour passé en arret maladie
	 * 
	 * @param toursEnArretMaladie 
	 */
	public void setToursEnArretMaladie(int nbtoursarret) {
		this.nbtoursArret = nbtoursarret;
	}

	/**
	 * Renvoie TRUE si le joueur est en arret maladie
	 * 
	 * @return estMalade
	 */
	public boolean getEstMalade() {
		return this.estMalade;
	}

	/**
	 * Met à jour si le joueur est malade
	 * 
	 * @param malade boolean
	 */
	public void setEstMalade(boolean malade) {
		this.estMalade = malade;
	}

	/**
	 * Renvoie TRUE si le joueur possède la carte "Sortie d'arret maladie" 
	 * 
	 * @return possedeCarteSortieArretMaladie
	 */
	public boolean getCarteSortieArretMaladie() {
		return possedeCarteSortieArretMaladie;
	}

	/**
	 * Met à jour si le joueur possède une carte "Sortir d'arret maladie"
	 * 
	 * @param b boolean
	 */
	public void setCarteSortieArretMaladie(boolean sortie) {
		possedeCarteSortieArretMaladie = sortie;
	}

	/* PARTIE COMPETENCES GRADES ENTREPRISE */

	

	/**
	 * Renvoie le nombre de Entreprises qu'un joueur possède
	 * 
	 * @return nombreEntreprisesPossedees
	 */
	public int getNbEntreprises() {
		return this.nombreEntreprisesPossedees;
	}

	/**
	 * Met à jour le nombre de Entreprises qu'un joueur possède
	 * 
	 * @param nb int
	 */
	public void setNbEntreprises(int nb) {
		this.nombreEntreprisesPossedees = nb;
	}

	/**A VOIR / QUOI METTRE A LA PLACE DE SERVICE
	 * Renvoie le nombre de services qu'un joueur possède
	 * 
	 * @return nombreServicesPossedes
	 */
	public int getNbServices() {
		return this.nombreServicesPossedes;
	}

	/**
	 * Met à jour le nombre de services qu'un joueur possède
	 * 
	 * @param nb int
	 */
	public void setNbServices(int nb) {
		this.nombreServicesPossedes = nb;
	}

	
	/**
	 * Ajoute une compétence à la liste 
	 * 
	 * @param competence Case
	 */
	public void ajouterCompetence(Case Competence) {
		this.Competences.add(Competence);
	}

	/**
	 * Affichage de la liste des compétences d'un joueur 
	 * 
	 * @return s
	 * @see Case
	 */
	public String getListeStringCompetences() {
		String s = "";
		for (Case t : this.Competences) {
			s += (t.getNom() + ",");
		}
		return s;
	}

	/**
	 * Retourne la liste de compétence qu'a acquis un joueur
	 * 
	 * @return Competences
	 */
	public ArrayList<Case> getListeCompetences() {
		return this.Competences;
	}

	
	/**
	 * Rentourne la liste de couleurs que possède un joueur
	 * 
	 * @return couleurs
	 * @see Case
	 */
	public ArrayList<String> getListeCouleur() {

		couleurs.clear();

		int brun = 0, turquoise = 0, mauve = 0, orange = 0, rouge = 0, jaune = 0, vert = 0, bleu = 0;

		for (Case t : this.getListeCompetences()) {

			if (t.getCouleur() == "brun")
				brun += 1;
			if (t.getCouleur() == "turquoise")
				turquoise += 1;
			if (t.getCouleur() == "mauve")
				mauve += 1;
			if (t.getCouleur() == "orange")
				orange += 1;
			if (t.getCouleur() == "rouge")
				rouge += 1;
			if (t.getCouleur() == "jaune")
				jaune += 1;
			if (t.getCouleur() == "vert")
				vert += 1;
			if (t.getCouleur() == "bleu")
				bleu += 1;
		}

		if (brun == 2)
			couleurs.add("brun");

		if (turquoise == 3)
			couleurs.add("turquoise");

		if (mauve == 3)
			couleurs.add("mauve");

		if (orange == 3)
			couleurs.add("orange");

		if (rouge == 3)
			couleurs.add("rouge");

		if (jaune == 3)
			couleurs.add("jaune");

		if (vert == 3)
			couleurs.add("vert");

		if (bleu == 2)
			couleurs.add("bleu");

		return this.couleurs;
	}

	/* PARTIE BANQUE*/

	/**
	 * Renvoie la somme en banque d'un joueur
	 * 
	 * @return argent
	 */
	public int getArgent() {
		return this.argent;
	}

	/**
	 * Ajoute un montant au compte en banque d'un joueur 
	 * 
	 * @param montant int
	 */
	public void ajouterArgent(int montant) {
		this.argent += montant;
	}

	/**
	 * Retire un montant au compte en banque d'un joueur 
	 * 
	 * @param montant int
	 */
	public void retirerArgent(int montant) {
		this.argent = this.argent - montant;
		if (this.argent <= 0) {
			this.argent = 0;
			this.setEstFauche(true);
		}
	}

	/**
	 * Renvoie si un joueur est en fauché ou non
	 * 
	 * @return estFauche
	 */
	public boolean getEstFauche() {
		return this.estFauche;
	}

	/**
	 * Met à jour si le joueur est Fauche ou pas
	 * 
	 * @param Fauche boolean
	 */
	public void setEstFauche(boolean Fauche) {
		this.estFauche = Fauche;
		clearSignets();
		this.Competences.clear();
	}

	/**
	 * Supprime le Signet de possession d'un Competence
	 * 
	 * @see Case
	 */
	public void clearSignets() {

		for (Case t : getListeCompetences()) {
			t.setProprietaire(null);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					t.getSignet().setFill(Color.TRANSPARENT);
				}
			});
		}
	}

	@Override
	public String toString() {
		return "JoueurMonopoly [" + super.toString() + ", argent=" + argent + ", estFauche=" + estFauche
				+ ", estMalade=" + estMalade + ", toursEnArretMaladie=" + nbtoursArret + ", possedeCarteSortieArretMaladie="
				+ possedeCarteSortieArretMaladie + ", nombreEntreprisesPossedees=" + nombreEntreprisesPossedees
				+ ", nombreServicesPossedes=" + nombreServicesPossedes + ", \nCompetences=[" + getListeStringCompetences()
				+ "], \ncouleurs=" + getListeCouleur() + "]";
	}
}
