package cases;

import java.util.Random;
import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case gare
*@author  Massourang Jugurtha Lina Emma
*/

public class CaseGare extends Case {

	private JoueurMonopoly proprietaire;
	private boolean reponseQuestion = false;

	/**
	 * Indique le nom et ajoute le prix d'une gare
	 * @param nom String
	 */
	public CaseGare(String nom) {
		super(nom, 200);
	}

	@Override
	/**
	 * Méthode gérant l'appropriation d'une gare à un joueur <br />
	 * Gère le changement du loyer en fonction du nombre de gare possédé par un joueur
	 * @see Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Console es = new Console();

		if(this.getProprietaire() == null) {
			if(getRep()) {
				if(acheterCompetence(joueur, fjeu))
					fjeu.setSignetProprietaire(joueur, this);
			}
			else {
				es.println("-> " + joueur.getNom() + " décide de ne pas acheter cette gare.");
				fjeu.afficherMessage(joueur.getNom() + " décide de ne pas acheter cette gare.");
			}
		}
		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, fjeu);

		else {
			es.println("-> " + joueur.getNom() + " est dans sa propre gare.");
			fjeu.afficherMessage(joueur.getNom() + " est dans sa propre gare.");
		}
	}


	public boolean acheterCompetence(JoueurMonopoly joueur, FenetreDeJeu fjeu) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		}
		else {
			setProprietaire(joueur);
			joueur.ajouterCompetence(this);
			joueur.retirerArgent(this.getPrix());
			joueur.setNbEntreprises(joueur.getNbEntreprises() + 1);

			System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			return true;
		}
	}

	public void payerLoyer(JoueurMonopoly joueur, FenetreDeJeu fjeu) {
		String beneficiaire = "la Banque";

		if(!this.getProprietaire().getEstMalade()) {

			joueur.retirerArgent(getLoyer());

			if(!this.getProprietaire().getEstFauche()) {
				this.getProprietaire().ajouterArgent(getLoyer());
				beneficiaire = this.getProprietaire().getNom();
			}
			System.out.println("-> " + joueur.getNom() + " paye un loyer de " + getLoyer() + "€ à " + beneficiaire);
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " paye un loyer de " + getLoyer() + "€ à " + beneficiaire);
		}
		else {
			System.out.println("-> Le propriétaire est en arret maladie. " + joueur.getNom() + " ne paye pas de loyer.");
			if(fjeu!=null) fjeu.afficherMessage("Le propriétaire est en arret maladie. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}


	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fenêtre d'achat de Competence
	 */
	public void fenetreAction(FenetreDeJeu fjeu) {

		if(fjeu.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fjeu.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fjeu.afficherFenetreAchatCompetence();
		else
			fjeu.getPartie().reprendrePartie();
	}


	/* ===========================
	   Méthodes abstraites de Case
	   =========================== */

	@Override
	public JoueurMonopoly getProprietaire() {
		return proprietaire;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getLoyer() {
		return proprietaire != null ? 50 * this.getProprietaire().getNbEntreprises() : 0;
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
	public boolean getPeutAcheterCompetence() {
		return false;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {
		this.proprietaire = j;
	}

	@Override
	public void setRep(boolean b) {
		this.reponseQuestion = b;
	}

	@Override
	public String toString() {
		return "CaseGare [" + super.toString() + ", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + "]";
	}

	public static void main(String[] args) {

		Console es = new Console();
		es.println("TEST DE LA CLASSE : CaseGare");

		JoueurMonopoly j1 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j2 = new JoueurMonopoly("Benoit", 1, 1000);
		PlateauMonopoly pm = new PlateauMonopoly(2);
		es.println(j1.toString());
		es.println(j2.toString()+"\n");

		CaseGare c = (CaseGare) pm.getCase(5);
		c.acheterCompetence(j1, null);

		es.println("- Nombres de gares de " + j1.getNom() + " : " + j1.getNbEntreprises());

		c.payerLoyer(j2, null);
		es.println("");

		c = (CaseGare) pm.getCase(15);
		c.acheterCompetence(j1, null);
		c = (CaseGare) pm.getCase(25);
		c.acheterCompetence(j1, null);
		es.println("- Nombres de gares de " + j1.getNom() + " : " + j1.getNbEntreprises());

		c.payerLoyer(j2, null);

		es.println("\n" + j1.toString());
		es.println(j2.toString());
	}

}
