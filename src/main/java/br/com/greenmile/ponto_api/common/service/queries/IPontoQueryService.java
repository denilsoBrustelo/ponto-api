package br.com.greenmile.ponto_api.common.service.queries;

import br.com.greenmile.ponto_api.domain.Ponto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPontoQueryService {

    Ponto findById(Long id);

    List<Ponto> findAll();

    Page<Ponto> findAll(Pageable pageable);
}
