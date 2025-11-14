package org.example.service;

import org.example.infraestrutura.repository.EmprestimoRepository;
import org.example.infraestrutura.repository.LivroRepository;
import org.example.model.Emprestimo;
import org.example.view.BibliotecaView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmprestimoService {
    BibliotecaView bibliotecaView = new BibliotecaView();
    int opcao = 0;

    public void gerenciadorEmprestimos(){
        do {
            opcao = bibliotecaView.menuEmprestimos();

            switch (opcao) {
                case 1 ->{
                    cadastrarEmprestimo();
                }
                case 2 ->{
                    registrarDevolucao();
                }
                case 3 ->{
                    consultarEmprestimos();
                }
                case 0 ->{
                    bibliotecaView.mostrarMenu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        }while (opcao !=0);

    }

    public void cadastrarEmprestimo() {
        int idUsuario = bibliotecaView.solicitarIdUsuario();
        int idLivro = bibliotecaView.solicitarIdLivro();

        if (idUsuario <= 0 && idLivro <= 0) {
            bibliotecaView.mensagemErro();
        } else {
            try {
                Emprestimo novoEmprestimo = new Emprestimo();

                novoEmprestimo.setUsuarioId(idUsuario);
                novoEmprestimo.setLivroId(idLivro);

                EmprestimoRepository emprestimoRepository = new EmprestimoRepository();

                int idNovoEmprestimo = emprestimoRepository.registrarEmprestimo(novoEmprestimo);

                LivroRepository livroRepository = new LivroRepository();

                if (idNovoEmprestimo > 0) {
                    bibliotecaView.mensagemSucesso();
                    livroRepository.atualizaStatusFalse(idLivro);
                } else {
                    bibliotecaView.mensagemErroCadastro("empréstimo");
                }
            } catch (SQLException e) {
                bibliotecaView.mensagemErroCadastro("empréstimo");
                e.printStackTrace();
            }
        }
    }

    public void consultarEmprestimos() {
        try{
            EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
            Map<Integer, String> emprestimos = emprestimoRepository.listarTodosOsEmprestimos();
            BibliotecaView bibliotecaView = new BibliotecaView();
            bibliotecaView.consultarEmprestimos(emprestimos);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void registrarDevolucao() {

        int idEmprestimo = bibliotecaView.solicitarIdEmprestimo();
        LocalDate devolucao = bibliotecaView.solicitarDataDevolucao();

        try {
            EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
            emprestimoRepository.registrarDevolucao(idEmprestimo, devolucao);
            bibliotecaView.mensagemDevolucaoSucesso();
            LivroRepository livroRepository = new LivroRepository();
            int idLivro = emprestimoRepository.selectIDEmprestimos(idEmprestimo);
            livroRepository.atualizaStatusTrue(idLivro);
        } catch (SQLException e) {
            bibliotecaView.mensagemErroCadastro("devolução");
            e.printStackTrace();
        }
    }
}
