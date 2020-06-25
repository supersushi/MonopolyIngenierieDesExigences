package fenetres;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Fen�tre lors du passage sur une carte chance.
 * @see FenetreDeJeu
 */
public class FenetreCarteChance {

	private FenetreDeJeu fjeu;
	private Stage stage;
	private HBox root;
	private VBox carte;
	private Button b_Ok;
	private Label l_Intitule = new Label("CHANCE");
	private Label l_Description = new Label("?");
	
	/**
	 * Constructeur
	 * @param fjeu FenetreDeJeu
	 * @see FenetreDeJeu
	 */
	public FenetreCarteChance(FenetreDeJeu fjeu) {
		this.fjeu = fjeu;
		this.stage = new Stage();
		this.stage.setTitle("Carte Chance");
		this.stage.initOwner(fjeu.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		root = new HBox();
		initRoot();
		
		Scene scene = new Scene(root,440,200);
		stage.setScene(scene);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	/**
	 * Initialisation de root de la FenetreCarteChance : image, description de la carte et un bouton OK.
	 */
	private void initRoot() {
		
		root.setFillHeight(true);
		root.setAlignment(Pos.CENTER_LEFT);
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(15);
		root.setStyle("-fx-background-color: #CDE6D0; -fx-border-style: dashed; -fx-border-width: 3px; -fx-border-color: orange");
		
		Image i_chance = new Image("images/chance.jpg");
		ImageView iv_chance = new ImageView(i_chance);
		root.getChildren().add(iv_chance);
		
		this.carte = new VBox();
		carte.setFillWidth(true);
		
		l_Intitule.setStyle("-fx-font-size: 20px");
		carte.getChildren().add(l_Intitule);
		l_Description.setStyle("-fx-padding: 10px 0px");
		carte.getChildren().add(l_Description);
		
		b_Ok = new Button("OK");
		b_Ok.setDefaultButton(true);
		b_Ok.setOnAction(new EvtValider());
		carte.getChildren().add(b_Ok);
		
		root.getChildren().add(carte);
	}
	
	/**
	 * Renvoie la Stage de la fen�tre.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * D�finit l'intitule de la carte.
	 * @param Intitule String
	 */
	public void setIntitule(String Intitule) {
		l_Intitule = new Label("CHANCE");
	}
	/**
	 * D�finit la description de la carte.
	 * @param description String
	 */
	public void setDescription(String description) {
		l_Description = new Label(description);
	}
	
	/**
	 * Affiche la fen�tre et r�initialisant root � chaque appel.
	 */
	public void afficherCarte() {
		root = new HBox();
		initRoot();
		
		Scene scene = new Scene(root,440,200);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Ev�nement 
	 * Fermeture de la fen�tre.
	 */
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			event.consume();
		}
	}
	
	/**
	 * Ev�nement 
	 * Reprise de la partie � la fermeture de la fen�tre.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fjeu.getPartie().reprendrePartie();
			event.consume();
		}
	}
}
