package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJSP;
import connection.SingleConnection;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJSP usuario) {

		try {
			String sql = "INSERT INTO usuario(login,senha,nome,telefone) values (?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<BeanCursoJSP> listar() {

		String sql = "SELECT * FROM usuario";
		List<BeanCursoJSP> lista = new ArrayList<BeanCursoJSP>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
				beanCursoJSP.setId(resultSet.getLong("id"));
				beanCursoJSP.setLogin(resultSet.getString("login"));
				beanCursoJSP.setSenha(resultSet.getString("senha"));
				beanCursoJSP.setNome(resultSet.getString("nome"));
				beanCursoJSP.setTelefone(resultSet.getString("telefone"));
				lista.add(beanCursoJSP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void delete(String id) {
		try {

			String sql = "DELETE FROM usuario where id = '" + id + "'";
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public BeanCursoJSP consultar(String id) throws Exception {

		String sql = "SELECT * FROM usuario where id ='" + id + "'";

		PreparedStatement consulta = connection.prepareStatement(sql);
		ResultSet resultSet = consulta.executeQuery();
		if (resultSet.next()) {
			BeanCursoJSP bean = new BeanCursoJSP();
			bean.setId(resultSet.getLong("id"));
			bean.setLogin(resultSet.getString("login"));
			bean.setSenha(resultSet.getString("senha"));
			bean.setNome(resultSet.getString("nome"));
			bean.setTelefone(resultSet.getString("telefone"));
			return bean;
		}

		return null;
	}
	
	public boolean validarLogin(String login) throws Exception {

		String sql = "SELECT count(1) as qtd from usuario where login ='" + login + "'";

		PreparedStatement consulta = connection.prepareStatement(sql);
		ResultSet resultSet = consulta.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <=0;
		}

		return false;
	}
	
	public boolean validarLoginEdicao(String login, String id) throws Exception {

		String sql = "SELECT count(1) as qtd from usuario where login ='" + login + "' and id <> " + id;

		PreparedStatement consulta = connection.prepareStatement(sql);
		ResultSet resultSet = consulta.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <=0;
		}

		return false;
	}


	public void atualizar(BeanCursoJSP usuario) {
		try {
			String sql = "UPDATE usuario set login = ?, senha = ?, nome = ?, telefone = ? WHERE id = " + usuario.getId();
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getNome());
			update.setString(4, usuario.getTelefone());
			update.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
