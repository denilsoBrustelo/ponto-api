package br.com.greenmile.ponto_api.service.command;

import br.com.greenmile.ponto_api.common.enums.TipoPontoEnum;
import br.com.greenmile.ponto_api.common.utils.BCryptUtil;
import br.com.greenmile.ponto_api.common.utils.DateUtil;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.service.query.UsuarioQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

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

    @Test
    @Transactional
    public void testSalvarPontoEmUsuario() throws IOException {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        Usuario usuarioSalvo = this.usuarioCommandService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());

        Long usuarioId = usuarioSalvo.getId();

        Ponto ponto = new Ponto();
        ponto.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        ponto.setId(null);
        ponto.setTipoPonto(TipoPontoEnum.CHECK_IN);
        ponto.setDescricao("Início de trabalho");

        HttpServletResponse mockHttpServletResponse = mock(HttpServletResponse.class);
        Ponto pontoSalvo = this.usuarioCommandService.savePonto(usuarioId, ponto, mockHttpServletResponse);

        assertNotNull(pontoSalvo);
        assertNotNull(pontoSalvo.getId());
        assertEquals(usuarioId, pontoSalvo.getUsuario().getId());

        Usuario usuarioEncontrado = this.usuarioQueryService.findById(usuarioId);

        assertNotNull(usuarioEncontrado);
        assertEquals(usuarioId, usuarioEncontrado.getId());
        assertEquals(1, usuarioEncontrado.getPontos().size());
    }

    @Test
    @Transactional
    public void testAtualizarPontoEmUsuario() throws IOException {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        Usuario usuarioSalvo = this.usuarioCommandService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());

        Long usuarioId = usuarioSalvo.getId();

        Ponto ponto = new Ponto();
        ponto.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        ponto.setId(null);
        ponto.setTipoPonto(TipoPontoEnum.CHECK_IN);
        ponto.setDescricao("Início de trabalho");

        HttpServletResponse mockHttpServletResponse = mock(HttpServletResponse.class);
        Ponto pontoSalvo = this.usuarioCommandService.savePonto(usuarioId, ponto, mockHttpServletResponse);

        assertNotNull(pontoSalvo);
        assertNotNull(pontoSalvo.getId());
        assertEquals(usuarioId, pontoSalvo.getUsuario().getId());

        Long pontoId = pontoSalvo.getId();
        Ponto pontoParaAtualizar = new Ponto();
        pontoParaAtualizar.setId(pontoId);
        pontoParaAtualizar.setTipoPonto(null);
        pontoParaAtualizar.setDescricao("Aqui vamos nós!");
        Ponto pontoAtualizado = this.usuarioCommandService.updatePonto(usuarioId, pontoParaAtualizar);

        assertNotNull(pontoAtualizado);
        assertEquals(pontoId, pontoAtualizado.getId());
        assertEquals(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"), pontoAtualizado.getDataCriacao());
        assertEquals(TipoPontoEnum.CHECK_IN, pontoAtualizado.getTipoPonto());
        assertEquals("Aqui vamos nós!", pontoAtualizado.getDescricao());
        assertEquals(usuarioId, pontoAtualizado.getUsuario().getId());
    }

    @Test
    @Transactional
    public void testDeletarPontoEmUsuario() throws IOException {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setUsername("lano");
        usuario.setPassword("123");
        Usuario usuarioSalvo = this.usuarioCommandService.save(usuario);

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());

        Long usuarioId = usuarioSalvo.getId();

        Ponto ponto = new Ponto();
        ponto.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        ponto.setId(null);
        ponto.setTipoPonto(TipoPontoEnum.CHECK_IN);
        ponto.setDescricao("Início de trabalho");

        HttpServletResponse mockHttpServletResponse = mock(HttpServletResponse.class);
        Ponto pontoSalvo = this.usuarioCommandService.savePonto(usuarioId, ponto, mockHttpServletResponse);

        assertNotNull(pontoSalvo);
        assertNotNull(pontoSalvo.getId());
        assertEquals(usuarioId, pontoSalvo.getUsuario().getId());

        Long pontoId = pontoSalvo.getId();
        this.usuarioCommandService.deletePonto(usuarioId, pontoId);

        Usuario usuarioEncontrado1 = this.usuarioQueryService.findById(usuarioId);

        assertNotNull(usuarioEncontrado1);
        assertEquals(usuarioId, usuarioEncontrado1.getId());
        assertEquals(0, usuarioEncontrado1.getPontos().size());
    }
}
