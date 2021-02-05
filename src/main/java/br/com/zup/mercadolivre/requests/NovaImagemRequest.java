package br.com.zup.mercadolivre.requests;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.com.zup.mercadolivre.customvalidation.ExistsFile;
import br.com.zup.mercadolivre.customvalidation.ExistsId;
import br.com.zup.mercadolivre.models.Imagem;
import br.com.zup.mercadolivre.models.Produto;

public class NovaImagemRequest {

	@NotNull
	@ExistsFile
	private List<MultipartFile> imagens = new ArrayList<>();
	
	@NotNull
	@ExistsId(domainClass = Produto.class)
	private Long produtoId;

	public NovaImagemRequest(@NotNull List<MultipartFile> imagens, @NotNull Long produtoId) {
		this.imagens = imagens;
		this.produtoId = produtoId;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public Long getProdutoId() {
		return produtoId;
	}
	
	public List<Imagem> toModel(Produto produto) {
		List<Imagem> imagens = new ArrayList<>();
		
		this.imagens.forEach(imagem -> {
			imagens.add(new Imagem("https://bucket.io/meracdo-livre-"+imagem.getOriginalFilename(), produto));
		});
		
		return imagens;
	}
}