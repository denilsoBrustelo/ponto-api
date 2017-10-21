package br.com.greenmile.ponto_api.service;

import br.com.greenmile.ponto_api.common.utils.BCryptUtil;
import br.com.greenmile.ponto_api.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioQueryServiceTests {

    @Autowired
    private UsuarioQueryService usuarioQueryService;

    @Autowired
    private UsuarioCommandService usuarioCommandService;

    @Test
    @Transactional
    public void testBuscarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        Usuario usuarioSalvo = this.usuarioCommandService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());

        Long usuarioId = usuarioSalvo.getId();
        Usuario usuarioEncontrado = this.usuarioQueryService.findById(usuarioId);

        assertNotNull(usuarioEncontrado);
        assertEquals(usuarioId, usuarioEncontrado.getId());
        assertEquals("Fulano", usuarioEncontrado.getNome());
        assertEquals("lano", usuarioEncontrado.getUsername());
        assertTrue(BCryptUtil.check("123", usuarioEncontrado.getPassword()));
    }

    @Test
    @Transactional
    public void testListarTodosOsUsuarios() {
        Usuario fulano = new Usuario();
        fulano.setNome("Fulano");
        fulano.setUsername("fulano");
        fulano.setPassword("123");
        Usuario usuarioFulanoSalvo = this.usuarioCommandService.save(fulano);

        Usuario usuarioBeltrano = new Usuario();
        usuarioBeltrano.setNome("Beltrano");
        usuarioBeltrano.setUsername("trano");
        usuarioBeltrano.setPassword("124");
        Usuario usuarioBeltranoSalvo = this.usuarioCommandService.save(usuarioBeltrano);

        Usuario usuarioSicrano = new Usuario();
        usuarioSicrano.setNome("Sicrano");
        usuarioSicrano.setUsername("crano");
        usuarioSicrano.setPassword("125");
        Usuario usuarioSicranoSalvo = this.usuarioCommandService.save(usuarioSicrano);

        assertNotNull(usuarioFulanoSalvo);
        assertNotNull(usuarioBeltranoSalvo);
        assertNotNull(usuarioSicranoSalvo);

        List<Usuario> usuarios = this.usuarioQueryService.findAll();

        assertNotNull(usuarios);
        assertEquals(3, usuarios.size());
        assertTrue(usuarios.contains(usuarioFulanoSalvo));
        assertTrue(usuarios.contains(usuarioBeltranoSalvo));
        assertTrue(usuarios.contains(usuarioSicranoSalvo));
    }
}
