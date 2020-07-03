package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cases.CaseAllerArretMaladie;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseAllerArretMaladie
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class CaseAllerArretMaladieTest {
	
	
	JoueurMonopoly joueur1 =  new JoueurMonopoly("Alice", 1, 1000);
	JoueurMonopoly joueur2 = new JoueurMonopoly("Marc", 2, 100);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseAllerArretMaladie caseArretMal = new CaseAllerArretMaladie();

	@Test
	/**
	 * Test permettant de tester la m�thode actionCase de la classe 
	 * dans le cas o� le joueur1 poss�de une carte sortie arret maladie donc n'est pas malade perd sa carte et reste sur la case
	 */
	void actionCaseCarteTest() {
		joueur1.setPosition(30);
		joueur1.setCarteSortieArretMaladie(true);
		caseArretMal.actionCase(joueur1, plateau, null);
		assertFalse(joueur1.getCarteSortieArretMaladie());
		assertFalse(joueur1.getEstMalade());
		assertEquals(30, joueur1.getPosition());
		
	}
	
	@Test
	/**
	 * Test permettant de tester la m�thode actionCase de la classe
	 * dans le cas o� le joueur2 ne poss�de pas de carte donc est malade et est transport� en case 10
	 */
	void actionCaseMaladeTest() {
		joueur2.setPosition(30);
		assertFalse(joueur2.getEstMalade());
		caseArretMal.actionCase(joueur2, plateau, null);
		assertTrue(joueur2.getEstMalade());
		assertEquals(10, joueur2.getPosition());
	}

}
