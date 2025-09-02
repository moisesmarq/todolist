package br.com.moisesmarques.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.moisesmarques.todolist.user.IUserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    //Por padrão esse filtro está sendo aplicado para todas as rotas
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        //Pegar a autenticação (usuario e senha)
        //Validar usuario
        //Validar senha
        //Se sucesso, segue a vida
            
        var servletPath = request.getServletPath();
        
        if (servletPath.equals("/tasks/")) {
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
            
            //Validacao do usuario
            var user = this.userRepository.findByUsername(username);
            if (user == null)
            {
            response.sendError(401);
            }
            else
            {
                //Validacao da senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            
                if(passwordVerify.verified)
                {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                }
                else
                {
                    response.sendError(401);
                }
            }
        } 
        else {
            filterChain.doFilter(request, response);
        }
    }    
}
