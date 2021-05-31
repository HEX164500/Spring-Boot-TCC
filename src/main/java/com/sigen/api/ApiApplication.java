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
import com.sigen.api.enums.NivelDeAcesso;
import com.sigen.api.enums.Genero;
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
		Usuario root = new Usuario();
		root.setNome("Lucas");
		root.setEmail("lucas@gmail.com");
		root.setSenha("password");
		root.setAcesso(NivelDeAcesso.EMPREGADO);
		root.setGenero(Genero.HOMEM);
		root.setAtivo(true);
		root.setNascimento(LocalDate.now());
		root.setTelefones(java.util.Arrays.asList("1234", "5678"));
		root.setCpf("12345678901");

		repo.save(root);
	}
}
