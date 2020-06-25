package jeumonopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import cartes.*;
import cases.CaseAllerArretMaladie;
import cases.CaseArretMaladie;
import cases.CaseChance;
import cases.CaseCommunaute;
import cases.CaseDepart;
import cases.CaseClient;
import cases.CaseImpots;
import cases.CaseOpenSpace;
import cases.CaseServicePublic;
import cases.CaseSalarie;
import jeudeplateau.Carte;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

/**
 * Initialise le plateau du monopoly avec toutes ses cases
*@author  Massourang Jugurtha Lina Emma
*/
public class PlateauMonopoly extends jeudeplateau.Plateau {

	/**
	 * @see JoueurMonopoly
	 */
	private ArrayList<JoueurMonopoly> joueurs = new ArrayList<JoueurMonopoly>();
	/**
	 * @see Carte
	 */
	private ArrayList<Carte> chance = new ArrayList<Carte>();
	private ArrayList<Carte> communaut� = new ArrayList<Carte>();

	/**
	 * Cr�e un plateau avec un nombre de joueur
	 * @param nombreDeJoueurs int
	 * @see jeudeplateau.Plateau
	 * @see CaseDepart
	 * @see CaseCommunaute
	 * @see CaseImpots
	 * @see CaseClient
	 * @see CaseChance
	 * @see CaseArretMaladie
	 * @see CaseServicePublic
	 * @see CaseParcGratuit
	 * @see CaseAllerArretMaladie
	 * @see CaseSalarie
	 * @see CarteDeplacement
	 * @see CartePayer
	 * @see CarteRecevoirArgent
	 * @see CarteSortirArretMaladie
	 */
	public PlateauMonopoly(int nombreDeJoueurs) {
		super(nombreDeJoueurs, 40);


		/* INITIALISATION DES JOUEURS */
		for(int i = 0; i < this.getNbJoueurs(); i++) {
			this.joueurs.add(new JoueurMonopoly("Joueur"+(i+1), i, 1000));
		}

		/* INITIALISATION DES CASES*/
		setCase(0, new CaseDepart());
		setCase(2, new CaseCommunaute());
		setCase(4, new CaseImpots("Impots sur le revenu", 200));
		setCase(5, new CaseClient("Client : Gestion d'actifs"));
		setCase(7, new CaseChance());
		setCase(10, new CaseArretMaladie());
		setCase(12, new CaseServicePublic("Or"));
		setCase(15, new CaseClient("Client : bancaire"));
		setCase(17, new CaseCommunaute());
		setCase(20, new CaseOpenSpace());
		setCase(22, new CaseChance());
		setCase(25, new CaseClient("Client : BTP"));
		setCase(28, new CaseServicePublic("Entreprise IT"));
		setCase(30, new CaseAllerArretMaladie());
		setCase(33, new CaseCommunaute());
		setCase(35, new CaseClient("Client: Service"));
		setCase(36, new CaseChance());
		setCase(38, new CaseImpots("Frais de mutuelle", 100));

		/* INITIALISATION DES SALARIES */
		setCase(1, new CaseSalarie("Responsable pilotage", 60, new ArrayList<Integer>(Arrays.asList(2, 10, 30, 90, 160, 250)), 50, 0, "brun"));
		setCase(3, new CaseSalarie("Technicien r�seau", 60, new ArrayList<Integer>(Arrays.asList(4, 20, 60, 180, 320, 450)), 50, 0, "brun"));

		setCase(6, new CaseSalarie("Admin", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise"));
		setCase(8, new CaseSalarie("Responsable de la conduite du changement", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise"));
		setCase(9, new CaseSalarie("Chef de projet MOE", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise"));

		setCase(11, new CaseSalarie("Chef de projet MOA", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve"));
		setCase(13, new CaseSalarie("Sponsor", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve"));
		setCase(14, new CaseSalarie("Acqu�reur", 160, new ArrayList<Integer>(Arrays.asList(12, 60, 180, 500, 700, 900)), 100, 0, "mauve"));

		setCase(16, new CaseSalarie("Gestionnaire d'application", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange"));
		setCase(18, new CaseSalarie("Architecte fonctionnel", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange"));
		setCase(19, new CaseSalarie("Expert m�tier", 200, new ArrayList<Integer>(Arrays.asList(16, 80, 220, 600, 800, 1000)), 100, 0, "orange"));

		setCase(21, new CaseSalarie("D�veloppeur", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge"));
		setCase(23, new CaseSalarie("Expert COTS", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge"));
		setCase(24, new CaseSalarie("Analyste concepteur", 240, new ArrayList<Integer>(Arrays.asList(20, 100, 300, 750, 925, 1100)), 150, 0, "rouge"));

		setCase(26, new CaseSalarie("Architecte SI", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune"));
		setCase(27, new CaseSalarie("Expert securite", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune"));
		setCase(29, new CaseSalarie("Architecte technique", 280, new ArrayList<Integer>(Arrays.asList(24, 120, 360, 850, 1025, 1200)), 150, 0, "jaune"));

		setCase(31, new CaseSalarie("Responsable portefeuille", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert"));
		setCase(32, new CaseSalarie("Auditeur", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert"));
		setCase(34, new CaseSalarie("Formateur", 320, new ArrayList<Integer>(Arrays.asList(28, 150, 450, 1000, 1200, 1400)), 200, 0, "vert"));

		setCase(37, new CaseSalarie("Auditeur projet", 350, new ArrayList<Integer>(Arrays.asList(35, 175, 500, 1100, 1300, 1500)), 200, 0, "bleu"));
		setCase(39, new CaseSalarie("Expert m�thodologique", 400, new ArrayList<Integer>(Arrays.asList(50, 200, 600, 1400, 1700, 2000)), 200, 0, "bleu"));


		/* INITIALISATION DES CARTES CHANCES */
		
		chance.add(new CarteDeplacement("Expert m�thodologique", "Vous avez-vous avec un expert m�thodologique.\\nSi vous passez par la case d�part, recevez 200�.", 39, false));
		chance.add(new CarteDeplacement("Formateur", "Vous avez une formation. Allez voir formateur. \nSi vous passez par la case d�part, recevez 200�.", 34, false));
		chance.add(new CarteDeplacement("D�veloppeur", "Vous avez rendez-vous avec un d�veloppeur pour votre site. \nSi vous passez par la case d�part, recevez 200�.", 21, false));
		chance.add(new CarteDeplacement("Chef de projet MOA", "Le chef de projet n'aime pas attendre. D�pechez vous. \nSi vous passez par la case d�part, recevez 200�.", 11, false));
		chance.add(new CarteDeplacement("Reculez", "Reculez de 4 cases.", -4, true));
		chance.add(new CarteDeplacement("Mutation", "Le joueur est mut�. Il prend un nouveau d�part.", 0, false));
		chance.add(new CartePayer("Amende", "Amende pour exc�s de vitesse : 55�.", 55));
		
		Collections.shuffle(chance); //M�lange des cartes


		/* INITIALISATION DES CARTES COMMUNAUT�S */
		communaut�.add(new CartePayer("Frais de scolarit�", "Payez 150� de frais de scolarit�.", 150));
		communaut�.add(new CartePayer("Frais d'hospitalisation", "Payez 100� de frais d'hospitalisation.", 100));
		communaut�.add(new CartePayer("M�decin", "Visite chez le m�decin : payez 25�.", 25));

		communaut�.add(new CarteRecevoirArgent("Remboursement", "Les imp�ts vous remboursent 20�.", 20));
		communaut�.add(new CarteRecevoirArgent("Assurance vie", "Votre assurance vie vous rapporte 100�.", 100));
		communaut�.add(new CarteRecevoirArgent("Anniversaire", "C'est votre anniversaire ! \nChaque joueur doit vous donner 10�.", 10));
		communaut�.add(new CarteRecevoirArgent("Commission d'expert", "Commission d'expert immobilier. \nRecevez 25�.", 25));
		communaut�.add(new CarteRecevoirArgent("Nuit de l'info", "Vous avez gagn� le premier prix de la Nuit de l'Info. \nRecevez 100�.", 100));
		communaut�.add(new CarteRecevoirArgent("Stock", "La vente de votre stock vous rapporte 50�.", 50));
		communaut�.add(new CarteRecevoirArgent("H�ritage", "Vous h�ritez de 100�.", 100));
		communaut�.add(new CarteRecevoirArgent("Placement", "Votre placement vous rapporte. \nRecevez 100�.", 100));
		communaut�.add(new CarteRecevoirArgent("Erreur de la Banque", "Erreur de la Banque en votre faveur. \nRecevez 200�.", 200));

		communaut�.add(new CarteDeplacement("Arret Maladie", "Allez en arret maladie. \n Allez sur la case arret maladie sans passez par la case d�part. Vous ne recevez pas 200�.", 10, false));
		communaut�.add(new CarteSortirArretMaladie("Gu�ri", "Vous �tes gu�ri. \n(Cette carte doit �tre conserv�e)"));

		Collections.shuffle(communaut�); //M�lange des cartes
	}

	/* PARTIE JOUEUR */

	/**
	 * Permet de d�placer un joueur d'un certain nombre de cases
	 * @param joueur JoueurMonopoly
	 * @param nombreDeCases int
	 * @see Joueur
	 * @see JoueurMonopoly
	 * @see jeudeplateau.Plateau
	 */
	public void deplacerJoueur(JoueurMonopoly joueur, int nombreDeCases) {
		int pos;

		if((joueur.getPosition() + nombreDeCases) >= getNbCases()) {
			pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
			System.out.println(" > " + joueur.getNom() + " passe par la case d�part et gagne 200� !");
			joueur.ajouterArgent(200);
		}
		else
			pos = joueur.getPosition() + nombreDeCases;

		if(!joueur.getEstFauche()) {
			joueur.setPosition(pos);
		}
	}

	/**
	 * Retourne quel joueur doit jouer 
	 * @return joueur
	 */
	public JoueurMonopoly getJoueurActif() {
		return this.joueurs.get(getJoueurActifID());
	}

	/**
	 * Changement de joueur
	 * @param i int
	 * @return joueur
	 */
	public JoueurMonopoly getJoueur(int i) {
		return this.joueurs.get(i);
	}

	/**
	 * Retourne le vainqueur de la partie
	 * @return joueur
	 */
	@Override
	public Joueur estVainqueur() {
		int res = 0;
		for(int i=0; i<joueurs.size(); i++) {
			if(getJoueur(i).getArgent() > getJoueur(res).getArgent())
				res = i;
		}
		return getJoueur(res);
	}

	/* PARTIE CASE */

	/**
	 * Retourne la case o� est le joueur actif
	 * @return Case
	 */
	public Case getCaseActive() {
		return getCase(getJoueurActif().getPosition());
	}

	/* PARTIE JEU */

	/**
	 * Mettre fin � la partie
	 * return boolean
	 */
	@Override
	public boolean finPartie() {
		int nbJoueursEnJeu = 0;
		for(JoueurMonopoly j:joueurs) {
			if(!j.getEstFauche()) nbJoueursEnJeu++;
		}
		return (nbJoueursEnJeu <= 1);
	}

	/*PARTIE CARTE */

	/**
	 * Retourne la liste des cartes chances
	 * @return c
	 * @see Carte
	 */
	public Carte tirerCarteChance() {
		Carte c = chance.remove(0);
		if(!c.getNom().equals("Sortie"))
			chance.add(c);
		return c;
	}

	/**
	 * Renvoie la liste des cartes communaut�s
	 * @return c
	 * @see Carte
	 */
	public Carte tirerCarteCommunaut�() {
		Carte c = communaut�.remove(0);
		if(!c.getNom().equals("Sortie"))
			communaut�.add(c);
		return c;
	}

	/**
	 * Remet dans le paquet la carte "Sortie d'arret maladie" lorsqu'un joueur l'utilise
	 * @see Carte
	 */
	public void remettreCarteSortieArretMaladie() {

		boolean carteDansPaquetChance = false;
		for(Carte c:chance) {
			if(c.getNom().equals("Sortie"))
				carteDansPaquetChance = true;  
		}

		if(carteDansPaquetChance)
			chance.add(new CarteSortirArretMaladie("Gu�ri", "Vous �tes gu�ri. \n(Cette carte doit �tre conserv�e)"));
		else
			communaut�.add(new CarteSortirArretMaladie("Gu�ri", "Vous �tes gu�ri. \n(Cette carte doit �tre conserv�e)"));
	}

	@Override
	/**
	 * redefinition de la methode toString
	 */
	public String toString() {
		return "PlateauMonopoly [toString()=" + super.getCase(1) + "]";
	}

}
