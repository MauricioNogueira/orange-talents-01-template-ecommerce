package br.com.zup.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.mercadolivre.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
