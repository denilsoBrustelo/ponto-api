package br.com.greenmile.ponto_api.common.service.commands;

import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IUsuarioCommandService {

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void delete(Long id);

    Ponto savePonto(Long usuarioId, Ponto ponto, HttpServletResponse response) throws IOException;

    Ponto updatePonto(Long usuarioId, Ponto ponto);

    void deletePonto(Long usuarioId, Long pontoId);
}
