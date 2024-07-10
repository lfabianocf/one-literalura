package com.LiterAlura.MenuPrincipal;


import com.LiterAlura.model.Autor;
import com.LiterAlura.model.DadosLivro;
import com.LiterAlura.model.Livro;
import com.LiterAlura.repository.AutorRepository;
import com.LiterAlura.repository.LivroRepository;
import com.LiterAlura.service.ConverteDados;
import com.LiterAlura.service.RequisicaoApi;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner leitura = new Scanner(System.in);

    private RequisicaoApi requisicaoApi = new RequisicaoApi();

    private ConverteDados converteDados = new ConverteDados();

    private final String urlApi = "https://gutendex.com/books/";

    private List<Livro> livros;

    private List<Autor> autores;

    private LivroRepository livroRepository;

    private AutorRepository autorRepository;

    public MenuPrincipal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

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
                    *-*-*-*        5 - Listar livro por Idioma                      *-*-*-*
                    *-*-*-*        0 - Sair                                         *-*-*-*
                    *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                    """;

            try {
                System.out.println(menu);
                opcao = leitura.nextInt();
                leitura.nextLine();
            } catch (Exception ex) {
                System.out.println("Opção invalida. Tente novamente !");
            }

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
                    listarLivrosPorIdioma();
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

       if (dadosLivro.resultados().isEmpty()){
           System.out.println("Livro não encontrado!");
       } else {

           Livro livro = new Livro(dadosLivro.resultados().get(0));

           Autor autor = new Autor(dadosLivro.resultados().get(0).listAutor().get(0));


           // System.out.println(livro.getTitulo());

           System.out.println("*-*-*-* LIVRO *-*-*-*");
           System.out.println("Título: " + livro.getTitulo());
           System.out.println("Autor: " + livro.getNomeAutor());
           System.out.println("Idioma: " + livro.getLinguagem());
           System.out.println("Número de Download: " + livro.getContDownloads());
           System.out.println("*-*-*-*-*-*-*-*");

           livroRepository.save(livro);
           autorRepository.save(autor);
       }


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

        //System.out.println(dadosLivro);

        return  dadosLivro;
    }

    /**
     *  Método exebi os livros da base de dados
     */
    private void listarLivros() {

        System.out.println("Livros registrado no LiterAlura");

        //List<Livro> livros = livroRepository.findAll();
        livros = livroRepository.findAll();

        //System.out.println("*-*-*-*-*-* Livro *-*-*-*-*-*" + livros.toString());

        livros.stream().forEach(l -> {
            System.out.println("""
                *-*-*-* Livros Catalogaos *-*-*-*-*    
                    Título: %s
                    Autor: %s
                    Linguagem: %s
                    Downloads: %s
                *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

                    """.formatted(
                            l.getTitulo(),
                            l.getNomeAutor(),
                            l.getLinguagem(),
                            l.getContDownloads()
                            .toString()

            ));
        });

    }

    /**
     * Métddo exebir os autores
     */
    private void listarAutores() {

       System.out.println("Autores registrado no LiterAlura");

       //List<Autor> autores = autorRepository.findAll();

       autores = autorRepository.findAll();

        autores.forEach(System.out::println);

        autores.stream().forEach(a ->
                        System.out.println("""
                          *-*-*-* Autores Catalogaos *-*-*-*-*
                          Autor: %s
                          Ano de Nascimento: %s
                          Ano de Falecimento: %s
                          """.formatted(
                                a.getNomeAutor(),
                                a.getDataNascimento(),
                                a.getDataFalecimento()
                                )
                        )
                );


    }

    /**
     * Método exevir autores por ano
     */
    private void pesquisarAutorPorAno() {

        System.out.println("Pesquisa Autor por ano");
        System.out.println("Digite o ano a ser pesquisado");

        var ano = leitura.nextInt();
        leitura.nextLine();

        //List<Autor> autores = autorRepository.autorPorAno(ano);
        autores = autorRepository.autorPorAno(ano);

        System.out.println(autores);
        autores.stream().forEach(a ->
                System.out.println("""
                          *-*-*-* Autores Catalogados *-*-*-*-*
                          Autor: %s
                          Ano de Nascimento: %s
                          Ano de Falecimento: %s
                          *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                          """.formatted(
                                a.getNomeAutor(),
                                a.getDataNascimento(),
                                a.getDataFalecimento()
                        )
                )
        );
        MenuAplicacao();
    }

    /**
     *  Método exiber livro por Idioma
     */
    private void listarLivrosPorIdioma() {

        System.out.println("""
                    *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                    *-*-*-*     Listar livros por determinado idioma    *-*-*-*
                    *-*-*-*        informa sigla do idioma              *-*-*-*
                    *-*-*-*        en - Inglês                          *-*-*-*
                    *-*-*-*        es - espanhol                        *-*-*-*
                    *-*-*-*        fr - francês                         *-*-*-*
                    *-*-*-*        pt - português                       *-*-*-*
                     *-*-*-*       0 - Retorna menu                    *-*-*-*
                """);

        try {

            var siglaIdioma = leitura.nextLine();


            switch (siglaIdioma) {

                case "en":
                    livros = livroRepository.findBylinguagem("en");
                    break;
                case "es":
                    livros = livroRepository.findBylinguagem("es");
                    break;
                case "fr":
                    livros = livroRepository.findBylinguagem("fr");
                    System.out.println(siglaIdioma);
                    break;
                case "pt":
                    livros = livroRepository.findBylinguagem("pt");
                    System.out.println(siglaIdioma);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Entre com uma sigla valida!");

            }

            if (!livros.isEmpty()) {

                livros.stream().forEach(l -> {
                    System.out.println("""
                *-*-*-* Livros Catalogaos *-*-*-*-*
                    Título: %s
                    Autor: %s
                    Linguagem: %s
                    Downloads: %s
                *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

                """.formatted(
                            l.getTitulo(),
                            l.getNomeAutor(),
                            l.getLinguagem(),
                            l.getContDownloads()
                                    .toString()

                    ));
                });
            } else {
                System.out.println("Livro não encontrado para idioma !");
            }

        } catch (Exception ex) {
            System.out.println("Opção invalida. Tente novamente!");
        }
    }
}
