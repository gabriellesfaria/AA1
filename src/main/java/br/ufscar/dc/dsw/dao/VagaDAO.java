package br.ufscar.dc.dsw.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;


public class VagaDAO extends GenericDAO {

    public void insert(Vaga vaga) {    
        String sql = "INSERT INTO Vaga (nome, status, descricao, salario, data_limite, empresa) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;    
            statement = conn.prepareStatement(sql);
            statement.setString(1, vaga.getNome());
            statement.setString(2, vaga.getStatus());
            statement.setString(3, vaga.getDescricao());
            statement.setFloat(4, vaga.getSalario());
            statement.setString(5, vaga.getDataLimite());
            statement.setLong(6, vaga.getEmpresa());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void delete(Vaga vaga) {
        String sql = "DELETE FROM Vaga where id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, vaga.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }
    
    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET nome = ?, status = ?, descricao = ?, salario = ?, data_limite = ?, empresa = ? WHERE id = ?";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, vaga.getNome());
            statement.setString(2, vaga.getStatus());
            statement.setString(3, vaga.getDescricao());
            statement.setFloat(4, vaga.getSalario());
            statement.setString(5, vaga.getDataLimite());
            statement.setLong(6, vaga.getEmpresa());
            statement.setLong(7, vaga.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	
    public List<Vaga> getAll() {   
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * from Vaga v, Empresa e WHERE v.empresa = e.cnpj ORDER BY v.id";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String status = resultSet.getString("status");
                String descricao = resultSet.getString("descricao");
            	Float salario = resultSet.getFloat("salario");
            	String data_limite = resultSet.getString("data_limite");
            	Long empresa = resultSet.getLong(6);
            	Vaga vaga = new Vaga(id, nome, status, descricao, salario, data_limite, empresa);
                listaVagas.add(vaga);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }  public List<Vaga> getAllAberta() {   
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * from Vaga v, Empresa e WHERE v.status = \"ABERTA\" AND v.empresa = e.cnpj ORDER BY v.id";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String status = resultSet.getString("status");
                String descricao = resultSet.getString("descricao");
            	Float salario = resultSet.getFloat("salario");
            	String data_limite = resultSet.getString("data_limite");
            	Long empresa = resultSet.getLong(7);
            	Vaga vaga = new Vaga(id, nome, status, descricao, salario, data_limite, empresa);
                listaVagas.add(vaga);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }  
    
    public Vaga get(Long id) {
    	Vaga vaga = null;
        String sql = "SELECT * from Vaga v, Empresa e WHERE v.id = ? AND e.cnpj = v.empresa";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long v_id = resultSet.getLong(1);
                String nome = resultSet.getString("nome");
                String status = resultSet.getString("status");
                String descricao = resultSet.getString("descricao");
            	Float salario = resultSet.getFloat("salario");
            	String data_limite = resultSet.getString("data_limite");
            	Long empresa = resultSet.getLong(7);
            	vaga = new Vaga( nome, status, descricao, salario, data_limite, empresa);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaga;
    }
    public List<Vaga> getByEmpresa(Long id_empresa) { // pega todas as vagas de certa empresa  
        List<Vaga> listaVagasEmpresa = new ArrayList<>();
        String sql = "SELECT * from Vaga v, Empresa e WHERE v.empresa = ? AND v.empresa = e.cnpj";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_empresa);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String status = resultSet.getString("status");
                String descricao = resultSet.getString("descricao");
            	Float salario = resultSet.getFloat("salario");
            	String data_limite = resultSet.getString("data_limite");
            	Vaga vaga = new Vaga(id, nome, status, descricao, salario, data_limite, id_empresa);
            	listaVagasEmpresa.add(vaga);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagasEmpresa;
    }
    
}