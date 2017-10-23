package br.com.greenmile.ponto_api.common.service.commands;

import br.com.greenmile.ponto_api.domain.Ponto;

public interface IPontoCommandService {

    Ponto save(Ponto ponto);

    Ponto update(Ponto ponto);

    void delete(Long id);
}
