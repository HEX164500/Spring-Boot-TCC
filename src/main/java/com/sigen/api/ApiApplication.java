package com.sigen.api;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sigen.api.entities.Usuario;
import com.sigen.api.enums.Genero;
import com.sigen.api.enums.NivelDeAcesso;
import com.sigen.api.repositories.UsuarioRepository;

@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@EnableWebMvc
@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository repo;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiApplication.class, args);

		DispatcherServlet dispatcherServlet = (DispatcherServlet) context.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario root = repo.findByEmail("lucas.rafael.164500@gmail.com").orElse(null);
		
		if (root == null) {
			root = new Usuario();
			root.setNome("Lucas");
			root.setEmail("lucas.rafael.164500@gmail.com");
			root.setSenha("f0258abb81d9be7887e5b4d0732a1bee");
			root.setAcesso(NivelDeAcesso.EMPREGADO);
			root.setGenero(Genero.MASCULINO);
			root.setAtivo(true);
			root.setNascimento(LocalDate.now());
			root.setCpf("12345678901");

			repo.save(root);
		}
	}
}
