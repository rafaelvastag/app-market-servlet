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
			String sql = "INSERT INTO usuario(login,senha) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
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
				lista.add(beanCursoJSP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void delete(String login) {
		try {

			String sql = "DELETE FROM usuario where login = '" + login + "'";
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

	public BeanCursoJSP consultar(String user) throws Exception {

		String sql = "SELECT * FROM usuario where login ='" + user + "'";

		PreparedStatement consulta = connection.prepareStatement(sql);
		ResultSet resultSet = consulta.executeQuery();
		if (resultSet.next()) {
			BeanCursoJSP bean = new BeanCursoJSP();
			bean.setId(resultSet.getLong("id"));
			bean.setLogin(resultSet.getString("login"));
			bean.setSenha(resultSet.getString("senha"));
			return bean;
		}

		return null;
	}

	public void atualizar(BeanCursoJSP usuario) {
		try {
			String sql = "UPDATE usuario set login = ?, senha = ? WHERE id = " + usuario.getId();
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
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
