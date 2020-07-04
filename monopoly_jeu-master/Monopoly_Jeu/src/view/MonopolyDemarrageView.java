package view;

import java.util.ArrayList;

import controller.DemarrageQuitterController;
import controller.DemarrageValiderController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.View;

/**
 * Fenêtre à afficher au démarrage d'une nouvelle partie, permettant de
 * sélectionner le nombre de joueur.
 */
public class MonopolyDemarrageView extends View {

	private MonopolyView monopolyView;
	private Stage stage;
	private VBox root;
	private Label lab;
	private Text lab2;
	private ArrayList<TextField> listePlayer = new ArrayList<TextField>();
	private Button butt;
	private int choix = 0;

	/**
	 * Constructeur.
	 * 
	 * @param monopolyView FenetreDeJeu
	 */
	public MonopolyDemarrageView(MonopolyView monopolyView) {

		this.monopolyView = monopolyView;

		this.stage = new Stage();

		this.stage.setTitle("Nouvelle partie Monopoly - Ingénierie des exigences");
		this.stage.initOwner(monopolyView.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);

		root = new VBox();
		initRoot();
		Scene scene = new Scene(root, 650.0, 650.0);
		stage.setScene(scene);
		stage.setOnHiding(new DemarrageQuitterController(this));
	}

	/**
	 * Initialise la HBox root de la FenetreDemarrage avec une Liste de joueurs et
	 * un bouton de validation.
	 */
	private void initRoot() {
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(5);

		lab2 = new Text("MIAGE - Projet Commun ");
		root.getChildren().add(lab2);
		lab2.setTranslateX(110);
		lab = new Label("Joueurs (2 minimum) :");
		HBox box = new HBox();

		butt = new Button("Commencer !");
		butt.setDefaultButton(true);
		butt.setOnAction(new DemarrageValiderController(this));
		butt.setTranslateX(160);
		ImageView iv = new ImageView();
		iv.setFitHeight(500.0);
		iv.setFitWidth(500.0);
		iv.setPickOnBounds(true);
		iv.setPreserveRatio(true);
		iv.setImage(new Image("/images/acc.png"));
		VBox VBox1 = new VBox();
		VBox1.setPrefWidth(300.0);
		HBox HBox1 = new HBox();
		HBox1.setPrefHeight(650.0);
		root.setStyle("-fx-background-color: rgba(63, 127, 191, 1);");

		HBox.setMargin((iv), new Insets(80, 0, 0, 0));

		VBox1.getChildren().add(lab);
		lab.setTranslateY(80);

		for (int i = 0; i < 4; i++) {
			TextField tf = new TextField(i < 2 ? "Player" + (i + 1) : "");
			listePlayer.add(tf);
			listePlayer.get(i).setPromptText("Player " + (i + 1));
			VBox1.getChildren().add(listePlayer.get(i));
			listePlayer.get(i).setPadding(new Insets(10, 10, 10, 10));
			listePlayer.get(i).setTranslateY(150 + i * 10);
		}

		box.getChildren().add(VBox1);

		VBox1.getChildren().add(butt);
		butt.setTranslateY(250);
		box.getChildren().add(iv);
		root.getChildren().add(box);

		butt.setStyle(" -fx-background-color:#090a0c,\n"
				+ "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n"
				+ "        linear-gradient(#20262b, #191d22),\n"
				+ "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n"
				+ "    -fx-background-radius: 5,4,3,5;\n" + "    -fx-background-insets: 0,1,2,0;\n"
				+ "    -fx-text-fill: white;\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n"
				+ "    -fx-font-family: \"Arial\";\n" + "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n"
				+ "    -fx-font-size: 12px;\n" + "    -fx-padding: 10 20 10 20;");

		lab.setStyle("-fx-font-size: 16pt;\n" + "    -fx-font-family: \"Arial\";\n" + "    -fx-text-fill: white;\n"
				+ "    -fx-opacity: 0.6;");
		lab2.setStyle("-fx-font-size: 30pt;\n" + "    -fx-font-family: \"Arial\";\n" + "    -fx-text-fill: white;\n"
				+ "    -fx-opacity: 0.6;");

	}

	/**
	 * Renvoie la Stage de la fenêtre de démarrage.
	 * 
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @return monopolyView MonopolyView
	 */
	public MonopolyView getMonopolyView() {
		return monopolyView;
	}

	/**
	 * @param monopolyView MonopolyView
	 */
	public void setMonopolyView(MonopolyView monopolyView) {
		this.monopolyView = monopolyView;
	}

	/**
	 * @return listePlayer ArrayList<TextField>
	 */
	public ArrayList<TextField> getListePlayer() {
		return listePlayer;
	}

	/**
	 * @param listePlayer ArrayList<TextField>
	 */
	public void setListePlayer(ArrayList<TextField> listePlayer) {
		this.listePlayer = listePlayer;
	}

	/**
	 * @return choix int
	 */
	public int getChoix() {
		return choix;
	}

	/**
	 * @param choix int
	 */
	public void setChoix(int choix) {
		this.choix = choix;
	}

}
