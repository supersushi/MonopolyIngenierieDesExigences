package jeu;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import model.CaseModel;
import model.JoueurModel;

/**
 * Définit un joueur et ses données dans le jeu du Monopoly : compétences,
 * entreprises possédées etc
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class JoueurMonopoly extends JoueurModel {

	private int argent = 1000;
	private boolean estFauche = false;
	private boolean estMalade = false;
	private int nbtoursArret = 1;
	private boolean possedeCarteSortieArretMaladie = false;
	private int nombreEntreprisesPossedees = 0;
	private int nombreServicesPossedes = 0;
	private ArrayList<CaseModel> Salaries = new ArrayList<CaseModel>();
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

	/* PARTIE Salaries GRADES ENTREPRISE */

	/**
	 * Renvoie le nombre d'entreprises qu'un joueur possède
	 * 
	 * @return nombreEntreprisesPossedees
	 */
	public int getNbEntreprises() {
		return this.nombreEntreprisesPossedees;
	}

	/**
	 * Met à jour le nombre d'entreprises qu'un joueur possède
	 * 
	 * @param nb int
	 */
	public void setNbEntreprises(int nb) {
		this.nombreEntreprisesPossedees = nb;
	}

	/**
	 * Ajoute une compétence à la liste
	 * 
	 * @param CaseSalarie
	 */
	public void ajouterSalarie(CaseModel Salarie) {
		this.Salaries.add(Salarie);
	}

	/**
	 * Affichage de la liste des compétences d'un joueur
	 * 
	 * @return s
	 */
	public String getListeStringSalaries() {
		String s = "";
		for (CaseModel t : this.Salaries) {
			s += (t.getNom() + ",");
		}
		return s;
	}

	/**
	 * Retourne la liste de compétences qu'a acquise un joueur
	 * 
	 * @return Salaries
	 */
	public ArrayList<CaseModel> getListeSalaries() {
		return this.Salaries;
	}

	/**
	 * Rentourne la liste de couleurs que possède un joueur
	 * 
	 * @return couleurs
	 */
	public ArrayList<String> getListeCouleur() {

		couleurs.clear();

		int brun = 0, turquoise = 0, mauve = 0, orange = 0, rouge = 0, jaune = 0, vert = 0, bleu = 0;

		for (CaseModel t : this.getListeSalaries()) {

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

	/* PARTIE BANQUE */

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
	 * Met à jour si le joueur est fauché ou pas
	 * 
	 * @param Fauche boolean
	 */
	public void setEstFauche(boolean Fauche) {
		this.estFauche = Fauche;
		clearSignets();
		this.Salaries.clear();
	}

	/**
	 * Supprime le Signet de possession d'un Salarie
	 * 
	 * @see CaseModel
	 */
	public void clearSignets() {

		for (CaseModel t : getListeSalaries()) {
			t.setPatron(null);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					t.getSignetPatron().setFill(Color.TRANSPARENT);
				}
			});
		}
	}

	@Override
	public String toString() {
		return "JoueurMonopoly [" + super.toString() + ", argent=" + argent + ", estFauche=" + estFauche
				+ ", estMalade=" + estMalade + ", toursEnArretMaladie=" + nbtoursArret
				+ ", possedeCarteSortieArretMaladie=" + possedeCarteSortieArretMaladie + ", nombreEntreprisesPossedees="
				+ nombreEntreprisesPossedees + ", nombreServicesPossedes=" + nombreServicesPossedes + ", \nSalaries=["
				+ getListeStringSalaries() + "], \ncouleurs=" + getListeCouleur() + "]";
	}
}
