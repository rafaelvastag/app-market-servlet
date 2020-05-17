package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProdutoJSP;
import dao.ProdutoDAO;

@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public Produto() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				produtoDAO.deletar(produto);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDAO.findAll());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProdutoJSP beanProdutoJSP = produtoDAO.findById(produto);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDAO.findAll());
				request.setAttribute("produto", beanProdutoJSP);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listarTodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDAO.findAll());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDAO.findAll());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			try {

				String msg = null;
				boolean podeInserir = true;

				if (valor == null || valor.isEmpty()) {
					msg = "Valor R$ deve ser informado";
					podeInserir = false;

				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "Quantidade deve ser informado";
					podeInserir = false;

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !produtoDAO.validarNome(nome)) {// QUANDO
																							// FDOR
					msg = "Produto já existe com o mesmo nome!";
					podeInserir = false;

				}

				BeanProdutoJSP produto = new BeanProdutoJSP();
				produto.setNome(nome);
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);

				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}

				if (valor != null && !valor.isEmpty())
					produto.setValor(Double.parseDouble(valor.replace(",", ".")));

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && produtoDAO.validarNome(nome) && podeInserir) {

					produtoDAO.salvar(produto);

				} else if (id != null && !id.isEmpty() && podeInserir && produtoDAO.validarEdicao(nome, id)) {
					
					produtoDAO.atualizar(produto);
					
				} else if (id != null && !id.isEmpty() && podeInserir && !produtoDAO.validarEdicao(nome, id)) {
					
					msg = "Produto já existe com o mesmo nome!";
					request.setAttribute("msg", msg);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDAO.findAll());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
