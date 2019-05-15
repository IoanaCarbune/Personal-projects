package business;

public class BaseProduct extends MenuItem{

	public BaseProduct(String name, Double price) {
		super(name, price);
	}

	@Override
	public Double computePrice() {
		
		return this.getPrice();
	}
	
}
