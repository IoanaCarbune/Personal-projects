package presentation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business.MenuItem;
import business.Order;
import business.Restaurant;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class WaiterGUI {
	private static Stage waiterStage;
	private TextField tableTxt = new TextField();
	private TableView<Order> orderTable = new TableView<Order>();
	private TableView<MenuItem> menuTable = new TableView<MenuItem>();
	private Button generateBillBtn = new Button("Generate Bill");
	private Button addProductBtn = new Button("Add product");
	private Button addOrderBtn = new Button("Add order");
	private Restaurant restaurant;
	private List<MenuItem> selectedItems = new ArrayList<MenuItem>();

	public WaiterGUI(Stage waiterStage2, Restaurant restaurant) {
		this.waiterStage = waiterStage2;
		this.restaurant = restaurant;
		BorderPane waiterLayout = new BorderPane();
		Scene scene = new Scene(waiterLayout, 750, 500);
		TableColumn<Order, Integer> orderIdCol = new TableColumn<Order, Integer>("Id");
		TableColumn<Order, Integer> orderTableCol = new TableColumn<Order, Integer>("Table");
		orderIdCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
		orderTableCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("table"));
		orderTable.getColumns().addAll(orderIdCol, orderTableCol);
		orderTable.setItems(FXCollections.observableArrayList(restaurant.getOrders().keySet()));
		orderTable.setEditable(true);

		TableColumn<MenuItem, String> itemNameCol = new TableColumn<MenuItem, String>("Name");
		TableColumn<MenuItem, String> itemPriceCol = new TableColumn<MenuItem, String>("Price");
		itemNameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("name"));
		itemPriceCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<MenuItem, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MenuItem, String> param) {
						return new SimpleStringProperty(param.getValue().computePrice().toString());
					}
				});
		menuTable.getColumns().addAll(itemNameCol, itemPriceCol);
		menuTable.setItems(FXCollections.observableArrayList(restaurant.getMenu()));
		menuTable.setEditable(true);

		orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		menuTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableTxt.setPromptText("Table");
		VBox billVBox = new VBox(10);
		billVBox.getChildren().addAll(orderTable, generateBillBtn);
		VBox orderVBox = new VBox(10);
		orderVBox.getChildren().addAll(menuTable, addProductBtn, tableTxt, addOrderBtn);
		HBox bigHBox = new HBox(10);
		bigHBox.setAlignment(Pos.CENTER);
		bigHBox.getChildren().addAll(billVBox, new Separator(Orientation.VERTICAL), orderVBox);
		waiterLayout.setCenter(bigHBox);
		waiterStage.setResizable(false);
		waiterStage.setScene(scene);
		waiterStage.setTitle("Waiter window");
		waiterStage.show();
		initialize();
	}

	private void initialize() {
		addOrderBtn.setOnAction(e -> {
			try {
				Format dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
				restaurant.createOrder(selectedItems, tableTxt.getText(), dateFormat.format(new Date()));
				selectedItems = new ArrayList<MenuItem>();
				orderTable.setItems(FXCollections.observableArrayList(restaurant.getOrders().keySet()));
			} catch (AssertionError e2) {

			}
		});
		addProductBtn.setOnAction(e -> {
			try {
				if (!menuTable.getSelectionModel().isEmpty()) {
					selectedItems.add(menuTable.getSelectionModel().getSelectedItem());
				}
			} catch (AssertionError e2) {

			}

		});
		generateBillBtn.setOnAction(e->{
			try {
				if (!orderTable.getSelectionModel().isEmpty()) {
					
					restaurant.generateBill(orderTable.getSelectionModel().getSelectedItem());
					
				}
				orderTable.setItems(FXCollections.observableArrayList(restaurant.getOrders().keySet()));
			} catch (AssertionError e2) {

			}
		});
	}

}
