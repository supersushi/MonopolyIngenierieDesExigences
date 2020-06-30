package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import application.Clavier;
import cartes.CartePayer;
import cartes.CarteSortirArretMaladie;
import cases.CaseClient;
import cases.CaseDepart;
import cases.CaseEntreprise;
import cases.CaseImpots;
import cases.CaseOpenSpace;
import cases.CaseSalarie;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

class Test {
	public static void main(String[] args) {

		Clavier es = new Clavier();
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
	

		System.out.println("TEST DE LA CLASSE : CaseChance");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(4);
		CartePayer payer = new CartePayer("Amende", "Amende pour excès de vitesse : 15€.", 15);
		System.out.println(payer.toString());
		payer.actionCarte(j, p, null);
		System.out.println(j.toString()); //Le joueur Yann perd 15€
		System.out.println(p.getCase(20).toString());

		CarteSortirArretMaladie arretMal = new CarteSortirArretMaladie("Guerir", "Vous êtes gueri. \n(Cette carte doit être conservée)");
		System.out.println(arretMal.toString());
		arretMal.actionCarte(j, p, null);
		System.out.println(j.toString()); //Le joueur Yann possède la carte de sortie d arret maladie
	

		Clavier es1 = new Clavier();
		es1.println("TEST DE LA CLASSE : CaseServicePublic");

		JoueurMonopoly j11 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j21 = new JoueurMonopoly("Benoit", 1, 1000);
		PlateauMonopoly pm1 = new PlateauMonopoly(2);
		es1.println(j11.toString()+"\n");

		CaseEntreprise c1 = (CaseEntreprise) pm1.getCase(12);
		c1.EmbaucheSalarie(j11, null);

		es1.println("== Nombres d'entreprise de " + j11.getNom() + " : " + j11.getNbEntreprises());

		c1.payerSalaire(j21, pm1, null);
		es1.println("");

		c1 = (CaseEntreprise) pm1.getCase(28);
		c1.EmbaucheSalarie(j11, null);
		es1.println("== Nombres d'entreprise de " + j11.getNom() + " : " + j11.getNbEntreprises());

		c1.payerSalaire(j21, pm1, null);

		es1.println("\n" + j11.toString());
	

		System.out.println("TEST DE LA CLASSE : CaseImpots");
		JoueurMonopoly j111 = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p1 = new PlateauMonopoly(4);

		CaseImpots c11 = (CaseImpots) p1.getCase(4);
		j111.setPosition(4);
		c11.actionCase(j111, p1, null);
		System.out.println(c11.toString());
		System.out.println(p1.getCase(20).toString());
		System.out.println(j111.toString());
	

		System.out.println("TEST DE LA CLASSE : CaseOpenSpace");
		JoueurMonopoly j1111 = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p11 = new PlateauMonopoly(4);

		CaseOpenSpace c111 = (CaseOpenSpace) p11.getCase(20);

		c111.setPrix(100);
		System.out.println("Initialisation de la case Open Space à 100€ : "+ c111.toString());
		System.out.println("Joueur avant le Open Space : "+ j1111.toString());
		j1111.setPosition(20);
		c111.actionCase(j1111, p11, null);

		System.out.println("Case Open Space après le passage du joueur : " + c111.toString());
		System.out.println("Joueur après le Open Space : " + j1111.toString());
	

		Clavier es11 = new Clavier();

		es11.println("TEST DE LA CLASSE : CaseSalarie\n");

		CaseSalarie c1111 = new CaseSalarie("Avenue de la République", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise");
		JoueurMonopoly j11111 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j211 = new JoueurMonopoly("Benoit", 1, 1000);

		es11.println(c1111.toString() + "\n");
		es11.println(j11111.toString() + "\n");
		es11.println(j211.toString() + "\n");

		c1111.embaucherSalarie(j11111, null);

		es11.println("== Patron de " + c1111.getNom() + " : "+ c1111.getPatron().getNom());
		es11.println("== Nombre de compétences : "+ c1111.getNbCompetence() + "");

		c1111.payerSalaire(j211, null);

		es11.println("");
		c1111.monterEnCompetence(null);
		c1111.monterEnCompetence(null);
		c1111.monterEnCompetence(null);
		es11.println("== Nombre de compétences : "+ c1111.getNbCompetence() + "");

		c1111.payerSalaire(j211, null);

		es11.println("\n" + c1111.toString() + "\n");
		es11.println(j11111.toString() + "\n");
		es11.println(j211.toString());

			System.out.println("TEST DE LA CLASSE : CaseDepart \n");
			JoueurMonopoly j111111 = new JoueurMonopoly("Yann", 0, 1000);
			PlateauMonopoly p111 = new PlateauMonopoly(4);

			CaseDepart c11111 = (CaseDepart) p111.getCase(0);

			j111111.setPosition(38);
			System.out.println("\n Le joueur est sur la case " + p111.getCase(j111111.getPosition()).toString()+" \n");
			p111.deplacerJoueur(j111111, 2);
			c11111.actionCase(j111111, p111, null);
			System.out.println("Le joueur possède : " + j111111.getArgent()+"€ \n");

			j111111.setPosition(38);
			System.out.println("\n Le joueur est sur la case " + p111.getCase(j111111.getPosition()).toString() + " \n");
			p111.deplacerJoueur(j111111, 3);
			System.out.println("\n Le joueur est sur la case " + p111.getCase(j111111.getPosition()).getNom()+" \n");
			System.out.println("Le joueur possède : " + j111111.getArgent()+"€ \n");
		}
}
