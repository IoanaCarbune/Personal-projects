package presentation;

import business.Restaurant;
import data.RestaurantSerializator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MainController {
		
	private static Stage primaryStage;
	private static Stage adminStage=new Stage();
	private static Stage waiterStage=new Stage();
	private static Stage chefStage=new Stage();
	private Button adminButton=new Button("Administrator");
	private Button waiterButton=new Button("Chelner");
	private Button chefButton=new Button("Bucatar");
	
	private Restaurant restaurant=new Restaurant();
	
	public MainController(Stage primaryStage2) {
		primaryStage=primaryStage2;
		restaurant=RestaurantSerializator.deserialization();
		BorderPane mainLayout=new BorderPane();
		Scene scene=new Scene(mainLayout,500,500);
		
		adminButton.setPrefSize(100, 100);
		waiterButton.setPrefSize(100, 100);
		chefButton.setPrefSize(100, 100);
		
		HBox buttonsHbox=new HBox(10);
		buttonsHbox.setAlignment(Pos.CENTER);
		buttonsHbox.getChildren().addAll(adminButton,waiterButton,chefButton);
		
		mainLayout.setCenter(buttonsHbox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Restaurant");
		primaryStage.setResizable(false);
		primaryStage.show();
		initializeButtons();
	}

	private void initializeButtons() {
		addAdminButtonActionListener(e->{
			AdminGUI admin=new AdminGUI(adminStage, restaurant);
		});
		
		addWaiterButtonActionListener(e->{
			WaiterGUI waiter=new WaiterGUI(waiterStage,restaurant);
		});
		
		addChefButtonActionListener(e->{
			ChefGUI chef=new ChefGUI(chefStage,restaurant);
			restaurant.addObserver(chef);
		});
		primaryStage.setOnCloseRequest(e->{
			RestaurantSerializator.serialization(restaurant);
		});
	}

	private void addAdminButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		adminButton.setOnAction(eventHandler);
	}
	
	private void addWaiterButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		waiterButton.setOnAction(eventHandler);
	}
	
	private void addChefButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		chefButton.setOnAction(eventHandler);
	}
	
	
}
