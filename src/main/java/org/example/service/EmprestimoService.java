package org.example.service;

import org.example.view.BibliotecaView;

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

        }while (opcao !=4);


    }
    public static void cadastrarEmprestimo() {

    }

    public static void listarEmprestimos() {

    }

    public static void registrarDevolucao() {

    }
}
