package br.com.zup.mercadolivre.requests;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.customvalidation.ExistsId;
import br.com.zup.mercadolivre.customvalidation.Unique;
import br.com.zup.mercadolivre.models.Categoria;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

public class CadastraCategoriaRequest {

	@NotBlank
	@Unique(domainClass = Categoria.class, field = "nome")
	private String nome;
	
	@ExistsId(domainClass = Categoria.class, message = "categoria não foi encontrada")
	private Long categoriaId;

	public String getNome() {
		return nome;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}
	
	public Categoria toModel(CategoriaRepository categoriaRepository) {
		Categoria categoria = new Categoria(this.nome);
		
		if (this.categoriaId != null) {
			Optional<Categoria> optional = categoriaRepository.findById(categoriaId);
			
			Categoria categoriaMae = optional.get();
			categoria.setCategoria(categoriaMae);
		}
		
		return categoria;
	}

	@Override
	public String toString() {
		return "CadastraCategoriaRequest [nome=" + nome + ", categoriaId=" + categoriaId + "]";
	}
}
