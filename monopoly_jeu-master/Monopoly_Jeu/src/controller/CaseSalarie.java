package cases;

import java.util.ArrayList;
import java.util.Random;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.FenetreDeJeu;

/**
 * Crée l'action de la case Salarie
*@author   Massourang Jugurtha Lina Emma
*/


public class CaseSalarie extends Case {

	private JoueurMonopoly Patron;
	private ArrayList<Integer> Salaire = new ArrayList<Integer>();
	private String couleur;
	private String descriptionPoste;
	private int prixCompetence;
	private int nbCompetence = 0;
	private boolean monterEnComptence = false;
	private boolean Rep = false;

	/**
	 * Indique le nom, le prix du Salarie, la liste de ses Salaires, le prix d'une maison, le nombre de maison, et la couleur que possède un Salarie
	 * @param nom String
	 * @param valeur int
	 * @param Salaire ArrayList
	 * @param prixCompetence int
	 * @param nbCompetence int
	 * @param couleur String
	 */
	public CaseSalarie(String nom, int valeur, ArrayList<Integer> Salaire, int prixCompetence, int nbCompetence, String couleur, String descriptionPoste) {
		super(nom, valeur);
		this.couleur = couleur;
		this.Salaire = Salaire;
		this.prixCompetence = prixCompetence;
		this.nbCompetence = nbCompetence;
		this.descriptionPoste = descriptionPoste;
	}

	/**
	 * Action a réaliser sur un salarié:
	 * - Si le salarié n'a pas de patron un joueur peut l'embaucher
	 * - Si un joueur tombe sur un salarié sans patron il règle un salaire au patron
	 * - Si un joueur tombe sur un de ses salaries il ne se passe rien, mais peut les faire monter en compétences
	 */

	@SuppressWarnings({ "unused", "static-access" })
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fp) {

		Clavier es = new Clavier();

		if(this.getPatron() == null) {
			if(getRep()) {
				if(embaucherSalarie(joueur, fp))
					if(fp != null) fp.setSignetPatron(joueur, this);
			}
			else {
				es.println("-> " + joueur.getNom() + " décide de ne pas embaucher le salarié.");
				if(fp != null) fp.afficherMessage(joueur.getNom() + " décide de ne pas embaucher le salarié.");
			}
		}

		else if(this.getPatron() != joueur)
			payerSalaire(joueur, fp);

		else {
			es.println("-> " + joueur.getNom() + " est le patron de ce salarié");
			fp.afficherMessage(joueur.getNom() + " est le patron de ce salarié");

			if(this.getPeutMonterEnCompetence() && fp.getPartie().PARTIE_AUTO) {
				this.monterEnCompetence(fp);
				fp.setCompetence(this);
				es.println("-> " + joueur.getNom() + " a fait monter en compétence " + getNbCompetence() + "fois ce salarié" );
			}
		}
	}


	public boolean embaucherSalarie(JoueurMonopoly joueur, FenetreDeJeu fp) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		}
		else {
			setPatron(joueur);
			joueur.ajouterSalarie(this);
			joueur.retirerArgent(this.getPrix());
			System.out.println("-> " + joueur.getNom() + " embauche " + this.getNom() + " pour " + this.getPrix() + "€");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " embauche " + this.getNom() + " pour " + this.getPrix() + "€");
			return true;
		}
	}

	public void payerSalaire(JoueurMonopoly joueur, FenetreDeJeu fp) {
		String beneficiaire = "la Banque";

		if(!this.getPatron().getEstMalade()) {
			joueur.retirerArgent(getSalaire());
			if(!this.getPatron().getEstFauche()) {
				this.getPatron().ajouterArgent(getSalaire());
				beneficiaire = this.getPatron().getNom();
			}
			System.out.println(" > " + joueur.getNom() + " paye un salaire de " + getSalaire() + "€ à " + beneficiaire);
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un salaire de " + getSalaire() + "€ à " + beneficiaire);
		}
		else {
			System.out.println("-> Le propriétaire est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
			if(fp!=null) fp.afficherMessage("Le propriétaire est en arret maladie. " + joueur.getNom() + " ne paye pas de salaire.");
		}
	}

	/**
	 * Permet l'ajout de compétence sur un salarié 
	 * @param fp FenetrePrincipale
	 */
	public void monterEnCompetence(FenetreDeJeu fp){

		nbCompetence++;
		Patron.retirerArgent(this.getPrixCompetence());

		if(this.nbCompetence <=4 ){
			System.out.println("-> " + Patron.getNom() + " a fait monter en compétence l'employé "+getNom()+" !");
			if(fp!=null) fp.afficherMessage("-> " + Patron.getNom() + " a fait monter en compétence l'employé "+getNom()+" !");
		}
		else{
			System.out.println("-> " + Patron.getNom() + " a obtenu un grade "+getNom()+" et ne peut plus monter en compétence!");
			if(fp!=null) fp.afficherMessage("-> " + Patron.getNom() + " a obtenu un grade "+getNom()+" et ne peut plus monter en compétence!");
		}
	}

	/**
	 * Méthode permettant l'affichage d'une fenêtre lors de l'embauche d'un salarie
	 */
	@SuppressWarnings("static-access")
	public void fenetreAction(FenetreDeJeu fp) {

		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				Rep = true;
			fp.getPartie().reprendrePartie();
		}
		else if(this.getPatron() == null)
			fp.afficherFenetreEmbaucherSalarie();
		else
			fp.getPartie().reprendrePartie();
	}


	/* ===========================
	   Méthodes abstraites de Case
	   =========================== */

	public boolean getPeutMonterEnCompetence() {
		if(Patron.getListeCouleur().contains(this.getCouleur())){

			ArrayList<Case> couleur = new ArrayList<Case>();
			for(Case c: Patron.getListeSalaries())
				if(c.getCouleur() == this.getCouleur() && c != this)
					couleur.add(c);

			this.monterEnComptence = true;
			for(Case c:couleur) {
				if(!(this.getNbCompetence() == c.getNbCompetence() || this.getNbCompetence() == c.getNbCompetence() - 1))
					this.monterEnComptence = false;
			}

			if(Patron.getArgent() < this.getPrixCompetence()) {
				this.monterEnComptence = false;
				System.out.println("Vous n'avez pas assez monter en competence!");
			}
			if(getNbCompetence() == 5) {
				this.monterEnComptence = false;
				System.out.println("Vous ne pouvez pas avoir plus de compétence !");
			}
		}
		else
			this.monterEnComptence = false;

		return this.monterEnComptence;
	}

	@Override
	public int getSalaire() {
		int aPayer = Salaire.get(getNbCompetence());
		if(Patron.getListeCouleur().contains(this.getCouleur()) && getNbCompetence() == 0)
			//le salaire est doublé si le joueur a embauché tous les salariés d'une même couleur
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
		return "CaseSalarie ["+ super.toString() +", Patron=" + (Patron==null?"null":Patron.getNom()) + ", couleur=" + couleur + ", Salaire=" + Salaire
				+ ", prixCompetence=" + prixCompetence + ", monterEnComptence=" + monterEnComptence + ", nbCompetence=" + nbCompetence + ", descriptionPoste" + descriptionPoste + "]";
	}

	public String descriptionPoste() {
		return descriptionPoste;}
	


}
