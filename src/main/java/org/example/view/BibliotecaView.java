package org.example.view;

import org.example.model.Emprestimo;
import org.example.model.Livro;
import org.example.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BibliotecaView {

    Scanner scNum = new Scanner(System.in);
    Scanner scStr = new Scanner (System.in);

    public int mostrarMenu(){
        System.out.println("\n===== Biblioteca =====");
        System.out.println("1 - Gerenciar Usuários");
        System.out.println("2 - Gerenciar Livros");
        System.out.println("3 - Gerenciar Empréstimos");
        System.out.println("0 - Sair");
        int opcao = capturarOpcao();
        return opcao;
    }

    public int capturarOpcao(){
        System.out.print("Escolha uma opção: ");
        return scNum.nextInt();
    }

    public int menuLivros(){
        System.out.println("\n===== Gerenciar Livros =====");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Listar Livros");
        System.out.println("0 - Voltar ao Menu Principal");
        int opcao = capturarOpcao();
        return opcao;
    }

    public Livro cadastrarLivro(){
        System.out.println("\n===== Cadastrar Livro =====");

        System.out.println("Digite o título do livro:");
        String nomeLivro = scStr.nextLine();

        System.out.println("Digite o autor do livro:");
        String autorLivro = scStr.nextLine();

        System.out.println("Digite o ano de publicação do livro:");
        int anoPublicacao = scNum.nextInt();

        var novoLivro = new Livro(nomeLivro, autorLivro, anoPublicacao);
        return novoLivro;
    }

    public void listarLivros(List<Livro> livros){
        System.out.println("\n===== Listar Livros =====");
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

    public int menuUsuarios(){
        System.out.println("\n===== Gerenciar Usuários =====");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Listar Usuários");
        System.out.println("0 - Voltar ao Menu Principal");
        int opcao = capturarOpcao();
        return opcao;
    }

    public Usuario cadastrarUsuario(){
        System.out.println("\n===== Cadastrar Usuário =====");

        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scStr.nextLine();

        System.out.println("Digite o email do usuário:");
        String emailUsuario = scStr.nextLine();

        Usuario novoUsuario = new Usuario(nomeUsuario, emailUsuario);
        return novoUsuario;
    }

    public void listarUsuarios(List<Usuario> usuarios){
        System.out.println("\n  ===== Listar Usuários =====");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("---------------------------");
        }
    }

    public int menuEmprestimos(){
        System.out.println("\n===== Gerenciar Empréstimos =====");
        System.out.println("1 - Registrar Empréstimo");
        System.out.println("2 - Registrar Devolução");
        System.out.println("3 - Listar Empréstimos");
        System.out.println("0 - Voltar ao Menu Principal");

        int opcao = capturarOpcao();
        return opcao;
    }

    public int solicitarIdUsuario(){
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = scNum.nextInt();
        return idUsuario;
    }

    public int solicitarIdLivro(){
        System.out.print("Digite o ID do livro: ");
        int idLivro = scNum.nextInt();
        return idLivro;
    }

    public void listarEmprestimos(Map<Integer, String> emprestimos){
        for(Map.Entry<Integer, String> s:  emprestimos.entrySet()){
            System.out.println(s.getValue());
            System.out.println("|| ---------------------------------------------------");
        }
    }

    public void consultarEmprestimos(Map<Integer, String> emprestimos){
        System.out.println("\n|| ----- CONSULTAR EMPRÉSTIMOS ----- |");

        if(emprestimos.isEmpty()){
            System.out.println("\n|| [ERRO] -> Nenhum empréstimo encontrado!\n");
        }else{

            emprestimos.forEach((emprestimoId, emprestimo) -> {
                System.out.println(emprestimo);
                System.out.println("|| -------------------------------------------------");
            });
            System.out.println("");
        }
    }

    public int solicitarIdEmprestimo() {
        System.out.print("Digite o ID do empréstimo para registrar a devolução: ");
        int id = scNum.nextInt();
        return id;
    }

    public LocalDate solicitarDataDevolucao() {
        System.out.println("Digite a data de devolução (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scStr.nextLine());
        return data;
    }

    public void mensagemDevolucaoSucesso(){
        System.out.println("\n[OK] Devolução registrada com sucesso!");
    }

    public void mensagemSucesso(){
        System.out.println("\n[OK] Operação realizada com sucesso!");
    }

    public void mensagemErroCadastro(String entidade){
        System.err.print("\n[ERRO] Ocorreu um erro ao cadastrar um "+entidade+". "  );
        System.out.println("Por favor, tente novamente.");
    }

    public void mensagemErro(){
        System.err.print("\n[ERRO] Ocorreu um erro na operação."  );
        System.out.println("Por favor, tente novamente.");
    }

    public void mensagemListaVazia(String tipo){
        System.out.println("\n[AVISO] Nenhum " + tipo + " cadastrado.");
    }

}
