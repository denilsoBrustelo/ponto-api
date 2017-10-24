package br.com.greenmile.ponto_api.common.service.queries;

import br.com.greenmile.ponto_api.domain.HoraTrabalhada;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IUsuarioQueryService {

    Usuario findById(Long id);

    Usuario findByUsername(String username);

    List<Usuario> findAll();

    Page<Usuario> findAll(Pageable pageable);

    Ponto findPontoByUsuarioIdAndPontoId(Long usuarioId, Long pontoId);

    List<Ponto> findAllPontosByUsuarioId(Long usuarioId);

    Page<Ponto> findAllPontosByUsuarioId(Long usuarioId, Pageable pageable);

    HoraTrabalhada getHoraTrabalhadaPorUsuarioIdEPeriodo(Long usuarioId, Date dataInicio, Date dataFim);
}
