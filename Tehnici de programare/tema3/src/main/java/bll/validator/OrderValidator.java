package bll.validator;

import model.Product;


/**
 * The Class OrderValidator.
 */
public class OrderValidator {

	/**
	 * Validate.
	 *
	 * @param quantity the quantity
	 * @param selectedProductOrder the selected product order
	 * @return the boolean
	 */
	public static Boolean validate(Integer quantity, Product selectedProductOrder) {
		if(selectedProductOrder.getProductQuantity()>=quantity ) {
			return true;
			}
		else return false;
	}

}
