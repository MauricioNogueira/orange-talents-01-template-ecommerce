package br.com.zup.mercadolivre.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtTokenUtil {
	
	private Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	@Value("${jwtSecret}")
	private String jwtSecret;
	
	@Value("${jwtExpirationMs}")
	private Long jwtExpirationMs;

	public String generateToken(Usuario usuario) {
		Date dataAtual = new Date();
		return Jwts.builder()
				.setSubject(usuario.getUsername())
				.setIssuer("example")
				.setIssuedAt(dataAtual)
				.setExpiration(new Date(dataAtual.getTime() + this.jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, this.jwtSecret)
				.compact();
	}
	
	public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
	
	public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
            
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        
        return false;
    }
}
