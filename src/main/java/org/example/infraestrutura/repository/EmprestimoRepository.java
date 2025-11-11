package org.example.infraestrutura.repository;

import com.mysql.cj.protocol.Resultset;
import org.example.infraestrutura.Conexao;
import org.example.model.Emprestimo;
import org.example.service.EmprestimoService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

            int rows = ps.executeUpdate();
            if (rows == 0) {
                return 0;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    emprestimo.setId(idGerado);
                    return idGerado;
                }
            }
        }

        return 0;
    }
    public List<Emprestimo>listarEmprestimos() throws SQLException{

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
                """;

        List<Emprestimo>emprestimos = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int idEmprestimo = rs.getInt("id");
                int nomeLivro = rs.getInt("nome_livro");
                int nomeUsuario = rs.getInt("nome_usuario");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = null;
                if(rs.getDate("data_devolucao") != null){
                    dataDevolucao = rs.getDate("data_devolucao").toLocalDate();
                }

                Emprestimo emprestimo = new Emprestimo(idEmprestimo, nomeLivro, nomeUsuario, dataEmprestimo, dataDevolucao);
                emprestimos.add(emprestimo);
            }
        }

        return emprestimos;
    }
}
