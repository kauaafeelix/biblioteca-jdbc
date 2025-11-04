package org.example.service;

import org.example.view.BibliotecaView;

public class LivroService {

    BibliotecaView bibliotecaView = new BibliotecaView();
    int opcao = bibliotecaView.menuLivros();

    public void gerenciadorLivros() {
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
    }

    public static void cadastrarLivro() {

    }

    public static void listarLivros() {

    }
}
