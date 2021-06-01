package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.dao.InscricaoDAO;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfissionalController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProfissionalDAO dao;
	
	// cria uma instancia da classe ProfissionalDAO
	@Override
	public void init() {
		dao = new ProfissionalDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Profissional profissional = (Profissional) request.getSession().getAttribute("profissionalLogado");
		Erro erros = new Erro();

		if(profissional == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		String action = request.getPathInfo(); // pega o que está depois de /profisisonais/ na url
		if (action == null) {
			action = "";
		}

		try {
			switch (action) {
			case "/cadastro":
				apresentaFormCadastro(request, response);
				break;
			case "/insercao":
				insere(request, response);
				break;
			case "/listaVagas":
				listaVagas(request, response);
				break;
			case "/remocao":
				remove(request, response);
				break;
			default:
				lista(request, response);
				break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}
	
	private void listaVagas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VagaDAO dao_vaga = new VagaDAO();
		List<Vaga> listaVagas = dao_vaga.getAllAberta();
		request.setAttribute("listaVagas", listaVagas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/listaVagas.jsp");
		dispatcher.forward(request, response);
	}

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Profissional profissional = (Profissional) request.getSession().getAttribute("profissionalLogado");
		Long p_id = profissional.getCpf();
		InscricaoDAO dao_inscricao = new InscricaoDAO();
		List<Inscricao> listaInscricoes = dao_inscricao.getByProfissional(p_id);
		request.setAttribute("listaInscricoes", listaInscricoes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/lista.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Profissional profissional = (Profissional) request.getSession().getAttribute("profissionalLogado");
		Long p_id = profissional.getCpf();
		InscricaoDAO dao_i = new InscricaoDAO();
		
		String cv = request.getParameter("cv");
		Long vaga = Long.parseLong(request.getParameter("vaga"));
		
		dao_i.insert(cv, p_id, vaga);
		response.sendRedirect("lista");
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));

		InscricaoDAO dao_i = new InscricaoDAO();
		Inscricao inscricao = new Inscricao(id);
		dao_i.delete(inscricao);
		response.sendRedirect("lista");
	}
}