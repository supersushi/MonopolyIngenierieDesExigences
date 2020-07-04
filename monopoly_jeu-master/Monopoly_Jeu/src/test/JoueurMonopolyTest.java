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
	 * Permet de vérifier que les paramètres sont bien initialisés lors de la création d'un joueur
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
	 * Permet de vérifier la méthode d'ajout de salarié chez un joueur
	 */
	void ajouterSalarieTest() {
		caseSalarie = new CaseSalarieController("salarié 1", 500, null, 50, 1, "mauve", "description salarié", new ArrayList<String>(Arrays.asList("compétence 1", "compétence 2")));
		joueur.ajouterSalarie(caseSalarie);
		for (Case salarie : joueur.getListeSalaries()) {
			assertEquals("mauve", salarie.getCouleur());
			assertEquals(500, salarie.getPrix());
			assertEquals("salarié 1", salarie.getNom());
		}
	}
	
	@Test
	/**
	 * Permet de tester l'ajout et le retrait d'argent sur le compte du joueur
	 * Teste également la situation où le joueur est fauché
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
