package br.com.fiap.view;

import br.com.fiap.controller.FilmeController;
import br.com.fiap.model.dto.Filme;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class FilmeView {

    public static void main(String[] args) {
        FilmeController controller = new FilmeController();
        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU FILMES =====");
            System.out.println("1 - Inserir filme");
            System.out.println("2 - Alterar filme");
            System.out.println("3 - Excluir filme");
            System.out.println("4 - Listar filmes");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scan.nextInt();
                scan.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Título: ");
                        String titulo = scan.nextLine();
                        System.out.print("Gênero: ");
                        String genero = scan.nextLine();
                        System.out.print("Produtora: ");
                        String produtora = scan.nextLine();
                        try {
                            System.out.println(controller.inserirFilme(titulo, genero, produtora));
                        } catch (ClassNotFoundException | SQLException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Código do filme: ");
                        int codigoAlterar = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Novo título: ");
                        String novoTitulo = scan.nextLine();
                        System.out.print("Novo gênero: ");
                        String novoGenero = scan.nextLine();
                        System.out.print("Nova produtora: ");
                        String novaProdutora = scan.nextLine();
                        try {
                            System.out.println(controller.alterarFilme(codigoAlterar, novoTitulo, novoGenero, novaProdutora));
                        } catch (ClassNotFoundException | SQLException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Código do filme: ");
                        int codigoExcluir = scan.nextInt();
                        scan.nextLine();
                        try {
                            System.out.println(controller.excluirFilme(codigoExcluir));
                        } catch (ClassNotFoundException | SQLException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 4:
                        try {
                            ArrayList<Filme> listaFilme = controller.listarTodosFilmes();
                            if (listaFilme != null && !listaFilme.isEmpty()) {
                                String exibe = "Listagem de Filmes\n\n";
                                for (Filme filme : listaFilme) {
                                    exibe += String.format("Código: %d | Título: %s | Gênero: %s | Produtora: %s\n",
                                            filme.getCodigo(), filme.getTitulo(), filme.getGenero(), filme.getProdutora());
                                }
                                JOptionPane.showMessageDialog(null, exibe, "Listagem de Filmes",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nenhum filme cadastrado.",
                                        "Listagem de Filmes", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (ClassNotFoundException | SQLException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.println("Fim de programa. Volte sempre!");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Formato de número incorreto!");
                scan.nextLine();
                opcao = 0;
            }

            if (opcao != 5) {
                System.out.print("Deseja continuar? (S/N): ");
                String continuar = scan.nextLine();
                if (continuar.equalsIgnoreCase("N")) {
                    System.out.println("Fim de programa. Volte sempre!");
                    break;
                }
            }
        } while (opcao != 5);

        scan.close();
    }
}