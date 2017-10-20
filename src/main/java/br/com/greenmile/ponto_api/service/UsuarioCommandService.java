package br.com.greenmile.ponto_api.service;

import br.com.greenmile.ponto_api.common.interfaces.IUsuarioCommandService;
import br.com.greenmile.ponto_api.common.utils.EntityUtil;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioCommandService implements IUsuarioCommandService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void save(Usuario usuario) {
        if (usuario != null && usuario.getId() == null) {
            this.usuarioRepository.save(usuario);
        }
    }

    @Override
    public void update(Usuario usuario) {
        if (usuario != null && usuario.getId() != null) {
            Long id = usuario.getId();
            Usuario usuarioEncontrado = this.usuarioRepository.findOne(id);
            Usuario usuarioAtualizado = EntityUtil.merge(usuarioEncontrado, usuario);
            this.usuarioRepository.save(usuarioAtualizado);
        }
    }

    @Override
    public void delete(Long id) {
        id = (id != null) ? id : 0L;
        this.usuarioRepository.delete(id);
    }
}
