package br.com.greenmile.ponto_api.controller.command;

import br.com.greenmile.ponto_api.common.controller.commands.IUsuarioCommandRest;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.service.command.UsuarioCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "api/v1/usuarios")
public class UsuarioCommandRestController implements IUsuarioCommandRest {

    @Autowired
    private UsuarioCommandService usuarioCommandService;

    // Início - Usuário
    @PostMapping("")
    @CacheEvict(value = "usuarios", allEntries = true)
    @Override
    public Usuario save(@RequestBody Usuario usuario) {
        return this.usuarioCommandService.save(usuario);
    }

    @PutMapping("")
    @Override
    public Usuario update(@RequestBody Usuario usuario) {
        return this.usuarioCommandService.update(usuario);
    }

    @DeleteMapping("/{usuario-id}")
    @Override
    public void delete(@PathVariable("usuario-id") Long id) {
        this.usuarioCommandService.delete(id);
    }
    // Fim - Usuário

    // Início - Ponto
    @PostMapping("/{usuario-id}/pontos")
    @CacheEvict(value = "pontos", allEntries = true)
    @Override
    public Callable<Ponto> savePonto(@PathVariable("usuario-id") Long usuarioId,
                                     @RequestBody Ponto ponto,
                                     HttpServletResponse response) {
        return () -> this.usuarioCommandService.savePonto(usuarioId, ponto, response);
    }

    @PutMapping("/{usuario-id}/pontos")
    @Override
    public Ponto updatePonto(@PathVariable("usuario-id") Long usuarioId,
                             @RequestBody Ponto ponto) {
        return this.usuarioCommandService.updatePonto(usuarioId, ponto);
    }

    @DeleteMapping("/{usuario-id}/pontos/{ponto-id}")
    @Override
    public void deletePonto(@PathVariable("usuario-id") Long usuarioId,
                            @PathVariable("ponto-id") Long pontoId) {
        this.usuarioCommandService.deletePonto(usuarioId, pontoId);
    }
    // Fim - Ponto
}
