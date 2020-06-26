package fenetres;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Fenêtre à afficher au démarrage d'une nouvelle {@link jeumonopoly.Partie}, permettant de sélectionner le nombre de joueur.
 * @see FenetreDeJeu
 */
public class FenetreDemarrage {

	private FenetreDeJeu fjeu;
	private Stage stage;
	private HBox root;
	private Label lab;
	private Label lab2;
	private ArrayList<TextField> listeJoueurs = new ArrayList<TextField>();
	private Button butt;
	private int choix = 0;

	/**
	 * Constructeur.
	 * @param fjeu FenetreDeJeu
	 * @see FenetreDeJeu
	 */
	public FenetreDemarrage(FenetreDeJeu fjeu) {

		this.fjeu = fjeu;

		this.stage = new Stage();

		this.stage.setTitle("Nouvelle partie Monopoly - Ingénierie des exigences");
		this.stage.initOwner(fjeu.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);

		root = new HBox();
		initRoot();
	    Scene scene = new Scene(root, 650.0,650.0);
		stage.setScene(scene);
		stage.setOnHiding(new EvtQuitter());
	}

	/**
	 * Initialise la HBox root de la FenetreDemarrage avec une Liste de joueurs et un bouton de validation.
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(5);

		lab = new Label("Joueurs (2 minimum) :");
		lab2 = new Label("MIAGE - Projet Commun ");
		butt = new Button("Commencer !");
		butt.setOnAction(new EvtValider());
		butt.setDefaultButton(true);
		butt.setOnAction(new EvtValider());
		ImageView iv=new ImageView();
		iv.setFitHeight(500.0);
		iv.setFitWidth(500.0);
		iv.setPickOnBounds(true);
		iv.setPreserveRatio(true);
		iv.setImage(new Image("/images/acc.png"));
		VBox VBox1 =new VBox();
		//VBox1.setPrefHeight(400.0);
		VBox1.setPrefWidth(400.0);
		
		HBox HBox1 =new HBox();
		HBox1.setPrefHeight(650.0);
		//HBox1.setPrefWidth(495.);
		root.setStyle("-fx-background-color: rgba(63, 127, 191, 1);");
		HBox1.setBlendMode(BlendMode.SRC_OVER);
		HBox1.setDepthTest(DepthTest.INHERIT);
		HBox1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		HBox1.setCenterShape(true);
		lab2.setCenterShape(true);

		HBox.setMargin((iv), new Insets(80,0,0,0));

		
		VBox1.getChildren().add(lab2);
		VBox1.getChildren().add(lab);
		lab.setTranslateY(80);
		for(int i=0; i<4; i++) {
			TextField tf = new TextField(i<2?"Player"+(i+1):"");
			listeJoueurs.add(tf);
			listeJoueurs.get(i).setPromptText("Player "+(i+1));
			VBox1.getChildren().add(listeJoueurs.get(i));
			listeJoueurs.get(i).setPadding(new Insets(10,10,10,10));
			listeJoueurs.get(i).setTranslateY(150+i*10);
			
		}
		root.getChildren().add(VBox1);
		
		VBox1.getChildren().add(butt);
		butt.setTranslateY(250);
		lab2.setCenterShape(true);
		
		HBox1.getChildren().add(iv);
		root.getChildren().add(HBox1);
	}



	/**
	 * Renvoie la Stage de la fenêtre de démarrage.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Evènement 
	 * Récupération du nombre de joueurs dans la page d'acceuil avant de lancer une partie .
	 * @see FenetreDeJeu
	 */
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			ArrayList<String> champs = new ArrayList<String>();
			for(int i=0; i<4; i++) {
				if(listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty())
					champs.add(listeJoueurs.get(i).getText());
			}
			if(champs.size()>=2) {
				choix = 1;
				fjeu.setPartie(champs.size(), champs);
				fjeu.getStage().show();
				stage.close();
			}
			event.consume();
		}
	}

	/**
	 * Evènement 
	 * Ferme la fenêtre de démarrage et arrête le programme si l'on a pas cliqué sur le bouton Valider.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			if(choix == 0)
				System.exit(0);
			event.consume();
		}
	}
}
