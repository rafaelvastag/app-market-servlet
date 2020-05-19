package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Telefones;
import connection.SingleConnection;

/*
 * Classe DaoTelefone
 * Classe Que Provê os Métodos e Validações Para Manipular Dados, e Acesso e Manipulação do BD
 */
public class TelefonesDAO {

	private Connection connection;
		
		/*
		 * Construtor DaoTelefone()
		 * Recebe um Objeto connection da Classe SingleConnection
		 */
		public TelefonesDAO() {
			connection = SingleConnection.getConnection();
		}
		
		/*
		 * Método salvar()
		 * Responsável Por Fazer a Inserção de Dados (INSERT) no BD
		 * @param BeanTelefone telefone = Objeto Telefone da Classe BeanTelefone
		 */
		public void salvar(Telefones telefone) {
			try {
				String sql = "INSERT INTO telefone(numero, tipo, usuario) VALUES(?, ?, ?)";
				PreparedStatement insert = connection.prepareStatement(sql);
				insert.setString(1, telefone.getNumero());
				insert.setString(2, telefone.getTipo());
				insert.setLong(3, telefone.getUsuario());
				insert.execute();
				connection.commit();
			} catch(Exception e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		/*
		 * Método listar()
		 * Responsável Por Listar Todos os Telefones do Sistema
		 */
		public List<Telefones> findByUser(Long user) throws Exception {
			List<Telefones> listar = new ArrayList<Telefones>();
			String sql = "SELECT * FROM telefone WHERE usuario = "+ user;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
					Telefones telefone = new Telefones();
					telefone.setId(resultSet.getLong("id"));
					telefone.setNumero(resultSet.getString("numero"));
					telefone.setTipo(resultSet.getString("tipo"));
					telefone.setUsuario(resultSet.getLong("usuario"));
					listar.add(telefone);
				}
				return listar;
		}
		
		/*
		 * Método delete()
		 * Responsável Por Fazer a Exclusão (Delete) no BD
		 * @param String id = Atributo ID do Telefone
		 */
		public void deletar(String id) {
			try {
				String sql = "DELETE FROM telefone WHERE id = '"+ id +"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.execute();
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
}
