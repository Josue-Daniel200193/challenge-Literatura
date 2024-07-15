package Segundo.Challenge.Literatura;


import Segundo.Challenge.Literatura.principal.Principal;
import Segundo.Challenge.Literatura.repository.UserAutorRepository;
import Segundo.Challenge.Literatura.repository.UserLibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BooksApplication implements CommandLineRunner {


	@Autowired
	private UserLibroRepository repository;

	@Autowired
	private UserAutorRepository autorRepo;

	public static void main(String[] args) {

		SpringApplication.run(BooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Principal principal = new Principal(repository, autorRepo);
		principal.muestreElMenu();


	}
}
