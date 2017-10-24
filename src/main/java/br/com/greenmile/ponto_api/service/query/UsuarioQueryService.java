package br.com.greenmile.ponto_api.service.query;

import br.com.greenmile.ponto_api.common.service.queries.IUsuarioQueryService;
import br.com.greenmile.ponto_api.domain.HoraTrabalhada;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.repository.UsuarioRepository;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
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
    public List<Ponto> findAllPontosByUsuarioId(Long usuarioId) {
        return this.usuarioRepository.findAllPontosByUsuarioId(usuarioId);
    }

    @Override
    public Page<Ponto> findAllPontosByUsuarioId(Long usuarioId, Pageable pageable) {
        return this.usuarioRepository.findAllPontosByUsuarioId(usuarioId, pageable);
    }

    @Override
    public HoraTrabalhada getHoraTrabalhadaPorUsuarioIdEPeriodo(Long usuarioId, Date dataInicio, Date dataFim) {
        HoraTrabalhada horasTrabalhadas = new HoraTrabalhada();

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(dataInicio);
        startDate.set(Calendar.HOUR, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(dataFim);
        endDate.set(Calendar.HOUR, 23);
        endDate.set(Calendar.MINUTE, 59);
        endDate.set(Calendar.SECOND, 59);

        List<Ponto> pontos = this.usuarioRepository.findAllPontosBetweenDatesByUsuarioId(usuarioId, startDate.getTime(), endDate.getTime());

        Integer minutos = 0;

        for (int i = 0; i < pontos.size(); i += 2) {
            if (i + 1 <= pontos.size() - 1) {
                DateTime inicio = new DateTime(pontos.get(i).getDataCriacao());
                DateTime fim = new DateTime(pontos.get(i + 1).getDataCriacao());
                minutos += Minutes.minutesBetween(inicio, fim).getMinutes();
            }
        }

        horasTrabalhadas.setHoras(minutos / 60);
        horasTrabalhadas.setMinutos(minutos % 60);
        return horasTrabalhadas;
    }
}
