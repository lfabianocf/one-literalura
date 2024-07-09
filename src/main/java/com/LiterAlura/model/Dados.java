package com.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> listAutor,
        @JsonAlias("languages") List<String> linguagem,
        @JsonAlias("download_count") Integer download
) {
}
