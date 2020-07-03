package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.CaseEntreprise;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseEntreprise
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class CaseEntrepriseTest {
	
	JoueurMonopoly joueur1 =  new JoueurMonopoly("Alice", 1, 1500);
	JoueurMonopoly joueur2 = new JoueurMonopoly("Marc", 2, 1000);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseEntreprise caseEnt = new CaseEntreprise("Or");

	@Test
	/**
	 * Permet de tester la méthode actionCase de la classe
	 * dans le cas où le joueur1 le joueur achète la case qui n'avait pas de patron 
	 * donc perd le prix de la case et augmente son nombre d'entreprise
	 */
	void actionCaseAchatTest() {
		caseEnt.setId(12);
		caseEnt.setRep(true);
		caseEnt.actionCase(joueur1, plateau, null);
		assertEquals(joueur1, caseEnt.getPatron());
		assertEquals(12, joueur1.getListeSalaries().get(0).getId());
		assertEquals(1350, joueur1.getArgent());
		assertEquals(1, joueur1.getNbEntreprises());
		
	}
	
	@Test
	/**
	 * Permet de tester la méthode actionCase de la classe
	 * dans le cas où le joueur2 paye un salaire au joueur1 donc le joueur1 gagne de l'argent et le joueur2 en perd
	 */
	void actionCaseSalaire() {
		caseEnt.setPatron(joueur1);
		caseEnt.actionCase(joueur2, plateau, null);
		assertTrue(1500 < joueur1.getArgent());
		assertTrue(joueur2.getArgent() < 1000);
	}
	
	@Test
	/**
	 * Permet de tester la méthode actionCase de la classe
	 * dans le cas où le joueur1 est malade donc le joueur2 ne lui paie pas de salaire
	 */
	void actionCaseSalaireMaladeTest() {
		joueur1.setEstMalade(true);
		caseEnt.setPatron(joueur1);
		caseEnt.actionCase(joueur2, plateau, null);
		assertEquals(1500, joueur1.getArgent());
		assertEquals(1000, joueur2.getArgent());
	}

}
