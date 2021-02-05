package br.com.zup.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.mercadolivre.models.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

}
