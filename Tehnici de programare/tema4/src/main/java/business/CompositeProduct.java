package business;

import java.util.ArrayList;
import java.util.List;


public class CompositeProduct extends MenuItem {
	
	private List<MenuItem> componentList=new ArrayList<MenuItem>();
	
	public CompositeProduct(String name, Double price, List<MenuItem> components) {
		super(name, price);
		this.componentList=components;
	}
	@Override
	public Double computePrice() {
		Double price=(double) 0;
		for(MenuItem currentItem:componentList) {
			price+=currentItem.getPrice();
		}
		return price;
	}
	public List<MenuItem> getComponentList() {
		return componentList;
	}
	public void setComponentList(List<MenuItem> componentList) {
		this.componentList = componentList;
	}

}
