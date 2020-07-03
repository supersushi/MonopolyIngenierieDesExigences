package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.CaseImpots;
import controller.CaseOpenSpace;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseImpots
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class CaseImpotsTest {
	
	JoueurMonopoly joueur1 =  new JoueurMonopoly("Alice", 1, 1000);
	JoueurMonopoly joueur2 = new JoueurMonopoly("Marc", 2, 100);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseImpots caseImp = new CaseImpots("Frais de mutuelle", 100);
	CaseOpenSpace caseOpenSp = (CaseOpenSpace) plateau.getCase(20);

	@Test
	/**
	 * Permet de tester la méthode actionCase de la classe 
	 * donc le joueur perd le prix de la case impôt et le prix de la case open space augmente du prix de la case impôt
	 */
	void actionCaseTest() {
		assertEquals(0, caseOpenSp.getPrix());
		caseImp.actionCase(joueur1, plateau, null);
		assertEquals(900, joueur1.getArgent());
		assertEquals(100, caseOpenSp.getPrix());
	}

}
