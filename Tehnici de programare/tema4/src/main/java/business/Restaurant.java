package business;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import data.FileWrite;




public class Restaurant extends Observable implements RestaurantProcessing,Serializable {

	private Map<Order,List<MenuItem>> orders=new HashMap<Order,List<MenuItem>>(); 
	private List<MenuItem> menu= new ArrayList<MenuItem>();
	private Integer orderId=0;
	
	@Override
	public void createMenuItem(String name, String price, List<MenuItem>components) {
		assert name.length()!=0 && price.length()!=0;
		assert name.chars().allMatch(Character::isLetter);
		assert price.chars().allMatch( Character::isDigit );
		if(components.size()==0) {
			MenuItem newItem= new BaseProduct(name, Double.valueOf(price));
			menu.add(newItem);
		}else {
			MenuItem newItem=new CompositeProduct(name, (double) 0, components);
			menu.add(newItem);
		}
		
	}

	@Override
	public void deleteMenuItem(MenuItem item) {
		assert item!=null;
		menu.remove(item);
		assert !menu.contains(item);
	}

	@Override
	public void editMenuItem(MenuItem item, String name, String price) {
		assert item!=null;
		assert name.length()!=0 && price.length()!=0;
		item.setName(name);
		item.setPrice(Double.valueOf(price));
	}

	@Override
	public void createOrder(List<MenuItem> items, String table,String date) {
		assert items.size()!=0;
		assert table.length()!=0 && date.length()!=0;
		//System.out.println(table);
		Order currentOrder=new Order(orderId, Integer.valueOf(table),date);
		orders.put(currentOrder, items);
		orderId++;
		assert orders.containsKey(currentOrder);
		setChanged();
		notifyObservers();
	}

	@Override
	public Double computeOrderPrice(Order order) {
		assert order!=null;
		Double price=(double) 0;
		for(MenuItem currentItem: orders.get(order)) {
			price+=currentItem.computePrice();
		}
		return price;
	}

	@Override
	public void generateBill(Order order) {
		assert order!=null;
		FileWrite fileWrite= new FileWrite("bill.txt", false);
		fileWrite.writeToFile(order,orders, computeOrderPrice(order));
		orders.remove(order);
		setChanged();
		notifyObservers();
	}

	public List<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}

	public Map<Order, List<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(Map<Order, List<MenuItem>> orders) {
		this.orders = orders;
	}
	

}
