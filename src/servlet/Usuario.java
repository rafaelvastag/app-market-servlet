package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJSP;
import dao.UsuarioDAO;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarUsuario")
@MultipartConfig
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
	@SuppressWarnings("static-access")
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
		} else if (acao.equalsIgnoreCase("download")) {

			try {
				BeanCursoJSP usuario = userDAO.consultar(user);
				if (usuario != null && !usuario.getContentType().isEmpty()) {

					String tipo = request.getParameter("tipo");
					byte[] arqByte = null;
					String contentType = "";

					if (tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContentType().split("\\/")[1];

						/* Conversão da base 64 da imagem do banco para byte Array */
						arqByte = new Base64().decodeBase64(usuario.getFotoBase64());

					} else if (tipo.equalsIgnoreCase("curriculo")) {

						contentType = usuario.getContentTypeCurriculo().split("\\/")[1];

						/* Conversão da base 64 da imagem do banco para byte Array */
						arqByte = new Base64().decodeBase64(usuario.getCurriculoBase64());

					}

					response.setHeader("Content-Disposition",
							"attachment;filename=" + usuario.getNome() + "." + contentType);

					/* Coloca os bytes em um objeto de entrada para ser processado */
					InputStream is = new ByteArrayInputStream(arqByte);

					/* Inicio da resposta para o navegador */

					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					/* Escrever a imagem na resposta */

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();
				}

			} catch (Exception e) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", userDAO.listar());
				view.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && !acao.equalsIgnoreCase("reset")) {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("uf");
			String ibge = request.getParameter("ibge");

			BeanCursoJSP bean = new BeanCursoJSP();

			bean.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			bean.setLogin(login);
			bean.setSenha(senha);
			bean.setNome(nome);
			bean.setTelefone(telefone);
			bean.setCep(cep);
			bean.setRua(rua);
			bean.setBairro(bairro);
			bean.setCidade(cidade);
			bean.setEstado(estado);
			bean.setIbge(ibge);

			try {

				/* Inicio File UPLOAD de imagens e PDF */

				if (ServletFileUpload.isMultipartContent(request)) {

					if (ServletFileUpload.isMultipartContent(request)) {

						Part imagemFoto = request.getPart("foto");

						if (imagemFoto != null) {

							String fotoBase64 = new Base64()
									.encodeBase64String(convertStreamToByte(imagemFoto.getInputStream()));
							bean.setFotoBase64(fotoBase64);
							bean.setContentType(imagemFoto.getContentType());

						}

						Part curriculo = request.getPart("curriculo");

						if (curriculo != null) {

							String curriculoBase64 = new Base64()
									.encodeBase64String(convertStreamToByte(curriculo.getInputStream()));
							bean.setCurriculoBase64(curriculoBase64);
							bean.setContentTypeCurriculo(curriculo.getContentType());

						}

					}
				}

				/* Termino File UPLOAD de imagens e PDF */

				if (id == null || id.isEmpty() && !userDAO.validarLogin(login)) {
					request.setAttribute("msg", "Login já cadastrado! ");
					request.setAttribute("user", bean);
				}

				if (id == null || id.isEmpty() && userDAO.validarLogin(login)) {

					userDAO.salvar(bean);
					request.setAttribute("msg", "Usuário salvo com sucesso! ");
				} else if (id != null && !id.isEmpty()) {

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
				e.printStackTrace();
			}

		} else {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", userDAO.listar());
			view.forward(request, response);
		}

	}

	private byte[] convertStreamToByte(InputStream file) throws Exception {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		int reads = file.read();
		while (reads != -1) {
			bytes.write(reads);
			reads = file.read();
		}
		return bytes.toByteArray();
	}

}
