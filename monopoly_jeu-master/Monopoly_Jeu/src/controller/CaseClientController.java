package controller;

import java.util.ArrayList;
import java.util.Random;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Case;
import view.MonopolyView;

/**
 * Cr�e l'action d'une case Client
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class CaseClientController extends Case implements DefaultControllerInterface {

	private JoueurMonopoly Patron;
	private boolean reponseQuestion = false;

	/**
	 * Indique le nom et ajoute le prix d'une Client
	 * 
	 * @param nom String
	 */
	public CaseClientController(String nom) {
		super(nom, 200);
	}

	@Override
	/**
	 * M�thode g�rant l'appropriation d'une Client � un joueur <br />
	 * G�re le changement du Salaire en fonction du nombre de Client poss�d� par un
	 * joueur
	 * 
	 * @see Joueur
	 * @see Case
	 */
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {

		Clavier es = new Clavier();

		if (this.getPatron() == null) {
			if (getRep()) {
				if (EmbaucheSalarie(joueur, monopolyView))
					monopolyView.setSignetPatron(joueur, this);
			} else {
				es.println("-> " + joueur.getNom() + " d�cide de ne pas faire affaire avec ce client.");
				monopolyView.afficherMessage(joueur.getNom() + " d�cide de ne pas faire affaire avec ce client");
			}
		} else if (this.getPatron() != joueur)
			payerSalaire(joueur, monopolyView);

		else {
			es.println("-> " + joueur.getNom() + " fait deja affaire avec ce client.");
			monopolyView.afficherMessage(joueur.getNom() + " ait deja affaire avec ce client.");
		}
	}

	public boolean EmbaucheSalarie(JoueurMonopoly joueur, MonopolyView fjeu) {
		if ((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		} else {
			setPatron(joueur);
			joueur.ajouterSalarie(this);
			joueur.retirerArgent(this.getPrix());
			joueur.setNbEntreprises(joueur.getNbEntreprises() + 1);

			System.out
					.println("-> " + joueur.getNom() + " embauche " + this.getNom() + " pour " + this.getPrix() + "�");
			if (fjeu != null)
				fjeu.afficherMessage(joueur.getNom() + " embauche " + this.getNom() + " pour " + this.getPrix() + "�");
			return true;
		}
	}

	public void payerSalaire(JoueurMonopoly joueur, MonopolyView fjeu) {
		String beneficiaire = "la Banque";

		if (!this.getPatron().getEstMalade()) {

			joueur.retirerArgent(getSalaire());

			if (!this.getPatron().getEstFauche()) {
				this.getPatron().ajouterArgent(getSalaire());
				beneficiaire = this.getPatron().getNom();
			}
			System.out.println("-> " + joueur.getNom() + " paye un Salaire de " + getSalaire() + "� � " + beneficiaire);
			if (fjeu != null)
				fjeu.afficherMessage(joueur.getNom() + " paye un Salaire de " + getSalaire() + "� � " + beneficiaire);
		} else {
			System.out.println("-> Le patron est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
			if (fjeu != null)
				fjeu.afficherMessage("Le patron est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
		}
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fen�tre pour embaucher un salari�
	 */
	public void fenetreAction(MonopolyView monopolyView) {

		if (monopolyView.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if (rand.nextBoolean())
				reponseQuestion = true;
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
		return Patron != null ? 50 * this.getPatron().getNbEntreprises() : 0;
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
		return "CaseClient [" + super.toString() + ", Patron=" + (Patron == null ? "null" : Patron.getNom()) + "]";
	}

	@Override
	public String descriptionPoste() {
		return "D�s qu�une entreprise effectue des prestations de services pour un client, elle\ndoit formaliser ses interventions dans un contrat de prestations de services.\n"
				+ "Ce contrat est utilis� dans de nombreux domaines d�activit�.\nIl pr�voit la nature des prestations d�livr�es par le prestataire et le prix � payer par\nle client en contrepartie, puis encadre les relations entre les parties.";
	}

	@Override
	public ArrayList<String> getCompetences() {
		// TODO Auto-generated method stub
		return null;
	}

}
