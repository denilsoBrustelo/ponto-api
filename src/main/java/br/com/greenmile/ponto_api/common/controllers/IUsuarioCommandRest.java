package br.com.greenmile.ponto_api.common.controllers;

import br.com.greenmile.ponto_api.domain.Usuario;

public interface IUsuarioCommandRest {

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void delete(Long id);
}
