package cases;

import java.util.Random;
import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

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

		Console es = new Console();

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

			System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
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
			System.out.println("-> Le propriétaire est en arret maladie. " + joueur.getNom() + " ne paye pas de Salaire.");
			if(fjeu!=null) fjeu.afficherMessage("Le propriétaire est en arret maladie. " + joueur.getNom() + " ne paye pas de Salaire.");
		}
	}


	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fenêtre d'achat de Salarie
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

	public static void main(String[] args) {

		Console es = new Console();
		es.println("TEST DE LA CLASSE : CaseClient");

		JoueurMonopoly j1 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j2 = new JoueurMonopoly("Benoit", 1, 1000);
		PlateauMonopoly pm = new PlateauMonopoly(2);
		es.println(j1.toString());
		es.println(j2.toString()+"\n");

		CaseClient c = (CaseClient) pm.getCase(5);
		c.EmbaucheSalarie(j1, null);

		es.println("- Nombres de Clients de " + j1.getNom() + " : " + j1.getNbEntreprises());

		c.payerSalaire(j2, null);
		es.println("");

		c = (CaseClient) pm.getCase(15);
		c.EmbaucheSalarie(j1, null);
		c = (CaseClient) pm.getCase(25);
		c.EmbaucheSalarie(j1, null);
		es.println("- Nombres de Clients de " + j1.getNom() + " : " + j1.getNbEntreprises());

		c.payerSalaire(j2, null);

		es.println("\n" + j1.toString());
		es.println(j2.toString());
	}

}
