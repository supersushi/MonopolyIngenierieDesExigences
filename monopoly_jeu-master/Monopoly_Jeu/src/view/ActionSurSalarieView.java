package view;

import controller.ActionSurSalarieAjoutCompetenceController;
import controller.ActionSurSalarieQuitterController;
import controller.ActionSurSalarieRevendreController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import model.ViewModel;

/**
 * Vue permettant d'effectuer des actions sur les salariés
 */
/**
 * @author Massourang Jugurtha Lina Emma
 *
 */
public class ActionSurSalarieView extends ViewModel {

	private MonopolyView monopolyView;
	private Stage stage;
	private HBox root;
	private int position;
	private Label txt;
	private Label errorTxt;
	private CheckBox competence;
	private Button revendreComp;

	/**
	 * Constructeur
	 * 
	 * @param MonopolyView
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

		VBox checkboxes = new VBox();
		if (position != 5 && position != 15 && position != 25 && position != 35 && position != 12 && position != 28) {
			for (int i = 0; i < monopolyView.getPartie().getPM().getCase(position).getCompetences().size(); i++) {
				competence = new CheckBox();
				competence.setText(monopolyView.getPartie().getPM().getCase(position).getCompetences().get(i) + " ("
						+ monopolyView.getPartie().getPM().getCase(position).getPrixCompetence() + "€)");
				checkboxes.getChildren().add(competence);
				competence.setOnAction(new ActionSurSalarieAjoutCompetenceController(this,
						monopolyView.getPartie().getPM().getCase(position).getCompetences().get(i)));
			}

			revendreComp = new Button("Licencier le salarié");
		} else {
			revendreComp = new Button("Mettre fin au contrat avec le client");
		}

		HBox button_licenciement = new HBox();
		revendreComp.setOnAction(new ActionSurSalarieRevendreController(this));
		button_licenciement.getChildren().add(revendreComp);

		aside.getChildren().add(checkboxes);
		aside.getChildren().add(button_licenciement);

		errorTxt = new Label("");
		errorTxt.setTextFill(Color.BLACK);
		aside.getChildren().add(errorTxt);

		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				if (competence.isFocused())
					competence.fire();
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

	/**
	 * @return monopolyView MonopolyView
	 */
	public MonopolyView getMonopolyView() {
		return monopolyView;
	}

	/**
	 * @param monopolyView
	 */
	public void setMonopolyView(MonopolyView monopolyView) {
		this.monopolyView = monopolyView;
	}

	/**
	 * @return position int
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position int
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return errorTxt label
	 */
	public Label getErrorTxt() {
		return errorTxt;
	}

	/**
	 * @param errorTxt
	 */
	public void setErrorTxt(Label errorTxt) {
		this.errorTxt = errorTxt;
	}

}
