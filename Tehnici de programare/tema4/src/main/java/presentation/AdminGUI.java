package presentation;

import java.util.ArrayList;

import business.MenuItem;
import business.Restaurant;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminGUI {

	private static Stage adminStage;
	private TextField addNameTxt = new TextField();
	private TextField addPriceTxt = new TextField();
	private TextField operateNameTxt = new TextField();
	private TextField operatePriceTxt = new TextField();
	private TableView<MenuItem> itemComponentTable = new TableView<MenuItem>();
	private TableView<MenuItem> menuTable = new TableView<MenuItem>();
	private Button addNewItemButton = new Button("Add product");
	private Button deleteItemButton = new Button("Delete product");
	private Button editItemButton = new Button("Edit product");
	private Button addBaseItemButton = new Button("Add component");
	private ToggleGroup group = new ToggleGroup();
	private RadioButton rb1 = new RadioButton("Base product");
	private RadioButton rb2 = new RadioButton("Composed product");
	private Restaurant restaurant;
	private MenuItem item;
	private ObservableList<MenuItem> baseProductsList = FXCollections.observableArrayList();

	public AdminGUI(Stage adminStage2, Restaurant restaurant) {
		this.adminStage = adminStage2;
		this.restaurant = restaurant;
		BorderPane adminLayout = new BorderPane();
		Scene scene = new Scene(adminLayout, 750, 500);

		rb1.setToggleGroup(group);
		rb1.setSelected(false);
		rb2.setUserData("RadioButton2");
		rb2.setToggleGroup(group);

		addNameTxt.setPromptText("Name");
		addPriceTxt.setPromptText("Price");
		operateNameTxt.setPromptText("Name");
		operatePriceTxt.setPromptText("Price");

		itemComponentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<MenuItem, String> componentNameCol = new TableColumn<MenuItem, String>("Name");
		TableColumn<MenuItem, String> componentPriceCol = new TableColumn<MenuItem, String>("Price");
		componentNameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("name"));
		componentPriceCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<MenuItem, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<MenuItem, String> param) {
						// if(param.getValue() instanceof CompositeProduct) {
						return new SimpleStringProperty(param.getValue().computePrice().toString());
						// }
//				else return addPriceTxt.getText();
					}
				});
		itemComponentTable.getColumns().addAll(componentNameCol, componentPriceCol);
		itemComponentTable.setItems(baseProductsList);

		TableColumn<MenuItem, String> itemNameCol = new TableColumn<MenuItem, String>("Name");
		TableColumn<MenuItem, String> itemPriceCol = new TableColumn<MenuItem, String>("Price");
		itemNameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("name"));
		itemPriceCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<MenuItem, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<MenuItem, String> param) {
						// if(param.getValue() instanceof CompositeProduct) {
						return new SimpleStringProperty(param.getValue().computePrice().toString());
						// }
//				else return addPriceTxt.getText();
					}
				});
		menuTable.setEditable(true);
		menuTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		menuTable.getColumns().addAll(itemNameCol, itemPriceCol);
		menuTable.setItems(FXCollections.observableArrayList(restaurant.getMenu()));

		VBox bigVBox = new VBox(5);
		// bigVBox.setAlignment(Pos.CENTER);
		bigVBox.getChildren().addAll(rb1, rb2, addNameTxt, addPriceTxt, itemComponentTable, addNewItemButton,
				new Separator(Orientation.HORIZONTAL), operateNameTxt, operatePriceTxt, addBaseItemButton,
				editItemButton, deleteItemButton);
		HBox mainHBox = new HBox(10);
		mainHBox.setAlignment(Pos.CENTER);
		mainHBox.getChildren().addAll(bigVBox, new Separator(Orientation.VERTICAL), menuTable);
		adminLayout.setCenter(mainHBox);
		adminStage.setResizable(false);
		adminStage.setScene(scene);
		adminStage.setTitle("Admin window");
		adminStage.show();
		initialize();
	}

	private void initialize() {
		rb1.setOnAction(e -> {
			if (rb1.isSelected()) {
				itemComponentTable.setVisible(false);
				addBaseItemButton.setVisible(false);
				addPriceTxt.setDisable(false);
			}
		});
		rb2.setOnAction(e -> {
			if (rb2.isSelected()) {
				itemComponentTable.setVisible(true);
				addBaseItemButton.setVisible(true);
				addPriceTxt.setDisable(true);
			}
		});
		menuTable.setOnMouseClicked(e -> {
			if (!menuTable.getSelectionModel().isEmpty()) {
				item = menuTable.getSelectionModel().getSelectedItem();
				operateNameTxt.setText(item.getName());
				operatePriceTxt.setText(item.getPrice().toString());
			}
		});
		addNewItemButton.setOnAction(e -> {
			try {
				if (rb1.isSelected()) {
					ObservableList<MenuItem> components = FXCollections.observableArrayList();
					restaurant.createMenuItem(addNameTxt.getText(), addPriceTxt.getText(), components);
				} else {
					restaurant.createMenuItem(addNameTxt.getText(), addPriceTxt.getText(), new ArrayList<MenuItem>(baseProductsList));
				}
				menuTable.setItems(FXCollections.observableArrayList(restaurant.getMenu()));
			} catch (AssertionError e2) {

			}
		});
		addBaseItemButton.setOnAction(e -> {
			baseProductsList.add(item);
		});
		editItemButton.setOnAction(e -> {
			try {
			if (!menuTable.getSelectionModel().isEmpty()) {
				restaurant.editMenuItem(menuTable.getSelectionModel().getSelectedItem(), operateNameTxt.getText(),
						operatePriceTxt.getText());
			}
			menuTable.refresh();
			}catch(AssertionError e2){
				
			}
		});
		deleteItemButton.setOnAction(e -> {
			try {
			if (!menuTable.getSelectionModel().isEmpty()) {
				restaurant.deleteMenuItem(menuTable.getSelectionModel().getSelectedItem());
			}
			menuTable.setItems(FXCollections.observableArrayList(restaurant.getMenu()));
			}catch(AssertionError e2){
				
			}
			});
	}

}
