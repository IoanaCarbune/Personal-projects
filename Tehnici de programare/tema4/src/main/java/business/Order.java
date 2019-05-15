package business;

import java.io.Serializable;

public class Order implements Serializable {
	private Integer orderId;
	private Integer table;
	private String date;
	
	public Order(Integer orderId, Integer table, String date) {
		super();
		this.orderId = orderId;
		this.table =table;
		this.date =date ;
	}
	
	@Override
	public final int hashCode() {
		int hash = super.hashCode();
        hash = 89 * hash + orderId;     
        hash = 89 * hash + date.hashCode();   
        return hash;  
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getTable() {
		return table;
	}

	public void setTable(Integer table) {
		this.table = table;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
