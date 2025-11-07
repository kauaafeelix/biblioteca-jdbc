package org.example.service;

import org.example.infraestrutura.repository.LivroRepository;
import org.example.model.Livro;
import org.example.model.Usuario;
import org.example.view.BibliotecaView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    BibliotecaView bibliotecaView = new BibliotecaView();

    int opcao = 0;

    public void gerenciadorLivros() {

        do {
            opcao = bibliotecaView.mostrarMenu();

            switch (opcao) {
                case 1 -> {
                    cadastrarLivro();
                }
                case 2 -> {
                    listarLivros();
                }
                case 3 -> {
                    bibliotecaView.mostrarMenu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");

            }
        }while (opcao != 3);
    }

    public void cadastrarLivro() {
        Livro livro = bibliotecaView.cadastrarLivro();

        try{
            var livroRepository = new LivroRepository();
            livroRepository.inserirLivro(livro);
            bibliotecaView.mensagemSucesso();
        } catch (SQLException e) {
            bibliotecaView.mensagemErroCadastro("livro");
            e.printStackTrace();
        }
    }

    public void listarLivros() {
        List<Usuario>usuarios = new ArrayList<>();
        try {
            var livroRepository = new LivroRepository();
            var livros = livroRepository.listarLivros();
            bibliotecaView.listarLivros(livros);
        } catch (SQLException e) {
            bibliotecaView.mensagemErro();
            e.printStackTrace();
        }
        if (usuarios.isEmpty()) {
            bibliotecaView.mensagemListaVazia("livro");
        }
    }
}
