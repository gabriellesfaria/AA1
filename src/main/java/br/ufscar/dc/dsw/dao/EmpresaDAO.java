package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;

public class EmpresaDAO extends GenericDAO {

    public void insert(Empresa empresa) {    
        String sql = "INSERT INTO Empresa (email, senha, cnpj, nome, descricao, cidade) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;    
            statement = conn.prepareStatement(sql);
            statement.setString(1, empresa.getEmail());
            statement.setString(2, empresa.getSenha());
            statement.setLong(3, empresa.getCnpj());
            statement.setString(4, empresa.getNome());
            statement.setString(5, empresa.getDescricao());
            statement.setString(6, empresa.getCidade());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
   
    public List<Empresa> getAll() { // pega todas as empresas cadastradas no sistema 
        List<Empresa> listaEmpresas = new ArrayList<>();
        String sql = "SELECT * from Empresa";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String email = resultSet.getString("email");
            	String senha = resultSet.getString("senha");
                long cnpj = resultSet.getLong("cnpj");
            	String nome = resultSet.getString("nome");
            	String descricao = resultSet.getString("descricao");
            	String cidade = resultSet.getString("cidade");
            	Empresa empresa = new Empresa(email, senha, cnpj, nome, descricao, cidade);
                listaEmpresas.add(empresa);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpresas;
    }
    
    public void delete(Empresa empresa) {
        String sql = "DELETE FROM Empresa where cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, empresa.getCnpj());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }
    
    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa SET email = ?, senha = ?, nome = ?, descricao = ?, cidade = ? WHERE cnpj = ?";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, empresa.getEmail());
            statement.setString(2, empresa.getSenha());
            statement.setString(3, empresa.getNome());
            statement.setString(4, empresa.getDescricao());
            statement.setString(5, empresa.getCidade());
            statement.setLong(6, empresa.getCnpj());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Empresa get(Long cnpj) {
    	Empresa empresa = null;
        String sql = "SELECT * from Empresa WHERE cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String email = resultSet.getString("email");
             	String senha = resultSet.getString("senha");
             	String nome = resultSet.getString("nome");
             	String descricao = resultSet.getString("descricao");
             	String cidade = resultSet.getString("cidade");
                empresa = new Empresa(email, senha, cnpj, nome, descricao, cidade);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }
    
    public Empresa getByEmail(String email) {
        Empresa empresa = null;

        String sql = "SELECT * from Empresa WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
             	String senha = resultSet.getString("senha");
             	Long cnpj = resultSet.getLong("cnpj");
             	String nome = resultSet.getString("nome");
             	String descricao = resultSet.getString("descricao");
             	String cidade = resultSet.getString("cidade");

                empresa = new Empresa(email, senha, cnpj, nome, descricao, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }
}