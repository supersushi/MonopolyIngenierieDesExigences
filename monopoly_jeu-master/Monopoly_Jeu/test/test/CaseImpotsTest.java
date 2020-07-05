package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controller.CaseImpotsController;
import controller.CaseOpenSpaceController;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseImpots
 * 
 * @author Massourang Jugurtha Lina Emma
 */

class CaseImpotsTest {

	JoueurMonopoly joueur1 = new JoueurMonopoly("Alice", 1, 1000);
	JoueurMonopoly joueur2 = new JoueurMonopoly("Marc", 2, 100);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseImpotsController caseImp = new CaseImpotsController("Frais de mutuelle", 100);
	CaseOpenSpaceController caseOpenSp = (CaseOpenSpaceController) plateau.getCase(20);

	@Test
	/**
	 * Permet de tester la méthode action de la classe donc le joueur perd le
	 * prix de la case impôt et le prix de la case open space augmente du prix de la
	 * case impôt
	 */
	void actionTest() {
		assertEquals(0, caseOpenSp.getPrix());
		caseImp.action(joueur1, plateau, null);
		assertEquals(900, joueur1.getArgent());
		assertEquals(100, caseOpenSp.getPrix());
	}

}
