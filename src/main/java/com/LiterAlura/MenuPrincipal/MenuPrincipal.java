package com.LiterAlura.MenuPrincipal;


import javax.xml.transform.Source;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner leitura = new Scanner(System.in);

    /*
      Menu com opções do console para API de Livros Guntendex
     */
    public void MenuAplicacao() {

        var opcao = -1;

        while (opcao != 0 ) {

            var menu="""
                    *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                        Catálogo de Livros - LiterAlura  Autores , livros por idioma
                    *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                    *-*-*-*                                                         *-*-*-*
                    *-*-*-*     Selecione uma opção informando o número:            *-*-*-*
                    *-*-*-*                                                         *-*-*-*
                    *-*-*-*        1 - Buscar Livro pelo Título                     *-*-*-*
                    *-*-*-*        2 - Listar Livros Regitrados                     *-*-*-*
                    *-*-*-*        3 - Listar Autores registrados                   *-*-*-*
                    *-*-*-*        4 - Listar Autores Vivos em determinado ano      *-*-*-*
                    *-*-*-*        5 - Listar livor por Idioma                       *-*-*-*
                    *-*-*-*        0 - Sair                                         *-*-*-*
                    *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    pesquisarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    pesquisarAutorPorAno();
                case 5:
                    listarLivrosProIdioma();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação!!!");
                    break;
                default:
                    System.out.println("Opção invalida. Inform opção valida !");
            }
        }
    }

     /**
     * Método Pesquisa livro na API Guntedex
     */
   private void pesquisarLivro() {
        System.out.println("Pesuisar Livro");
    }

    /**
     *  Método exebi os livros da base de dados
     */
    private void listarLivros() {
        System.out.println("Livros registrado no LiterAlura");
    }

    /**
     * Métddo exebir os autores
     */
    private void listarAutores() {
        System.out.println("litagem de autores");
    }

    /**
     * Método exevir autores por ano
     */
    private void pesquisarAutorPorAno() {
        System.out.println("Autores por ano");
    }

    /**
     *  Método exiber livro por Idioma
     */
    private void listarLivrosProIdioma() {
        System.out.println("Livro por idioma");
    }

}
