package br.com.greenmile.ponto_api.common.controller.queries;

import br.com.greenmile.ponto_api.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioQueryRest {

    Usuario findById(Long id);

    Page<Usuario> findAll(Pageable pageable);
}
