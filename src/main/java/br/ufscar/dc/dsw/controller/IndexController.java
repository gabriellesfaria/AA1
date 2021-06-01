package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.dao.VagaDAO;


@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp", "/lista" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VagaDAO vagaDAO = new VagaDAO();
		Erro erros = new Erro();
		
		// checagem para login
		if (request.getParameter("bOK") != null) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			// se for empresa
			if(request.getParameter("tipo") != null) {
				if (email == null || email.isEmpty()) {
					erros.add("E-mail não informado!");
				}
				if (senha == null || senha.isEmpty()) {
					erros.add("Senha não informada!");
				}
				if (!erros.isExisteErros()) {
						EmpresaDAO dao = new EmpresaDAO();
						Empresa e = dao.getByEmail(email);
						

						if (e != null) {
							if (e.getSenha().equals(senha)) {
								request.getSession().setAttribute("empresaLogado", e);
								response.sendRedirect("empresas/");
								return;
							} else {
								erros.add("Senha inválida!");
							}
						} else {
							erros.add("Empresa não encontrada!");
						}
				}
			}
			// senao, é profissional
			else {
				if (email == null || email.isEmpty()) {
					erros.add("E-mail não informado!");
				}
				if (senha == null || senha.isEmpty()) {
					erros.add("Senha não informada!");
				}
				if (!erros.isExisteErros()) {
						ProfissionalDAO dao = new ProfissionalDAO();
						Profissional p = dao.getByEmail(email);
						if (p != null) {
							if (p.getSenha().equals(senha)) {
								if (p.getEmail().equals("admin")) {
									request.getSession().setAttribute("profissionalLogado", p);
									response.sendRedirect("admin/");
								} else {
									request.getSession().setAttribute("profissionalLogado", p);
									response.sendRedirect("profissionais/");
								}
								return;
							} else {
								erros.add("Senha inválida!");
							}
						} else {
							erros.add("Profissional não encontrado!");
						}
				}
			}
		}
		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		String action = request.getRequestURI();
	    

		if (action == null) {
			action = "";
		}
		if (action.equals("/AA1_web/lista")){
			lista(request, response);
		}
		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
		
	}
	
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VagaDAO vagaDAO = new VagaDAO();
    	List<Vaga> lista = vagaDAO.getAllAberta();
        request.setAttribute("lista", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista.jsp");
        dispatcher.forward(request, response);
    }
}