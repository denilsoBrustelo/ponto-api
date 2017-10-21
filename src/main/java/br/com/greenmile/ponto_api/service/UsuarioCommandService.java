package br.com.greenmile.ponto_api.service;

import br.com.greenmile.ponto_api.common.interfaces.IUsuarioCommandService;
import br.com.greenmile.ponto_api.common.utils.EntityUtil;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioCommandService implements IUsuarioCommandService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        Usuario usuarioSalvo = new Usuario();

        if (usuario != null && usuario.getId() == null) {
            usuarioSalvo = this.usuarioRepository.save(usuario);
        }
        return usuarioSalvo;
    }

    @Override
    public Usuario update(Usuario usuario) {
        Usuario usuarioAtualizado = new Usuario();

        if (usuario != null && usuario.getId() != null) {
            Long id = usuario.getId();
            Usuario usuarioEncontrado = this.usuarioRepository.findOne(id);
            Usuario usuarioMergiado = EntityUtil.merge(usuarioEncontrado, usuario);
            usuarioAtualizado = this.usuarioRepository.save(usuarioMergiado);
        }
        return usuarioAtualizado;
    }

    @Override
    public void delete(Long id) {
        id = (id != null) ? id : 0L;
        this.usuarioRepository.delete(id);
    }
}
