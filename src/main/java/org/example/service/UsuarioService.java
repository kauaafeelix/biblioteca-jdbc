package org.example.service;

import org.example.infraestrutura.repository.UsuarioRepository;
import org.example.model.Usuario;
import org.example.view.BibliotecaView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    BibliotecaView bibliotecaView = new BibliotecaView();
    int opcao = 0;

    public void gerenciadorUsuarios() {
        do {
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
        }while (opcao!= 3);

    }

    public void cadastrarUsuario() {

        Usuario usuario = bibliotecaView.cadastrarUsuario();
        try{
            var usuarioRepository = new UsuarioRepository();
            usuarioRepository.inserirUsuario(usuario);
            bibliotecaView.mensagemSucesso();
        } catch (SQLException e) {
            bibliotecaView.mensagemErroCadastro("usuário");
            e.printStackTrace();
        }

    }

    public void listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
            try {
                var usuarioRepository = new UsuarioRepository();
                usuarios = usuarioRepository.listarUsuarios();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!usuarios.isEmpty()) {
                bibliotecaView.listarUsuarios(usuarios);
            } else {
                bibliotecaView.mensagemListaVazia("usuário");
            }
        }

    }
