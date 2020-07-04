package controller;

import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.MonopolyView;

public interface DefaultControllerInterface {
	void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView);

}
