package br.com.greenmile.ponto_api.service.query;

import br.com.greenmile.ponto_api.common.service.queries.IUsuarioQueryService;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class UsuarioQueryService implements IUsuarioQueryService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Long id) {
        id = (id != null) ? id : 0L;
        return this.usuarioRepository.findOne(id);
    }

    @Override
    public Usuario findByUsername(String username) {
        Usuario usuario = null;

        if (!StringUtils.isEmpty(username)) {
            usuario = this.usuarioRepository.findByUsername(username);
        }
        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    @Override
    public Ponto findPontoByUsuarioIdAndPontoId(Long usuarioId, Long pontoId) {
        return this.usuarioRepository.findPontoByUsuarioIdAndPontoId(usuarioId, pontoId);
    }

    @Override
    public Page<Ponto> findAllPontosByUsuarioId(Long usuarioId, Pageable pageable) {
        return this.usuarioRepository.findAllPontosByUsuarioId(usuarioId, pageable);
    }

    public List<Ponto> findAllPontosByUsuarioId(Long usuarioId) {
        return this.usuarioRepository.findAllPontosByUsuarioId(usuarioId);
    }

    public Ponto findLastPontoByUsuarioId(Long id) {
        return this.usuarioRepository.findLastPontoByUsuarioId(id);
    }
}
