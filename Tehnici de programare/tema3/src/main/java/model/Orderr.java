package model;

/**
 * The Class Orderr.
 */
public class Orderr {
	
	/** The id order. */
	private Integer idOrder;
	
	/** The id client. */
	private Integer idClient;
	
	/** The client first name. */
	private String clientFirstName;
	
	/** The client last name. */
	private String clientLastName;
	
	/** The id product. */
	private Integer idProduct;
	
	/** The product name. */
	private String productName;
	
	/** The quantity. */
	private Integer quantity;
	
	/** The total order price. */
	private Double totalOrderPrice;
	
	/**
	 * Gets the id order.
	 *
	 * @return the id order
	 */
	public Integer getIdOrder() {
		return idOrder;
	}
	
	/**
	 * Sets the id order.
	 *
	 * @param idOrder the new id order
	 */
	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}
	
	/**
	 * Gets the id client.
	 *
	 * @return the id client
	 */
	public Integer getIdClient() {
		return idClient;
	}
	
	/**
	 * Sets the id client.
	 *
	 * @param idClient the new id client
	 */
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	
	/**
	 * Gets the id product.
	 *
	 * @return the id product
	 */
	public Integer getIdProduct() {
		return idProduct;
	}
	
	/**
	 * Sets the id product.
	 *
	 * @param idProduct the new id product
	 */
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Gets the total order price.
	 *
	 * @return the total order price
	 */
	public Double getTotalOrderPrice() {
		return totalOrderPrice;
	}
	
	/**
	 * Sets the total order price.
	 *
	 * @param totalOrderPrice the new total order price
	 */
	public void setTotalOrderPrice(Double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
	
	/**
	 * Gets the client first name.
	 *
	 * @return the client first name
	 */
	public String getClientFirstName() {
		return clientFirstName;
	}
	
	/**
	 * Sets the client first name.
	 *
	 * @param clientFirstName the new client first name
	 */
	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}
	
	/**
	 * Gets the client last name.
	 *
	 * @return the client last name
	 */
	public String getClientLastName() {
		return clientLastName;
	}
	
	/**
	 * Sets the client last name.
	 *
	 * @param clientLastName the new client last name
	 */
	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}
	
	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * Gets the order client.
	 *
	 * @return the order client
	 */
	public String getOrderClient() {
		
		return clientFirstName+" "+clientLastName;
	}
	
	/**
	 * Gets the order product.
	 *
	 * @return the order product
	 */
	public String getOrderProduct() {
		return productName;
	}
	
	
}