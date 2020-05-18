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
			String sql = "INSERT INTO usuario(login,senha,nome,telefone,cep,rua,bairro,cidade,estado,ibge,fotobase64,contenttype,curriculobase64,contenttypecurriculo,fotobase64miniatura,status,sexo,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			insert.setString(15, usuario.getFotoBase64miniatura());
			insert.setBoolean(16, usuario.isStatus());
			insert.setString(17, usuario.getSexo());
			insert.setString(18, usuario.getPerfil());

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

		String sql = "SELECT * FROM usuario where login <> 'admin'";
		return consultarUsuarios(sql);
	}

	public List<BeanCursoJSP> listar(String descricao) {

		String sql = "SELECT * FROM usuario where login <> 'admin' and nome like '%" + descricao + "%'";
		return consultarUsuarios(sql);
	}

	private List<BeanCursoJSP> consultarUsuarios(String sql) {

		List<BeanCursoJSP> lista = new ArrayList<>();
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
				// beanCursoJSP.setFotoBase64(resultSet.getString("fotobase64"));
				beanCursoJSP.setContentType(resultSet.getString("contenttype"));
				beanCursoJSP.setCurriculoBase64(resultSet.getString("curriculobase64"));
				beanCursoJSP.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
				beanCursoJSP.setFotoBase64miniatura(resultSet.getString("fotobase64miniatura"));
				beanCursoJSP.setStatus(resultSet.getBoolean("status"));
				beanCursoJSP.setSexo(resultSet.getString("sexo"));
				beanCursoJSP.setPerfil(resultSet.getString("perfil"));
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

			String sql = "DELETE FROM usuario where id = '" + id + "' and login <> 'admin'";
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
			bean.setFotoBase64miniatura(resultSet.getString("fotobase64miniatura"));
			bean.setStatus(resultSet.getBoolean("status"));
			bean.setSexo(resultSet.getString("sexo"));
			bean.setPerfil(resultSet.getString("perfil"));
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

	/*
	 * Método atualizar() Método Responsável Por Atualizar os Dados (UPDATE) no BD
	 * 
	 * @param BeanCursoJsp usuario = Objeto usuario da Classe BeanCursoJsp
	 */
	public void atualizar(BeanCursoJSP usuario) {
		try {
			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE usuario SET login = ?, senha = ?, nome = ?, telefone = ");
			sql.append(" ?, cep = ?, rua = ?, bairro = ?, cidade = ?, ");
			sql.append(" estado = ?, ibge = ?, status = ?, sexo = ?, perfil = ?");

			if (usuario.isAtualizarImage()) {
				sql.append(", fotobase64 =?, contenttype = ? ");
			}

			if (usuario.isAtualizarPdf()) {
				sql.append(", curriculobase64 = ?, contenttypecurriculo = ? ");
			}

			if (usuario.isAtualizarImage()) {
				sql.append(", fotobase64miniatura = ? ");
			}

			sql.append(" WHERE id = " + usuario.getId());

			// fotobase64, contenttype, curriculobase64, contenttypecurriculo
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getTelefone());
			preparedStatement.setString(5, usuario.getCep());
			preparedStatement.setString(6, usuario.getRua());
			preparedStatement.setString(7, usuario.getBairro());
			preparedStatement.setString(8, usuario.getCidade());
			preparedStatement.setString(9, usuario.getEstado());
			preparedStatement.setString(10, usuario.getIbge());
			preparedStatement.setBoolean(11, usuario.isStatus());
			preparedStatement.setString(12, usuario.getSexo());
			preparedStatement.setString(13, usuario.getPerfil());

			if (usuario.isAtualizarImage()) {
				preparedStatement.setString(14, usuario.getFotoBase64());
				preparedStatement.setString(15, usuario.getContentType());
			}

			if (usuario.isAtualizarPdf()) {

				if (usuario.isAtualizarPdf() && !usuario.isAtualizarImage()) {
					preparedStatement.setString(14, usuario.getCurriculoBase64());
					preparedStatement.setString(15, usuario.getContentTypeCurriculo());
				} else {
					preparedStatement.setString(16, usuario.getCurriculoBase64());
					preparedStatement.setString(17, usuario.getContentTypeCurriculo());
				}

			} else {
				if (usuario.isAtualizarImage()) {
					preparedStatement.setString(16, usuario.getFotoBase64miniatura());
				}
			}

			if (usuario.isAtualizarImage() && usuario.isAtualizarPdf()) {
				preparedStatement.setString(18, usuario.getFotoBase64miniatura());
			}

			preparedStatement.executeUpdate();
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
}
