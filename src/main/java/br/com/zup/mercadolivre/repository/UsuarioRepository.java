package br.com.zup.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.mercadolivre.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
