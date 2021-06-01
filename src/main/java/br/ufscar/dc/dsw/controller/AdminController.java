package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.dao.InscricaoDAO;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProfissionalDAO daoPro;
    private EmpresaDAO daoEmp;

    // cria uma instancia da classe ProfissionalDAO
    @Override
    public void init() {
        daoPro = new ProfissionalDAO();
        daoEmp = new EmpresaDAO();
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

        if (profissional == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String action = request.getPathInfo(); // pega o que está depois de /profisisonais/ na url
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/edicaoEmpresa":
                    apresentaFormEdicaoEmpresa(request, response);
                    break;
                case "/edicaoProfissional":
                    apresentaFormEdicaoProfissional(request, response);
                    break;
                case "/cadastroEmpresa":
                    apresentaFormCadastroEmpresa(request, response);
                    break;
                case "/cadastroProfissional":
                    apresentaFormCadastroProfissional(request, response);
                    break;
                case "/insereEmpresa":
                    insereEmpresa(request, response);
                    break;
                case "/insereProfissional":
                    insereProfissional(request, response);
                    break;
                // case "/insercao":
                // insere(request, response);
                // break;
                // case "/edicao":
                // edita(request, response);
                // break;
                case "/listaProfissionais":
                    listaProfissionais(request, response);
                    break;
                case "/listaEmpresas":
                    listaEmpresas(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/atualizaEmpresa":
                    atualizaEmpresa(request, response);
                    break;
                case "/atualizaProfissional":
                    atualizaProfissional(request, response);
                    break;
                default:
                    listaProfissionais(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaProfissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProfissionalDAO dao = new ProfissionalDAO();
        List<Profissional> listaPros = dao.getAll();
        request.setAttribute("listaPros", listaPros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/listaPros.jsp");
        dispatcher.forward(request, response);
    }

    private void listaEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpresaDAO dao = new EmpresaDAO();
        List<Empresa> listaEmps = dao.getAll();
        request.setAttribute("listaEmps", listaEmps);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/listaEmps.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastroEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/form_empresa.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastroProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/form_profissional.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long cnpj = Long.parseLong(request.getParameter("cnpj"));
        EmpresaDAO dao = new EmpresaDAO();
        Empresa emp = dao.get(cnpj);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/form_empresa.jsp");
        request.setAttribute("empresa", emp);
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long cpf = Long.parseLong(request.getParameter("cpf"));
        ProfissionalDAO dao = new ProfissionalDAO();
        Profissional pro = dao.get(cpf);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/form_profissional.jsp");
        request.setAttribute("profissional", pro);
        dispatcher.forward(request, response);
    }

    private void insereEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpresaDAO dao = new EmpresaDAO();

        Long cnpj = Long.parseLong(request.getParameter("cnpj"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String descricao = request.getParameter("descricao");
        String cidade = request.getParameter("cidade");
        Empresa emp = new Empresa(email, senha, cnpj, nome, descricao, cidade);

        Empresa check = dao.get(cnpj);
        Erro erros = new Erro();
        if (check != null) {
            erros.add("Este CNPJ já existe no banco de dados!");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/cadastroEmpresa");
            rd.forward(request, response);
        } else {
            dao.insert(emp);
            response.sendRedirect("listaEmpresas");
        }
    }

    private void insereProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProfissionalDAO dao = new ProfissionalDAO();

        Long cpf = Long.parseLong(request.getParameter("cpf"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String nascimento = request.getParameter("nascimento");

        Profissional pro = new Profissional(email, senha, cpf, nome, telefone, sexo, nascimento);

        Profissional check = dao.get(cpf);
        Erro erros = new Erro();
        if (check != null) {
            erros.add("Este CPF já existe no banco de dados!");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/cadastroProfissional");
            rd.forward(request, response);
        } else {
            dao.insert(pro);
            response.sendRedirect("listaProfissionais");
        }
    }

    private void atualizaEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long cnpj = Long.parseLong(request.getParameter("cnpj"));
        EmpresaDAO dao = new EmpresaDAO();

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String descricao = request.getParameter("descricao");
        String cidade = request.getParameter("cidade");

        Empresa emp = new Empresa(email, senha, cnpj, nome, descricao, cidade);

        dao.update(emp);
        response.sendRedirect("listaEmpresas");
    }

    private void atualizaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long cpf = Long.parseLong(request.getParameter("cpf"));
        ProfissionalDAO dao = new ProfissionalDAO();

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String nascimento = request.getParameter("nascimento");

        Profissional pro = new Profissional(email, senha, cpf, nome, telefone, sexo, nascimento);

        dao.update(pro);
        response.sendRedirect("listaProfissionais");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Long cpf = Long.parseLong(request.getParameter("cpf"));
            ProfissionalDAO dao = new ProfissionalDAO();
            Profissional pro = new Profissional(cpf);
            dao.delete(pro);
            response.sendRedirect("listaProfissionais");
        } catch (NumberFormatException nfe) {
            Long cnpj = Long.parseLong(request.getParameter("cnpj"));
            EmpresaDAO dao = new EmpresaDAO();
            Empresa emp = new Empresa(cnpj);
            dao.delete(emp);
            response.sendRedirect("listaEmpresas");
        }
    }
}