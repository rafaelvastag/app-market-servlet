package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJSP;
import beans.Telefones;
import dao.TelefonesDAO;
import dao.UsuarioDAO;

@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private TelefonesDAO dao = new TelefonesDAO();

	public TelefonesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			response.getWriter().append("Served at: ").append(request.getContextPath());

			String user = request.getParameter("user");

			String acao = request.getParameter("acao");

			String numero = request.getParameter("numero");

			BeanCursoJSP usuario = usuarioDAO.consultar(user);

			if (acao.equalsIgnoreCase("delete")) {
				dao.deletar(numero);
				request.setAttribute("msg", "Telefone deletado com sucesso! ");
			}

			request.getSession().setAttribute("userEscolhido", usuario);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			request.setAttribute("telefones", dao.findByUser(usuario.getId()));
			request.setAttribute("userEscolhido", usuario);
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			BeanCursoJSP beanCursoJSP = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");
			String acao = request.getParameter("acao");

			if (!acao.equalsIgnoreCase("voltar")) {

				Telefones telefones = new Telefones();
				telefones.setNumero(numero);
				telefones.setTipo(tipo);
				telefones.setUsuario(beanCursoJSP.getId());

				dao.salvar(telefones);

				request.getSession().setAttribute("userEscolhido", beanCursoJSP);
				request.setAttribute("userEscolhido", beanCursoJSP);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", dao.findByUser(beanCursoJSP.getId()));
				request.setAttribute("msg", "Salvo com sucesso! ");
				view.forward(request, response);

			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", usuarioDAO.listar());
				view.forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
