package br.com.greenmile.ponto_api.repository;

import br.com.greenmile.ponto_api.common.enums.PermissaoEnum;
import br.com.greenmile.ponto_api.common.utils.BCryptUtil;
import br.com.greenmile.ponto_api.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Transactional
    public void testFindUsuarioByUsername() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        usuario.setPermissao(PermissaoEnum.ROLE_ADMIN);
        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());

        Long userId = usuarioSalvo.getId();
        Usuario usuarioEncontrado = this.usuarioRepository.findByUsername("lano");

        assertNotNull(usuarioEncontrado);
        assertEquals(userId, usuarioEncontrado.getId());
        assertEquals(PermissaoEnum.ROLE_ADMIN, usuarioEncontrado.getPermissao());
        assertEquals(1, usuarioEncontrado.getAuthorities().size());
    }
}
