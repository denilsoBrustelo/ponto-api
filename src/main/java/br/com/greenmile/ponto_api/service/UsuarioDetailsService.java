package br.com.greenmile.ponto_api.service;

import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.service.query.UsuarioQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioQueryService usuarioQueryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioQueryService.findByUsername(username);

        if (username == null) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }
        return usuario;
    }
}
