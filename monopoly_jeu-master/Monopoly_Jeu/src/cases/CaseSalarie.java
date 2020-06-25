package cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action de la case Salarie
*@author   Massourang Jugurtha Lina Emma
*/


public class CaseSalarie extends Case {

	private JoueurMonopoly Patron;
	private ArrayList<Integer> Salaire = new ArrayList<Integer>();
	private String couleur;
	private int prixMaison;
	private int nbCompetence = 0;
	private boolean monterEnComptence = false;
	private boolean Rep = false;

	/**
	 * Indique le nom, le prix du Salarie, la liste de ses Salaires, le prix d'une maison, le nombre de maison, et la couleur que possède un Salarie
	 * @param nom String
	 * @param valeur int
	 * @param Salaire ArrayList
	 * @param prixMaison int
	 * @param nbCompetence int
	 * @param couleur String
	 */
	public CaseSalarie(String nom, int valeur, ArrayList<Integer> Salaire, int prixMaison, int nbCompetence, String couleur) {
		super(nom, valeur);
		this.couleur = couleur;
		this.Salaire = Salaire;
		this.prixMaison = prixMaison;
		this.nbCompetence = nbCompetence;
	}

	/**
	 * Méthode permettant de vérifier un Salarie : <br>
	 * <ul>
	 * <li>Si personne ne possède le Salarie, un joueur peut l'acheter</li>
	 * <li>Si un joueur tombe sur un Salarie appartenant à un autre joueur, il paye un Salaire au joueur qui le possède</li>
	 * <li>si un joueur tombe sur un de ses Salaries il ne se passe rien, mais peut acheter des maisons</li>
	 * </ul>
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */

	@SuppressWarnings({ "unused", "static-access" })
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fp) {

		Console es = new Console();

		if(this.getPatron() == null) {
			if(getRep()) {
				if(embaucherSalarie(joueur, fp))
					fp.setSignetPatron(joueur, this);
			}
			else {
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter ce Salarie.");
				fp.afficherMessage(joueur.getNom() + " décide de ne pas acheter ce Salarie.");
			}
		}

		else if(this.getPatron() != joueur)
			payerSalaire(joueur, fp);

		else {
			es.println(" > " + joueur.getNom() + " est sur son propre Salarie");
			fp.afficherMessage(joueur.getNom() + " est sur son propre Salarie");

			if(this.getPeutMonterEnCompetence() && fp.getPartie().PARTIE_AUTO) {
				this.monterEnCompetence(fp);
				fp.setCompetence(this);
				es.println(" > " + joueur.getNom() + " possède désormais " + getNbCompetence() + " competennce" + (getNbCompetence()>0?"s":"") + " sur ce Salarie.");
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
			System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
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
			System.out.println(" > " + joueur.getNom() + " paye un Salaire de " + getSalaire() + "€ à " + beneficiaire);
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un Salaire de " + getSalaire() + "€ à " + beneficiaire);
		}
		else {
			System.out.println(" > Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de Salaire.");
			if(fp!=null) fp.afficherMessage("Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de Salaire.");
		}
	}

	/**
	 * Permet l'ajout d'une maison sur un Salarie
	 * @param fp FenetrePrincipale
	 */
	public void monterEnCompetence(FenetreDeJeu fp){

		nbCompetence++;
		Patron.retirerArgent(this.getPrixCompetence());

		if(this.nbCompetence <=4 ){
			System.out.println(" > " + Patron.getNom() + " a posé une maison sur "+getNom()+" !");
			if(fp!=null) fp.afficherMessage(" > " + Patron.getNom() + " a posé une maison sur "+getNom()+" !");
		}
		else{
			System.out.println(" > " + Patron.getNom() + " a posé un hôtel sur "+getNom()+" et ne peut plus poser de bâtiments!");
			if(fp!=null) fp.afficherMessage(" > " + Patron.getNom() + " a posé un hôtel sur "+getNom()+" et ne peut plus poser de bâtiments!");
		}
	}

	/**
	 * Méthode permettant l'affichage d'une fenêtre pour l'achat d'un Salarie, et reprenant le cours de la partie
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
			aPayer*=2; // Salaire double si le joueur possède tous les Salaries d'une couleur mais sans maison.

		return aPayer;
	}

	@Override
	public String getCouleur() {
		return couleur;
	}

	@Override
	public int getPrixCompetence() {
		return prixMaison;
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
				+ ", prixMaison=" + prixMaison + ", monterEnComptence=" + monterEnComptence + ", nbCompetence=" + nbCompetence + "]";
	}

	public static void main(String[] args) {

		Console es = new Console();

		es.println("TEST DE LA CLASSE : CaseSalarie\n");

		CaseSalarie c = new CaseSalarie("Avenue de la République", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise");
		JoueurMonopoly j1 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j2 = new JoueurMonopoly("Benoit", 1, 1000);

		es.println(c.toString() + "\n");
		es.println(j1.toString() + "\n");
		es.println(j2.toString() + "\n");

		c.embaucherSalarie(j1, null);

		es.println("== Propriétaire de " + c.getNom() + " : "+ c.getPatron().getNom());
		es.println("== Nombre de maisons : "+ c.getNbCompetence() + "");

		c.payerSalaire(j2, null);

		es.println("");
		c.monterEnCompetence(null);
		c.monterEnCompetence(null);
		c.monterEnCompetence(null);
		es.println("== Nombre de maisons : "+ c.getNbCompetence() + "");

		c.payerSalaire(j2, null);

		es.println("\n" + c.toString() + "\n");
		es.println(j1.toString() + "\n");
		es.println(j2.toString());
	}


}
