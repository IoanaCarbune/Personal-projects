package View;

import java.util.ArrayList;
import java.util.List;

import Controller.MessageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainView {
	private static Stage primaryStage;
	
	private TextField minArrivingTimeTxt=new TextField();
	private TextField maxArrivingTimeTxt=new TextField();
	private TextField minServiceTimeTxt=new TextField();
	private TextField maxServiceTimeTxt=new TextField();
	private TextField numberOfQueuesTxt=new TextField();
	private TextField simulationTimeTxt=new TextField();
	
	private TextField averageWaitingTimeTxt=new TextField();
	private TextField averageServiceTimeTxt=new TextField();
	private TextField averageEmptyQueueTimeTxt=new TextField();
	private TextField peakHourTxt=new TextField();
	private TextArea logEventsTxt=new TextArea();
	
	private Label avgWaitingTime=new Label("Avg. waiting time ");
	private Label avgServiceTime=new Label("Avg. service time ");
	private Label avgEmptyWTime=new Label("Avg. empty queue time ");
	private Label peakHour=new Label("Peak hour ");
	
	private Button startButton=new Button("Start");
	
	private VBox qVBox=new VBox(20);
	private List <HBox> queuesList=new ArrayList<HBox>();
	private Label errorLabel=new Label();
	public MainView(Stage primaryStage2) {
		primaryStage=primaryStage2;
		BorderPane mainLayout=new BorderPane();
		Scene scene= new Scene(mainLayout,1500,500);
		
		minArrivingTimeTxt.setPromptText("Min arriving time");
		maxArrivingTimeTxt.setPromptText("Max arriving time");
		minServiceTimeTxt.setPromptText("Min service time");
		maxServiceTimeTxt.setPromptText("Max service time");
		numberOfQueuesTxt.setPromptText("Nr of queues");
		simulationTimeTxt.setPromptText("Simulation time");
		
		averageWaitingTimeTxt.setPrefSize(200, 30);
		averageServiceTimeTxt.setPrefSize(200, 30);
		averageEmptyQueueTimeTxt.setPrefSize(200, 30);
		peakHourTxt.setPrefSize(200, 30);
		
		averageWaitingTimeTxt.setEditable(false);
		averageServiceTimeTxt.setEditable(false);
		averageEmptyQueueTimeTxt.setEditable(false);
		peakHourTxt.setEditable(false);
		logEventsTxt.setEditable(false);
		
		VBox arrivingVbox=new VBox(10);
		arrivingVbox.setAlignment(Pos.CENTER);
		arrivingVbox.getChildren().addAll(minArrivingTimeTxt, maxArrivingTimeTxt);
		
		VBox serviceVBox=new VBox(10);
		serviceVBox.setAlignment(Pos.CENTER);
		serviceVBox.setPadding(new Insets(7,0,0,0));
		serviceVBox.getChildren().addAll(errorLabel,minServiceTimeTxt,maxServiceTimeTxt,startButton);
		
		VBox simulationVBox=new VBox(10);
		simulationVBox.setAlignment(Pos.CENTER);
		simulationVBox.setPadding(new Insets(0,225,0,0));
		simulationVBox.getChildren().addAll(numberOfQueuesTxt,simulationTimeTxt);
		
		VBox labelVBox=new VBox(25);
		labelVBox.setAlignment(Pos.TOP_LEFT);
		labelVBox.setPadding(new Insets(10,0,10,-70));
		labelVBox.getChildren().addAll(avgWaitingTime,avgServiceTime,avgEmptyWTime,peakHour);
		
		VBox dataVBox=new VBox(10);
		dataVBox.setAlignment(Pos.CENTER);
		dataVBox.setPadding(new Insets(10,0,10,0));
		dataVBox.getChildren().addAll(averageWaitingTimeTxt,averageServiceTimeTxt,averageEmptyQueueTimeTxt,peakHourTxt);
		
		HBox downHBox=new HBox(10);
		downHBox.setAlignment(Pos.CENTER);
		downHBox.getChildren().addAll(arrivingVbox,serviceVBox,simulationVBox,labelVBox,dataVBox);
				
		HBox upHbox=new HBox(10);
		upHbox.setAlignment(Pos.CENTER);
		
		qVBox.minHeightProperty().bind(logEventsTxt.heightProperty());
		qVBox.minWidthProperty().bind(logEventsTxt.widthProperty());
		qVBox.setAlignment(Pos.CENTER_LEFT);
		upHbox.getChildren().addAll(qVBox,logEventsTxt);
		
		mainLayout.setCenter(upHbox);
		mainLayout.setBottom(downHBox);
		primaryStage.setTitle("aaa ");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
	}

	public void show() {
		primaryStage.show();
		
	}
	
	public void addStartButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		startButton.setOnAction(eventHandler);
	}
	
	public String getMinimArrivingTime() {
		return minArrivingTimeTxt.getText();
	}
	
	public String getMaximArrivingTime() {
		return maxArrivingTimeTxt.getText();
	}
	
	public String getMinimServiceTime() {
		return minServiceTimeTxt.getText();
	}
	
	public String getMaximServiceTime() {
		return maxServiceTimeTxt.getText();
	}
	
	public String getNumberOfQueues() {
		return numberOfQueuesTxt.getText();
	}
	
	public String getSimulationTime() {
		return simulationTimeTxt.getText();
	}

	public void creatAnimationSpace(Integer noOfQueues) {
		//qVBox=new VBox(20);
		qVBox.getChildren().clear();
		 queuesList=new ArrayList<HBox>();
		for(int i=0;i<noOfQueues;i++) {
			HBox currentHBox=new HBox(5);
			Label cashierLabel=new Label();
			Image image = new Image(getClass().getResourceAsStream("cashier.png"));
			ImageView imageView=new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			cashierLabel.setGraphic(imageView);
			currentHBox.getChildren().add(cashierLabel);
			queuesList.add(currentHBox);
		}
		qVBox.getChildren().addAll(queuesList);
		}

	public void addCustomerIcon(Integer qNumber) {
		Image image = new Image(getClass().getResourceAsStream("shopper.png"));
		ImageView imageView=new ImageView(image);
		Label customerLabel=new Label();
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		customerLabel.setGraphic(imageView);
		queuesList.get(qNumber).getChildren().add(customerLabel);
	}
	
	public void displayLogEventMessage(String string)
	{
		logEventsTxt.appendText(string);
	}

	public void removeCustomerIcon(int index) {
		if(queuesList.get(index).getChildren().size()!=1)
				queuesList.get(index).getChildren().remove(1);
	}
	
	
	public void setAverageWaitingTimeTxt(String averageWaitingTimeTxt) {
		this.averageWaitingTimeTxt.setText(averageWaitingTimeTxt);
	}

	public void setAverageServiceTimeTxt(String averageServiceTimeTxt) {
		this.averageServiceTimeTxt .setText(averageServiceTimeTxt);
	}

	public void setAverageEmptyQueueTimeTxt(String averageEmptyQueueTimeTxt) {
		this.averageEmptyQueueTimeTxt .setText(averageEmptyQueueTimeTxt);
	}

	public void setPeakHourTxt(String peakHourTxt) {
		this.peakHourTxt .setText( peakHourTxt);
	}

	public void setAvgEmptyWTime(String avgEmptyWTime) {
		this.averageEmptyQueueTimeTxt.setText(avgEmptyWTime);
	}
	
	public void errorLabel(String str) {
		MessageController.displayMessage(str, errorLabel, Color.RED);
	}

	public void emptyLog() {
		logEventsTxt.clear();
	}
}
