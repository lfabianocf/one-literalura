package com.LiterAlura.MenuPrincipal;


import com.LiterAlura.model.Autor;
import com.LiterAlura.model.DadosLivro;
import com.LiterAlura.model.Livro;
import com.LiterAlura.service.ConverteDados;
import com.LiterAlura.service.RequisicaoApi;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner leitura = new Scanner(System.in);

    private RequisicaoApi requisicaoApi = new RequisicaoApi();

    private ConverteDados converteDados = new ConverteDados();

    private final String urlApi = "https://gutendex.com/books/";

    private List<Livro> livros;

    private List<Autor> autores;

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
                    *-*-*-*        5 - Listar livor por Idioma                      *-*-*-*
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
     *  chama : getDadosLivro()
     */
   private void pesquisarLivro() {


       DadosLivro  dadosLivro = getDadosLivro();

       Livro livro = new Livro(dadosLivro.resultados().get(0));

       Autor autor = new Autor(dadosLivro.resultados().get(0).listAutor().get(0));


      // System.out.println(livro.getTitulo());

       System.out.println("*-*-*-* LIVRO *-*-*-*");
       System.out.println("Título: " + livro.getTitulo());
       System.out.println("Autor: " + livro.getNomeAutor());
       System.out.println("Idioma: " + livro.getLinguagem());
       System.out.println("Número de Download: " + livro.getContDownloads());
       System.out.println("*-*-*-*-*-*-*-*");



   }

    /**
     * Pesqquisar Livro na API Gutendex
     * @return dadoslivro
     */
    private DadosLivro getDadosLivro() {

        System.out.println("Informe o nome do livro a ser pesquisado:");

        var busca = leitura.nextLine().toLowerCase().replace(" ", "+");

        System.out.println(urlApi + "?search=" + busca);

        var json = requisicaoApi.obterDados(urlApi + "?search=" + busca);

        DadosLivro  dadosLivro = converteDados.obterDados(json, DadosLivro.class);

        System.out.println(dadosLivro);

        return  dadosLivro;
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
