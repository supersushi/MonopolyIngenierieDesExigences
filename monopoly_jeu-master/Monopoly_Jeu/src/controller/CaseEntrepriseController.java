package controller;

import java.util.Random;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Case;
import view.FenetreDeJeu;

/**
 * Cr�e l'action de la case Service Public
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class CaseEntrepriseController extends Case implements DefaultControllerInterface {

	private JoueurMonopoly Patron;
	private boolean reponseQuestion = false;

	/**
	 * Indique le nom de la case et d�finit son prix
	 * 
	 * @param nom String
	 */
	public CaseEntrepriseController(String nom) {
		super(nom, 150);
	}

	@Override
	/**
	 * M�thode g�rant l'acquisition d'une entreprise par un joueur G�re le
	 * changement du Salaire en fonction du nombre d'entreprise poss�d� par un
	 * joueur
	 */
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Clavier es = new Clavier();

		if (this.getPatron() == null) {
			if (getRep()) {
				if (EmbaucheSalarie(joueur, fjeu))
					if (fjeu != null)
						fjeu.setSignetPatron(joueur, this);
			} else {
				es.println("-> " + joueur.getNom() + " d�cide de ne pas acheter cette entreprise.");
				if (fjeu != null)
					fjeu.afficherMessage(joueur.getNom() + " d�cide de ne pas acheter cette entreprise.");
			}
		}

		else if (this.getPatron() != joueur)
			payerSalaire(joueur, plateau, fjeu);

		else {
			es.println(" > " + joueur.getNom() + " poss�de l'entreprise.");
			if (fjeu != null)
				fjeu.afficherMessage(
						"Le directeur est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
		}
	}

	public boolean EmbaucheSalarie(JoueurMonopoly joueur, FenetreDeJeu fjeu) {
		if ((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		} else {
			setPatron(joueur);
			joueur.ajouterSalarie(this);
			joueur.retirerArgent(this.getPrix());
			joueur.setNbEntreprises(joueur.getNbEntreprises() + 1);

			System.out.println("-> " + joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			if (fjeu != null)
				fjeu.afficherMessage(joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			return true;
		}
	}

	public void payerSalaire(JoueurMonopoly joueur, PlateauMonopoly pm, FenetreDeJeu fjeu) {
		String beneficiaire = "la Banque";

		if (!this.getPatron().getEstMalade()) {

			int Salaire = pm.des.lancerDes();
			if (fjeu != null) {
				fjeu.effacerDes();
				fjeu.afficherDes(pm);
			}

			if (this.getPatron().getNbEntreprises() == 2)
				Salaire *= 10;
			else
				Salaire *= 4;

			System.out.println(" > " + joueur.getNom() + " lance les d�s... [" + pm.des.getDe1() + "]["
					+ pm.des.getDe2() + "]... et obtient un " + pm.des.getDes());
			joueur.retirerArgent(Salaire);

			if (!this.getPatron().getEstFauche()) {
				this.getPatron().ajouterArgent(Salaire);
				beneficiaire = this.getPatron().getNom();
			}
			System.out.println("-> " + joueur.getNom() + " paye un salaire de " + Salaire + "� � " + beneficiaire);
			if (fjeu != null)
				fjeu.afficherMessage(joueur.getNom() + " paye un salaire de " + Salaire + "� � " + beneficiaire);
		} else {
			System.out.println("-> Le directeur est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
			if (fjeu != null)
				fjeu.afficherMessage(
						"Le directeur est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
		}
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fen�tre pour l'achat de la case concern�e
	 */
	public void fenetreAction(FenetreDeJeu fjeu) {

		if (fjeu.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if (rand.nextBoolean())
				reponseQuestion = true;
			fjeu.getPartie().reprendrePartie();
		} else if (this.getPatron() == null)
			fjeu.afficherFenetreEmbaucherSalarie();
		else
			fjeu.getPartie().reprendrePartie();
	}

	@Override
	public JoueurMonopoly getPatron() {
		return Patron;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getSalaire() {
		return 0;
	}

	@Override
	public int getPrixCompetence() {
		return 0;
	}

	@Override
	public int getNbCompetence() {
		return 0;
	}

	@Override
	public boolean getRep() {
		return reponseQuestion;
	}

	@Override
	public boolean getPeutMonterEnCompetence() {
		return false;
	}

	@Override
	public void setPatron(JoueurMonopoly j) {
		this.Patron = j;
	}

	@Override
	public void setRep(boolean b) {
		this.reponseQuestion = b;
	}

	@Override
	public String toString() {
		return "CaseServicePublic [" + super.toString() + ", Patron=" + (Patron == null ? "null" : Patron.getNom())
				+ "]";
	}

	@Override
	public String descriptionPoste() {

		return "Tout projet d�acquisition, de rachat d�une entreprise ou de branches d�activit�,\ns�inscrit dans une strat�gie de d�veloppement globale.";
	}

}
