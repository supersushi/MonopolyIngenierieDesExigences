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
			new ArrayList<String>(Arrays.asList("compétence 1", "compétence 2")));
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseSalarieController caseSal1 = new CaseSalarieController("Salarié 1", 100,
			new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise",
			"description salarié 1", new ArrayList<String>(Arrays.asList("compétence 1", "compétence 2")));
	CaseSalarieController caseSal2 = new CaseSalarieController("Salarié 2", 100,
			new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise",
			"description salarié 2", new ArrayList<String>(Arrays.asList("compétence 1", "compétence 2")));
	CaseSalarieController caseSal3 = new CaseSalarieController("Salarié 3", 120,
			new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise",
			"description salarié 3", new ArrayList<String>(Arrays.asList("compétence 1", "compétence 2")));

	@Test
	/**
	 * Permet de tester la méthode actionCase dans le cas où le joueur1 embauche le
	 * salarie donc le joueur1 devient le patron de la case, son argent baisse et le
	 * salarié est ajouté dans sa liste de salariés
	 */
	void actionCaseEmbaucheTest() {
		caseSal.setRep(true);
		caseSal.setId(25);
		caseSal.action(joueur1, null, null);
		assertEquals(joueur1, caseSal.getPatron());
		assertEquals(25, joueur1.getListeSalaries().get(0).getId());
		assertEquals(900, joueur1.getArgent());

	}

	@Test
	/**
	 * Permet de tester la méthode actionCase dans le cas où ce n'est pas le patron
	 * de la case qui tombe dessus donc l'argent du joueur2 baisse et celui du
	 * joueur1 augmente
	 */
	void actionCasePayerTest() {
		caseSal.setPatron(joueur1);
		caseSal.action(joueur2, null, null);
		System.out.println(caseSal.getSalaire());
		assertEquals(85, joueur2.getArgent());
		assertEquals(1015, joueur1.getArgent());
	}

	@Test
	/**
	 * Permet de tester la méthode embaucherSalarie dans le cas où le joueur2 n'a
	 * pas assez d'argent donc la fonction renvoie false
	 */
	void embaucherSalarieImpossibleTest() {
		caseSal.setPrix(200);
		assertFalse(caseSal.embaucherSalarie(joueur2, null));
	}

	@Test
	/**
	 * Permet de tester la méthode payerSalaire dans le cas où le patron (joueur1)
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
	 * Permet de tester la méthode monterEnCompetence donc le nbCompetence augmente
	 * de 1
	 */
	void monterEnCompetenceTest() {
		caseSal.setPatron(joueur1);
		assertEquals(0, caseSal.getNbCompetence());
		caseSal.monterEnCompetence(null, null);
		assertEquals(1, caseSal.getNbCompetence());
	}

	@Test
	/**
	 * Permet de tester la méthode getPeutMonterEnCompetence dans les différents cas
	 * de la méthode (si le joueur1 possède toutes les cases de la même couleur, si
	 * le nombre de compétence a atteint son maximum)
	 */
	void getPeutMonterEnCompetenceTest() {
		caseSal.setPatron(joueur1);
		assertFalse(caseSal.getPeutMonterEnCompetence());
		caseSal1.embaucherSalarie(joueur1, null);
		caseSal2.embaucherSalarie(joueur1, null);
		caseSal3.embaucherSalarie(joueur1, null);
		caseSal.setPatron(joueur1);
		assertTrue(caseSal.getPeutMonterEnCompetence());
		caseSal1.setNbCompetence(2);
		assertFalse(caseSal.getPeutMonterEnCompetence());
		caseSal.setNbCompetence(5);
		assertFalse(caseSal.getPeutMonterEnCompetence());
	}

}
