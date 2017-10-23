package br.com.greenmile.ponto_api.service.command;

import br.com.greenmile.ponto_api.common.service.commands.IPontoCommandService;
import br.com.greenmile.ponto_api.common.utils.EntityUtil;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PontoCommandService implements IPontoCommandService {

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public Ponto save(Ponto ponto) {
        Ponto pontoSalvo = null;

        if (ponto != null && ponto.getId() == null) {
            pontoSalvo = this.pontoRepository.save(ponto);
        }
        return pontoSalvo;
    }

    @Override
    public Ponto update(Ponto ponto) {
        Ponto pontoAtualizado = null;

        if (ponto != null && ponto.getId() != null) {
            Long id = ponto.getId();
            Ponto pontoEncontrado = this.pontoRepository.findOne(id);
            Ponto pontoMergiado = EntityUtil.merge(pontoEncontrado, ponto);
            pontoAtualizado = this.pontoRepository.save(pontoMergiado);
        }
        return pontoAtualizado;
    }

    @Override
    public void delete(Long id) {
        id = (id != null) ? id : 0L;
        this.pontoRepository.delete(id);
    }
}
