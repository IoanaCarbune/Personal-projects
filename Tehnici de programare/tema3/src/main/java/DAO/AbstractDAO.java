package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * The Class AbstractDAO.
 *
 * @param <T> the generic type
 */
public class AbstractDAO<T> {
	
	/** The Constant LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	/** The type. */
	private final Class<T> type;

	/**
	 * Instantiates a new abstract DAO.
	 */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Creates the insert query.
	 *
	 * @param object the object
	 * @return the string
	 */
	private String createInsertQuery(Object object) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append(type.getSimpleName().toLowerCase());
		sb.append(" ( ");
		int i = 1;
		int length = object.getClass().getDeclaredFields().length;
		for (Field field : object.getClass().getDeclaredFields()) {
			if (i != 1) {
				field.setAccessible(true);
				sb.append(field.getName());
				if (i == length)
					break;
				sb.append(",");
			}
			i++;
		}
		sb.append(" ) ");
		sb.append(" VALUES (");
		i = 1;
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (i != 1) {
				try {
					sb.append("'"+field.get(object)+"'");
					if (i == length)
						break;
					sb.append(",");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			i++;
		}
		sb.append(")");
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * Adds the.
	 *
	 * @param object the object
	 */
	public void add(Object object) {
		Connection connection = null;
		Statement statement = null;
		String query = createInsertQuery(object);
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Creates the select query.
	 *
	 * @return the string
	 */
	public String createSelectQuery() {
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName().toLowerCase());
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Show all.
	 *
	 * @return the observable list
	 */
	public ObservableList<T> showAll(){
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		String query=createSelectQuery();
		connection=ConnectionFactory.getConnection();
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createObjects(resultSet);
	}

	/**
	 * Creates the objects.
	 *
	 * @param resultSet the result set
	 * @return the observable list
	 */
	private ObservableList<T> createObjects(ResultSet resultSet) {
		ObservableList<T> list =FXCollections.observableArrayList();
		try {
			while(resultSet.next()) {
				T instance=type.newInstance();
				for(Field field: type.getDeclaredFields()) {
					Object value=resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(),type);
					Method method=propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Creates the delete query.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String createDeleteQuery(int id) {
		StringBuilder sb=new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName().toLowerCase());
		sb.append(" where id");
		sb.append(type.getSimpleName()+"=");
		sb.append(id);
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(int id) {
		Connection connection=null;
		Statement statement=null;
		String query=createDeleteQuery(id);
		connection=ConnectionFactory.getConnection();
		try {
			statement=connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the update query.
	 *
	 * @param object the object
	 * @return the string
	 */
	public String createUpdateQuery(Object object) {
		StringBuilder sb=new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName().toLowerCase());
		sb.append(" set ");
		int i = 1;
		int id=0;
		int length = object.getClass().getDeclaredFields().length;
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (i != 1) {

				try {
					sb.append(field.getName()+"='"+field.get(object)+"'");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				if(i==length) break;
				sb.append(",");
			} else
				try {
					id=(int) field.get(object);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			i++;
		}
		sb.append(" where id"+type.getSimpleName()+"="+id+";");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Update.
	 *
	 * @param obj the obj
	 */
	public void update(Object obj) {
		Connection connection=null;
		Statement statement=null;
		String query=createUpdateQuery(obj);
		connection=ConnectionFactory.getConnection();
		try {
			statement=connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
