package myproject.weatherapi.model;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static myproject.weatherapi.util.DialogUtil.showError;

public class UsuarioDAO {

    public void cadastrarUsuario(UsuarioDTO usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?,?,?)";

        try (Connection con = new Conexao().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("Erro ao cadastrar o usuário");
        }
    }

    public boolean login(UsuarioDTO usuario) throws SQLException {
        String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection con = new Conexao().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    return true;
                }
                else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao fazer login: " + e.getMessage());
        }
    }

    public UsuarioDTO buscarEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (Connection con = new Conexao().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                // outros campos...
                return usuario;
            }
        }
        return null;
    }

    public void deletarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection con = new Conexao().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            showError("Erro ao deletar o usuário");
        }
    }

    public void atualizarUsuario(UsuarioDTO usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (Connection con = new Conexao().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getId());
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            showError("Erro ao atualizar o usuário");
        }
    }
}
