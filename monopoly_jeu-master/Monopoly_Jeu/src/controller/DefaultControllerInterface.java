package controller;

import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.FenetreDeJeu;

public interface DefaultControllerInterface {
	void action(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fenetreDeJeu);

}
