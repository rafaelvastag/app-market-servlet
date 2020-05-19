package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJSP;
import dao.UsuarioDAO;

/**
 * Servlet implementation class ServletPesquisa
 */
@WebServlet("/servletPesquisa")
public class ServletPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private UsuarioDAO dao = new UsuarioDAO();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String descricaoPesquisa = request.getParameter("descricaoconsulta");
		if (descricaoPesquisa != null && !descricaoPesquisa.isEmpty()) {
			try {
				List<BeanCursoJSP> listaPesquisa = dao.listar(descricaoPesquisa);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", listaPesquisa);
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
