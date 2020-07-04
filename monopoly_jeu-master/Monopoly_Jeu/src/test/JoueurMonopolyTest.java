package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import controller.CaseSalarieController;
import jeu.JoueurMonopoly;
import model.Case;

/**
 * Teste la classe JoueurMonopoly
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class JoueurMonopolyTest {
	
	JoueurMonopoly joueur = new JoueurMonopoly("Alice", 1, 1000);
	Case caseSalarie;

	@Test
	/**
	 * Permet de v�rifier que les param�tres sont bien initialis�s lors de la cr�ation d'un joueur
	 */
	void paramsCreationJoueurTest() {
		assertFalse(joueur.getEstFauche());
		assertFalse(joueur.getEstMalade());
		assertEquals(1, joueur.getToursEnArretMaladie());
		assertFalse(joueur.getCarteSortieArretMaladie());
		assertEquals(0, joueur.getNbEntreprises());
		assertEquals(1000, joueur.getArgent());
		assertTrue(joueur.getListeSalaries().isEmpty());
		assertTrue(joueur.getListeCouleur().isEmpty());
	}
	
	@Test
	/**
	 * Permet de v�rifier la m�thode d'ajout de salari� chez un joueur
	 */
	void ajouterSalarieTest() {
		caseSalarie = new CaseSalarieController("salari� 1", 500, null, 50, 1, "mauve", "description salari�", new ArrayList<String>(Arrays.asList("comp�tence 1", "comp�tence 2")));
		joueur.ajouterSalarie(caseSalarie);
		for (Case salarie : joueur.getListeSalaries()) {
			assertEquals("mauve", salarie.getCouleur());
			assertEquals(500, salarie.getPrix());
			assertEquals("salari� 1", salarie.getNom());
		}
	}
	
	@Test
	/**
	 * Permet de tester l'ajout et le retrait d'argent sur le compte du joueur
	 * Teste �galement la situation o� le joueur est fauch�
	 */
	void ajouterRetirerArgentTest() {
		joueur.ajouterArgent(200);
		assertEquals(1200, joueur.getArgent());
		joueur.retirerArgent(500);
		assertEquals(700, joueur.getArgent());
		joueur.retirerArgent(800);
		assertTrue(joueur.getEstFauche());
	}

}
