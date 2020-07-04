package view;

import controller.ActionSurSalarieAcquerirController;
import controller.ActionSurSalarieQuitterController;
import controller.ActionSurSalarieRevendreController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.View;

/**
 * Fenêtre d'affichage lors du clique sur une compétence. Actions : - Embaucher
 * un salarié - Licencier un salarié - Faire monter en compétence le salarié
 */
public class ActionSurSalarieView extends View {

	private MonopolyView monopolyView;
	private Stage stage;
	private HBox root;
	private int position;
	private Label txt;
	private Label errorTxt;
	private Button acqComp;
	private Button revendreComp;

	/**
	 * Constructeur
	 * 
	 * @param fjeu FenetreDeJeu
	 * @see MonopolyView
	 */
	public ActionSurSalarieView(MonopolyView monopolyView) {

		this.monopolyView = monopolyView;

		this.stage = new Stage();
		this.stage.setTitle("Action sur le Salarie :");
		this.stage.initOwner(monopolyView.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);

		stage.setOnHiding(new ActionSurSalarieQuitterController(this));
	}

	/**
	 * Initialisation de root : image, un label et des boutons.
	 */
	private void initRoot() {
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(10);
		root.setStyle("-fx-background-color: #CDE6D0; ");

		Image i_Salarie;

		switch (position) {
		case 5:
			i_Salarie = new Image("images/contest.png");
			break;
		case 15:
			i_Salarie = new Image("images/contest.png");
			break;
		case 25:
			i_Salarie = new Image("images/contest.png");
			break;
		case 35:
			i_Salarie = new Image("images/contest.png");
			break;
		case 28:
			i_Salarie = new Image("images/computer.png");
			break;
		case 12:
			i_Salarie = new Image("images/gold.png");
			break;
		default: {
			String couleur = monopolyView.getPartie().getPM().getCase(position).getCouleur();
			i_Salarie = new Image("images/c_" + couleur + ".png");
		}
			;
			break;
		}

		ImageView iv_Salarie = new ImageView(i_Salarie);
		root.getChildren().add(iv_Salarie);

		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);

		txt = new Label("Quelle action voulez-vous faire pour ce salarié "
				+ monopolyView.getPartie().getPM().getCase(position).getNom() + " ?");
		aside.getChildren().add(txt);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);

		acqComp = new Button("Monter en competence ("
				+ monopolyView.getPartie().getPM().getCase(position).getPrixCompetence() + "€)");
		acqComp.setOnAction(new ActionSurSalarieAcquerirController(this));
		if (position != 5 && position != 15 && position != 25 && position != 35 && position != 12 && position != 28)
			buttons_horiz.getChildren().add(acqComp);

		revendreComp = new Button("Licencier le salarié");
		revendreComp.setOnAction(new ActionSurSalarieRevendreController(this));
		buttons_horiz.getChildren().add(revendreComp);

		aside.getChildren().add(buttons_horiz);

		errorTxt = new Label("");
		errorTxt.setTextFill(Color.BLACK);
		aside.getChildren().add(errorTxt);

		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				if (acqComp.isFocused())
					acqComp.fire();
				else
					revendreComp.fire();
				ev.consume();
			}
		});
	}

	/**
	 * Affichage de la fenêtre et réinitialisation de root.
	 * 
	 * @param pos int
	 */
	public void afficherFenetre(int pos) {
		position = pos;
		root = new HBox();
		initRoot();

		Scene scene = new Scene(root, 470, 130);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Renvoie la Stage de la fenêtre.
	 * 
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}

	public MonopolyView getMonopolyView() {
		return monopolyView;
	}

	public void setFenetreDeJeu(MonopolyView fenetreDeJeu) {
		this.monopolyView = fenetreDeJeu;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Label getErrorTxt() {
		return errorTxt;
	}

	public void setErrorTxt(Label errorTxt) {
		this.errorTxt = errorTxt;
	}

}
