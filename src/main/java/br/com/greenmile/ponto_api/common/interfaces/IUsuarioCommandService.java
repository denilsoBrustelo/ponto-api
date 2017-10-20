package br.com.greenmile.ponto_api.common.interfaces;

import br.com.greenmile.ponto_api.domain.Usuario;

public interface IUsuarioCommandService {

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Long id);
}
