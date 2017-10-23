package br.com.greenmile.ponto_api.service.query;

import br.com.greenmile.ponto_api.common.enums.TipoPontoEnum;
import br.com.greenmile.ponto_api.common.utils.DateUtil;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.service.command.PontoCommandService;
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
public class PontoQueryServiceTests {

    @Autowired
    private PontoQueryService pontoQueryService;

    @Autowired
    private PontoCommandService pontoCommandService;

    @Test
    @Transactional
    public void testBuscarPonto() {
        Ponto ponto = new Ponto();
        ponto.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        ponto.setTipoPonto(TipoPontoEnum.CHECK_IN);
        ponto.setDescricao("Início de trabalho");
        Ponto pontoSalvo = this.pontoCommandService.save(ponto);

        assertNotNull(pontoSalvo);
        assertNotNull(pontoSalvo.getId());

        Long pontoId = pontoSalvo.getId();
        Ponto pontoEncontrado = this.pontoQueryService.findById(pontoId);

        assertNotNull(pontoEncontrado);
        assertEquals(pontoId, pontoEncontrado.getId());
        assertEquals(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"), pontoEncontrado.getDataCriacao());
        assertEquals(TipoPontoEnum.CHECK_IN, pontoEncontrado.getTipoPonto());
        assertEquals("Início de trabalho", pontoEncontrado.getDescricao());
    }

    @Test
    @Transactional
    public void testListarPontos() {
        Ponto pontoEntrada = new Ponto();
        pontoEntrada.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        pontoEntrada.setTipoPonto(TipoPontoEnum.CHECK_IN);
        pontoEntrada.setDescricao("Início de trabalho");
        Ponto pontoEntradaSalvo = this.pontoCommandService.save(pontoEntrada);

        Ponto pontoSaida = new Ponto();
        pontoSaida.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 17:00:00"));
        pontoSaida.setTipoPonto(TipoPontoEnum.CHECK_OUT);
        pontoSaida.setDescricao("Término do trabalho");
        Ponto pontoSaidaSalvo = this.pontoCommandService.save(pontoSaida);

        assertNotNull(pontoSaidaSalvo);
        assertNotNull(pontoEntradaSalvo);

        List<Ponto> pontos = this.pontoQueryService.findAll();

        assertNotNull(pontos);
        assertEquals(2, pontos.size());
        assertTrue(pontos.contains(pontoEntradaSalvo));
        assertTrue(pontos.contains(pontoSaidaSalvo));
    }

    @Test
    @Transactional
    public void testListarPontosPorFaixaDeDataEHora() {
        Ponto pontoEntrada = new Ponto();
        pontoEntrada.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        pontoEntrada.setTipoPonto(TipoPontoEnum.CHECK_IN);
        pontoEntrada.setDescricao("Início de trabalho");
        Ponto pontoEntradaSalvo = this.pontoCommandService.save(pontoEntrada);

        Ponto pontoSaida = new Ponto();
        pontoSaida.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 17:00:00"));
        pontoSaida.setTipoPonto(TipoPontoEnum.CHECK_OUT);
        pontoSaida.setDescricao("Término do trabalho");
        Ponto pontoSaidaSalvo = this.pontoCommandService.save(pontoSaida);

        assertNotNull(pontoSaidaSalvo);
        assertNotNull(pontoEntradaSalvo);

        List<Ponto> pontos = this.pontoQueryService.findAllByDataCriacaoBetween(
                        DateUtil.convertStringToDateAndHour("2017-10-22 00:00:00"),
                        DateUtil.convertStringToDateAndHour("2017-10-22 23:59:59"));

        assertNotNull(pontos);
        assertEquals(2, pontos.size());
    }

    @Test
    @Transactional
    public void testListarPontosPorFaixaDeData() {
        Ponto pontoEntrada = new Ponto();
        pontoEntrada.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 08:00:00"));
        pontoEntrada.setTipoPonto(TipoPontoEnum.CHECK_IN);
        pontoEntrada.setDescricao("Início de trabalho");
        Ponto pontoEntradaSalvo = this.pontoCommandService.save(pontoEntrada);

        Ponto pontoSaida = new Ponto();
        pontoSaida.setDataCriacao(DateUtil.convertStringToDateAndHour("2017-10-22 17:00:00"));
        pontoSaida.setTipoPonto(TipoPontoEnum.CHECK_OUT);
        pontoSaida.setDescricao("Término do trabalho");
        Ponto pontoSaidaSalvo = this.pontoCommandService.save(pontoSaida);

        assertNotNull(pontoSaidaSalvo);
        assertNotNull(pontoEntradaSalvo);

        List<Ponto> pontos = this.pontoQueryService.findAllByDataCriacaoBetweenDates(
                DateUtil.convertStringToDate("2017-10-22"),
                DateUtil.convertStringToDate("2017-10-22"));

        assertNotNull(pontos);
        assertEquals(2, pontos.size());
    }
}
