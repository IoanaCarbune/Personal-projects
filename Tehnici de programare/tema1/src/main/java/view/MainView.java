package view;


import javafx.scene.paint.Color;
import controller.MessageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainView  {

	private static Stage primaryStage;
	
	private Button additionButton=new Button("Adună");
	private Button subtractionButton=new Button("Scade");
	private Button multiplicationButton=new Button("Înmulțește");
	private Button divisionButton=new Button("Împarte");
	private Button derivativeButton=new Button("Derivează");
	private Button integrationButton=new Button("Integrează");
	
	private TextField polinom1TextField=new TextField();
	private TextField polinom2TextField=new TextField();
	private TextField result=new TextField();
	
	private Label polinom1Label=new Label("Polinom 1: ");
	private Label polinom2Label=new Label("Polinom 2: ");
	
	private Label errorLabel1=new Label("");
	private Label errorLabel2=new Label("");
	
	private Button resetButton=new Button("Reset");
	
	private RadioButton polinom1Radio=new RadioButton("Polinom 1");
	private RadioButton polinom2Radio=new RadioButton("Polinom 2");
	
	private ToggleGroup toggleGroup=new ToggleGroup();
	
	public MainView(Stage primaryStage2)  {
		primaryStage=primaryStage2;
		BorderPane mainLayout= new BorderPane();
		Scene scene=new Scene(mainLayout, 550,500);
		HBox polinom1HBox=new HBox(10);
		polinom1Label.setFont(new Font("Times New Roman", 20));
		polinom1TextField.setPrefSize(300, 30);
		polinom1HBox.setAlignment(Pos.CENTER);
		polinom1HBox.getChildren().add(polinom1Label);
		VBox polinom1VBox=new VBox(0);
		polinom1VBox.setAlignment(Pos.CENTER);
		polinom1VBox.getChildren().add(errorLabel1);
		polinom1VBox.getChildren().add(polinom1TextField);
		polinom1VBox.setPadding(new Insets(-10,0,0,0));
		polinom1HBox.getChildren().add(polinom1VBox);
		
		polinom1HBox.setStyle("-fx-padding: 10;" + "-fx-border-width: 3;" + "-fx-border-insets: 15;"
		        + "-fx-border-radius: 10;" + "-fx-border-color: PowderBlue; -fx-background-color:  #f0f8ff; ");
		
		HBox polinom2HBox=new HBox(10);
		polinom2Label.setFont(new Font("Times New Roman", 20));
		polinom2TextField.setPrefSize(300, 30);
		polinom2HBox.setAlignment(Pos.CENTER);
		polinom2HBox.getChildren().add(polinom2Label);
		VBox polinom2VBox=new VBox(0);
		polinom2VBox.setAlignment(Pos.CENTER);
		polinom2VBox.getChildren().add(errorLabel2);
		polinom2VBox.getChildren().add(polinom2TextField);
		polinom2VBox.setPadding(new Insets(-10,0,0,0));
		polinom2HBox.getChildren().add(polinom2VBox); 
		polinom2HBox.setStyle("-fx-padding: 10;" + "-fx-border-width: 3;" + "-fx-border-insets: 15;"
		        + "-fx-border-radius: 10;" + "-fx-border-color: PowderBlue; -fx-background-color:  #f0f8ff;");

		additionButton.setPrefSize(130, 50);
		subtractionButton.setPrefSize(130, 50);
		divisionButton.setPrefSize(130, 50);
		multiplicationButton.setPrefSize(130, 50);
		derivativeButton.setPrefSize(130, 50);
		integrationButton.setPrefSize(130, 50);
	
		additionButton.setStyle("-fx-font: 22 TimesNewRoman; -fx-base: #b0e0e6; ");
		subtractionButton.setStyle("-fx-font: 22 TimesNewRoman; -fx-base: #b0e0e6;");
		multiplicationButton.setStyle("-fx-font: 20 TimesNewRoman; -fx-base: #b0e0e6;");
		divisionButton.setStyle("-fx-font: 20 TimesNewRoman; -fx-base: #b0e0e6;");
		derivativeButton.setStyle("-fx-font: 22 TimesNewRoman; -fx-base: #b0e0e6;");
		integrationButton.setStyle("-fx-font: 20 TimesNewRoman; -fx-base: #b0e0e6;");
		
		VBox column1Vbox=new VBox(15);
		VBox column2Vbox=new VBox(15);
		VBox column3Vbox=new VBox(15);
		
		column1Vbox.setAlignment(Pos.CENTER);
		column2Vbox.setAlignment(Pos.CENTER);
		column3Vbox.setAlignment(Pos.CENTER);
		
		column1Vbox.getChildren().add(additionButton);
		column1Vbox.getChildren().add(subtractionButton);
		
		column2Vbox.getChildren().add(divisionButton);
		column2Vbox.getChildren().add(multiplicationButton);
		column3Vbox.getChildren().add(derivativeButton);
		column3Vbox.getChildren().add(integrationButton);
	   
		
		polinom1Radio.setToggleGroup(toggleGroup);
		polinom2Radio.setToggleGroup(toggleGroup);
		
		VBox toggleVBox=new VBox(10);
		toggleVBox.getChildren().add(polinom1Radio);
		toggleVBox.getChildren().add(polinom2Radio);
		HBox operationsHBox=new HBox(15);
		operationsHBox.setAlignment(Pos.CENTER);
		operationsHBox.getChildren().add(column1Vbox);
		operationsHBox.getChildren().add(column2Vbox);
		operationsHBox.getChildren().add(column3Vbox);
		operationsHBox.getChildren().add(toggleVBox);
		result.setStyle("-fx-padding: 10;" + "-fx-border-width: 3;" + "-fx-border-insets: 15;"
		        + "-fx-border-radius: 10;" + "-fx-border-color: PowderBlue;");
		result.setEditable(false);
		
		VBox mainVbox=new VBox(20);
		mainVbox.setAlignment(Pos.CENTER);
		mainVbox.getChildren().add(polinom1HBox);
		mainVbox.getChildren().add(polinom2HBox);
		mainVbox.getChildren().add(operationsHBox);
		mainVbox.getChildren().add(result);
		mainVbox.getChildren().add(resetButton);
		mainLayout.setCenter(mainVbox);
		primaryStage.setTitle("Calculator polinoame");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
	}

	public void show() {
		primaryStage.show();
	}
	
	public void addSumButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		additionButton.setOnAction(eventHandler);
	}
	
	public void addSubButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		subtractionButton.setOnAction(eventHandler);
	}

	public void addDivButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		divisionButton.setOnAction(eventHandler);
	}

	public void addMultButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		multiplicationButton.setOnAction(eventHandler);
	}

	public void addDerivButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		derivativeButton.setOnAction(eventHandler);
	}

	public void addIntegrButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		integrationButton.setOnAction(eventHandler);
	}
	public void addResetButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		resetButton.setOnAction(eventHandler);
	}

	
	public String getPolinom1()
	{
		return polinom1TextField.getText();
	}
	
	public String getPolinom2()
	{
		return polinom2TextField.getText();
	}

	public void error1(String string, Color color) {
		MessageController.displayMessage(string, errorLabel1, color);
	}
	public void error2(String string, Color color) {
		MessageController.displayMessage(string, errorLabel2, color);
	}
	
	public void showResult(String string)
	{
		result.setText(string);
	}

	public void resetFields() {
		polinom1TextField.setText("");
		polinom2TextField.setText("");
		polinom1TextField.setEditable(true);
		polinom2TextField.setEditable(true);
		polinom1Radio.setSelected(false);
		polinom2Radio.setSelected(false);
	}

	public void addRadio1ButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		polinom1Radio.setOnAction(eventHandler);
	}
	public void addRadio2ButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		polinom2Radio.setOnAction(eventHandler);
	}

	public boolean radio1IsSelected(){
		if (polinom1Radio.isSelected()) {
			polinom2TextField.setEditable(false);
			polinom1TextField.setEditable(true);
			polinom2TextField.setText("");
		return true;
		}
		else 
			return false;
		
	}
	public boolean radio2IsSelected(){
		if(polinom2Radio.isSelected()) {
			polinom1TextField.setEditable(false);
			polinom2TextField.setEditable(true);
			polinom1TextField.setText("");
			return true;
			}
			else 
				return false;
	}
	
}

