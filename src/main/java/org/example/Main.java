package org.example;

import org.example.model.Usuario;
import org.example.service.EmprestimoService;
import org.example.service.LivroService;
import org.example.service.UsuarioService;
import org.example.view.BibliotecaView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();
        LivroService livroService = new LivroService();
        EmprestimoService emprestimoService = new EmprestimoService();

        int escolha = 0;

        do {
            var bibliotecaView = new BibliotecaView();
            escolha = bibliotecaView.mostrarMenu();

            switch (escolha) {

                case 1 -> {
                    usuarioService.gerenciadorUsuarios();
                }
                case 2 -> {
                    livroService.gerenciadorLivros();
                }
                case 3 -> {
                    emprestimoService.gerenciadorEmprestimos();
                }
                case 4 -> {
                    System.out.println("Saindo do sistema. Até logo!");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");

            }
        } while (escolha != 4);

    }
}