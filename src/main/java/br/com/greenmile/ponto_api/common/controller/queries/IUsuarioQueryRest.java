package br.com.greenmile.ponto_api.common.controller.queries;

import br.com.greenmile.ponto_api.domain.HoraTrabalhada;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.concurrent.Callable;

public interface IUsuarioQueryRest {

    Usuario findById(Long id);

    Page<Usuario> findAll(Pageable pageable);

    Ponto findPontoByUsuarioIdAndPontoId(Long usuarioId, Long pontoId);

    Page<Ponto> findAllPontosByUsuarioId(Long usuarioId, Pageable pageable);

    Callable<HoraTrabalhada> getHoraTrabalhadaPorUsuarioIdEPeriodo(Long usuarioId, Date dataInicio, Date dataFim);
}
