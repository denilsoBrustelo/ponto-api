package br.com.greenmile.ponto_api.service.command;

import br.com.greenmile.ponto_api.common.enums.TipoPontoEnum;
import br.com.greenmile.ponto_api.common.service.commands.IUsuarioCommandService;
import br.com.greenmile.ponto_api.common.utils.BCryptUtil;
import br.com.greenmile.ponto_api.common.utils.EntityUtil;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.repository.UsuarioRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class UsuarioCommandService implements IUsuarioCommandService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PontoCommandService pontoCommandService;

    @Override
    public Usuario save(Usuario usuario) {
        Usuario usuarioSalvo = null;

        if (usuario != null && usuario.getId() == null) {

            if (!StringUtils.isEmpty(usuario.getPassword())) {
                String hash = BCryptUtil.getHash(usuario.getPassword());
                usuario.setPassword(hash);
            }

            usuarioSalvo = this.usuarioRepository.save(usuario);
        }
        return usuarioSalvo;
    }

    @Override
    public Usuario update(Usuario usuario) {
        Usuario usuarioAtualizado = null;

        if (usuario != null && usuario.getId() != null) {
            Long id = usuario.getId();

            if (!StringUtils.isEmpty(usuario.getPassword())) {
                String hash = BCryptUtil.getHash(usuario.getPassword());
                usuario.setPassword(hash);
            }

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

    @Override
    public Ponto savePonto(Long usuarioId, Ponto ponto, HttpServletResponse response) throws IOException {
        Ponto pontoSalvo = null;
        usuarioId = (usuarioId != null) ? usuarioId : 0L;

        Optional<Ponto> pontoOptional = Optional.ofNullable(ponto);
        Optional<Usuario> usuarioOptional = Optional.ofNullable(this.usuarioRepository.findOne(usuarioId));

        if (usuarioOptional.map(Usuario::getPontos).isPresent() && !pontoOptional.map(Ponto::getId).isPresent()) {
            Usuario usuarioEncontrado = usuarioOptional.get();
            Ponto pontoEncontrado = pontoOptional.get();

            Ponto lastPonto = this.usuarioRepository.findLastPontoByUsuarioId(usuarioEncontrado.getId());

            if (lastPonto == null) {
                if (pontoEncontrado.getTipoPonto().equals(TipoPontoEnum.CHECK_OUT)) {
                    response.sendError(HttpStatus.BAD_REQUEST.value(), "O primeiro ponto não pode ser CHECK OUT");
                    return null;
                } else {
                    usuarioEncontrado.getPontos().add(pontoEncontrado);
                    pontoEncontrado.setUsuario(usuarioEncontrado);
                    response.sendError(HttpStatus.OK.value(), "Ponto salvo");
                    pontoSalvo = this.pontoCommandService.save(pontoEncontrado);
                }
            }
            else if (lastPonto != null) {
                DateTime start = new DateTime(lastPonto.getDataCriacao());
                DateTime end = new DateTime(pontoEncontrado.getDataCriacao());
                int days = Days.daysBetween(start, end).getDays();

                if (days > 1) {
                    response.sendError(HttpStatus.BAD_REQUEST.value(), "A diferença de datas não pode passar de 1 dia");
                    return null;
                }

                if (lastPonto.getTipoPonto().equals(pontoEncontrado.getTipoPonto())) {
                    response.sendError(HttpStatus.BAD_REQUEST.value(), "Tipo do ponto é igual com o anterior");
                    return null;
                }

                usuarioEncontrado.getPontos().add(pontoEncontrado);
                pontoEncontrado.setUsuario(usuarioEncontrado);
                response.sendError(HttpStatus.OK.value(), "Ponto salvo");
                pontoSalvo = this.pontoCommandService.save(pontoEncontrado);
            }
        }
        return pontoSalvo;
    }

    @Override
    public Ponto updatePonto(Long usuarioId, Ponto ponto) {
        Ponto pontoAtualizado = null;
        usuarioId = (usuarioId != null) ? usuarioId : 0L;

        Optional<Ponto> pontoOptional = Optional.ofNullable(ponto);
        Optional<Usuario> usuarioOptional = Optional.ofNullable(this.usuarioRepository.findOne(usuarioId));

        if (usuarioOptional.map(Usuario::getPontos).isPresent() && pontoOptional.map(Ponto::getId).isPresent()) {
            Ponto pontoParaAtualizar = pontoOptional.get();
            pontoAtualizado = this.pontoCommandService.update(pontoParaAtualizar);
        }
        return pontoAtualizado;
    }

    @Override
    public void deletePonto(Long usuarioId, Long pontoId) {
        usuarioId = (usuarioId != null) ? usuarioId : 0L;
        pontoId = (pontoId != null) ? pontoId : 0L;

        Optional<Ponto> pontoOptional = Optional.ofNullable(this.usuarioRepository.findPontoByUsuarioIdAndPontoId(usuarioId, pontoId));
        Optional<Usuario> usuarioOptional = Optional.ofNullable(this.usuarioRepository.findOne(usuarioId));

        if (usuarioOptional.map(Usuario::getPontos).isPresent() && pontoOptional.map(Ponto::getId).isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Ponto ponto = pontoOptional.get();
            usuario.getPontos().remove(ponto);
            this.pontoCommandService.delete(pontoId);
            this.usuarioRepository.save(usuario);
        }
    }
}
