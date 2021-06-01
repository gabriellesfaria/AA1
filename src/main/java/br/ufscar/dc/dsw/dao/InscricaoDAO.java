package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;

public class InscricaoDAO extends GenericDAO {
	
    public void insert(String cv, Long profissional, Long vaga) {    
        String sql = "INSERT INTO Inscricao (cv, status, data_inscricao, profissional, vaga) VALUES (?, ?, ?, ?, ?)";
        try {
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     	   	LocalDateTime now = LocalDateTime.now();
     	   	String data = dtf.format(now);
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;    
            statement = conn.prepareStatement(sql);
            statement.setString(1, cv);
            statement.setString(2, "ABERTO");
            statement.setString(3, data);
            statement.setLong(4, profissional);
            statement.setLong(5, vaga);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // MUDAR
    public List<Inscricao> getAll() {   
        List<Inscricao> listaInscricoes = new ArrayList<>();
        String sql = "SELECT * from Inscricao i ORDER BY id";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            	String cv = resultSet.getString("cv");
            	String status = resultSet.getString("status");
             	String data_inscricao = resultSet.getString("data_inscricao");
             	Long profissional = resultSet.getLong("profissional");
                Long vaga = resultSet.getLong("vaga");
                Inscricao inscricao = new Inscricao(cv, status, data_inscricao, profissional, vaga);
                listaInscricoes.add(inscricao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaInscricoes;
    }
    
    public List<Inscricao> getByEmpresa(Long id) {   
        List<Inscricao> listaInscricoes = new ArrayList<>();
        String sql = "SELECT i.id, i.cv, i.status, i.data_inscricao, i.profissional, i.vaga from Empresa e, Inscricao i, Vaga v, Profissional p WHERE e.cnpj = ? AND v.empresa = e.cnpj AND i.vaga = v.id AND i.profissional = p.cpf ORDER BY v.id; ";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long i_id = resultSet.getLong("id");
             	String cv = resultSet.getString("cv");
                String status = resultSet.getString("status");
                String data_inscricao = resultSet.getString("data_inscricao");
                Long profissional = resultSet.getLong("profissional");
                Long vaga = resultSet.getLong("vaga");
                Inscricao inscricao = new Inscricao(i_id, cv, status, data_inscricao, profissional, vaga);
                listaInscricoes.add(inscricao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaInscricoes;
    }
    
    public void delete(Inscricao inscricao) {
        String sql = "DELETE FROM Inscricao where id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, inscricao.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }
    
    public void update(Inscricao inscricao) {
        String sql = "UPDATE Inscricao SET cv = ?, status = ?, data_inscricao = ?, profissional = ?, vaga = ? WHERE id = ?";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, inscricao.getCV());
            statement.setString(2, inscricao.getStatus());
            statement.setString(3, inscricao.getDataInscricao());
            statement.setLong(4, inscricao.getProfissional());
            statement.setLong(5, inscricao.getVaga());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateStatus(Long id, String status) {
        String sql = "UPDATE Inscricao SET status = ? WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, status);
            statement.setLong(2, id);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Inscricao get(Long id) {
    	Inscricao inscricao = null;
        String sql = "SELECT * from Inscricao WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
             	String cv = resultSet.getString("cv");
             	String status = resultSet.getString("status");
             	String data_inscricao = resultSet.getString("data_inscricao");
             	Long profissional = resultSet.getLong("profissional");
                Long vaga = resultSet.getLong("vaga");
                inscricao = new Inscricao(id, cv, status, data_inscricao, profissional, vaga);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inscricao;
    }
    
    public Long getIdProfissional(Long id) {
    	Inscricao inscricao = null;
    	Long profissional = 0l;
        String sql = "SELECT profissional from Inscricao WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	profissional = resultSet.getLong("profissional");
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    
    public List<Inscricao> getByProfissional(Long id) {
    	 List<Inscricao> listaInscricoes = new ArrayList<>();
         String sql = "SELECT * from Inscricao WHERE profissional = ?";
         try {
             Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             statement.setLong(1, id);
             ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()) {
                Long i_id = resultSet.getLong("id");
             	String cv = resultSet.getString("cv");
             	String status = resultSet.getString("status");
              	String data_inscricao = resultSet.getString("data_inscricao");
              	Long profissional = resultSet.getLong("profissional");
                Long vaga = resultSet.getLong("vaga");
                Inscricao inscricao = new Inscricao(i_id, cv, status, data_inscricao, profissional, vaga);
                listaInscricoes.add(inscricao);
             }
             resultSet.close();
             statement.close();
             conn.close();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return listaInscricoes;
    }
    
    
}