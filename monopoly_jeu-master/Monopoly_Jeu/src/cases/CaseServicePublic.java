package cases;

import java.util.Random;
import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action de la case Service Public
*@author   Massourang Jugurtha Lina Emma
*/

public class CaseServicePublic extends Case {

	private JoueurMonopoly proprietaire;
	private boolean reponseQuestion = false;

	/**
	 * Indique le nom de la case et définit son prix
	 * @param nom String
	 */
	public CaseServicePublic(String nom) {
		super(nom, 150);
	}

	@Override
	/**A VOIR
	 * Méthode gérant l'acquisition d'une entreprise par un joueur <br />
	 * Gère le changement du loyer en fonction du nombre d'entreprise possédé par un joueur
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
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter cette entreprise.");
				fjeu.afficherMessage(joueur.getNom() + " décide de ne pas acheter cette entreprise.");
			}
		}

		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, plateau, fjeu);

		else {
			es.println(" > " + joueur.getNom() + " possède l'entreprise.");
			if(fjeu!=null) fjeu.afficherMessage("Le directeur est en arret maladie. " + joueur.getNom() + " ne paye pas de loyer.");
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
			joueur.setNbServices(joueur.getNbServices() + 1);

			System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			return true;
		}
	}

	public void payerLoyer(JoueurMonopoly joueur, PlateauMonopoly pm, FenetreDeJeu fjeu) {
		String beneficiaire = "la Banque";

		if(!this.getProprietaire().getEstMalade()) {

			int loyer = pm.des.lancerDes();
			if(fjeu!=null) {
				fjeu.effacerDes();
				fjeu.afficherDes(pm);
			}

			if(this.getProprietaire().getNbServices() == 2) loyer*=10;
			else loyer*=4;

			System.out.println(" > " + joueur.getNom() + " lance les dés... [" + pm.des.getDe1() + "][" + pm.des.getDe2() + "]... et obtient un " + pm.des.getDes());
			joueur.retirerArgent(loyer);

			if(!this.getProprietaire().getEstFauche()) {
				this.getProprietaire().ajouterArgent(loyer);
				beneficiaire = this.getProprietaire().getNom();
			}
			System.out.println("-> " + joueur.getNom() + " paye un loyer de " + loyer + "€ à " + beneficiaire);
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " paye un loyer de " + loyer + "€ à " + beneficiaire);
		}
		else {
			System.out.println("-> Le directeur est en arret maladie. " + joueur.getNom() + " ne paye pas de loyer.");
			if(fjeu!=null) fjeu.afficherMessage("Le directeur est en arret maladie. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fenêtre pour l'achat de la case et reprend le cours de la partie
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
		return "CaseServicePublic [" + super.toString() + ", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + "]";
	}


	public static void main(String[] args) {

		Console es = new Console();
		es.println("TEST DE LA CLASSE : CaseServicePublic");

		JoueurMonopoly j1 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j2 = new JoueurMonopoly("Benoit", 1, 1000);
		PlateauMonopoly pm = new PlateauMonopoly(2);
		es.println(j1.toString()+"\n");

		CaseServicePublic c = (CaseServicePublic) pm.getCase(12);
		c.acheterCompetence(j1, null);

		es.println("== Nombres de SP de " + j1.getNom() + " : " + j1.getNbServices());

		c.payerLoyer(j2, pm, null);
		es.println("");

		c = (CaseServicePublic) pm.getCase(28);
		c.acheterCompetence(j1, null);
		es.println("== Nombres de SP de " + j1.getNom() + " : " + j1.getNbServices());

		c.payerLoyer(j2, pm, null);

		es.println("\n" + j1.toString());
	}

}
