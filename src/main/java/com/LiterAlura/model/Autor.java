package com.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAutor;

    private Integer anoNascimento;

    private Integer anoFalecimento;

    public Autor(DadosAutor dadosAutor) {
        this.nomeAutor = dadosAutor.nomeAutor();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    public Autor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getDataNascimento() {
        return anoNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.anoNascimento = dataNascimento;
    }

    public Integer getDataFalecimento() {
        return anoFalecimento;
    }

    public void setDataFalecimento(Integer dataFalecimento) {
        this.anoFalecimento = dataFalecimento;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nomeAutor='" + nomeAutor + '\'' +
                ", dataNascimento=" + anoNascimento +
                ", dataFalecimento=" + anoFalecimento +
                '}';
    }
}
