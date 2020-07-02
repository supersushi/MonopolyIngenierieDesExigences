package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import cartes.*;
import cases.Case;
import cases.CaseAllerArretMaladie;
import cases.CaseArretMaladie;
import cases.CaseChance;
import cases.CaseCommunaute;
import cases.CaseDepart;
import cases.CaseEntreprise;
import cases.CaseClient;
import cases.CaseImpots;
import cases.CaseOpenSpace;
import cases.CaseSalarie;

/**
 * Initialise le plateau du monopoly avec toutes ses cases
*@author  Massourang Jugurtha Lina Emma
*/
public class PlateauMonopoly extends jeu.Plateau {

	private ArrayList<JoueurMonopoly> joueurs = new ArrayList<JoueurMonopoly>();
	private ArrayList<Carte> chance = new ArrayList<Carte>();
	private ArrayList<Carte> communauté = new ArrayList<Carte>();

	/**
	 * Crée un plateau avec un nombre de joueur
	 * @param nombreDeJoueurs int
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
		setCase(12, new CaseEntreprise("Or"));
		setCase(15, new CaseClient("Client : Bancaire"));
		setCase(17, new CaseCommunaute());
		setCase(20, new CaseOpenSpace());
		setCase(22, new CaseChance());
		setCase(25, new CaseClient("Client : BTP"));
		setCase(28, new CaseEntreprise("Entreprise IT"));
		setCase(30, new CaseAllerArretMaladie());
		setCase(33, new CaseCommunaute());
		setCase(35, new CaseClient("Client : Service"));
		setCase(36, new CaseChance());
		setCase(38, new CaseImpots("Frais de mutuelle", 100));

		/* INITIALISATION DES SALARIES */
		setCase(1, new CaseSalarie("Responsable pilotage", 60, new ArrayList<Integer>(Arrays.asList(2, 10, 30, 90, 160, 250)), 50, 0, "brun","Rattaché au responsable du service pilotage et performance opérationnelle, \nvous assurez le suivi des indicateurs de pilotage des activités internes \net externalisées."));
		setCase(3, new CaseSalarie("Technicien réseau", 60, new ArrayList<Integer>(Arrays.asList(4, 20, 60, 180, 320, 450)), 50, 0, "brun", "Vous veillerez au bon fonctionnement du réseau ou des réseaux de l’entreprise.\n Vous serez responsable de la qualité de l’acheminement de toutes les \ninformations qui circulent sur le réseau."));
		setCase(6, new CaseSalarie("Admin", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise", "Vous occuperez un rôle hautement stratégique à la tête d'une entreprise.\nNommé par les actionnaires, vous ferez partie du conseil d'administration \net voterez les décisions les plus importantes pour la vie de sa société."));
		setCase(8, new CaseSalarie("Responsable de la conduite du changement", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise", "Votre mission sera d’accompagner les entreprises dans la restructuration de\nleurs systèmes d’information au niveau des technologies, des ressources\nhumaines et des finances."));
		setCase(9, new CaseSalarie("Chef de projet MOE", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise", "Vous concevrez des applications informatiques répondant à des besoins \nspécifiques."));
		setCase(11, new CaseSalarie("Chef de projet MOA", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve", "Vous serez chef de projet fonctionnel web. \nVous aiderez le client à définir ses besoins et les traduirez « fonctionnellement »\ndans un cahier des charges.\nCe cahier des charges sera ensuite traduit « techniquement »\npar la maîtrise d'œuvre (MOE)."));
		setCase(13, new CaseSalarie("Sponsor", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve", "Vous serez en charge de trouver des accords stratégiques avec des entreprises et\nautres personnes morales, afin qu'elles financent des clubs sportifs, associations ou\nautres organisations."));
		setCase(14, new CaseSalarie("Acquéreur", 160, new ArrayList<Integer>(Arrays.asList(12, 60, 180, 500, 700, 900)), 100, 0, "mauve", "Vous serez chargé de l'achat des produits nécessaires à l'activité de votre entreprise.\nVous rechercherez les fournisseurs qui correspondent aux exigences et négocierez\navec eux les prix et les délais de livraison."));
		setCase(16, new CaseSalarie("Gestionnaire d'application", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange", "Vous représenterez les maîtres d'ouvrage lors de la vie courante des systèmes. \nVous participerez à l'élaboration des règles de fonctionnement \net d'utilisation du système d'information."));
		setCase(18, new CaseSalarie("Architecte fonctionnel", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange", "Vous traduirez les besoins fonctionnels d'un système d'information en \nfonction des contraintes logistiques, économiques et des objectifs \ndu domaine métier."));
		setCase(19, new CaseSalarie("Expert métier", 200, new ArrayList<Integer>(Arrays.asList(16, 80, 220, 600, 800, 1000)), 100, 0, "orange", "Vous assurerez principalement le rôle de conseil, d'assistance et de formation.\nÀ ce titre, vous interviendrez directement sur la totalité ou sur une partie d'un\nprojet."));
		setCase(21, new CaseSalarie("Développeur", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge", "Vous endosserez plusieurs casquettes en fonction des demandes diverses.\nVotre quotidien consistera à concevoir, programmer, développer et améliorer\ndes logiciels, ou encore assurer un rôle de maintenance et de suivi. "));
		setCase(23, new CaseSalarie("Expert technique", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge", "Vous assurerez un rôle de conseil, d'assistance, d'information \net de formation."));
		setCase(24, new CaseSalarie("Analyste concepteur", 240, new ArrayList<Integer>(Arrays.asList(20, 100, 300, 750, 925, 1100)), 150, 0, "rouge", "Vous mettrez au point ou améliore les systèmes et les applications informatiques\nutilisées dans l'entreprise, sous la direction d'un chef de projet."));
		setCase(26, new CaseSalarie("Architecte SI", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune", "Vous serez garant de la cohérence du SI, avec un focus fort sur la partie infrastructure.\n Vous préconiserez les solutions techniques en cas d'évolution de ce dernier.\n Votre connaissance technique sera un atout majeur dans cet objectif."));
		setCase(27, new CaseSalarie("Expert securite", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune", "Vous réaliserez un diagnostic du système d'information d'une entreprise \ndans le but de déceler les éventuels points faibles "));
		setCase(29, new CaseSalarie("Architecte technique", 280, new ArrayList<Integer>(Arrays.asList(24, 120, 360, 850, 1025, 1200)), 150, 0, "jaune", "Vous conçevrez l'architecture technique du système d'information.\nC'est-à-dire que vous contrôlerez tout l'aspect technique des différents \nprojets informatiques qui sont sous sa gestion."));
		setCase(31, new CaseSalarie("Responsable portefeuille", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert", "Vous choisirez objectivement les produits de placement, achèterez\net vendrez des actions et obligations en bourse puis assurerez le \nsuivi en informant le client."));
		setCase(32, new CaseSalarie("Auditeur", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert","Expert en gestion, management et comptabilité votre mission première est de\nvérifier la régularité des comptes d'une entreprise.\nDans la majorité des cas vous ne faites pas partie des salariés de l'entreprise \nque vous passerez au crible."));
		setCase(34, new CaseSalarie("Formateur", 320, new ArrayList<Integer>(Arrays.asList(28, 150, 450, 1000, 1200, 1400)), 200, 0, "vert", "Vous transmetrez des connaissances théoriques, pratiques et professionnelles \ntrès spécialisées."));
		setCase(37, new CaseSalarie("Auditeur projet", 350, new ArrayList<Integer>(Arrays.asList(35, 175, 500, 1100, 1300, 1500)), 200, 0, "bleu","Expert en gestion, management et comptabilité votre mission première \nest de vérifier la régularité des comptes d'une entreprise.\nDans la majorité des cas vous ne faites pas partie des salariés de l'entreprise  \nque vous passerez au crible."));
		setCase(39, new CaseSalarie("Expert méthodologique", 400, new ArrayList<Integer>(Arrays.asList(50, 200, 600, 1400, 1700, 2000)), 200, 0, "bleu", "Vous mettrez en place une démarche qualité pour établir des référentiels de bonnes\npratique et implanter des démarches fondées sur l’amélioration continue.\nEn tant que contributeur aux audits qualité, vous utiliserez et mettrez en œuvre les\ntechniques et les procédures d’audit. "));


		/* INITIALISATION DES CARTES CHANCES */
		
		chance.add(new CarteDeplacement("Expert méthodologique", "Vous avez rendez-vous avec un expert\nméthodologique.\nSi vous passez par la case départ, recevez 200€.", 39, false));
		chance.add(new CarteDeplacement("Formateur", "Vous avez une formation. Allez voir formateur. \nSi vous passez par la case départ, recevez 200€.", 34, false));
		chance.add(new CarteDeplacement("Développeur", "Vous avez rendez-vous avec un développeur\npour votre site. \nSi vous passez par la case départ, recevez 200€.", 21, false));
		chance.add(new CarteDeplacement("Chef de projet MOA", "Le chef de projet n'aime pas attendre. Dépechez vous. \nSi vous passez par la case départ, recevez 200€.", 11, false));
		chance.add(new CarteDeplacement("Reculez", "Reculez de 4 cases.", -4, true));
		chance.add(new CarteDeplacement("Mutation", "Le joueur est muté. Il prend un nouveau départ.", 0, false));
		chance.add(new CartePayer("Amende", "Amende pour excès de vitesse : 55€.", 55));
		
		Collections.shuffle(chance); //Mélange des cartes


		/* INITIALISATION DES CARTES COMMUNAUTÉS */
		communauté.add(new CartePayer("Frais de mutuelle", "Payez 10€ de frais de mutuelle.", 10));
		communauté.add(new CartePayer("Frais d'hospitalisation", "Payez 100€ de frais d'hospitalisation.", 100));
		communauté.add(new CartePayer("Médecin", "Visite chez le médecin : payez 25€.", 25));
		communauté.add(new CartePayer("Campagne de publicité", "Pour lancer votre nouveau produit vous\ndevez payez 30€.", 30));
		communauté.add(new CartePayer("Erreur de la Banque", "Erreur de la Banque en votre défaveur. \n Remboursez 150€.", 150));
		communauté.add(new CarteRecevoirArgent("Remboursement", "Les impôts vous remboursent 20€.", 20));
		communauté.add(new CarteRecevoirArgent("Assurance vie", "Votre assurance vie vous rapporte 100€.", 100));
		communauté.add(new CarteRecevoirArgent("Promotion", "Vous venez d'être promu, félicitations ! \n La banque vous donne 50", 50));
		communauté.add(new CartePayer("Audit", "Vous avez fait une erreur, les auditeurs vous pénalise. \n Donnez leur 50€.", 50));
		communauté.add(new CarteRecevoirArgent("Nuit de l'info", "Bravo: Vous avez gagné le premier prix de la Nuit de l'Info. \nRecevez 100€.", 100));
		communauté.add(new CarteRecevoirArgent("Nouveau produit", "La vente de votre nouveau produit\nvous rapporte 150€.", 150));
		communauté.add(new CarteRecevoirArgent("Hackathon", "Bravo: Vous avez gagné votre premier hackaton. \nRecevez 200€.", 200));
		communauté.add(new CarteRecevoirArgent("Héritage", "Vous héritez de 100€.", 100));
		communauté.add(new CarteRecevoirArgent("Placement", "Votre placement vous rapporte. \nRecevez 100€.", 100));
		communauté.add(new CarteRecevoirArgent("Erreur de la Banque", "Erreur de la Banque en votre faveur. \nRecevez 200€.", 200));
		communauté.add(new CarteDeplacement("Arret Maladie", "Allez en arret maladie. \n Allez sur la case arret maladie sans passez\npar la case départ. \nVous ne recevez pas 200€.", 10, false));
		communauté.add(new CarteSortirArretMaladie("Guérison anticipée", "Vous êtes guéri. \n Vous conservez cette carte."));

		//utilisé pour mélanger les cartes
		Collections.shuffle(communauté); 
	}

	/* PARTIE JOUEUR */

	/**
	 * Permet de déplacer un joueur d'un certain nombre de cases
	 * @param joueur JoueurMonopoly
	 * @param nombreDeCases int
	 */
	public void deplacerJoueur(JoueurMonopoly joueur, int nombreDeCases) {
		int pos;

		if((joueur.getPosition() + nombreDeCases) >= getNbCases()) {
			pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
			System.out.println("-> " + joueur.getNom() + " passe par la case départ et gagne 200€ !");
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
	 * Retourne la case où est le joueur actif
	 * @return Case
	 */
	public Case getCaseActive() {
		return getCase(getJoueurActif().getPosition());
	}

	/* PARTIE JEU */

	/**
	 * Mettre fin à la partie
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
	 */
	public Carte tirerCarteChance() {
		Carte c = chance.remove(0);
		if(!c.getNom().equals("Sortie"))
			chance.add(c);
		return c;
	}

	/**
	 * Renvoie la liste des cartes communautés
	 * @return c
	 */
	public Carte tirerCarteCommunauté() {
		Carte c = communauté.remove(0);
		if(!c.getNom().equals("Sortie"))
			communauté.add(c);
		return c;
	}

	/**
	 * Remet dans le paquet la carte "Sortie d'arret maladie" lorsqu'un joueur l'a utilisé
	 */
	public void remettreCarteSortieArretMaladie() {

		boolean carteDansPaquetChance = false;
		for(Carte c:chance) {
			if(c.getNom().equals("Sortie"))
				carteDansPaquetChance = true;  
		}

		if(carteDansPaquetChance)
			chance.add(new CarteSortirArretMaladie("Guérison anticipée", "Vous êtes guéri. \n Vous conservez cette carte."));
		else
			communauté.add(new CarteSortirArretMaladie("Guérison anticipée", "Vous êtes guéri. \n Vous conservez cette carte."));

	}

	@Override
	/**
	 * redefinition de la methode toString
	 */
	public String toString() {
		return "PlateauMonopoly [toString()=" + super.getCase(1) + "]";
	}

}
