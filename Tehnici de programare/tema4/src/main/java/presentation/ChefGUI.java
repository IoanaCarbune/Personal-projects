package presentation;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import business.MenuItem;
import business.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChefGUI implements Observer {
	private static Stage chefStage;
	private TableView<MenuItem> ordersTable=new TableView<MenuItem>();
	private Button executeOrder=new Button("Execute order");
	private Restaurant restaurant;
	private ObservableList<MenuItem> items=FXCollections.observableArrayList();
	public ChefGUI(Stage chefStage2,Restaurant restaurant) {
		this.restaurant=restaurant;
		this.chefStage=chefStage2;
		BorderPane chefLayout=new BorderPane();
		Scene scene=new Scene(chefLayout,500,500);
		TableColumn<MenuItem, String> menuItemName = new TableColumn<MenuItem, String>("Name");
		menuItemName.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("name"));
		ordersTable.getColumns().add(menuItemName);
		getOrderedItems();
		ordersTable.setEditable(true);
		ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		VBox orderVBox=new VBox(5);
		orderVBox.getChildren().addAll(ordersTable,executeOrder);
		orderVBox.setAlignment(Pos.CENTER);
		
		chefLayout.setCenter(orderVBox);
		chefStage.setResizable(false);
		chefStage.setScene(scene);
		chefStage.setTitle("Chef window");
		chefStage.show();
		initialize();
	}
	private void initialize() {
		chefStage.setOnCloseRequest(e->{
			restaurant.deleteObserver(this);
		});
		executeOrder.setOnAction(e->{
			if(!ordersTable.getSelectionModel().isEmpty()) {
				items.remove(ordersTable.getSelectionModel().getSelectedItem());
			}
		});
	}
	@Override
	public void update(Observable o, Object arg) {
		getOrderedItems();
		ordersTable.refresh();
	}

	private void getOrderedItems() {
		items=FXCollections.observableArrayList();
		for(List<MenuItem> order:restaurant.getOrders().values()) {
			
			for(MenuItem item:order) {
				items.add(item);
			}
		}
		ordersTable.setItems(items);
		
	}

}
