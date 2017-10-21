package br.com.greenmile.ponto_api.common.interfaces;

import br.com.greenmile.ponto_api.domain.Usuario;

public interface IUsuarioCommandService {

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void delete(Long id);
}
