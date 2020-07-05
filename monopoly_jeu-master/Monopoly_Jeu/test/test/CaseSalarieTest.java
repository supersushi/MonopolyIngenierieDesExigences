package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import controller.CaseSalarieController;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseSalarie
 * 
 * @author Massourang Jugurtha Lina Emma
 */

class CaseSalarieTest {

	JoueurMonopoly joueur1 = new JoueurMonopoly("Alice", 1, 1000);
	JoueurMonopoly joueur2 = new JoueurMonopoly("Marc", 2, 100);
	CaseSalarieController caseSal = new CaseSalarieController("Chef de projet", 100,
			new ArrayList<Integer>(Arrays.asList(15, 30, 60, 120, 240, 480)), 80, 0, "turquoise", "description poste", 
			new ArrayList<String>(Arrays.asList("comp�tence 1", "comp�tence 2")));
	PlateauMonopoly plateau = new PlateauMonopoly(2);

	@Test
	/**
	 * Permet de tester la m�thode action dans le cas o� le joueur1 embauche le
	 * salarie donc le joueur1 devient le patron de la case, son argent baisse et le
	 * salari� est ajout� dans sa liste de salari�s
	 */
	void actionEmbaucheTest() {
		caseSal.setRep(true);
		caseSal.setId(25);
		caseSal.action(joueur1, null, null);
		assertEquals(joueur1, caseSal.getPatron());
		assertEquals(25, joueur1.getListeSalaries().get(0).getId());
		assertEquals(900, joueur1.getArgent());

	}

	@Test
	/**
	 * Permet de tester la m�thode action dans le cas o� ce n'est pas le patron
	 * de la case qui tombe dessus donc l'argent du joueur2 baisse et celui du
	 * joueur1 augmente
	 */
	void actionPayerTest() {
		caseSal.setPatron(joueur1);
		caseSal.action(joueur2, null, null);
		System.out.println(caseSal.getSalaire());
		assertEquals(85, joueur2.getArgent());
		assertEquals(1015, joueur1.getArgent());
	}

	@Test
	/**
	 * Permet de tester la m�thode embaucherSalarie dans le cas o� le joueur2 n'a
	 * pas assez d'argent donc la fonction renvoie false
	 */
	void embaucherSalarieImpossibleTest() {
		caseSal.setPrix(200);
		assertFalse(caseSal.embaucherSalarie(joueur2, null));
	}

	@Test
	/**
	 * Permet de tester la m�thode payerSalaire dans le cas o� le patron (joueur1)
	 * est malade donc joueur2 ne verse pas de salaire
	 */
	void payerSalaireTest() {
		joueur1.setEstMalade(true);
		caseSal.setPatron(joueur1);
		caseSal.payerSalaire(joueur2, null);
		assertEquals(100, joueur2.getArgent());
	}

	@Test
	/**
	 * Permet de tester la m�thode monterEnCompetence de la classe
	 * donc le nbCompetence augmente de 1, l'argent du patron baisse du montant de la comp�tence, 
	 * le montant de la case augmente du prix de la coomp�tence et le prix de la comp�tence augment de 50�
	 */
	void monterEnCompetenceTest() {
		caseSal.setPatron(joueur1);
		caseSal.setPrixCompetence(50);
		caseSal.setPrix(150);
		assertEquals(0, caseSal.getNbCompetence());
		caseSal.monterEnCompetence(null, null);
		assertEquals(1, caseSal.getNbCompetence());
		assertEquals(950, caseSal.getPatron().getArgent());
		assertEquals(200, caseSal.getPrix());
		assertEquals(100, caseSal.getPrixCompetence());
	}

	@Test
	/**
	 * Permet de tester la m�thode getPeutMonterEnCompetence dans les diff�rents cas
	 * de la m�thode (s'il peut monter en comp�tenece, si le patron n'a plus d'argent et et si le nombre de comp�tence a atteint son maximum)
	 */
	void getPeutMonterEnCompetenceTest() {
		caseSal.setPatron(joueur1);
		caseSal.setPrixCompetence(100);
		assertTrue(caseSal.getPeutMonterEnCompetence());
		caseSal.setPatron(joueur1);
		assertTrue(caseSal.getPeutMonterEnCompetence());
		joueur1.retirerArgent(950);
		assertFalse(caseSal.getPeutMonterEnCompetence());
		joueur1.ajouterArgent(950);
		caseSal.setNbCompetence(5);
		assertFalse(caseSal.getPeutMonterEnCompetence());
	}

}
