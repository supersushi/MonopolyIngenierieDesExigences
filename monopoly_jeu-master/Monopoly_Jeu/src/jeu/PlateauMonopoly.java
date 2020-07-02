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
	private ArrayList<Carte> communaut� = new ArrayList<Carte>();

	/**
	 * Cr�e un plateau avec un nombre de joueur
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
		setCase(1, new CaseSalarie("Responsable pilotage", 60, new ArrayList<Integer>(Arrays.asList(2, 10, 30, 90, 160, 250)), 50, 0, "brun","Rattach� au responsable du service pilotage et performance op�rationnelle, \nvous assurez le suivi des indicateurs de pilotage des activit�s internes \net externalis�es."));
		setCase(3, new CaseSalarie("Technicien r�seau", 60, new ArrayList<Integer>(Arrays.asList(4, 20, 60, 180, 320, 450)), 50, 0, "brun", "Vous veillerez au bon fonctionnement du r�seau ou des r�seaux de l�entreprise.\n Vous serez responsable de la qualit� de l�acheminement de toutes les \ninformations qui circulent sur le r�seau."));
		setCase(6, new CaseSalarie("Admin", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise", "Vous occuperez un r�le hautement strat�gique � la t�te d'une entreprise.\nNomm� par les actionnaires, vous ferez partie du conseil d'administration \net voterez les d�cisions les plus importantes pour la vie de sa soci�t�."));
		setCase(8, new CaseSalarie("Responsable de la conduite du changement", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise", "Votre mission sera d�accompagner les entreprises dans la restructuration de\nleurs syst�mes d�information au niveau des technologies, des ressources\nhumaines et des finances."));
		setCase(9, new CaseSalarie("Chef de projet MOE", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise", "Vous concevrez des applications informatiques r�pondant � des besoins \nsp�cifiques."));
		setCase(11, new CaseSalarie("Chef de projet MOA", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve", "Vous serez chef de projet fonctionnel web. \nVous aiderez le client � d�finir ses besoins et les traduirez � fonctionnellement �\ndans un cahier des charges.\nCe cahier des charges sera ensuite traduit � techniquement �\npar la ma�trise d'�uvre (MOE)."));
		setCase(13, new CaseSalarie("Sponsor", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve", "Vous serez en charge de trouver des accords strat�giques avec des entreprises et\nautres personnes morales, afin qu'elles financent des clubs sportifs, associations ou\nautres organisations."));
		setCase(14, new CaseSalarie("Acqu�reur", 160, new ArrayList<Integer>(Arrays.asList(12, 60, 180, 500, 700, 900)), 100, 0, "mauve", "Vous serez charg� de l'achat des produits n�cessaires � l'activit� de votre entreprise.\nVous rechercherez les fournisseurs qui correspondent aux exigences et n�gocierez\navec eux les prix et les d�lais de livraison."));
		setCase(16, new CaseSalarie("Gestionnaire d'application", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange", "Vous repr�senterez les ma�tres d'ouvrage lors de la vie courante des syst�mes. \nVous participerez � l'�laboration des r�gles de fonctionnement \net d'utilisation du syst�me d'information."));
		setCase(18, new CaseSalarie("Architecte fonctionnel", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange", "Vous traduirez les besoins fonctionnels d'un syst�me d'information en \nfonction des contraintes logistiques, �conomiques et des objectifs \ndu domaine m�tier."));
		setCase(19, new CaseSalarie("Expert m�tier", 200, new ArrayList<Integer>(Arrays.asList(16, 80, 220, 600, 800, 1000)), 100, 0, "orange", "Vous assurerez principalement le r�le de conseil, d'assistance et de formation.\n� ce titre, vous interviendrez directement sur la totalit� ou sur une partie d'un\nprojet."));
		setCase(21, new CaseSalarie("D�veloppeur", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge", "Vous endosserez plusieurs casquettes en fonction des demandes diverses.\nVotre quotidien consistera � concevoir, programmer, d�velopper et am�liorer\ndes logiciels, ou encore assurer un r�le de maintenance et de suivi. "));
		setCase(23, new CaseSalarie("Expert technique", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge", "Vous assurerez un r�le de conseil, d'assistance, d'information \net de formation."));
		setCase(24, new CaseSalarie("Analyste concepteur", 240, new ArrayList<Integer>(Arrays.asList(20, 100, 300, 750, 925, 1100)), 150, 0, "rouge", "Vous mettrez au point ou am�liore les syst�mes et les applications informatiques\nutilis�es dans l'entreprise, sous la direction d'un chef de projet."));
		setCase(26, new CaseSalarie("Architecte SI", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune", "Vous serez garant de la coh�rence du SI, avec un focus fort sur la partie infrastructure.\n Vous pr�coniserez les solutions techniques en cas d'�volution de ce dernier.\n Votre connaissance technique sera un atout majeur dans cet objectif."));
		setCase(27, new CaseSalarie("Expert securite", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune", "Vous r�aliserez un diagnostic du syst�me d'information d'une entreprise \ndans le but de d�celer les �ventuels points faibles "));
		setCase(29, new CaseSalarie("Architecte technique", 280, new ArrayList<Integer>(Arrays.asList(24, 120, 360, 850, 1025, 1200)), 150, 0, "jaune", "Vous con�evrez l'architecture technique du syst�me d'information.\nC'est-�-dire que vous contr�lerez tout l'aspect technique des diff�rents \nprojets informatiques qui sont sous sa gestion."));
		setCase(31, new CaseSalarie("Responsable portefeuille", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert", "Vous choisirez objectivement les produits de placement, ach�terez\net vendrez des actions et obligations en bourse puis assurerez le \nsuivi en informant le client."));
		setCase(32, new CaseSalarie("Auditeur", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert","Expert en gestion, management et comptabilit� votre mission premi�re est de\nv�rifier la r�gularit� des comptes d'une entreprise.\nDans la majorit� des cas vous ne faites pas partie des salari�s de l'entreprise \nque vous passerez au crible."));
		setCase(34, new CaseSalarie("Formateur", 320, new ArrayList<Integer>(Arrays.asList(28, 150, 450, 1000, 1200, 1400)), 200, 0, "vert", "Vous transmetrez des connaissances th�oriques, pratiques et professionnelles \ntr�s sp�cialis�es."));
		setCase(37, new CaseSalarie("Auditeur projet", 350, new ArrayList<Integer>(Arrays.asList(35, 175, 500, 1100, 1300, 1500)), 200, 0, "bleu","Expert en gestion, management et comptabilit� votre mission premi�re \nest de v�rifier la r�gularit� des comptes d'une entreprise.\nDans la majorit� des cas vous ne faites pas partie des salari�s de l'entreprise  \nque vous passerez au crible."));
		setCase(39, new CaseSalarie("Expert m�thodologique", 400, new ArrayList<Integer>(Arrays.asList(50, 200, 600, 1400, 1700, 2000)), 200, 0, "bleu", "Vous mettrez en place une d�marche qualit� pour �tablir des r�f�rentiels de bonnes\npratique et implanter des d�marches fond�es sur l�am�lioration continue.\nEn tant que contributeur aux audits qualit�, vous utiliserez et mettrez en �uvre les\ntechniques et les proc�dures d�audit. "));


		/* INITIALISATION DES CARTES CHANCES */
		
		chance.add(new CarteDeplacement("Expert m�thodologique", "Vous avez rendez-vous avec un expert\nm�thodologique.\nSi vous passez par la case d�part, recevez 200�.", 39, false));
		chance.add(new CarteDeplacement("Formateur", "Vous avez une formation. Allez voir formateur. \nSi vous passez par la case d�part, recevez 200�.", 34, false));
		chance.add(new CarteDeplacement("D�veloppeur", "Vous avez rendez-vous avec un d�veloppeur\npour votre site. \nSi vous passez par la case d�part, recevez 200�.", 21, false));
		chance.add(new CarteDeplacement("Chef de projet MOA", "Le chef de projet n'aime pas attendre. D�pechez vous. \nSi vous passez par la case d�part, recevez 200�.", 11, false));
		chance.add(new CarteDeplacement("Reculez", "Reculez de 4 cases.", -4, true));
		chance.add(new CarteDeplacement("Mutation", "Le joueur est mut�. Il prend un nouveau d�part.", 0, false));
		chance.add(new CartePayer("Amende", "Amende pour exc�s de vitesse : 55�.", 55));
		
		Collections.shuffle(chance); //M�lange des cartes


		/* INITIALISATION DES CARTES COMMUNAUT�S */
		communaut�.add(new CartePayer("Frais de mutuelle", "Payez 10� de frais de mutuelle.", 10));
		communaut�.add(new CartePayer("Frais d'hospitalisation", "Payez 100� de frais d'hospitalisation.", 100));
		communaut�.add(new CartePayer("M�decin", "Visite chez le m�decin : payez 25�.", 25));
		communaut�.add(new CartePayer("Campagne de publicit�", "Pour lancer votre nouveau produit vous\ndevez payez 30�.", 30));
		communaut�.add(new CartePayer("Erreur de la Banque", "Erreur de la Banque en votre d�faveur. \n Remboursez 150�.", 150));
		communaut�.add(new CarteRecevoirArgent("Remboursement", "Les imp�ts vous remboursent 20�.", 20));
		communaut�.add(new CarteRecevoirArgent("Assurance vie", "Votre assurance vie vous rapporte 100�.", 100));
		communaut�.add(new CarteRecevoirArgent("Promotion", "Vous venez d'�tre promu, f�licitations ! \n La banque vous donne 50", 50));
		communaut�.add(new CartePayer("Audit", "Vous avez fait une erreur, les auditeurs vous p�nalise. \n Donnez leur 50�.", 50));
		communaut�.add(new CarteRecevoirArgent("Nuit de l'info", "Bravo: Vous avez gagn� le premier prix de la Nuit de l'Info. \nRecevez 100�.", 100));
		communaut�.add(new CarteRecevoirArgent("Nouveau produit", "La vente de votre nouveau produit\nvous rapporte 150�.", 150));
		communaut�.add(new CarteRecevoirArgent("Hackathon", "Bravo: Vous avez gagn� votre premier hackaton. \nRecevez 200�.", 200));
		communaut�.add(new CarteRecevoirArgent("H�ritage", "Vous h�ritez de 100�.", 100));
		communaut�.add(new CarteRecevoirArgent("Placement", "Votre placement vous rapporte. \nRecevez 100�.", 100));
		communaut�.add(new CarteRecevoirArgent("Erreur de la Banque", "Erreur de la Banque en votre faveur. \nRecevez 200�.", 200));
		communaut�.add(new CarteDeplacement("Arret Maladie", "Allez en arret maladie. \n Allez sur la case arret maladie sans passez\npar la case d�part. \nVous ne recevez pas 200�.", 10, false));
		communaut�.add(new CarteSortirArretMaladie("Gu�rison anticip�e", "Vous �tes gu�ri. \n Vous conservez cette carte."));

		//utilis� pour m�langer les cartes
		Collections.shuffle(communaut�); 
	}

	/* PARTIE JOUEUR */

	/**
	 * Permet de d�placer un joueur d'un certain nombre de cases
	 * @param joueur JoueurMonopoly
	 * @param nombreDeCases int
	 */
	public void deplacerJoueur(JoueurMonopoly joueur, int nombreDeCases) {
		int pos;

		if((joueur.getPosition() + nombreDeCases) >= getNbCases()) {
			pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
			System.out.println("-> " + joueur.getNom() + " passe par la case d�part et gagne 200� !");
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
	 */
	public Carte tirerCarteCommunaut�() {
		Carte c = communaut�.remove(0);
		if(!c.getNom().equals("Sortie"))
			communaut�.add(c);
		return c;
	}

	/**
	 * Remet dans le paquet la carte "Sortie d'arret maladie" lorsqu'un joueur l'a utilis�
	 */
	public void remettreCarteSortieArretMaladie() {

		boolean carteDansPaquetChance = false;
		for(Carte c:chance) {
			if(c.getNom().equals("Sortie"))
				carteDansPaquetChance = true;  
		}

		if(carteDansPaquetChance)
			chance.add(new CarteSortirArretMaladie("Gu�rison anticip�e", "Vous �tes gu�ri. \n Vous conservez cette carte."));
		else
			communaut�.add(new CarteSortirArretMaladie("Gu�rison anticip�e", "Vous �tes gu�ri. \n Vous conservez cette carte."));

	}

	@Override
	/**
	 * redefinition de la methode toString
	 */
	public String toString() {
		return "PlateauMonopoly [toString()=" + super.getCase(1) + "]";
	}

}
