package br.com.fef.campingclubapi.filter;

import br.com.fef.campingclubapi.model.User;
import br.com.fef.campingclubapi.service.JWTTokenAutenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe que Gera o JWT ao Logar
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * Configura o Gerenciador de Autenticação
     *
     * @param defaultFilterProcessesUrl
     * @param manager
     */
    public JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager manager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(manager);
    }

    /**
     * Retorna o Usuario autenticado
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        User usuario = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        new JWTTokenAutenticationService().addAuthentication(response,(User) authResult.getPrincipal());
    }
}
