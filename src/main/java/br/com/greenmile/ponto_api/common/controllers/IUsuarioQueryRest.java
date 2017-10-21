package br.com.greenmile.ponto_api.common.controllers;

import br.com.greenmile.ponto_api.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuarioQueryRest {

    Usuario findById(Long id);

    Page<Usuario> findAll(Pageable pageable);
}
