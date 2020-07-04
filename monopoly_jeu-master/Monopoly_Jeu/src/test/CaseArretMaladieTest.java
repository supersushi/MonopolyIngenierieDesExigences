package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import controller.CaseArretMaladieController;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseArretMaladie
 * 
 * @author Massourang Jugurtha Lina Emma
 */

class CaseArretMaladieTest {

	JoueurMonopoly joueur1 = new JoueurMonopoly("Alice", 1, 1000);
	JoueurMonopoly joueur2 = new JoueurMonopoly("Marc", 2, 100);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseArretMaladieController caseArret = new CaseArretMaladieController();

	@Test
	/**
	 * Permet de tester la méthode actionCase de la classe dans le cas où le joueur1
	 * est malade et souhaite payer pour se soigner donc perd 25€ et son nombre de
	 * tours en arrêt repasse à 1
	 */
	void actionCasePayerTest() {
		joueur1.setEstMalade(true);
		caseArret.setRep(true);
		joueur1.setToursEnArretMaladie(2);
		caseArret.action(joueur1, plateau, null);
		assertEquals(975, joueur1.getArgent());
		assertEquals(1, joueur1.getToursEnArretMaladie());
	}

	@Test
	/**
	 * Permet de tester la méthode actionCase de la classe dans le cas où le joueur2
	 * est malade et est forcé de payer puisque son nombre de tours en arrêt est > 2
	 * donc perd 25€, n'est plus malade et son nombre de tours en arrêt repasse à 1
	 */
	void actionCaseImpayeTest() {
		joueur2.setEstMalade(true);
		caseArret.setRep(false);
		joueur2.setToursEnArretMaladie(3);
		caseArret.action(joueur2, plateau, null);
		assertEquals(75, joueur2.getArgent());
		assertFalse(joueur2.getEstMalade());
		assertEquals(1, joueur2.getToursEnArretMaladie());
	}

}
