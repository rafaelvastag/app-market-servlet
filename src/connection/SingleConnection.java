package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por fazer a conexão com o Banco de Dados
 * 
 * @author Rafael Vastag
 *
 */
public class SingleConnection {

	private static String banco = "jbdc:postgresql://localhost:5432/curso-jsp";
	private static String password = "123456";
	private static String user = "postgres";
	private static Connection connection ;

	static {

		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				System.out.println(connection);
				connection.setAutoCommit(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
