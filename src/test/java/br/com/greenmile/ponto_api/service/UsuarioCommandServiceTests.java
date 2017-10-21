package br.com.greenmile.ponto_api.service;

import br.com.greenmile.ponto_api.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioCommandServiceTests {

    @Autowired
    private UsuarioCommandService usuarioCommandService;

    @Test
    @Transactional
    public void testeSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        Usuario usuarioSalvo = this.usuarioCommandService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());
        assertEquals("Fulano", usuarioSalvo.getNome());
        assertEquals("lano", usuarioSalvo.getUsername());
        assertEquals("123", usuarioSalvo.getPassword());
    }
}
