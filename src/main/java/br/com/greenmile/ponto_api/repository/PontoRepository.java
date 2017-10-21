package br.com.greenmile.ponto_api.repository;

import br.com.greenmile.ponto_api.domain.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

}
