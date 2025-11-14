package org.example.infraestrutura.repository;

import org.example.infraestrutura.Conexao;
import org.example.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    public void inserirLivro(Livro livro) throws SQLException {
        String sql = """
                INSERT INTO livros 
                (titulo,
                autor,
                ano,
                disponivel) 
                VALUES (?, ?, ?, true)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAnoPublicacao());
            ps.executeUpdate();
        }
    }

    public List<Livro> listarLivros() throws SQLException{

        List <Livro> livros = new ArrayList<>();

        String sql = """
                SELECT 
                id,
                titulo,
                autor,
                ano,
                disponivel 
                FROM livros
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int idLivro = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoPublicacao = rs.getInt("ano");
                boolean disponivel = rs.getBoolean("disponivel");
                Livro livro = new Livro(idLivro, titulo, autor, anoPublicacao, disponivel);
                livros.add(livro);
            }
        }
        return livros;
    }

    public void atualizaStatusFalse(int idLivro) throws SQLException {
        String sql = """
                UPDATE livros
                SET disponivel = false
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLivro);
            ps.executeUpdate();
        }
    }
    public void atualizaStatusTrue(int idLivro) throws SQLException {
        String sql = """
                UPDATE livros
                SET disponivel = true
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLivro);
            ps.executeUpdate();
        }
    }
}
