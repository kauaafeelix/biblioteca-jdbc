package org.example.infraestrutura;

import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            Conexao.conectar();
            System.out.println("Conexão bem-sucedida!");
        } catch (SQLException e) {
            System.out.println("Falha na conexão: " + e.getMessage());
        }
    }
}
