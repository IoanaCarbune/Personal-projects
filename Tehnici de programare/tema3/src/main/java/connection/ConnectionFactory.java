package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;



/**
 * A factory for creating Connection objects.
 */
public class ConnectionFactory {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
	
	/** The Constant DRIVER. */
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	
	/** The Constant DBURL. */
	private static final String DBURL="jdbc:mysql://localhost:3306/assig3_tp?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	/** The Constant USER. */
	private static final String USER="root";
	
	/** The Constant PASS. */
	private static final String PASS="ioanaelena21";
	
	/** The single instance. */
	private static ConnectionFactory singleInstance= new ConnectionFactory();
	
	/** The connection. */
	private static Connection connection;
	
	/**
	 * Instantiates a new connection factory.
	 */
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
			connection=createConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new Connection object.
	 *
	 * @return the connection
	 */
	private Connection createConnection() {
		try {
			return DriverManager.getConnection(DBURL,USER,PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/**
	 * Close.
	 *
	 * @param connection the connection
	 */
	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close.
	 *
	 * @param statement the statement
	 */
	public static void close(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close.
	 *
	 * @param resultSet the result set
	 */
	public static void close(ResultSet resultSet) {
	try {
		resultSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
