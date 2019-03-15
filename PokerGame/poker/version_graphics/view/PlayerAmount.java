package poker.version_graphics.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.controller.PokerGameController;
import poker.version_graphics.model.PokerGameModel;

public class PlayerAmount extends Stage {
	
	private PokerGameView view;
	private PokerGameController controller;
	private PokerGameModel model;
	private HBox plrBox = new HBox();
	private BorderPane main = new BorderPane();
	
	Button btnOk = new Button("Ok");
	Button btnCancel = new Button("Cancel");
	TextField plrText = new TextField();
	Label plrLabel = new Label("Amount of Players (2-6)");
	
	
	public PlayerAmount(PokerGameView view) {
		Stage stage = new Stage();
		stage.setTitle("Amount Of Players");
		plrBox.getChildren().addAll(plrLabel, plrText);
		HBox controll = new HBox();
		controll.getChildren().addAll(btnOk, btnCancel);
		main.setCenter(plrBox);
		main.setBottom(controll);
		
		Scene scene = new Scene(main);
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	
	
	public Button getBtnOk() {
		return btnOk;
	}
	public Button getBtnCancel() {
		return btnCancel;
	}
	
	public String getPlrText() {
		return plrText.getText();
	}
	

}


