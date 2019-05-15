package bll;

import DAO.ClientDAO;
import javafx.collections.ObservableList;

/**
 * The Class ClientBLL.
 */
public class ClientBLL {
	
	/** The client. */
	private static ClientDAO client= new ClientDAO();
	
	/**
	 * Adds the client.
	 *
	 * @param obj the obj
	 */
	public static void addClient(Object obj) {
		client.add(obj);
	}
	
	/**
	 * Show clients.
	 *
	 * @return the observable list
	 */
	public static ObservableList showClients() {
		return client.showAll();
	}
	
	/**
	 * Delete client.
	 *
	 * @param objId the obj id
	 */
	public static void deleteClient(int objId) {
		client.delete(objId);
	}
	
	/**
	 * Update client.
	 *
	 * @param obj the obj
	 */
	public static void updateClient(Object obj) {
		client.update(obj);
	}
}
