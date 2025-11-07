package org.example.infraestrutura.repository;

import org.example.infraestrutura.Conexao;
import org.example.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                int anoPublicacao = rs.getInt("ano_publicacao");
                boolean disponivel = rs.getBoolean("disponivel");
                Livro livro = new Livro(idLivro, titulo, autor, anoPublicacao, disponivel);
                livros.add(livro);
            }
        }
        return livros;
    }
}
