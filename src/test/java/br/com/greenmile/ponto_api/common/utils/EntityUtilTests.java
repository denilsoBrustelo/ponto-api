package br.com.greenmile.ponto_api.common.utils;

import br.com.greenmile.ponto_api.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EntityUtilTests {

    @Test
    public void testVerificarMerge() {
        Usuario usuarioAntigo = new Usuario();
        usuarioAntigo.setId(1L);
        usuarioAntigo.setNome("Chico");
        usuarioAntigo.setUsername("chico");
        usuarioAntigo.setPassword(null);

        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setId(1L);
        usuarioNovo.setNome("Chico Bento");
        usuarioNovo.setUsername(null);
        usuarioNovo.setPassword("123");

        Usuario usuarioMergiado = EntityUtil.merge(usuarioAntigo, usuarioNovo);

        assertEquals(1, usuarioMergiado.getId().intValue());
        assertEquals("Chico Bento", usuarioMergiado.getNome());
        assertEquals("chico", usuarioMergiado.getUsername());
        assertEquals("123", usuarioMergiado.getPassword());
    }
}
