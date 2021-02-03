package br.com.zup.mercadolivre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.mercadolivre.models.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

@SpringBootApplication
public class MercadoLivreApplication implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario("mauricio@email.com", new BCryptPasswordEncoder().encode("12345"));
		
		this.usuarioRepository.save(usuario);
		
		System.out.println("\nUsuário padrão foi adicionado\n--------------------------------");
		System.out.println("Rota para autenticação: http://localhost:8080/auth/sign");
		System.out.println("Login: " + usuario.getLogin());
		System.out.println("Password: 12345\n--------------------------------");
	}
}
