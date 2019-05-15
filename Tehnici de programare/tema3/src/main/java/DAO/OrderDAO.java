package DAO;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;
import model.Orderr;


/**
 * The Class OrderDAO.
 */
public class OrderDAO extends AbstractDAO<Orderr> {
	
	/**
	 * Creates the delete client query.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public String createDeleteClientQuery(Object obj) {
		int id=-1;
		
			for(Field field:obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				try {
					id = (int) field.get(obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				break;
				}
			
		StringBuilder sb=new StringBuilder();
		sb.append("DELETE FROM orderr");
		sb.append(" where idClient=");
		sb.append(id);
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Creates the delete product query.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public String createDeleteProductQuery(Object obj) {
		int id=-1;
		try {
			for(Field field:obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				id = (int) field.get(obj);
				break;
				}
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
			
			e.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		sb.append("DELETE FROM orderr");
		sb.append(" where idProduct=");
		sb.append(id);
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Delete order.
	 *
	 * @param obj the obj
	 */
	public void deleteOrder(Object obj) {
		Connection connection=null;
		Statement statement=null;
		String query;
		System.out.println(obj.getClass().getSimpleName());
		if(obj.getClass().getSimpleName().equals("Client"))
			query=createDeleteClientQuery(obj);
		else 
			query=createDeleteProductQuery(obj);
		connection=ConnectionFactory.getConnection();
		try {
			statement=connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
