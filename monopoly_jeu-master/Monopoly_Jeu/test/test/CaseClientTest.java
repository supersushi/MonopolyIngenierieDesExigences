package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import controller.CaseClientController;
import jeu.JoueurMonopoly;

/**
 * Teste la classe CaseClient
 * 
 * @author Massourang Jugurtha Lina Emma
 */

class CaseClientTest {

	JoueurMonopoly joueur1 = new JoueurMonopoly("Alice", 1, 1000);
	JoueurMonopoly joueur2 = new JoueurMonopoly("Marc", 2, 100);
	CaseClientController caseCli = new CaseClientController("client");

	
	@Test
	/**
	 * Permet de tester la méthode action de la classe
	 * dans le cas où il n'y a pas de patron donc le joueur1 achète la case
	 * puis dans le cas où le joueur2 tombe sur la case et doit donc payer un salaire au joueur1
	 */
	void actionSansPatronTest() {
		caseCli.setPrix(50);
		caseCli.setRep(true);
		caseCli.action(joueur1, null, null);
		assertEquals("client", joueur1.getListeSalaries().get(0).getNom());
		assertEquals(950, joueur1.getArgent());
		caseCli.action(joueur2, null, null);
		assertEquals(50, joueur2.getArgent());
		assertEquals(1000, joueur1.getArgent());
	}
	
	@Test
	/**
	 * Permet de tester la méthode embaucheSalarie quand un joueur a assez d'argent
	 * ainsi que dans le cas contraire. Le salarié coûte 200€ donc le joueur1 peut
	 * l'embaucher mais pas le joueur2
	 */
	void embaucheSalarieTest() {
		caseCli.setPrix(100);
		assertTrue(caseCli.EmbaucheSalarie(joueur1, null));
		assertEquals("client", joueur1.getListeSalaries().get(0).getNom());
		assertEquals(900, joueur1.getArgent());
		assertEquals(1, joueur1.getNbEntreprises());
		assertFalse(caseCli.EmbaucheSalarie(joueur2, null));
	}

	@Test
	/**
	 * Permet de tester la méthode payerSalaire afin de vérifier que le montant du salaire est retiré de l'argent du joueur
	 */
	void payerSalaireTest() {
		joueur2.setNbEntreprises(1);
		caseCli.setPatron(joueur2);
		caseCli.payerSalaire(joueur1, null);
		assertEquals(950, joueur1.getArgent());
	}

}
