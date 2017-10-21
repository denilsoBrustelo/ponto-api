package br.com.greenmile.ponto_api.service.command;

import br.com.greenmile.ponto_api.common.utils.BCryptUtil;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.service.command.UsuarioCommandService;
import br.com.greenmile.ponto_api.service.query.UsuarioQueryService;
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

    @Autowired
    private UsuarioQueryService usuarioQueryService;

    @Test
    @Transactional
    public void testSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        Usuario usuarioSalvo = this.usuarioCommandService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());
        assertEquals("Fulano", usuarioSalvo.getNome());
        assertEquals("lano", usuarioSalvo.getUsername());
        assertTrue(BCryptUtil.check("123", usuarioSalvo.getPassword()));
    }

    @Test
    @Transactional
    public void testAtualizarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        Usuario usuarioSalvo = this.usuarioCommandService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());

        Long usuarioId = usuarioSalvo.getId();
        Usuario usuarioParaAtualizar = new Usuario();
        usuarioParaAtualizar.setId(usuarioId);
        usuarioParaAtualizar.setNome("Beltrano");
        usuarioParaAtualizar.setUsername(null);
        usuarioParaAtualizar.setPassword(null);
        Usuario usuarioAtualizado = this.usuarioCommandService.update(usuarioParaAtualizar);

        assertNotNull(usuarioAtualizado);
        assertEquals(usuarioId, usuarioAtualizado.getId());
        assertEquals("Beltrano", usuarioAtualizado.getNome());
        assertEquals("lano", usuarioAtualizado.getUsername());
        assertTrue(BCryptUtil.check("123", usuarioSalvo.getPassword()));
    }

    @Test
    @Transactional
    public void testDeletarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        Usuario usuarioSalvo = this.usuarioCommandService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());

        Long usuarioId = usuarioSalvo.getId();
        this.usuarioCommandService.delete(usuarioId);
        Usuario usuarioEncontrado = this.usuarioQueryService.findById(usuarioId);

        assertNull(usuarioEncontrado);
    }
}
