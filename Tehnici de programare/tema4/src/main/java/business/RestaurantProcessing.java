package business;



import java.util.List;

public interface RestaurantProcessing {
	/**
	 * @pre name.length()!=0 && price.length()!=0
	 */
	public void createMenuItem(String name, String price, List<MenuItem>components);
	/**
	 * @pre item!=null
	 */
	public void deleteMenuItem(MenuItem item);
	/**
	 * @pre item!=null && name.length()!=0 && price.length!=0
	 */
	public void editMenuItem(MenuItem item, String name, String price);
	/**
	 * 
	 * @pre items.size()!=0 && table.length()!=0 && date.length()!=0
	 */
	public void createOrder(List<MenuItem> items, String table,String date);
	/**
	 * 
	 * @pre order!=null
	 */
	public Double computeOrderPrice(Order order);
	/**
	 * 
	 * @pre order!=null
	 */
	public void generateBill(Order order);
}
