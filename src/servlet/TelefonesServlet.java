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

@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public TelefonesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());

			String user = request.getParameter("user");
			BeanCursoJSP usuario = usuarioDAO.consultar(user);

			request.getSession().setAttribute("userEscolhido", usuario);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			// request.setAttribute("telefones", userDAO.listar());
			request.setAttribute("userEscolhido", usuario);
			request.setAttribute("msg", "Salvo com sucesso! ");
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BeanCursoJSP beanCursoJSP = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

		System.out.println(numero);
		System.out.println(tipo);

	}

}
