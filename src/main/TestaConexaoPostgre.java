package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexaoPostgre {

	public static void main(String[] args)  {
		
		
		try(Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pos-java", "postgres", "123456")){
			
			if (conexao != null) {
				
				System.out.println("Conectado a base de dados");
				System.out.println(conexao);
				
			} else {System.out.println("Falha");}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
