package bll.validator;

import java.util.List;

import model.Product;


/**
 * The Class ProductValidator.
 */
public class ProductValidator {

	/**
	 * Validate.
	 *
	 * @param productFields the product fields
	 * @return the product
	 */
	public static Product validate(List<String> productFields) {
		int ok=0;
		System.out.println(productFields.get(0));
		if (productFields.get(0).matches("[a-zA-Z]+")&&productFields.get(1).matches("[1-9]([0-9]+)?(.[0-9]+)?") && productFields.get(2).matches("[0-9]+"))
			ok=1;
		else ok=0;
		for(String str:productFields) {
			if(str.length()==0)
				ok=0;
		}
		if(ok==1)
		{
			Product newProduct=new Product(8,productFields.get(0),Double.valueOf(productFields.get(1)),Integer.valueOf(productFields.get(2)));
			return newProduct;
		}
		else
			return null;
	}

}
