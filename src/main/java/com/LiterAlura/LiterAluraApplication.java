package com.LiterAlura;

import com.LiterAlura.MenuPrincipal.MenuPrincipal;
import com.LiterAlura.repository.AutorRepository;
import com.LiterAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.transform.Source;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;



	public static void main(String[] args)  {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MenuPrincipal menu = new MenuPrincipal(livroRepository, autorRepository);
		menu.MenuAplicacao();

	}
}
