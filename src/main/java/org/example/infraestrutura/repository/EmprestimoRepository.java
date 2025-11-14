package org.example.infraestrutura.repository;

import com.mysql.cj.protocol.Resultset;
import org.example.infraestrutura.Conexao;
import org.example.model.Emprestimo;
import org.example.service.EmprestimoService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EmprestimoRepository {

    public int registrarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = """
        INSERT INTO emprestimos (
            livro_id,
            usuario_id,
            data_emprestimo,
            data_devolucao
        ) VALUES (?, ?, NOW(), null)
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, emprestimo.getLivroId());
            ps.setInt(2, emprestimo.getUsuarioId());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    emprestimo.setId(idGerado);
                    return idGerado;
                }
            }
        }
        return 0;
    }
    public Map<Integer, String> listarEmprestimosSemDevolucao() throws SQLException{

        Map<Integer, String>emprestimos = new ConcurrentHashMap<>();

        String sql = """
                SELECT
                  e.id,
                  l.titulo AS nome_livro,
                  u.nome AS nome_usuario,
                  e.data_emprestimo
                  FROM emprestimos e
                  JOIN livros l ON e.livro_id = l.id
                  JOIN usuarios u ON e.usuario_id = u.id
                  WHERE e.data_devolucao IS NULL
                """;


        try(Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int idEmprestimo = rs.getInt("id");
                String nomeLivro = rs.getString("nome_livro");
                String nomeUsuario = rs.getString("nome_usuario");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();

                emprestimos.put(idEmprestimo, "Livro: " + nomeLivro + ", Usuário: " + nomeUsuario + ", Data Empréstimo: " + dataEmprestimo);
            }
        }

        return emprestimos;
    }

    public Map<Integer, String> listarTodosOsEmprestimos() throws SQLException{

        Map<Integer, String>emprestimos = new ConcurrentHashMap<>();

        String sql = """
                SELECT
                  e.id,
                  l.titulo AS nome_livro,
                  u.nome AS nome_usuario,
                  e.data_emprestimo,
                  e.data_devolucao
                  FROM emprestimos e
                  JOIN livros l ON e.livro_id = l.id
                  JOIN usuarios u ON e.usuario_id = u.id
                  WHERE e.data_devolucao IS NULL
                """;


        try(Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int idEmprestimo = rs.getInt("id");
                String nomeLivro = rs.getString("nome_livro");
                String nomeUsuario = rs.getString("nome_usuario");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null;

                emprestimos.put(idEmprestimo, "Livro: " + nomeLivro + ", Usuário: " + nomeUsuario + ", Data Empréstimo: " + dataEmprestimo + ", Data Devolução: " + dataDevolucao);
            }
        }

        return emprestimos;
    }

    public void registrarDevolucao (int idEmprestimo, LocalDate devolucao)throws SQLException{
        String sql = """
                UPDATE emprestimos
                SET data_devolucao = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, Date.valueOf(devolucao));
            ps.setInt(2, idEmprestimo);
            ps.executeUpdate();
            }
        }

    public int selectIDEmprestimos(int id) throws SQLException{
        String query = "SELECT livro_id FROM emprestimos WHERE id = ?";

        int idLivro = 0;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()){
                    idLivro = rs.getInt("livro_id");
                }
            }
        }

        return idLivro;
    }
}
