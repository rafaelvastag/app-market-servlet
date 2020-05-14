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
			String sql = "INSERT INTO usuario(login,senha,nome,telefone,cep,rua,bairro,cidade,estado,ibge,fotobase64,contenttype,curriculobase64,contenttypecurriculo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
			insert.setString(10, usuario.getIbge());
			insert.setString(10, usuario.getIbge());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			insert.execute();
			connection.commit();
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
				beanCursoJSP.setCep(resultSet.getString("cep"));
				beanCursoJSP.setRua(resultSet.getString("rua"));
				beanCursoJSP.setBairro(resultSet.getString("bairro"));
				beanCursoJSP.setCidade(resultSet.getString("cidade"));
				beanCursoJSP.setEstado(resultSet.getString("estado"));
				beanCursoJSP.setIbge(resultSet.getString("ibge"));
				beanCursoJSP.setFotoBase64(resultSet.getString("fotobase64"));
				beanCursoJSP.setContentType(resultSet.getString("contenttype"));
				beanCursoJSP.setCurriculoBase64(resultSet.getString("curriculobase64"));
				beanCursoJSP.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
				beanCursoJSP.setTempFotoUser();
				beanCursoJSP.setTempCurriculoUser();
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
			bean.setCep(resultSet.getString("cep"));
			bean.setRua(resultSet.getString("rua"));
			bean.setBairro(resultSet.getString("bairro"));
			bean.setCidade(resultSet.getString("cidade"));
			bean.setEstado(resultSet.getString("estado"));
			bean.setIbge(resultSet.getString("ibge"));
			bean.setFotoBase64(resultSet.getString("fotobase64"));
			bean.setContentType(resultSet.getString("contenttype"));
			bean.setCurriculoBase64(resultSet.getString("curriculobase64"));
			bean.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			bean.setTempFotoUser();
			bean.setTempCurriculoUser();
			return bean;
		}

		return null;
	}

	public boolean validarLogin(String login) throws Exception {

		String sql = "SELECT count(1) as qtd from usuario where login ='" + login + "'";

		PreparedStatement consulta = connection.prepareStatement(sql);
		ResultSet resultSet = consulta.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public boolean validarLoginEdicao(String login, String id) throws Exception {

		String sql = "SELECT count(1) as qtd from usuario where login ='" + login + "' and id <> " + id;

		PreparedStatement consulta = connection.prepareStatement(sql);
		ResultSet resultSet = consulta.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void atualizar(BeanCursoJSP usuario) {
		try {
			String sql = "UPDATE usuario set login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, "
					+ "ibge = ?, fotobase64 = ?, contenttype = ?, curriculoBase64 = ?, contenttypecurriculo = ?  WHERE id = " + usuario.getId();
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getNome());
			update.setString(4, usuario.getTelefone());
			update.setString(5, usuario.getCep());
			update.setString(6, usuario.getRua());
			update.setString(7, usuario.getBairro());
			update.setString(8, usuario.getCidade());
			update.setString(9, usuario.getEstado());
			update.setString(10, usuario.getIbge());
			update.setString(11, usuario.getFotoBase64());
			update.setString(12, usuario.getContentType());
			update.setString(13, usuario.getCurriculoBase64());
			update.setString(14, usuario.getContentTypeCurriculo());
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
