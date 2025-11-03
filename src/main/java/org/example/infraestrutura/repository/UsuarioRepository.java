package org.example.infraestrutura.repository;

import org.example.infraestrutura.Conexao;
import org.example.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
