package br.com.zup.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.mercadolivre.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<UserDetails> findByLogin(String username);

}
