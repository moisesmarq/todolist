package br.com.moisesmarques.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        //Pegar a autenticação (usuario e senha)
        //Validar usuario
        //Validar senha
        //Se sucesso, segue a vida

        //Pega a autenticação
        var authorization = request.getHeader("Authorization");
        System.out.println(authorization);

        //Remove a palavra "Basic"
        var authEncoded = authorization.substring("Basic".length()).trim();
        
        //Decodifica a informação de credenciais
        Base64.getDecoder().decode(authEncoded);
        byte[] authDecode = Base64.getDecoder().decode(authEncoded);

        var authString = new String(authDecode);

        String[] credentials = authString.split(":");

        String username = credentials[0];
        String password = credentials[1];
        
        System.out.println(username);
        System.out.println(password);
        filterChain.doFilter(request, response);
    }
}
