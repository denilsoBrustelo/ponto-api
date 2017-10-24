package br.com.greenmile.ponto_api.common.controller.commands;

import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

public interface IUsuarioCommandRest {

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void delete(Long id);

    Callable<Ponto> savePonto(Long usuarioId, Ponto ponto, HttpServletResponse response);

    Ponto updatePonto(Long usuarioId, Ponto ponto);

    void deletePonto(Long usuarioId, Long pontoId);
}
