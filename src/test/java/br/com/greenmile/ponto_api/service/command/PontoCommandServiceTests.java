package br.com.greenmile.ponto_api.service.command;

import br.com.greenmile.ponto_api.common.enums.TipoPontoEnum;
import br.com.greenmile.ponto_api.common.utils.DateUtil;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.service.query.PontoQueryService;
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
public class PontoCommandServiceTests {

    @Autowired
    private PontoCommandService pontoCommandService;

    @Autowired
    private PontoQueryService pontoQueryService;

    @Test
    @Transactional
    public void testSalvarPonto() {
        Ponto ponto = new Ponto();
        ponto.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        ponto.setTipoPonto(TipoPontoEnum.CHECK_IN);
        ponto.setDescricao("Início de trabalho");
        Ponto pontoSalvo = this.pontoCommandService.save(ponto);

        assertNotNull(pontoSalvo);
        assertNotNull(pontoSalvo.getId());
        assertEquals(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"), pontoSalvo.getDataCriacao());
        assertEquals(TipoPontoEnum.CHECK_IN, pontoSalvo.getTipoPonto());
        assertEquals("Início de trabalho", pontoSalvo.getDescricao());
    }

    @Test
    @Transactional
    public void testAtualizarPonto() {
        Ponto ponto = new Ponto();
        ponto.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        ponto.setTipoPonto(TipoPontoEnum.CHECK_IN);
        ponto.setDescricao("Início de trabalho");
        Ponto pontoSalvo = this.pontoCommandService.save(ponto);

        assertNotNull(pontoSalvo);
        assertNotNull(pontoSalvo.getId());

        Long pontoId = pontoSalvo.getId();
        Ponto pontoParaAtualizar = new Ponto();
        pontoParaAtualizar.setId(pontoId);
        pontoParaAtualizar.setTipoPonto(null);
        pontoParaAtualizar.setDescricao("Aqui vamos nós!");
        Ponto pontoAtualizado = this.pontoCommandService.update(pontoParaAtualizar);

        assertNotNull(pontoAtualizado);
        assertEquals(pontoId, pontoAtualizado.getId());
        assertEquals(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"), pontoAtualizado.getDataCriacao());
        assertEquals(TipoPontoEnum.CHECK_IN, pontoAtualizado.getTipoPonto());
        assertEquals("Aqui vamos nós!", pontoAtualizado.getDescricao());
    }

    @Test
    @Transactional
    public void testDeletarPonto() {
        Ponto ponto = new Ponto();
        ponto.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        ponto.setTipoPonto(TipoPontoEnum.CHECK_IN);
        ponto.setDescricao("Início de trabalho");
        Ponto pontoSalvo = this.pontoCommandService.save(ponto);

        assertNotNull(pontoSalvo);
        assertNotNull(pontoSalvo.getId());

        Long pontoId = pontoSalvo.getId();
        this.pontoCommandService.delete(pontoId);
        Ponto pontoDeletado = this.pontoQueryService.findById(pontoId);

        assertNull(pontoDeletado);
    }
}
