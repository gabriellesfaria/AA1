package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {    
        String sql = "INSERT INTO Profissional (email, senha, cpf, nome, telefone, sexo, nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;    
            statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setLong(3, profissional.getCpf());
            statement.setString(4, profissional.getNome());
            statement.setString(5, profissional.getTelefone());
            statement.setString(6, profissional.getSexo());
            statement.setString(7, profissional.getNascimento());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Profissional> getAll() {   
        List<Profissional> listaProfissionais = new ArrayList<>();
        String sql = "SELECT * from Profissional p";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String email = resultSet.getString("email");
            	String senha = resultSet.getString("senha");
            	Long cpf = resultSet.getLong("cpf");
            	String nome = resultSet.getString("nome");
            	String telefone = resultSet.getString("telefone");
            	String sexo = resultSet.getString("sexo");
            	String nascimento = resultSet.getString("nascimento");
                Profissional profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, nascimento);
                listaProfissionais.add(profissional);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }
    
    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Profissional where cpf = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, profissional.getCpf());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }
    
    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET email = ?, senha = ?, nome = ?, telefone = ?, sexo = ?, nascimento = ? WHERE cpf = ?";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getNome());
            statement.setString(4, profissional.getTelefone());
            statement.setString(5, profissional.getSexo());
            statement.setString(6, profissional.getNascimento());
            statement.setLong(7, profissional.getCpf());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Profissional getbyID(Long cpf) {
    	Profissional profissional = null;
        String sql = "SELECT * from Profissional WHERE cpf = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String email = resultSet.getString("email");
             	String senha = resultSet.getString("senha");
             	String nome = resultSet.getString("nome");
             	String telefone = resultSet.getString("telefone");
             	String sexo = resultSet.getString("sexo");
             	String nascimento = resultSet.getString("nascimento");
                profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, nascimento);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    
	public Profissional get(Long cpf) {
        Profissional profissional = null;

        String sql = "SELECT * from Profissional p WHERE p.cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String email = resultSet.getString("email");
             	String senha = resultSet.getString("senha");
             	String nome = resultSet.getString("nome");
             	String telefone = resultSet.getString("telefone");
             	String sexo = resultSet.getString("sexo");
             	String nascimento = resultSet.getString("nascimento");

                profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    
    public Profissional getByEmail(String email) {
    	Profissional profissional = null;
        String sql = "SELECT * from Profissional WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
             	String senha = resultSet.getString("senha");
             	Long cpf = resultSet.getLong("cpf");
             	String nome = resultSet.getString("nome");
             	String telefone = resultSet.getString("telefone");
             	String sexo = resultSet.getString("sexo");
             	String nascimento = resultSet.getString("nascimento");
                profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, nascimento);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
}