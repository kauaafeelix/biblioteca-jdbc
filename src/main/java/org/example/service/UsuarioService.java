package org.example.service;

import org.example.infraestrutura.repository.UsuarioRepository;
import org.example.model.Usuario;
import org.example.view.BibliotecaView;

import java.sql.SQLException;

public class UsuarioService {

    BibliotecaView bibliotecaView = new BibliotecaView();
    int opcao = 0;

    public void gerenciadorUsuarios() {

        opcao = bibliotecaView.menuUsuarios();

        switch (opcao){
            case 1 -> {
                cadastrarUsuario();
            }
            case 2 -> {
                listarUsuarios();
            }
            case 3 -> {
                bibliotecaView.mostrarMenu();
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public void cadastrarUsuario() {

        Usuario usuario = bibliotecaView.cadastrarUsuario();
        try{
            var usuarioRepository = new UsuarioRepository();
            usuarioRepository.inserirUsuario(usuario);
            bibliotecaView.mensagemSucesso();
        } catch (SQLException e) {
            bibliotecaView.mensagemErro();
            e.printStackTrace();
        }

    }

    public void listarUsuarios() {

    }
}
