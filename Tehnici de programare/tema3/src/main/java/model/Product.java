package model;

/**
 * The Class Product.
 */
public class Product {
	
	/** The id product. */
	private Integer idProduct;
	
	/** The product name. */
	private String productName;
	
	/** The product price. */
	private Double productPrice;
	
	/** The product quantity. */
	private Integer productQuantity;
	

	/**
	 * Instantiates a new product.
	 */
	public Product() {
		super();
	}
	
	/**
	 * Instantiates a new product.
	 *
	 * @param idProduct the id product
	 * @param productName the product name
	 * @param productPrice the product price
	 * @param productQuantity the product quantity
	 */
	public Product(Integer idProduct, String productName, Double productPrice, Integer productQuantity) {
		super();
		this.idProduct = idProduct;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
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
	 * Gets the product price.
	 *
	 * @return the product price
	 */
	public Double getProductPrice() {
		return productPrice;
	}
	
	/**
	 * Sets the product price.
	 *
	 * @param productPrice the new product price
	 */
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	/**
	 * Gets the product quantity.
	 *
	 * @return the product quantity
	 */
	public Integer getProductQuantity() {
		return productQuantity;
	}
	
	/**
	 * Sets the product quantity.
	 *
	 * @param productQuantity the new product quantity
	 */
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	

}
