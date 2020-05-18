package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProdutoJSP;
import beans.beanCategoria;
import connection.SingleConnection;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanProdutoJSP produto) {
		try {

			String sql = "insert into produto (nome,quantidade,preco,categoria_id) values (?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
			insert.setLong(4, produto.getCategoria_id());
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

	public List<BeanProdutoJSP> findAll() {

		List<BeanProdutoJSP> lista = new ArrayList<>();
		String sql = "select * from produto";

		try {
			PreparedStatement findAll = connection.prepareStatement(sql);
			ResultSet resultSet = findAll.executeQuery();

			while (resultSet.next()) {
				BeanProdutoJSP produto = new BeanProdutoJSP(resultSet.getString("nome"), resultSet.getDouble("preco"),
						resultSet.getDouble("quantidade"));
				produto.setId(resultSet.getLong("id"));
				produto.setCategoria_id(resultSet.getLong("categoria_id"));
				lista.add(produto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;

	}
	
	public List<beanCategoria> findCategorias() {

		List<beanCategoria> lista = new ArrayList<>();
		String sql = "select * from categoria";

		try {
			PreparedStatement findAll = connection.prepareStatement(sql);
			ResultSet resultSet = findAll.executeQuery();

			while (resultSet.next()) {
				beanCategoria categoria = new beanCategoria(resultSet.getString("nome"));
				categoria.setId(resultSet.getLong("id"));
				lista.add(categoria);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;

	}

	public BeanProdutoJSP findById(String id) throws Exception {
		String sql = "select * from produto where id='" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanProdutoJSP produto = new BeanProdutoJSP();
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setQuantidade(resultSet.getDouble("quantidade"));
			produto.setValor(resultSet.getDouble("preco"));
			produto.setCategoria_id(resultSet.getLong("categoria_id"));
			return produto;
		}

		return null;
	}

	public void atualizar(BeanProdutoJSP produto) {

		try {
			String sql = "update produto set nome = ?, quantidade = ?, preco = ?, categoria_id = ?  where id = " + produto.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setDouble(2, produto.getQuantidade());
			preparedStatement.setDouble(3, produto.getValor());
			preparedStatement.setLong(4, produto.getCategoria_id());
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

	public void deletar(String id) {

		try {
			String sql = "delete from produto where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

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

	public boolean validarNome(String nome) throws Exception {
		String sql = "select count(1) as qtd from produto where nome='" + nome + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/* Return true */
		}

		return false;
	}
	
	public boolean validarEdicao(String nome, String id) throws Exception {

		String sql = "SELECT count(1) as qtd from produto where nome ='" + nome + "' and id <> " + id;

		PreparedStatement consulta = connection.prepareStatement(sql);
		ResultSet resultSet = consulta.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <=0;
		}

		return false;
	}

}
