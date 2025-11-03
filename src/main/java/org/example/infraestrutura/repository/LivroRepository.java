package org.example.infraestrutura.repository;

import org.example.infraestrutura.Conexao;
import org.example.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivroRepository {

    public void inserirLivro(Livro livro) throws SQLException {
        String sql = """
                INSERT INTO livros 
                (titulo,
                autor,
                ano_publicacao,
                disponivel) 
                VALUES (?, ?, ?, true)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAnoPublicacao());
            ps.executeUpdate();
        }
    }
}
