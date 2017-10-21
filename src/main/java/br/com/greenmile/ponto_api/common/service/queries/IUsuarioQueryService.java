package br.com.greenmile.ponto_api.common.service.queries;

import br.com.greenmile.ponto_api.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuarioQueryService {

    Usuario findById(Long id);

    List<Usuario> findAll();

    Page<Usuario> findAll(Pageable pageable);
}
