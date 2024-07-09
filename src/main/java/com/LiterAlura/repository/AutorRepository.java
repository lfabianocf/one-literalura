package com.LiterAlura.repository;

import com.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {


    @Query("""
           SELECT a
                FROM Autor a
            WHERE
                a.anoNascimento >= :ano
           """)
    List<Autor> autorPorAno(int ano);
}
