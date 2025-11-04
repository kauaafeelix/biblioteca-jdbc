package org.example.service;

import org.example.view.BibliotecaView;

public class EmprestimoService {
    BibliotecaView bibliotecaView = new BibliotecaView();
    int opcao = bibliotecaView.menuEmprestimos();

    public void gerenciadorEmprestimos(){
        switch (opcao) {
            case 1 ->{
                cadastrarEmprestimo();
            }
            case 2 ->{
                listarEmprestimos();
            }
            case 3 ->{
                registrarDevolucao();
            }
            case 4 ->{
                bibliotecaView.mostrarMenu();
            }
            default -> System.out.println("Opção inválida. Tente novamente.");

        }
    }
    public static void cadastrarEmprestimo() {

    }

    public static void listarEmprestimos() {

    }

    public static void registrarDevolucao() {

    }
}
