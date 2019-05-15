package presentation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Client;
import model.Orderr;
import model.Product;

/**
 * The Class MainView.
 */
public class MainView {
	
	/** The primary stage. */
	private static Stage primaryStage;

	/** The client first name. */
	private TextField clientFirstName=new TextField();
	
	/** The client last name. */
	private TextField clientLastName=new TextField();
	
	/** The client address. */
	private TextField clientAddress=new TextField();
	
	/** The client phone number. */
	private TextField clientPhoneNumber=new TextField();
	
	/** The product name. */
	private TextField productName=new TextField();
	
	/** The product price. */
	private TextField productPrice=new TextField();
	
	/** The product quantity. */
	private TextField productQuantity=new TextField();
	
	/** The product quantity order. */
	private TextField productQuantityOrder=new TextField();

	/** The add client. */
	private Button addClient=new Button("Add");
	
	/** The add product. */
	private Button addProduct=new Button("Add");
	
	/** The add order. */
	private Button addOrder=new Button("Add");
	
	/** The table clients. */
	private TableView<Object> tableClients=new TableView<Object>();
	
	/** The table products. */
	private TableView<Object> tableProducts=new TableView<Object>();
	
	/** The table orders. */
	private TableView<Object> tableOrders=new TableView<Object>();
	
	/** The table clients order. */
	private TableView<Object> tableClientsOrder=new TableView<Object>();
	
	/** The table products order. */
	private TableView<Object> tableProductsOrder=new TableView<Object>();
	
	/** The clients list. */
	private List<Object> clientsList=new ArrayList<Object>();
	
	/** The products list. */
	private List<Object> productsList=new ArrayList<Object>();
	
	/** The orders list. */
	private List<Object> ordersList=new ArrayList<Object>();
	
	/** The client first name search. */
	private TextField clientFirstNameSearch=new TextField();
	
	/** The client last name search. */
	private TextField clientLastNameSearch=new TextField();
	
	/** The edit client. */
	private Button editClient=new Button("Edit");
	
	/** The edit product. */
	private Button editProduct=new Button("Edit");
	
	/** The delete client. */
	private Button deleteClient=new Button("Delete");
	
	/** The delete product. */
	private Button deleteProduct=new Button("Delete");
	
	/** The client first name edit. */
	private TextField clientFirstNameEdit=new TextField();
	
	/** The client last name edit. */
	private TextField clientLastNameEdit=new TextField();
	
	/** The client address edit. */
	private TextField clientAddressEdit=new TextField();
	
	/** The client phone number edit. */
	private TextField clientPhoneNumberEdit=new TextField();
	
	/** The product name edit. */
	private TextField productNameEdit=new TextField();
	
	/** The product price edit. */
	private TextField productPriceEdit=new TextField();
	
	/** The product quantity edit. */
	private TextField productQuantityEdit=new TextField();
	
	/** The error label 1. */
	private Label errorLabel1=new Label(" ");
	
	/** The error label 2. */
	private Label errorLabel2=new Label(" ");
	
	/** The error label 3. */
	private Label errorLabel3=new Label(" ");
	
	/**
	 * Instantiates a new main view.
	 *
	 * @param primaryStage2 the primary stage 2
	 */
	public MainView(Stage primaryStage2) {
		ConnectionFactory.getConnection();
		primaryStage=primaryStage2;
		BorderPane mainLayout=new BorderPane();
		TabPane tabLayout=new TabPane();
		tabLayout.setPadding(new Insets(10));
		Scene scene=new Scene(mainLayout,1700,500);
		Tab tab1=new Tab("Clients");
		Tab tab2=new Tab("Products");
		Tab tab3=new Tab("Orders");
		
		tab1.setClosable(false);
		tab2.setClosable(false);
		tab3.setClosable(false);
	
		
		clientFirstName.setPromptText("First name");
		clientLastName.setPromptText("Last name");
		clientAddress.setPromptText("Address");
		clientPhoneNumber.setPromptText("Phone");
		
		productName.setPromptText("Product name");
		productPrice.setPromptText("Product price");
		productQuantity.setPromptText("Quantity");
		
		
		productQuantityOrder.setPromptText("Product quantity");
	
		
		clientFirstNameEdit.setPromptText("Client first name");
	    clientLastNameEdit.setPromptText("Client last name");
		clientAddressEdit.setPromptText("Address");
		clientPhoneNumberEdit.setPromptText("Phone");
		
		productNameEdit.setPromptText("Product name");
		productPriceEdit.setPromptText("Product price");
		productQuantityEdit.setPromptText("Quantity");
		
		clientFirstNameSearch.setPromptText("Client first name");
		clientLastNameSearch.setPromptText("Client last name");
		
		HBox tab1HBox=new HBox(50);
		VBox addClientVBox=new VBox(10);
		addClientVBox.getChildren().addAll(new Label("Add"),clientFirstName,clientLastName,clientAddress,clientPhoneNumber,addClient,errorLabel1);
		clientsList.add(new Client());
		tableClients=createTable(clientsList);
		tableClients.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY);
		tableClients.setMinWidth(600);
		clientTableToFields();
		
		VBox editClientVBox=new VBox(10);
		editClientVBox.getChildren().addAll(new Label("Edit"), clientFirstNameEdit,clientLastNameEdit,clientAddressEdit,clientPhoneNumberEdit,editClient,deleteClient);
		
		VBox viewClientVBox=new VBox(10);
		viewClientVBox.getChildren().addAll(new Label("View"),tableClients);
		tab1HBox.getChildren().addAll(addClientVBox, new Separator(Orientation.VERTICAL),editClientVBox, new Separator(Orientation.VERTICAL),viewClientVBox);
		
		HBox tab2HBox=new HBox(50);
		VBox addProductVBox=new VBox(10);
		addProductVBox.getChildren().addAll(new Label("Add"),productName,productPrice,productQuantity,addProduct,errorLabel2);
		productsList.add(new Product());
		tableProducts=createTable(productsList);
		tableProducts.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY);
		tableProducts.setMinWidth(600);
		productTableToFields();

		VBox editProductVBox=new VBox(10);
		editProductVBox.getChildren().addAll(new Label("Edit"), productNameEdit,productPriceEdit,productQuantityEdit,editProduct,deleteProduct);
		
		VBox viewProductsVBox=new VBox(10);
		viewProductsVBox.getChildren().addAll(new Label("View"),tableProducts);
		tab2HBox.getChildren().addAll(addProductVBox, new Separator(Orientation.VERTICAL),editProductVBox, new Separator(Orientation.VERTICAL),viewProductsVBox);
		
		HBox tab3HBox=new HBox(50);
		VBox addOrderVBox=new VBox(10);
		addOrderVBox.getChildren().addAll(new Label("Add"),productQuantityOrder,addOrder, errorLabel3);
		ordersList.add(new Orderr());
		tableOrders=createTable(ordersList);
		tableOrders.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY);
		tableOrders.setMinWidth(500);
		
		tableClientsOrder=createTable(clientsList);
		tableClientsOrder.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY);
		tableClientsOrder.setMinWidth(500);
		tableProductsOrder=createTable(productsList);
		tableProductsOrder.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY);
		tableProductsOrder.setMinWidth(350);
	
		
		VBox viewOrdersVBox=new VBox(10);
		viewOrdersVBox.getChildren().addAll(new Label("View"),tableOrders);
		tab3HBox.getChildren().addAll(addOrderVBox,tableClientsOrder,tableProductsOrder, new Separator(Orientation.VERTICAL),viewOrdersVBox);
		
		tab1.setContent(tab1HBox);
		tab2.setContent(tab2HBox);
		tab3.setContent(tab3HBox);
		tabLayout.getTabs().addAll(tab1,tab2,tab3);
		
		mainLayout.setCenter(tabLayout);
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
	}

	

	/**
	 * Show.
	 */
	public void show() {
		primaryStage.show();
	}
	
	/**
	 * Creates the table.
	 *
	 * @param objects the objects
	 * @return the table view
	 */
	public TableView<Object> createTable(List<Object> objects) {
		TableView<Object> table=new TableView<Object>();
		int index=0;
		for(Object object:objects) {
			if(index==0) {
			for(Field field:object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				TableColumn<Object,Object> tableCol=new TableColumn<Object,Object>(field.getName());
				tableCol.setCellValueFactory(new PropertyValueFactory<Object,Object>(field.getName()));
				table.getColumns().add(tableCol);
				
			}
			}else {
			table.getItems().add(objects);
			}
			index++;
		}
		return table;
	}

	/**
	 * Gets the clients list.
	 *
	 * @return the clients list
	 */
	public List<Object> getClientsList() {
		return clientsList;
	}

	/**
	 * Sets the clients list.
	 *
	 * @param clientsList the new clients list
	 */
	public void setClientsList(List<Object> clientsList) {
		this.clientsList = clientsList;
	}

	/**
	 * Sets the table clients.
	 *
	 * @param objects the new table clients
	 */
	public void setTableClients(ObservableList objects) {
		this.tableClients.setItems(objects);
	}

	/**
	 * Client table to fields.
	 */
	public void clientTableToFields() { 
		tableClients.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if(tableClients.getSelectionModel().getSelectedItem()!=null) {
					Client selectedClient= (Client) tableClients.getSelectionModel().getSelectedItem();
					clientFirstNameEdit.setText(selectedClient.getFirstName());
					clientLastNameEdit.setText(selectedClient.getLastName());
					clientAddressEdit.setText(selectedClient.getAddress());
					clientPhoneNumberEdit.setText(selectedClient.getPhoneNumber());
				}
				
			}
		});

	}
	
	/**
	 * Product table to fields.
	 */
	public void productTableToFields() { 
		tableProducts.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if(tableProducts.getSelectionModel().getSelectedItem()!=null) {
					Product selectedProduct= (Product) tableProducts.getSelectionModel().getSelectedItem();
					productNameEdit.setText(selectedProduct.getProductName());
					productPriceEdit.setText(String.valueOf(selectedProduct.getProductPrice()));
					productQuantityEdit.setText(String.valueOf(selectedProduct.getProductQuantity()));
				}
				
			}
		});

	}
	

	/**
	 * Adds the delete client button action listener.
	 *
	 * @param eventHandler the event handler
	 */
	public void addDeleteClientButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		deleteClient.setOnAction(eventHandler);
	}

	/**
	 * Adds the edit client button action listener.
	 *
	 * @param eventHandler the event handler
	 */
	public void addEditClientButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		editClient.setOnAction(eventHandler);
	}
	
	/**
	 * Adds the add client button action listener.
	 *
	 * @param eventHandler the event handler
	 */
	public void addAddClientButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		addClient.setOnAction(eventHandler);
	}
	
	/**
	 * Adds the delete product button action listener.
	 *
	 * @param eventHandler the event handler
	 */
	public void addDeleteProductButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		deleteProduct.setOnAction(eventHandler);
	}

	/**
	 * Adds the edit product button action listener.
	 *
	 * @param eventHandler the event handler
	 */
	public void addEditProductButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		editProduct.setOnAction(eventHandler);
	}
	
	/**
	 * Adds the add product button action listener.
	 *
	 * @param eventHandler the event handler
	 */
	public void addAddProductButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		addProduct.setOnAction(eventHandler);
	}
	
	/**
	 * Adds the add order button action listener.
	 *
	 * @param eventHandler the event handler
	 */
	public void addAddOrderButtonActionListener(EventHandler<ActionEvent> eventHandler) {
		addOrder.setOnAction(eventHandler);
	}


	/**
	 * Gets the selected client.
	 *
	 * @return the selected client
	 */
	public Client getSelectedClient() {
		
		if(tableClients.getSelectionModel().getSelectedItem()!=null) {
			 Client selectedClient= (Client) tableClients.getSelectionModel().getSelectedItem();
			selectedClient.setAddress(clientAddressEdit.getText());
			selectedClient.setLastName(clientLastNameEdit.getText());
			selectedClient.setFirstName(clientFirstNameEdit.getText());
			selectedClient.setPhoneNumber(clientPhoneNumberEdit.getText());
			System.out.println(selectedClient.getIdClient());
			return selectedClient;
		}
		
		return null;
		
	}
	
	/**
	 * Gets the selected product.
	 *
	 * @return the selected product
	 */
	public Product getSelectedProduct() {
		if(tableProducts.getSelectionModel().getSelectedItem()!=null) {
			Product selectedProduct= (Product) tableProducts.getSelectionModel().getSelectedItem();
			selectedProduct.setProductName(productNameEdit.getText());
			selectedProduct.setProductQuantity(Integer.valueOf(productQuantityEdit.getText()));
			selectedProduct.setProductPrice(Double.valueOf(productQuantityEdit.getText()));
			
			return selectedProduct;
		}
		
		return null;
		
	}



	/**
	 * Sets the table products.
	 *
	 * @param showProducts the new table products
	 */
	public void setTableProducts(ObservableList showProducts) {
		this.tableProducts.setItems(showProducts);	
	}



	/**
	 * Sets the table orders.
	 *
	 * @param showOrders the new table orders
	 */
	public void setTableOrders(ObservableList showOrders) {
		this.tableOrders.setItems(showOrders);	
	}

	/**
	 * Sets the table clients order.
	 *
	 * @param showOrders the new table clients order
	 */
	public void setTableClientsOrder(ObservableList showOrders) {
		this.tableClientsOrder.setItems(showOrders);	
	}
	
	/**
	 * Sets the table products order.
	 *
	 * @param showOrders the new table products order
	 */
	public void setTableProductsOrder(ObservableList showOrders) {
		this.tableProductsOrder.setItems(showOrders);	
	}


	/**
	 * Gets the selected product order.
	 *
	 * @return the selected product order
	 */
	public Product getSelectedProductOrder() {
		if(tableProductsOrder.getSelectionModel().getSelectedItem()!=null) {
			Product selectedProduct= (Product) tableProductsOrder.getSelectionModel().getSelectedItem();
			return selectedProduct;
		}
		
		return null;
		
	}
	
	/**
	 * Gets the selected client order.
	 *
	 * @return the selected client order
	 */
	public Client getSelectedClientOrder() {
		
		if(tableClientsOrder.getSelectionModel().getSelectedItem()!=null) {
			Client selectedClient= (Client) tableClientsOrder.getSelectionModel().getSelectedItem();
			return selectedClient;
		}
		
		return null;
		
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Integer getQuantity() {
		if(productQuantityOrder.getText().length()==0)
			return 0;
		else
			return Integer.valueOf(productQuantityOrder.getText());
	}
	
	/**
	 * Client fields.
	 *
	 * @return the list
	 */
	public List<String> clientFields(){
		List<String> clientFields=new ArrayList<String>();
		clientFields.add(clientFirstName.getText());
		clientFields.add(clientLastName.getText());
		clientFields.add(clientAddress.getText());
		clientFields.add(clientPhoneNumber.getText());
		return clientFields;
	}
	
	/**
	 * Product fields.
	 *
	 * @return the list
	 */
	public List<String> productFields(){
		List<String> productFields=new ArrayList<String>();
		productFields.add(productName.getText());
		productFields.add(productPrice.getText());
		productFields.add(productQuantity.getText());
		return productFields;
	}

	/**
	 * Client fields edit.
	 *
	 * @return the list
	 */
	public List<String> clientFieldsEdit(){
		List<String> clientFields=new ArrayList<String>();
		clientFields.add(clientFirstNameEdit.getText());
		clientFields.add(clientLastNameEdit.getText());
		clientFields.add(clientAddressEdit.getText());
		clientFields.add(clientPhoneNumberEdit.getText());
		return clientFields;
	}
	
	/**
	 * Product fields edit.
	 *
	 * @return the list
	 */
	public List<String> productFieldsEdit(){
		List<String> productFields=new ArrayList<String>();
		productFields.add(productNameEdit.getText());
		productFields.add(productPriceEdit.getText());
		productFields.add(productQuantityEdit.getText());
		return productFields;
	}
	
	/**
	 * Client selected.
	 *
	 * @return the boolean
	 */
	public Boolean clientSelected() {
		if(tableClients.getSelectionModel().getSelectedItem()!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Product selected.
	 *
	 * @return the boolean
	 */
	public Boolean productSelected() {
		if(tableProducts.getSelectionModel().getSelectedItem()!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Order selected.
	 *
	 * @return the boolean
	 */
	public Boolean orderSelected() {
		if(tableClientsOrder.getSelectionModel().getSelectedItem()!=null && tableProductsOrder.getSelectionModel().getSelectedItem()!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Display message 1.
	 *
	 * @param msg the msg
	 */
	public void displayMessage1(String msg) {
		MessageController.displayMessage(msg,errorLabel1,Color.RED);
	}
	
	/**
	 * Display message 2.
	 *
	 * @param msg the msg
	 */
	public void displayMessage2(String msg) {
		MessageController.displayMessage(msg,errorLabel2,Color.RED);
	}
	
	/**
	 * Display message 3.
	 *
	 * @param msg the msg
	 */
	public void displayMessage3(String msg) {
		MessageController.displayMessage(msg,errorLabel3,Color.RED);
	}
	
}
