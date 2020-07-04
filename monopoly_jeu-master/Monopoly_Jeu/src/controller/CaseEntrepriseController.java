package controller;

import java.util.Random;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Case;
import view.FenetreDeJeu;

/**
 * Crée l'action de la case Service Public
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class CaseEntrepriseController extends Case implements DefaultControllerInterface {

	private JoueurMonopoly Patron;
	private boolean reponseQuestion = false;

	/**
	 * Indique le nom de la case et définit son prix
	 * 
	 * @param nom String
	 */
	public CaseEntrepriseController(String nom) {
		super(nom, 150);
	}

	@Override
	/**
	 * Méthode gérant l'acquisition d'une entreprise par un joueur Gère le
	 * changement du Salaire en fonction du nombre d'entreprise possédé par un
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
				es.println("-> " + joueur.getNom() + " décide de ne pas acheter cette entreprise.");
				if (fjeu != null)
					fjeu.afficherMessage(joueur.getNom() + " décide de ne pas acheter cette entreprise.");
			}
		}

		else if (this.getPatron() != joueur)
			payerSalaire(joueur, plateau, fjeu);

		else {
			es.println(" > " + joueur.getNom() + " possède l'entreprise.");
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

			System.out.println("-> " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			if (fjeu != null)
				fjeu.afficherMessage(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
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

			System.out.println(" > " + joueur.getNom() + " lance les dés... [" + pm.des.getDe1() + "]["
					+ pm.des.getDe2() + "]... et obtient un " + pm.des.getDes());
			joueur.retirerArgent(Salaire);

			if (!this.getPatron().getEstFauche()) {
				this.getPatron().ajouterArgent(Salaire);
				beneficiaire = this.getPatron().getNom();
			}
			System.out.println("-> " + joueur.getNom() + " paye un salaire de " + Salaire + "€ à " + beneficiaire);
			if (fjeu != null)
				fjeu.afficherMessage(joueur.getNom() + " paye un salaire de " + Salaire + "€ à " + beneficiaire);
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
	 * Affiche une fenêtre pour l'achat de la case concernée
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

		return "Tout projet d’acquisition, de rachat d’une entreprise ou de branches d’activité,\ns’inscrit dans une stratégie de développement globale.";
	}

}
