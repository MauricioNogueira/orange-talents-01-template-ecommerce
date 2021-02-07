package br.com.zup.mercadolivre.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.zup.mercadolivre.repository.UsuarioRepository;
import br.com.zup.mercadolivre.requests.AuthRequest;
import br.com.zup.mercadolivre.service.JwtTokenUtil;

@WebMvcTest(AuthController.class)
@EnableAutoConfiguration
class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@MockBean
	private JwtTokenUtil jwtTokenUtil;
	
//	@BeforeEach
//	public void carrega(UserDetailsService userDetailsService) {
//		this.userDetailsService = userDetailsService;
//	}
	
	@Test
	void deveCriarUmUsuarioComLoginESenha() throws Exception {
		AuthRequest request = new AuthRequest("mauricio@email.com", "12345");
		ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = mapper.writeValueAsString(request);
		
		System.out.println(json);
		
		this.mockMvc.perform(post("/auth/sign")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk());
		
	}
}
