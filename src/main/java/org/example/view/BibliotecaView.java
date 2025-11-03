package org.example.view;

import java.util.Scanner;

public class BibliotecaView {

    static Scanner scNum = new Scanner(System.in);

    public static void mostrarMenu(){
        System.out.println("===== Biblioteca =====");
        System.out.println("1 - Gerenciar Livros");
        System.out.println("2 - Gerenciar Usuários");
        System.out.println("3 - Gerenciar Empréstimos");
        System.out.println("0 - Sair");
    }

    public static int capturarOpcao(){
        System.out.print("Escolha uma opção: ");
        return scNum.nextInt();
    }


}
