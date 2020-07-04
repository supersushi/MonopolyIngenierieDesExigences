package settings;

import java.util.HashMap;
import java.util.Map;

import controller.ActionSurSalarieAcquerirController;
import controller.ActionSurSalarieQuitterController;
import controller.ActionSurSalarieRevendreController;
import controller.ChanceQuitterController;
import controller.ChanceValiderController;
import controller.CommunauteQuitterController;
import controller.CommunauteValiderController;
import controller.DemarrageQuitterController;
import controller.DemarrageValiderController;
import controller.EmbaucheQuitterController;
import controller.EmbaucheSalarieNonController;
import controller.EmbaucheSalarieOuiController;
import controller.JeuClicRootController;
import controller.JeuNewPartieController;
import controller.JeuTourSuivantController;
import controller.SortirArretMaladieNonController;
import controller.SortirArretMaladieOuiController;
import controller.SortirArretMaladieQuitterController;
import model.Fenetre;
import view.FenetreActionSurSalarie;
import view.FenetreCarteChance;
import view.FenetreCarteCommunaute;
import view.FenetreDeJeu;
import view.FenetreDemarrage;
import view.FenetreEmbaucheSalarie;
import view.FenetreSortirArretMaladie;

public class FenetresControllerSettings {
	public static Map<String, Object> controllerByRequest = new HashMap<String, Object>();

	public static void configuration(Fenetre fenetre) {
		controllerByRequest.put("ActionSurSalarieAcquerirController",
				new ActionSurSalarieAcquerirController((FenetreActionSurSalarie) fenetre));
		controllerByRequest.put("ActionSurSalarieQuitterController",
				new ActionSurSalarieQuitterController((FenetreActionSurSalarie) fenetre));
		controllerByRequest.put("ActionSurSalarieRevendreController",
				new ActionSurSalarieRevendreController((FenetreActionSurSalarie) fenetre));
		controllerByRequest.put("ChanceQuitterController", new ChanceQuitterController((FenetreCarteChance) fenetre));
		controllerByRequest.put("ChanceValiderController", new ChanceValiderController((FenetreCarteChance) fenetre));
		controllerByRequest.put("CommunauteQuitterController",
				new CommunauteQuitterController((FenetreCarteCommunaute) fenetre));
		controllerByRequest.put("CommunauteValiderController",
				new CommunauteValiderController((FenetreCarteCommunaute) fenetre));
		controllerByRequest.put("DemarrageQuitterController",
				new DemarrageQuitterController((FenetreDemarrage) fenetre));
		controllerByRequest.put("DemarrageValiderController",
				new DemarrageValiderController((FenetreDemarrage) fenetre));
		controllerByRequest.put("EmbaucheSalarieOuiController",
				new EmbaucheSalarieOuiController((FenetreEmbaucheSalarie) fenetre));
		controllerByRequest.put("EmbaucheSalarieNonController",
				new EmbaucheSalarieNonController((FenetreEmbaucheSalarie) fenetre));
		controllerByRequest.put("EmbaucheQuitterController",
				new EmbaucheQuitterController((FenetreEmbaucheSalarie) fenetre));
		controllerByRequest.put("JeuClicRootController", new JeuClicRootController((FenetreDeJeu) fenetre));
		controllerByRequest.put("JeuNewPartieController", new JeuNewPartieController((FenetreDeJeu) fenetre));
		controllerByRequest.put("JeuTourSuivantController", new JeuTourSuivantController((FenetreDeJeu) fenetre));
		controllerByRequest.put("SortirArretMaladieOuiController",
				new SortirArretMaladieOuiController((FenetreSortirArretMaladie) fenetre));
		controllerByRequest.put("SortirArretMaladieNonController",
				new SortirArretMaladieNonController((FenetreSortirArretMaladie) fenetre));
		controllerByRequest.put("SortirArretMaladieQuitterController",
				new SortirArretMaladieQuitterController((FenetreSortirArretMaladie) fenetre));
	}

	public FenetresControllerSettings(Fenetre fenetre) {
		configuration(fenetre);
	}
}
