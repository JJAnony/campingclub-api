package br.com.fef.campingclubapi.service;

import br.com.fef.campingclubapi.model.User;
import br.com.fef.campingclubapi.repository.UserRepository;
import br.com.fef.campingclubapi.util.ApplicationContextLoad;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que gera e Valida JWT
 */
@Service
@Component
public class JWTTokenAutenticationService {

    private static final long EXPIRATION_TIME = 17280000;
    private static final String SECRET = "!sDA^j^xYi!LotPVTk9a";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    /**
     * Gera o Token de Acesso que sera passado via Http
     *
     * @param response
     * @param user
     * @throws IOException
     */
    public void addAuthentication(HttpServletResponse response, User user) throws IOException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", user.getId_user());
        if (user.getEmployee() != null) {
            claims.put("type", user.getEmployee().getOffice().equals("root") ? "A" : "E");
        } else {
            claims.put("type", "C");
        }
        claims.put("sub", user.getUsername());

        String JWT = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        String token = TOKEN_PREFIX + JWT;

        response.addHeader(HEADER_STRING, token);
        response.addHeader("Content-Type", "application/json");
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }

    /**
     * Valida o Token de Acesso colocado no Http
     *
     * @param request
     * @return
     */
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject();
            if (user != null) {
                User usuario = ApplicationContextLoad
                        .getApplicationContext().getBean(UserRepository.class).findByUsername(user);
                if (usuario != null) {
                    return new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
                }
            }
        }
        return null;
    }


}
