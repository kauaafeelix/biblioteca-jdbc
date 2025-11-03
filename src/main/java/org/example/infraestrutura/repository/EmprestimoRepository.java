package org.example.infraestrutura.repository;

import org.example.infraestrutura.Conexao;
import org.example.model.Emprestimo;
import org.example.service.EmprestimoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmprestimoRepository {

    public void registrarEmprestimo(Emprestimo emprestimo) throws SQLException {

        String sql = """
                INSERT INTO emprestimos (
                id_livro,
                id_usuario,
                data_emprestimo,
                data_devolucao)
                VALUES (?, ?, NOW(), null)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, emprestimo.getLivroId());
            ps.setInt(2, emprestimo.getUsuarioId());
            ps.executeUpdate();
        }
    }
}
