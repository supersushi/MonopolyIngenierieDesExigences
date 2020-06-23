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
 * Crée l'action de la case Competence
*@author   Massourang Jugurtha Lina Emma
*/


public class CaseCompetence extends Case {

	private JoueurMonopoly proprietaire;
	private ArrayList<Integer> loyer = new ArrayList<Integer>();
	private String couleur;
	private int prixCompetence;
	private int nbCompetence = 0;
	private boolean peutAcheterCompetence = false;
	private boolean rep = false;

	/**
	 * Indique la competence et son prix d'achat ainsi que la couleur qui lui est rattaché 
	 * @param nom String
	 * @param valeur int
	 * @param loyer ArrayList
	 * @param prixCompetence int
	 * @param nbCompetence int
	 * @param couleur String
	 */
	public CaseCompetence(String nom, int valeur, ArrayList<Integer> loyer, int prixCompetence, int nbCompetence, String couleur) {
		super(nom, valeur);
		this.couleur = couleur;
		this.loyer = loyer;
		this.prixCompetence = prixCompetence;
		this.nbCompetence = nbCompetence;
	}

	/**
	 * Méthode qui permet de choisir un action a effectuer sur la competence : 
	 * Si personne ne possède le competence, un joueur peut l'obtenir
	 * Si un joueur tombe sur un competence qui appartient à un joueur, il paye des pénalités au joueur qui lq possède
	 * Si un joueur tombe sur une de ses competences il n'a rien a faire mais il peut monter en compétence (au bout de 4 acheté il a un grade)
	 *  @see jeudeplateau.Joueur
	 * @see Case
	 */

	@SuppressWarnings({ "unused", "static-access" })
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Console es = new Console();

		if(this.getProprietaire() == null) {
			if(getRep()) {
				if(acheterCompetence(joueur, fjeu))
					fjeu.setSignetProprietaire(joueur, this);
			}
			else {
				es.println("-> " + joueur.getNom() + " décide de ne pas acheter cette competence.");
				fjeu.afficherMessage(joueur.getNom() + " décide de ne pas acheter cette competence.");
			}
		}

		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, fjeu);

		else {
			es.println(" -> " + joueur.getNom() + " possède cette competence");
			fjeu.afficherMessage(joueur.getNom() + " possède cette competence");

			if(this.getPeutAcheterCompetence() && fjeu.getPartie().PARTIE_AUTO) {
				this.ajouterCompetence(fjeu);
				fjeu.setCompetence(this);
				es.println("-> " + joueur.getNom() + " acquiert" + getNbCompetence() + " une nouvelle competence : " + (getNbCompetence()>0?"s":"") + ".");
			}
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
			System.out.println(" -> " + joueur.getNom() + " acquiert " + this.getNom() + " pour " + this.getPrix() + "€");
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " acquiert " + this.getNom() + " pour " + this.getPrix() + "€");
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
			System.out.println(" > " + joueur.getNom() + " paye un loyer de " + getLoyer() + "€ à " + beneficiaire);
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " paye un loyer de " + getLoyer() + "€ à " + beneficiaire);
		}
		else {
			System.out.println(" -> Le propriétaire est en arret maladie. " + joueur.getNom() + " ne paye pas de loyer.");
			if(fjeu!=null) fjeu.afficherMessage("Le propriétaire est en arret maladie. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}

	/**A VOIR
	 * Permet l'ajout d'une Competence sur un Competence
	 * @param fjeu FenetreDeJeu
	 */
	public void ajouterCompetence(FenetreDeJeu fjeu){

		nbCompetence++;
		proprietaire.retirerArgent(this.getPrixCompetence());

		if(this.nbCompetence <=4 ){
			System.out.println(" -> " + proprietaire.getNom() + " a posé une Competence sur "+getNom()+" !");
			if(fjeu!=null) fjeu.afficherMessage("-> " + proprietaire.getNom() + " a posé une Competence sur "+getNom()+" !");
		}
		else{
			System.out.println(" -> " + proprietaire.getNom() + " a posé un hôtel sur "+getNom()+" et ne peut avoir de compétence supplémentaire!");
			if(fjeu!=null) fjeu.afficherMessage("-> " + proprietaire.getNom() + " a posé un hôtel sur "+getNom()+" et ne peut plus poser de bâtiments!");
		}
	}

	/**
	 * Méthode permettant l'affichage d'une fenêtre lors de l'achat d'une competence pui reprend la partie
	 */
	@SuppressWarnings("static-access")
	public void fenetreAction(FenetreDeJeu fjeu) {

		if(fjeu.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				rep = true;
			fjeu.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fjeu.afficherFenetreAchatCompetence();
		else
			fjeu.getPartie().reprendrePartie();
	}


	public boolean getPeutAcheterCompetence() {
		if(proprietaire.getListeCouleur().contains(this.getCouleur())){

			ArrayList<Case> couleur = new ArrayList<Case>();
			for(Case c: proprietaire.getListeCompetences())
				if(c.getCouleur() == this.getCouleur() && c != this)
					couleur.add(c);

			this.peutAcheterCompetence = true;
			for(Case c:couleur) {
				if(!(this.getNbCompetence() == c.getNbCompetence() || this.getNbCompetence() == c.getNbCompetence() - 1))
					this.peutAcheterCompetence = false;
			}

			if(proprietaire.getArgent() < this.getPrixCompetence()) {
				this.peutAcheterCompetence = false;
				System.out.println("Vous n'avez pas assez d'argent pour acquérir une nouvelle compétence !");
			}
			if(getNbCompetence() == 5) {
				this.peutAcheterCompetence = false;
				System.out.println("Vous avez déjà le nombre de compétence maximal pour ce grade !");
			}
		}
		else
			this.peutAcheterCompetence = false;

		return this.peutAcheterCompetence;
	}

	@Override
	public int getLoyer() {
		int aPayer = loyer.get(getNbCompetence());
		if(proprietaire.getListeCouleur().contains(this.getCouleur()) && getNbCompetence() == 0)
			aPayer*=2; // loyer double si le joueur possède tous les Competences d'une couleur mais sans Competence.GRADE?

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
	public JoueurMonopoly getProprietaire() {
		return proprietaire;
	}

	@Override
	public boolean getRep() {
		return rep;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {
		this.proprietaire = j;
	}

	@Override
	public void setRep(boolean b) {
		this.rep = b;
	}

	@Override
	public String toString() {
		return "CaseCompetence ["+ super.toString() +", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + ", couleur=" + couleur + ", loyer=" + loyer
				+ ", prixCompetence=" + prixCompetence + ", peutAcheterCompetence=" + peutAcheterCompetence + ", nbCompetence=" + nbCompetence + "]";
	}

	public static void main(String[] args) {

		Console es = new Console();

		es.println("TEST DE LA CLASSE : CaseCompetence\n");

		CaseCompetence c = new CaseCompetence("Avenue de la République", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise");
		JoueurMonopoly j1 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j2 = new JoueurMonopoly("Benoit", 1, 1000);

		es.println(c.toString() + "\n");
		es.println(j1.toString() + "\n");
		es.println(j2.toString() + "\n");

		c.acheterCompetence(j1, null);

		es.println("- Propriétaire de " + c.getNom() + " : "+ c.getProprietaire().getNom());
		es.println("- Nombre de competences : "+ c.getNbCompetence() + "");

		c.payerLoyer(j2, null);

		es.println("");
		c.ajouterCompetence(null);
		c.ajouterCompetence(null);
		c.ajouterCompetence(null);
		es.println("== Nombre de competences : "+ c.getNbCompetence() + "");

		c.payerLoyer(j2, null);

		es.println("\n" + c.toString() + "\n");
		es.println(j1.toString() + "\n");
		es.println(j2.toString());
	}

}
