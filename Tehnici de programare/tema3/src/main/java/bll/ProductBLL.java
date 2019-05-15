package bll;

import DAO.ProductDAO;
import javafx.collections.ObservableList;

/**
 * The Class ProductBLL.
 */
public class ProductBLL {
		
		/** The product. */
		private static ProductDAO product= new ProductDAO();
		
		/**
		 * Adds the product.
		 *
		 * @param obj the obj
		 */
		public static void addProduct(Object obj) {
			product.add(obj);
		}
		
		/**
		 * Show products.
		 *
		 * @return the observable list
		 */
		public static ObservableList showProducts() {
			return product.showAll();
		}
		
		/**
		 * Delete product.
		 *
		 * @param objId the obj id
		 */
		public static void deleteProduct(int objId) {
			product.delete(objId);
		}
		
		/**
		 * Update product.
		 *
		 * @param obj the obj
		 */
		public static void updateProduct(Object obj) {
			product.update(obj);
		}
	}

