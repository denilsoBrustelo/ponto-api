package br.com.greenmile.ponto_api.service.query;

import br.com.greenmile.ponto_api.common.service.queries.IPontoQueryService;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PontoQueryService implements IPontoQueryService {

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public Ponto findById(Long id) {
        id = (id != null) ? id : 0L;
        return this.pontoRepository.findOne(id);
    }

    @Override
    public List<Ponto> findAll() {
        return this.pontoRepository.findAll();
    }

    @Override
    public Page<Ponto> findAll(Pageable pageable) {
        return this.pontoRepository.findAll(pageable);
    }

    public List<Ponto> findAllByDataCriacaoBetween(Date dateStart, Date dateEnd) {
        return this.pontoRepository.findAllByDataCriacaoBetween(dateStart, dateEnd);
    }

    public List<Ponto> findAllByDataCriacaoBetweenDates(Date dateStart, Date dateEnd) {
        return this.pontoRepository.findAllByDataCriacaoBetweenDates(dateStart, dateEnd);
    }
}
