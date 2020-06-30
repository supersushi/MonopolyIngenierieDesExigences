package cases;

import java.util.Random;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.FenetreDeJeu;

/**
 * Crée l'action d'une case Client
*@author  Massourang Jugurtha Lina Emma
*/

public class CaseClient extends Case {

	private JoueurMonopoly Patron;
	private boolean reponseQuestion = false;

	/**
	 * Indique le nom et ajoute le prix d'une Client
	 * @param nom String
	 */
	public CaseClient(String nom) {
		super(nom, 200);
	}

	@Override
	/**
	 * Méthode gérant l'appropriation d'une Client à un joueur <br />
	 * Gère le changement du Salaire en fonction du nombre de Client possédé par un joueur
	 * @see Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Clavier es = new Clavier();

		if(this.getPatron() == null) {
			if(getRep()) {
				if(EmbaucheSalarie(joueur, fjeu))
					fjeu.setSignetPatron(joueur, this);
			}
			else {
				es.println("-> " + joueur.getNom() + " décide de ne pas faire affaire avec ce client.");
				fjeu.afficherMessage(joueur.getNom() + " décide de ne pas faire affaire avec ce client");
			}
		}
		else if(this.getPatron() != joueur)
			payerSalaire(joueur, fjeu);

		else {
			es.println("-> " + joueur.getNom() + " fait deja affaire avec ce client.");
			fjeu.afficherMessage(joueur.getNom() + " ait deja affaire avec ce client.");
		}
	}


	public boolean EmbaucheSalarie(JoueurMonopoly joueur, FenetreDeJeu fjeu) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		}
		else {
			setPatron(joueur);
			joueur.ajouterSalarie(this);
			joueur.retirerArgent(this.getPrix());
			joueur.setNbEntreprises(joueur.getNbEntreprises() + 1);

			System.out.println("-> " + joueur.getNom() + " embauche " + this.getNom() + " pour " + this.getPrix() + "€");
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " embauche " + this.getNom() + " pour " + this.getPrix() + "€");
			return true;
		}
	}

	public void payerSalaire(JoueurMonopoly joueur, FenetreDeJeu fjeu) {
		String beneficiaire = "la Banque";

		if(!this.getPatron().getEstMalade()) {

			joueur.retirerArgent(getSalaire());

			if(!this.getPatron().getEstFauche()) {
				this.getPatron().ajouterArgent(getSalaire());
				beneficiaire = this.getPatron().getNom();
			}
			System.out.println("-> " + joueur.getNom() + " paye un Salaire de " + getSalaire() + "€ à " + beneficiaire);
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " paye un Salaire de " + getSalaire() + "€ à " + beneficiaire);
		}
		else {
			System.out.println("-> Le patron est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
			if(fjeu!=null) fjeu.afficherMessage("Le patron est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
		}
	}


	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fenêtre pour embaucher un salarié
	 */
	public void fenetreAction(FenetreDeJeu fjeu) {

		if(fjeu.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fjeu.getPartie().reprendrePartie();
		}
		else if(this.getPatron() == null)
			fjeu.afficherFenetreEmbaucherSalarie();
		else
			fjeu.getPartie().reprendrePartie();
	}


	/* ===========================
	   Méthodes abstraites de Case
	   =========================== */

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
		return "CaseClient [" + super.toString() + ", Patron=" + (Patron==null?"null":Patron.getNom()) + "]";
	}

	
}
