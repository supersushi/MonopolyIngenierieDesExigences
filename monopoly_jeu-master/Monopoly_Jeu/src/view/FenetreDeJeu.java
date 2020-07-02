package view;

import java.util.ArrayList;
import java.util.Random;

import cases.Case;
import cases.CaseSalarie;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import jeu.JoueurMonopoly;
import jeu.Partie;
import jeu.PlateauMonopoly;

/**
 * Fenêtre javafx principale pour l'affichage du jeu de Monopoly.
 */
public class FenetreDeJeu {

	private Stage stage;
	private StackPane root;
	private Label l_OpenSpace = new Label("0€");
	private Label l_Message = new Label("");
	private ArrayList <Label> l_Joueurs = new ArrayList <Label>();
	private ArrayList <Label> l_ListeSalaries = new ArrayList <Label>();
	private ArrayList <Circle> l_Pions = new ArrayList <Circle>();
	private ArrayList<Label> l_Logs = new ArrayList<Label>();
	private ArrayList<Image> imageDes = new ArrayList<Image>();
	private ArrayList<ImageView> Des = new ArrayList<ImageView>();
	private Button tourSuivant = new Button("  Tour \nsuivant");
	private Button newPartie = new Button("Nouvelle partie");
	public Random rand = new Random();
	public Color[] Couleurs = new Color[] {Color.MAGENTA, Color.GREY, Color.BLUE, Color.ORANGE};
	private FenetreDemarrage fd = new FenetreDemarrage(this);
	private FenetreCarteChance fch = new FenetreCarteChance(this);
	private FenetreCarteCommunaute fco = new FenetreCarteCommunaute(this);
	private FenetreEmbaucheSalarie fat = new FenetreEmbaucheSalarie(this);
	private FenetreSortirArretMaladie farretmaladie = new FenetreSortirArretMaladie(this);
	private FenetreActionSurSalarie fenSal = new FenetreActionSurSalarie(this);
	private Partie partie;

	/**
	 * Constructeur 
	 * Une instance de Stage est généré au démarrage. Ceci afin de choisir un nombre de joueur avant le début de la partie.
	 * @param primaryStage Stage
	 */

	public FenetreDeJeu(Stage primaryStage) {
		this.stage = primaryStage;
		root = new StackPane();
		root.setOnMouseClicked(new EvtClicRoot());
		
		initRoot();

		Scene scene = new Scene(root,1000,655);
		stage.setScene(scene);
		stage.setTitle("Monopoly - Ingenierie des exigences");

		fd.getStage().show(); 
	}

	/**
	 * Initialise la StackPane root de la FenetreDeJeu (images, labels et boutons).
	 */
	@SuppressWarnings("static-access")
	private void initRoot() {
		root.setStyle("-fx-background-image: url('images/plateau.png'); -fx-background-repeat: no-repeat;");
		
		root.setAlignment(Pos.TOP_LEFT);

		for(int i=1; i<7; i++)
			imageDes.add(new Image("images/de"+i+".jpg"));

		Des.add(new ImageView());
		Des.add(new ImageView());
		Des.get(0).setTranslateX(315);
		Des.get(0).setTranslateY(420);
		Des.get(1).setTranslateX(315);
		Des.get(1).setTranslateY(470);
		root.getChildren().add(Des.get(0));
		root.getChildren().add(Des.get(1));

		l_OpenSpace.setTranslateX(3);
		l_OpenSpace.setTranslateY(68);
		root.getChildren().add(l_OpenSpace);

		l_Message.setFont(Font.font("Calibri Light", 16));
		l_Message.setTranslateX(95);
		l_Message.setTranslateY(20);
		l_Message.setMaxWidth(470);
		root.getChildren().add(l_Message);

		tourSuivant.setTranslateX(470);
		tourSuivant.setTranslateY(210);
		tourSuivant.setOnAction(new EvtTourSuivant());
		tourSuivant.setDefaultButton(true);
		tourSuivant.setStyle(" -fx-background-color:#090a0c,\n" + 
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" + 
				"        linear-gradient(#20262b, #191d22),\n" + 
				"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" + 
				"    -fx-background-radius: 5,4,3,5;\n" + 
				"    -fx-background-insets: 0,1,2,0;\n" + 
				"    -fx-text-fill: white;\n" + 
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" + 
				"    -fx-font-family: \"Calibri Light\";\n" + 
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" + 
				"    -fx-font-size: 12px;\n" + 
				"    -fx-padding: 10 20 10 20;");
		
		if(!partie.PARTIE_AUTO)
			root.getChildren().add(tourSuivant);
	}

	/**
	 * Renvoie la StackPane 
	 * @return root StackPane
	 */
	public StackPane getRoot() {
		return root;
	}

	/**
	 * Renvoie la Stage stage 
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Renvoie le pion du joueur actif.
	 * @return pion Circle
	 */
	public Circle getPionActif() {
		return l_Pions.get(partie.getPM().getJoueurActifID());
	}

	/**
	 * Renvoie la partie en cours.
	 * @return partie 
	 */
	public Partie getPartie() {
		return partie;
	}

	/**
	 * Méthode permettant de lancer une partie. 
	 * Instanciation d'une partie avec le nombre de joueurs rentré
	 * C'est ci que les comptes des joueurs et les éléments graphique vont être initialisés
	 * la liste de leurs Salaries et les pions.
	 * @param nbJoueurs int
	 */
	public void setPartie(int nbJoueurs, ArrayList<String> nomsDesJoueurs) {

		partie = new Partie(nbJoueurs, nomsDesJoueurs, this);

		for(int i=0; i<2; i++) {
			Label l_nomJoueur = new Label(partie.getPM().getJoueur(i).getNom());
			l_nomJoueur.setTextFill(Couleurs[i]);
			l_nomJoueur.setTranslateX(700+i*150);
			l_nomJoueur.setTranslateY(100);
			root.getChildren().add(l_nomJoueur);

			l_Joueurs.add(new Label(""+partie.getPM().getJoueur(i).getArgent()+"€"));
			l_Joueurs.get(i).setTranslateX(700+i*150);
			l_Joueurs.get(i).setTranslateY(120);
			l_Joueurs.get(i).setFont(Font.font("Calibri Light", 12));
			root.getChildren().add(l_Joueurs.get(i));

			l_ListeSalaries.add(new Label("\n"));
			l_ListeSalaries.get(i).setTranslateX(700+i*150);
			l_ListeSalaries.get(i).setTranslateY(140);
			l_ListeSalaries.get(i).setMaxWidth(170);
			root.getChildren().add(l_ListeSalaries.get(i));

			l_Pions.add(new Circle(7));
			l_Pions.get(i).setFill(Couleurs[i]);
			if(i<2) {
				l_Pions.get(i).setTranslateX(598 + i*15);
				l_Pions.get(i).setTranslateY(605);
			}
			else {
				l_Pions.get(i).setTranslateX(598 + (i-2)*15);
				l_Pions.get(i).setTranslateY(620);
			}
			root.getChildren().add(l_Pions.get(i));
		}
		for(int i=2; i<nbJoueurs; i++) {
			Label l_nomJoueur = new Label(partie.getPM().getJoueur(i).getNom());
			l_nomJoueur.setTextFill(Couleurs[i]);
			l_nomJoueur.setTranslateX(400+i*150);
			l_nomJoueur.setTranslateY(300);
			root.getChildren().add(l_nomJoueur);

			l_Joueurs.add(new Label(""+partie.getPM().getJoueur(i).getArgent()+"€"));
			l_Joueurs.get(i).setTranslateX(400+i*150);
			l_Joueurs.get(i).setTranslateY(320);
			l_Joueurs.get(i).setFont(Font.font("Calibri Light", 12));
			root.getChildren().add(l_Joueurs.get(i));

			l_ListeSalaries.add(new Label("\n"));
			l_ListeSalaries.get(i).setTranslateX(400+i*150);
			l_ListeSalaries.get(i).setTranslateY(340);
			l_ListeSalaries.get(i).setMaxWidth(170);
			root.getChildren().add(l_ListeSalaries.get(i));

			l_Pions.add(new Circle(7));
			l_Pions.get(i).setFill(Couleurs[i]);
			if(i<2) {
				l_Pions.get(i).setTranslateX(598 + i*15);
				l_Pions.get(i).setTranslateY(605);
			}
			else {
				l_Pions.get(i).setTranslateX(598 + (i-2)*15);
				l_Pions.get(i).setTranslateY(620);
			}
			root.getChildren().add(l_Pions.get(i));
		}

		refreshLabels(partie.getPM());
		partie.demarrerLaPartie();
	}

	/**
	 * Affiche du message adequat.
	 * @param msg String
	 */
	public void afficherMessage(String msg) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	l_Message.setTextFill(Couleurs[getPartie().getPM().getJoueurActifID()]);
            	l_Message.setText(msg);
            	l_Message.setTranslateX(100);
            	l_Message.setTranslateY(140);
            	
            }
        });
	}

	/**
	 * Cette méthode est appelé à chaque fois qu'un rafraichissement des labels est nécessaire. Elle va chercher les informations dans
	 * les champs de la partie pour mettre à jours les labels.
	 * @param pm PlateauMonopoly
	 * @see PlateauMonopoly
	 */
	public void refreshLabels(PlateauMonopoly pm) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

        		l_OpenSpace.setText(""+pm.getCase(20).getPrix()+"€");

        		for(int i=0; i<pm.getNbJoueurs(); i++) {
            		l_Joueurs.get(i).setText(""+pm.getJoueur(i).getArgent()+"€ "+(pm.getJoueur(i).getCarteSortieArretMaladie()?"[S]":""));

            		String listeSalaries = pm.getJoueur(i).getListeStringSalaries();
            		listeSalaries = listeSalaries.replaceAll(",", "\n");
            		l_ListeSalaries.get(i).setText(listeSalaries);
        		}

            }
        });
	}

	/**
	 * Affiche la fenêtre FenetreEmbaucheSalarie
	 * @see FenetreEmbaucheSalarie
	 */
	public void afficherFenetreEmbaucherSalarie() {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	fat.afficherFenetre();
            }
		});
	}

	/**
	 * Affiche la fenêtre FenetreSortirArretMaladie
	 * @see FenetreSortirArretMaladie
	 */
	public void afficherFenetreArretMaladie() {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	farretmaladie.afficherFenetre();
            }
		});
	}

	/**
	 * Affiche la fenêtre FenetreCarteChance}. 
	 * Les paramètres String Intitule et String description passés seront utilisés dans la fenêtre pour indiquer qu'elle carte on a tiré.
	 * @param Intitule String
	 * @param description String
	 * @see FenetreCarteChance
	 */
	public void afficherFenetreCarteChance(String Intitule, String description) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

				fch.setIntitule(Intitule);
				fch.setDescription(description);
				fch.afficherCarte();
            }
		});
	}

	/**
	 * Affiche la fenêtre {@link FenetreCarteCommunaute}. <br>
	 * Les paramètres String Intitule et String description passés seront utilisés dans la fenêtre pour indiquer qu'elle carte on a tiré.
	 * @param Intitule String
	 * @param description String
	 * @see FenetreCarteCommunaute
	 */
	public void afficherFenetreCarteCommunauté(String Intitule, String description) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

				fco.setIntitule(Intitule);
				fco.setDescription(description);
				fco.afficherCarte();
            }
		});
	}

	/**
	 * Méthode plaçant un SignetPatron désignant le propriétaire du Salarie quand le joueur achète le Salarie.
	 * @param joueur JoueurMonopoly
	 * @param cells Case
	 * @see JoueurMonopoly
	 * @see Case
	 */
	public void setSignetPatron(JoueurMonopoly joueur, Case cells) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	cells.getSignetPatron().setFill(getPionActif().getFill());

            	double x = 100, y = 100;
        		int pos = joueur.getPosition();

        		if(cells.getSignetPatron().getPoints().isEmpty())
        			root.getChildren().add(cells.getSignetPatron());

        		if(pos > 0 && pos < 10) {
        			if(cells.getSignetPatron().getPoints().isEmpty())
        				cells.getSignetPatron().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 517 - ((pos-1) * 54);
        			y = 642;
        		}
        		else if(pos > 10 && pos < 20) {
        			if(cells.getSignetPatron().getPoints().isEmpty())
        				cells.getSignetPatron().getPoints().addAll(new Double[] {0.,12.,12.,12.,12.,0.});
        			x = 51;
        			y = 558 - ((pos-11) * 54);
        		}
        		else if(pos > 20 && pos < 30) {
        			if(cells.getSignetPatron().getPoints().isEmpty())
        				cells.getSignetPatron().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 85 + ((pos-21) * 54);
        			y = 51;
        		}
        		else if(pos > 30 && pos < 40) {
        				if(cells.getSignetPatron().getPoints().isEmpty())
        			cells.getSignetPatron().getPoints().addAll(new Double[] {0.,0.,12.,0.,0.,12.});
        			x = 592;
        			y = 85 + ((pos-31) * 54);
        		}

        		if(pos == 15 || pos == 12)
        			x+=21;
        		else if(pos == 25 || pos == 28)
        			y+=21;
        		else if(pos == 35)
        			x-=21;

        		cells.getSignetPatron().setTranslateX(x);
        		cells.getSignetPatron().setTranslateY(y);
            }
        });
	}

	/**
	 * Méthode ajoutant un salarie dans la fenêtre principale en fonction de la case passée en paramètre.
	 * @param cells Case
	 * @see Case
	 */
	public void setCompetence(CaseSalarie cells){

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	Polygon Salarie = cells.Salaries.get(cells.getNbCompetence());

            	Salarie.setFill(Color.BLACK);

            	int x = -50;
            	int y = -50;
            	int pos = cells.getId();

            	if(cells.getSignetPatron().getPoints().isEmpty())
            		root.getChildren().add(Salarie);

            	boolean grade = (cells.getNbCompetence() == 5);
            	if(!grade)
            		Salarie.getPoints().addAll(new Double[] {0., 11., 0., 3., 5., 0., 10., 3., 10., 11.});
            	else if((pos > 0 && pos < 10) || (pos > 20 && pos < 30))
            		Salarie.getPoints().addAll(new Double[] {0., 0., 0., 11., 46., 11., 46., 0.});
            	else
            		Salarie.getPoints().addAll(new Double[] {0., 0., 0., 50., 10., 50., 10., 0.});


            	if(pos > 0 && pos < 10) {
        			x = 520 - ((pos-1) * 54) + (grade?0:(cells.getNbCompetence()-1)*12);
        			y = 577;
        		}
        		else if(pos > 10 && pos < 20) {
        			x = 69;
        			y = 519 - ((pos-11) * 54) + (grade?0:(cells.getNbCompetence()-1)*13);
        		}
        		else if(pos > 20 && pos < 30) {
        			x = 87 + ((pos-21) * 54)  + (grade?0:(cells.getNbCompetence()-1)*12);
        			y = 69;
        		}
        		else if(pos > 30 && pos < 40) {
        			x = 576;
        			y = 87 + ((pos-31) * 54) + (grade?0:(cells.getNbCompetence()-1)*13);
        		}

            	Salarie.setTranslateX(x);
            	Salarie.setTranslateY(y);

            }
		});
	}

	/**
	 * Afficher les images des dés dans la FenetreDeJeu.
	 * @param pm PlateauMonopoly
	 * @see PlateauMonopoly
	 */
	public void afficherDes(PlateauMonopoly pm) {
		Platform.runLater(new Runnable() {
            @Override public void run() {

            	effacerDes();
				Des.get(0).setImage(imageDes.get(pm.des.getDe1()-1));
				Des.get(1).setImage(imageDes.get(pm.des.getDe2()-1));

            }
       });
	}

	public void effacerDes() {
		Des.get(0).setImage(null);
		Des.get(1).setImage(null);
	}

	/**
	 * Déplace le pion du joueur actif en fonction de la position sur le plateau de joueur passé en paramètre.
	 * @param joueur JoueurMonopoly
	 * @see JoueurMonopoly
	 */
	public void deplacerPion(JoueurMonopoly joueur){

		double x, y;
		int pos = joueur.getPosition();
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), getPionActif());

		if(joueur.getEstFauche()) {
			x = 103;
			y = 538;
		}
		else if(pos == 0) {
			x = 604;
			y = 604;
		}
		else if(pos == 10) {
			if(joueur.getEstMalade()) {
				x = 47;
				y = 598;
			}
			else if(joueur.getID() == 0 || joueur.getID() == 1){
				x = 16;
				y = 644;
			}
			else  {
				x = 48;
				y = 628;
			}
		}
		else if(pos == 20) {
			x = 30;
			y = 34;
		}
		else if(pos == 30) {
			x = 604;
			y = 34;
		}
		else if(pos > 0 && pos < 10) {
			x = 537 - ((pos-1) * 54);
			y = 620;
		}
		else if(pos > 10 && pos < 20) {
			x = 30;
			y = 538 - ((pos-11) * 54);
		}
		else if(pos > 20 && pos < 30) {
			x = 104 + ((pos-21) * 54);
			y = 30;
		}
		else if(pos > 30 && pos < 40) {
			x = 612;
			y = 106 + ((pos-31) * 54);
		}
		else {
			x = -50;
			y = -50;
		}

		switch(joueur.getID()) {
		case 0: x-=8; y-=8; break;
		case 1: x+=8; y-=8; break;
		case 2: x-=8; y+=8; break;
		case 3: x+=8; y+=8; break;
		default: break;
		}

	    tt.setToX(x);
	    tt.setToY(y);
	    tt.play();
	}

	/**
	 * Affiche le vainqueur de la partie. Ajoute également le bouton newPartie à la fenêtre princiaple.
	 * @param pm PlateauMonopoly
	 * @see PlateauMonopoly
	 */
	public void afficherVainqueur(PlateauMonopoly pm) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	Label vainqueur = new Label("Le vainqueur est "+pm.estVainqueur().getNom()+" !");
            	vainqueur.setTextFill(l_Pions.get(pm.estVainqueur().getID()).getFill());
            	vainqueur.setFont(Font.font("Calibri Light", 26));
            	vainqueur.setTranslateX(145);
            	vainqueur.setTranslateY(525);

        		root.getChildren().add(vainqueur);

        		root.getChildren().remove(tourSuivant);

        		newPartie.setTranslateX(463);
        		newPartie.setTranslateY(533);
        		newPartie.setOnAction(new EvtNewPartie());
        		root.getChildren().add(newPartie);

            }
		});
	}

	/**
	 * Réinitialise les éléments graphiques de la fenêtre tels que les labels, les pions et les logs.
	 */
	public void resetElementsGraphiques() {
		l_OpenSpace.setText("0€");
		l_Joueurs.clear();
		l_ListeSalaries.clear();
		l_Pions.clear();
		l_Logs.clear();
	}

	/**
	 * Évènement lorque l'on appuie sur le bouton tourSuivant : la partie reprend.
	 */
	private class EvtTourSuivant implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			partie.reprendrePartie();
		}
	}
	/**
	 * Évènement lorque l'on appuie sur le bouton newPartie : la fenètre principale se ferme, les éléments graphiques sont
	 * réinitialisés, la StackPane root est redéfinie et on réaffiche la fenêtre de démarrage.
	 * @see FenetreDemarrage
	 */
	private class EvtNewPartie implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			resetElementsGraphiques();
			root = new StackPane();
			initRoot();
			Scene scene = new Scene(root,655,655);
			stage.setScene(scene);
			fd.getStage().show();
		}
	}
	/**
	 * Évènement lorqu'on clic dans la StackPane root :
	 * en fonction des coordonnées du pointeurs, on peux obtenir la position de la case visée. <br>
	 * Si cette position est une position valide (càd que l'on clic sur une {@link CaseSalarie} qui appartient au joueur dont
	 * c'est le tour), alors on peut déclencher l'affichage d'une {@link FenetreEmbaucheSalarie} avec en paramètre la position cliquée.
	 * @see CaseSalarie
	 * @see FenetreEmbaucheSalarie
	 */
	private class EvtClicRoot implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {

			int pos = -1;

			if(event.getSceneX() < 84) {
				if(event.getSceneY() < 84)
					pos = 20;
				else if(event.getSceneY() < 570)
					pos = 19 - (int)((event.getSceneY()-84)/54);
				else
					pos = 10;
			}
			else if(event.getSceneX() < 570) {
				if(event.getSceneY() < 84)
					pos = 21 + (int)((event.getSceneX()-84)/54);
				else if(event.getSceneY() >= 570)
					pos = 9 - (int)((event.getSceneX()-84)/54);
			}
			else {
				if(event.getSceneY() < 84)
					pos = 30;
				else if(event.getSceneY() < 570)
					pos = 31 + (int)((event.getSceneY()-84)/54);
				else
					pos = 0;
			}

			ArrayList<Integer> CasesInterdites = new ArrayList<Integer>();
			for(int i=0; i<40; i++) {
				CasesInterdites.add(i);
			}
			CasesInterdites.add(-1);
			for(Case t:getPartie().getPM().getJoueurActif().getListeSalaries()) {
				CasesInterdites.remove((Object)(t.getId()));
			}

			if(!CasesInterdites.contains(pos)) {
				fenSal.afficherFenetre(pos);
			}
		}
	}

}