package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJSP;
import dao.UsuarioDAO;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO userDAO = new UsuarioDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		if (acao.equalsIgnoreCase("delete")) {
			userDAO.delete(user);

			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", userDAO.listar());
			view.forward(request, response);
		} else if (acao.equalsIgnoreCase("editar")) {

			BeanCursoJSP beanCursoJsp;
			try {
				beanCursoJsp = userDAO.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", userDAO.listar());
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (acao.equalsIgnoreCase("listarTodos")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", userDAO.listar());
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && !acao.equalsIgnoreCase("reset")) {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			BeanCursoJSP bean = new BeanCursoJSP();

			bean.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			bean.setLogin(login);
			bean.setSenha(senha);
			bean.setNome(nome);
			bean.setTelefone(telefone);

			try {
				
				if (id == null || id.isEmpty() && !userDAO.validarLogin(login)) {
					request.setAttribute("msg", "Login já cadastrado! ");
					request.setAttribute("user", bean);
				}
				
				if (id == null || id.isEmpty() && userDAO.validarLogin(login)) {

					userDAO.salvar(bean);
				} else if(id != null && !id.isEmpty()) {
					
					if (!userDAO.validarLoginEdicao(login, id)) {
						request.setAttribute("msg", "Login já cadastrado! ");
						request.setAttribute("user", bean);
					} else if (userDAO.validarLoginEdicao(login, id)) 
						userDAO.atualizar(bean);
					
				}
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", userDAO.listar());
				view.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", userDAO.listar());
			view.forward(request, response);
		}

	}

}
