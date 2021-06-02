package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.EmailService;
import java.io.UnsupportedEncodingException;
import javax.mail.internet.InternetAddress;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.dao.InscricaoDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/empresas/*")
public class EmpresaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private EmpresaDAO dao;

	@Override
	public void init() {
		dao = new EmpresaDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogado");
		Erro erros = new Erro();
		
		if(empresa == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

			switch (action) {
			// abre form de cadastro de vaga
			case "/cadastro":
				apresentaFormCadastro(request, response);
				break;
			// insere vaga no bd
			case "/insercao":
				insere(request, response);
				break;
			case "/mudarStatus":
				apresentaFormStatus(request, response);
				break;
			// insere vaga no bd
			case "/atualizacaoStatus":
				atualizeStatus(request, response);
				break;
			// remove vaga
			case "/remocao":
				remove(request, response);
				break;
			// abre form de edicao
			case "/edicao":
				apresentaFormEdicao(request, response);
				break;
			// salva os resultados do form de edicao
			case "/atualizacao":
				atualize(request, response);
				break;
			case "/listaInscricao":
				listaInscricao(request, response);
				break;
			// lista de vagas
			default:
				lista(request, response);
				break;
			}
	
	}

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogado");
		Long e_id = empresa.getCnpj();
		VagaDAO dao_vaga = new VagaDAO();
		List<Vaga> listaVagas = dao_vaga.getByEmpresa(e_id);
		request.setAttribute("listaVagas", listaVagas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/lista.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listaInscricao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogado");
		Long e_id = empresa.getCnpj();
		InscricaoDAO dao_i = new InscricaoDAO();
		List<Inscricao> listaInscricao = dao_i.getByEmpresa(e_id);
		request.setAttribute("listaInscricao", listaInscricao);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/listaInscricao.jsp");
		dispatcher.forward(request, response);
	}
	private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("id"));		
		VagaDAO dao_vaga = new VagaDAO();
		Vaga vaga = dao_vaga.get(id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/formulario.jsp");
		request.setAttribute("vaga", vaga);
		dispatcher.forward(request, response);
	}
	
	private void apresentaFormStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/formStatus.jsp");
		dispatcher.forward(request, response);
	}

	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogado");
		Long e_id = empresa.getCnpj();
		VagaDAO dao_vaga = new VagaDAO();
		String nome = request.getParameter("nome");
		String status = request.getParameter("status");
		String descricao = request.getParameter("descricao");
		Float salario = Float.parseFloat(request.getParameter("salario"));
		String data_limite = request.getParameter("dataLimite");
		Vaga vaga = new Vaga(nome, status, descricao, salario, data_limite, e_id);

		dao_vaga.insert(vaga);
		response.sendRedirect("lista");
	}

	private void atualize(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogado");
		Long e_id = empresa.getCnpj();
		VagaDAO dao_vaga = new VagaDAO();

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String status = request.getParameter("status");
		String descricao = request.getParameter("descricao");
		Float salario = Float.parseFloat(request.getParameter("salario"));
		String data_limite = request.getParameter("dataLimite");
		
		Vaga vaga = new Vaga(id, nome, status, descricao, salario, data_limite, e_id);

		dao_vaga.update(vaga);
		response.sendRedirect("lista");
	}
	// atualiza status em inscricao e envia email aos candidatos
	private void atualizeStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		EmailService service = new EmailService();

		// alteracao em inscricao
		request.setCharacterEncoding("UTF-8");
		Long id_inscricao = Long.parseLong(request.getParameter("id"));
		String status_inscricao = request.getParameter("status");
		InscricaoDAO dao_i = new InscricaoDAO();		
		dao_i.updateStatus(id_inscricao, status_inscricao);
		Inscricao inscricao = dao_i.get(id_inscricao);
		Long id_vaga = inscricao.getVaga();
		
		/*
		 * 
		 * // pega email da empresa
		 * VagaDAO dao_v = new VagaDAO();
		 * Vaga vaga = dao_v.get(id_vaga);
		 * Long id_empresa = vaga.getEmpresa();
		 * EmpresaDAO dao_e = new EmpresaDAO();
		 * Empresa empresa = dao_e.get(id_empresa);
		 * String from = empresa.getEmail();
		 * String fromNome = empresa.getNome();
		 * 
		 */
		
		String from = "gabriellefaria@estudante.ufscar.br";
		String fromNome = "From";

    	// pega email profissional
		Long id_profissional = dao_i.getIdProfissional(id_inscricao);
		ProfissionalDAO dao_p = new ProfissionalDAO();
		Profissional profissional = dao_p.get(id_profissional);
    	String to = profissional.getEmail();
    	String toNome = profissional.getNome();
  
    	InternetAddress de = new InternetAddress(from, fromNome);
    	InternetAddress para = new InternetAddress(to, toNome);
    	
		EmailService email = new EmailService();
		
    	
		// se ele tiver ido pra entrevista
    	if(status_inscricao.equals("ENTREVISTA")) {
    		String data = request.getParameter("dataEntrevista");
    		String link = request.getParameter("linkEntrevista");
    		
        	String assunto = "PARABÉNS!!!";
        	String mensagem = "Você foi chamado para entrevista na vaga de identificador " + id_vaga + ". A entrevista será no dia"  + data  + ", pelo link "+ link;
        	
    		email.send(de, para, assunto, mensagem);
    	}
    	// se nao foi selecionado, email de condolencias
    	if(status_inscricao.equals("NAO SELECIONADO")) { 
        	String assunto = "NÃO SELECIONADO";
        	String mensagem = "Infelizmente achamos que você não tem perfil para vaga " + id_vaga + "! Boa sorte em sua jornada.";
        	
    		email.send(de, para, assunto, mensagem);
    	}
		response.sendRedirect("listaInscricao");
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VagaDAO dao_vaga = new VagaDAO();
		Long id = Long.parseLong(request.getParameter("id"));
		Vaga vaga = new Vaga(id);
		dao_vaga.delete(vaga);
		response.sendRedirect("lista");
	}
}