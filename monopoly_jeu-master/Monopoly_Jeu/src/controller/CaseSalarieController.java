package controller;

import java.util.ArrayList;
import java.util.Random;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Case;
import view.MonopolyView;

/**
 * Cr�e l'action de la case Salarie
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class CaseSalarieController extends Case implements DefaultControllerInterface {

	private JoueurMonopoly Patron;
	private ArrayList<Integer> Salaire = new ArrayList<Integer>();
	private String couleur;
	private String descriptionPoste;
	private int prixCompetence;
	private int nbCompetence = 0;
	private ArrayList<String> competences = new ArrayList<String>();
	private boolean monterEnComptence = false;
	private boolean Rep = false;

	/**
	 * Indique le nom, le prix du Salarie, la liste de ses Salaires, le prix d'une
	 * maison, le nombre de maison, et la couleur que poss�de un Salarie
	 * 
	 * @param nom            String
	 * @param valeur         int
	 * @param Salaire        ArrayList
	 * @param prixCompetence int
	 * @param nbCompetence   int
	 * @param competences	 ArrayList
	 * @param couleur        String
	 */
	public CaseSalarieController(String nom, int valeur, ArrayList<Integer> Salaire, int prixCompetence,
			int nbCompetence, String couleur, String descriptionPoste, ArrayList<String> competences) {
		super(nom, valeur);
		this.couleur = couleur;
		this.Salaire = Salaire;
		this.prixCompetence = prixCompetence;
		this.nbCompetence = nbCompetence;
		this.descriptionPoste = descriptionPoste;
		this.competences = competences;
	}

	/**
	 * Action a r�aliser sur un salari�: - Si le salari� n'a pas de patron un joueur
	 * peut l'embaucher - Si un joueur tombe sur un salari� sans patron il r�gle un
	 * salaire au patron - Si un joueur tombe sur un de ses salaries il ne se passe
	 * rien, mais peut les faire monter en comp�tences
	 */

	@SuppressWarnings({ "unused", "static-access" })
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {

		Clavier es = new Clavier();

		if (this.getPatron() == null) {
			if (getRep()) {
				if (embaucherSalarie(joueur, monopolyView))
					if (monopolyView != null)
						monopolyView.setSignetPatron(joueur, this);
			} else {
				es.println("-> " + joueur.getNom() + " d�cide de ne pas embaucher le salari�.");
				if (monopolyView != null)
					monopolyView.afficherMessage(joueur.getNom() + " d�cide de ne pas embaucher le salari�.");
			}
		}

		else if (this.getPatron() != joueur)
			payerSalaire(joueur, monopolyView);

		else {
			es.println("-> " + joueur.getNom() + " est le patron de ce salari�");
			monopolyView.afficherMessage(joueur.getNom() + " est le patron de ce salari�");

			if (this.getPeutMonterEnCompetence() && monopolyView.getPartie().PARTIE_AUTO) {
				this.monterEnCompetence(monopolyView, null);
				monopolyView.setCompetence(this);
				es.println("-> " + joueur.getNom() + " a fait monter en comp�tence " + getNbCompetence()
						+ "fois ce salari�");
			}
		}
	}

	public boolean embaucherSalarie(JoueurMonopoly joueur, MonopolyView monopolyView) {
		if ((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		} else {
			setPatron(joueur);
			joueur.ajouterSalarie(this);
			joueur.retirerArgent(this.getPrix());
			System.out
					.println("-> " + joueur.getNom() + " embauche " + this.getNom() + " pour " + this.getPrix() + "�");
			if (monopolyView != null)
				monopolyView.afficherMessage(joueur.getNom() + " embauche " + this.getNom() + " pour " + this.getPrix() + "�");
			return true;
		}
	}


	public ArrayList<String> getCompetences() {
		return competences;
	}

	public void setCompetences(ArrayList<String> competences) {
		this.competences = competences;
	}

	public void payerSalaire(JoueurMonopoly joueur, MonopolyView monopolyView) {
		String beneficiaire = "la Banque";

		if (!this.getPatron().getEstMalade()) {
			joueur.retirerArgent(getSalaire());
			if (!this.getPatron().getEstFauche()) {
				this.getPatron().ajouterArgent(getSalaire());
				beneficiaire = this.getPatron().getNom();
			}
			System.out.println(" > " + joueur.getNom() + " paye un salaire de " + getSalaire() + "� � " + beneficiaire);
			if (monopolyView != null)
				monopolyView.afficherMessage(joueur.getNom() + " paye un salaire de " + getSalaire() + "� � " + beneficiaire);
		} else {
			System.out.println(
					"-> Le propri�taire est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
			if (monopolyView != null)
				monopolyView.afficherMessage(
						"Le propri�taire est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
		}
	}

	/**
	 * Permet l'ajout de comp�tence sur un salari�
	 * 
	 * @param monopolyView FenetrePrincipale
	 */
	public void monterEnCompetence(MonopolyView monopolyView, String competence) {

		nbCompetence++;
		competences.remove(competence);
		Patron.retirerArgent(this.getPrixCompetence());

		if (this.nbCompetence <= 4) {
			System.out.println("-> " + Patron.getNom() + " a permis � l'employ� " + getNom() + "\n d'int�grer une formation pour \n"+ competence + "!");
			if (monopolyView != null) 
				monopolyView.afficherMessage(
						"-> " + Patron.getNom() + " a permis � l'employ� " + getNom() + " d'int�grer une formation pour "+ competence + "!");
		} else if(this.nbCompetence == 5) {
			System.out.println(getNom() + " a 5 comp�tences! Il obtient un grade!");
			if (monopolyView != null) { 
				monopolyView.afficherMessage(
						"-> " + getNom() + " obtient un grade!");
			}
		}else {
			System.out.println("-> " + Patron.getNom() + " a obtenu un grade pour \n" + getNom()
					+ " et ne peut plus monter en comp�tence!");
			if (monopolyView != null)
				monopolyView.afficherMessage("-> " + Patron.getNom() + " a obtenu un grade pour " + getNom()
						+ " et ne peut plus monter en comp�tence!");
		}
	}

	/**
	 * M�thode permettant l'affichage d'une fen�tre lors de l'embauche d'un salarie
	 */
	@SuppressWarnings("static-access")
	public void fenetreAction(MonopolyView monopolyView) {

		if (monopolyView.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if (rand.nextBoolean())
				Rep = true;
			monopolyView.getPartie().reprendrePartie();
		} else if (this.getPatron() == null)
			monopolyView.afficherEmbaucherSalarieView();
		else
			monopolyView.getPartie().reprendrePartie();
	}

	/*
	 * =========================== M�thodes abstraites de Case
	 * ===========================
	 */

	public boolean getPeutMonterEnCompetence() {
//		if (Patron.getListeCouleur().contains(this.getCouleur())) {
//
//			ArrayList<Case> couleur = new ArrayList<Case>();
//			for (Case c : Patron.getListeSalaries())
//				if (c.getCouleur() == this.getCouleur() && c != this)
//					couleur.add(c);
//
//			this.monterEnComptence = true;
//			for (Case c : couleur) {
//				if (!(this.getNbCompetence() == c.getNbCompetence()
//						|| this.getNbCompetence() == c.getNbCompetence() - 1))
//					this.monterEnComptence = false;
//			}
//
//			if (Patron.getArgent() < this.getPrixCompetence()) {
//				this.monterEnComptence = false;
//				System.out.println("Vous n'avez pas assez monter en competence!");
//			}
//			if (getNbCompetence() == 5) {
//				this.monterEnComptence = false;
//				System.out.println("Vous ne pouvez pas avoir plus de comp�tence !");
//			}
//		} 
		if (Patron.getArgent() < this.getPrixCompetence()) {
			this.monterEnComptence = false;
			System.out.println("Vous n'avez pas assez d'argent pour monter en comp�tence!");
		}
		if (getNbCompetence() == 5) {
			this.monterEnComptence = false;
			System.out.println("Vous ne pouvez pas avoir plus de comp�tence !");
		}
		else
			this.monterEnComptence = true;

		return this.monterEnComptence;
	}

	@Override
	public int getSalaire() {
		int aPayer = Salaire.get(getNbCompetence());
		if (Patron.getListeCouleur().contains(this.getCouleur()) && getNbCompetence() == 0)
			// le salaire est doubl� si le joueur a embauch� tous les salari�s d'une m�me
			// couleur
			aPayer = aPayer * 2;

		return aPayer;
	}

	@Override
	public String getCouleur() {
		return couleur;
	}

	@Override
	public int getPrixCompetence() {
		return prixCompetence;
	}

	@Override
	public int getNbCompetence() {
		return nbCompetence;
	}

	public void setNbCompetence(int i) {
		this.nbCompetence = i;
	}

	@Override
	public JoueurMonopoly getPatron() {
		return Patron;
	}

	@Override
	public boolean getRep() {
		return Rep;
	}

	@Override
	public void setPatron(JoueurMonopoly j) {
		this.Patron = j;
	}

	@Override
	public void setRep(boolean b) {
		this.Rep = b;
	}

	@Override
	public String toString() {
		return "CaseSalarie [" + super.toString() + ", Patron=" + (Patron == null ? "null" : Patron.getNom())
				+ ", couleur=" + couleur + ", Salaire=" + Salaire + ", prixCompetence=" + prixCompetence
				+ ", monterEnComptence=" + monterEnComptence + ", nbCompetence=" + nbCompetence + ", descriptionPoste"
				+ descriptionPoste + "]";
	}

	public String descriptionPoste() {
		return descriptionPoste;
	}

}
