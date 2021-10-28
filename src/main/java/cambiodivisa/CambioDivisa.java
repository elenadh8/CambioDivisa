package cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {
	private Button calcular;
	private TextField aTextField, bTextField;
	private ComboBox<Divisa> aCombobox, bCombobox;

	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.8873);
	private Divisa dolar = new Divisa("Dolar", 1.2007);
	private Divisa yen = new Divisa("Yen", 133.59);

	private Divisa[] a = { euro, libra, dolar, yen };

	@Override
	public void start(Stage primaryStage) throws Exception {
		calcular = new Button("Calcular");
		calcular.setOnAction(e -> onCalcular(e));
		aTextField = new TextField();
		aTextField.setPrefColumnCount(6);

		bTextField = new TextField();
		bTextField.setPrefColumnCount(6);
		bTextField.setDisable(true);
		
		aCombobox = new ComboBox<Divisa>();
		aCombobox.getItems().addAll(a);
		aCombobox.getSelectionModel().selectFirst();

		bCombobox = new ComboBox<Divisa>();
		bCombobox.getItems().addAll(a);
		bCombobox.getSelectionModel().select(yen);

		HBox aHBox = new HBox(aTextField, aCombobox);
		HBox bHBox = new HBox(bTextField, bCombobox);
		aHBox.setAlignment(Pos.BASELINE_CENTER);
		bHBox.setAlignment(Pos.CENTER);
		aHBox.setSpacing(8);
		bHBox.setSpacing(8);

		VBox root = new VBox(aHBox, bHBox, calcular);
		root.setSpacing(8);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Calculadora.fxml");
		primaryStage.show();

	}

	private void onCalcular(ActionEvent e) {
		try {
			Double aDouble = Double.parseDouble(aTextField.getText());
			
			Divisa aDivisa = aCombobox.getSelectionModel().getSelectedItem();
			Divisa bDivisa = bCombobox.getSelectionModel().getSelectedItem();
			Double bDouble = aDivisa.toEuro(aDouble);
			Double cDouble = bDivisa.fromEuro(bDouble); 
						
			bTextField.setText(cDouble + "");
						
			
		} catch (NumberFormatException e2) {
			Alert aAlerta = new Alert(AlertType.ERROR);
			aAlerta.setTitle("error");
			aAlerta.setHeaderText("Error");
			aAlerta.setContentText("Formato no Válido");
			aAlerta.show();

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
