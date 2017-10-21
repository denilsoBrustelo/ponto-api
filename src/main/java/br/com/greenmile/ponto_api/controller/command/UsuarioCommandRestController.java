package br.com.greenmile.ponto_api.controller.command;

import br.com.greenmile.ponto_api.common.controllers.IUsuarioCommandRest;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.service.command.UsuarioCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/usuarios")
public class UsuarioCommandRestController implements IUsuarioCommandRest {

    @Autowired
    private UsuarioCommandService usuarioCommandService;

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
}
