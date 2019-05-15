package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import bll.validator.ClientValidator;
import bll.validator.OrderValidator;
import bll.validator.ProductValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Client;
import model.Product;

/**
 * The Class MainController.
 */
public class MainController {
	
	/** The main view. */
	MainView mainView;
	
	/** The clients list. */
	ObservableList clientsList = FXCollections.observableArrayList();
	
	/** The products list. */
	ObservableList productsList = FXCollections.observableArrayList();
	
	/** The orders list. */
	ObservableList ordersList = FXCollections.observableArrayList();

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 */
	public void start(Stage primaryStage) {
		mainView = new MainView(primaryStage);
		mainView.show();
		clientsList = ClientBLL.showClients();

		mainView.setTableClients(ClientBLL.showClients());
		mainView.setTableProducts(ProductBLL.showProducts());
		mainView.setTableOrders(OrderBLL.showOrders());
		mainView.setTableClientsOrder(ClientBLL.showClients());
		mainView.setTableProductsOrder(ProductBLL.showProducts());
		initializeButtons();
	}

	/**
	 * Initialize buttons.
	 */
	private void initializeButtons() {
		mainView.addAddClientButtonActionListener(e -> {
			Client currentClient = ClientValidator.validate(mainView.clientFields());
			if (currentClient != null) {
				ClientBLL.addClient(currentClient);
				clientsList = ClientBLL.showClients();
				mainView.setTableClients(ClientBLL.showClients());
			} else {
				mainView.displayMessage1("Format gresit!");
			}
		});
		mainView.addDeleteClientButtonActionListener(e -> {
			if (mainView.clientSelected() != false) {
				OrderBLL.deleteOrder(mainView.getSelectedClient());
				ClientBLL.deleteClient(mainView.getSelectedClient().getIdClient());
				clientsList = ClientBLL.showClients();
				mainView.setTableClients(ClientBLL.showClients());
			}
		});
		mainView.addEditClientButtonActionListener(e -> {
			Client currentClient = ClientValidator.validate(mainView.clientFieldsEdit());
			if (currentClient != null && mainView.clientSelected() != false) {
				currentClient.setIdClient(mainView.getSelectedClient().getIdClient());
				ClientBLL.updateClient(currentClient);
				clientsList = ClientBLL.showClients();
				mainView.setTableClients(ClientBLL.showClients());
			} else {
				mainView.displayMessage1("Format gresit!");
			}
		});
		mainView.addEditProductButtonActionListener(e -> {
			Product currentProduct = ProductValidator.validate(mainView.productFieldsEdit());
			if (currentProduct != null && mainView.productSelected() != false) {
				currentProduct.setIdProduct(mainView.getSelectedProduct().getIdProduct());
				ProductBLL.updateProduct(currentProduct);
				productsList = ProductBLL.showProducts();
				mainView.setTableProducts(ProductBLL.showProducts());
			} else {
				mainView.displayMessage2("Format gresit!");
			}
		});
		mainView.addAddProductButtonActionListener(e -> {
			Product currentProduct = ProductValidator.validate(mainView.productFields());
			if (currentProduct != null) {
				ProductBLL.addProduct(currentProduct);
				clientsList = ProductBLL.showProducts();
				mainView.setTableProducts(ProductBLL.showProducts());
			} else {
				mainView.displayMessage2("Format gresit!");
			}
		});

		mainView.addDeleteProductButtonActionListener(e -> {
			if (mainView.productSelected() != false) {
				OrderBLL.deleteOrder(mainView.getSelectedProduct());
				ProductBLL.deleteProduct(mainView.getSelectedProduct().getIdProduct());
				clientsList = ProductBLL.showProducts();
				mainView.setTableProducts(ProductBLL.showProducts());
			}
		});

		mainView.addAddOrderButtonActionListener(e -> {
			if ( mainView.orderSelected() != false&& OrderValidator.validate(mainView.getQuantity(), mainView.getSelectedProductOrder()) != false
					&&  mainView.getQuantity()!=0) {
				OrderBLL.addOrder(mainView.getSelectedClientOrder(), mainView.getSelectedProductOrder(),
						mainView.getQuantity());
				Product newProduct = new Product();
				newProduct.setIdProduct(mainView.getSelectedProductOrder().getIdProduct());
				newProduct.setProductName(mainView.getSelectedProductOrder().getProductName());
				newProduct.setProductPrice(mainView.getSelectedProductOrder().getProductPrice());
				newProduct.setProductQuantity(
						mainView.getSelectedProductOrder().getProductQuantity() - mainView.getQuantity());
				ProductBLL.updateProduct(newProduct);

				ordersList = OrderBLL.showOrders();
				productsList = ProductBLL.showProducts();
				clientsList = ClientBLL.showClients();
				mainView.setTableClientsOrder(ClientBLL.showClients());
				mainView.setTableProductsOrder(ProductBLL.showProducts());
				mainView.setTableOrders(OrderBLL.showOrders());
			} else {
				mainView.displayMessage3("Understock");
			}
		});

	}

}
