package com.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(unique = true)
    private String nomeAutor;

    private String linguagem;

    private Integer contDownloads;

    public Livro(Dados dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.nomeAutor = dadosLivro.listAutor().get(0).nomeAutor();
        this.linguagem = dadosLivro.linguagem().get(0);
        this.contDownloads = dadosLivro.download();
    }

    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

   public String getLinguagem() {
        return linguagem;
    }

    public Integer getContDownloads() {
        return contDownloads;
    }

    public void setContDownloads(Integer contDownloads) {
        this.contDownloads = contDownloads;
    }
}
