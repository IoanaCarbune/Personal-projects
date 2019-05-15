package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
	private String name;
	private Double price;
	
	public MenuItem(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public abstract Double computePrice();

	public Double getPrice() {
		return this.price;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
