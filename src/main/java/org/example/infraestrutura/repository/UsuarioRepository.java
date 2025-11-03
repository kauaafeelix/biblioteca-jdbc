package org.example.infraestrutura.repository;

import org.example.infraestrutura.Conexao;
import org.example.model.Usuario;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public void inserirUsuario(Usuario usuario)throws SQLException{

        String sql = """
                INSERT INTO 
                usuarios (
                nome,
                email )
                VALUES (?, ?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.executeUpdate();
        }
    }

    public List<Usuario> listarUsuarios() throws SQLException{

        List<Usuario>usuarios = new ArrayList<>();

        String sql = """
                SELECT
                id, 
                nome,
                email
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int idUsuario = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                Usuario usuario = new Usuario(idUsuario, nome, email);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
