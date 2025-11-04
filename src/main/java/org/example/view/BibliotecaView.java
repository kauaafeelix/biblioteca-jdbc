package org.example.view;

import org.example.model.Livro;
import org.example.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaView {

    static Scanner scNum = new Scanner(System.in);
    static Scanner scStr = new Scanner (System.in);

    public void mostrarMenu(){
        System.out.println("===== Biblioteca =====");
        System.out.println("1 - Gerenciar Livros");
        System.out.println("2 - Gerenciar Usuários");
        System.out.println("3 - Gerenciar Empréstimos");
        System.out.println("0 - Sair");
    }

    public int capturarOpcao(){
        System.out.print("Escolha uma opção: ");
        return scNum.nextInt();
    }

    public int menuLivros(){
        System.out.println("===== Gerenciar Livros =====");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Listar Livros");
        System.out.println("0 - Voltar ao Menu Principal");
        return capturarOpcao();
    }

    public void cadastrarLivro(){
        System.out.println("===== Cadastrar Livro =====");

        System.out.println("Digite o título do livro:");
        String nomeLivro = scStr.nextLine();

        System.out.println("Digite o autor do livro:");
        String autorLivro = scStr.nextLine();

        System.out.println("Digite o ano de publicação do livro:");
        int anoPublicacao = scNum.nextInt();
    }

    public void listarLivros(){
        System.out.println("===== Listar Livros =====");
        List<Livro> livros = new ArrayList<>();
        for (Livro livro : livros) {
            System.out.println("ID: " + livro.getId());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println("Disponível: " + livro.isDisponivel());
            System.out.println("---------------------------");
        }
    }

//    ====================================================================================

    public void menuUsuarios(){
        System.out.println("===== Gerenciar Usuários =====");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Listar Usuários");
        System.out.println("0 - Voltar ao Menu Principal");
    }

    public void cadastrarUsuario(){
        System.out.println("===== Cadastrar Usuário =====");

        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scStr.nextLine();

        System.out.println("Digite o email do usuário:");
        String emailUsuario = scStr.nextLine();
    }

    public void listarUsuarios(){
        System.out.println("===== Listar Usuários =====");
        List<Usuario> usuarios = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("---------------------------");
        }
    }

    public int menuEmprestimos(){
        System.out.println("===== Gerenciar Empréstimos =====");
        System.out.println("1 - Registrar Empréstimo");
        System.out.println("2 - Registrar Devolução");
        System.out.println("3 - Listar Empréstimos");
        System.out.println("0 - Voltar ao Menu Principal");

        return capturarOpcao();
    }

}
