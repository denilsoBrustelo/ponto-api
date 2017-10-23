package br.com.greenmile.ponto_api.repository;

import br.com.greenmile.ponto_api.domain.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

    List<Ponto> findAllByDataCriacaoBetween(Date dateStart, Date dateEnd);

    @Query(value = "SELECT * FROM ponto WHERE " +
            "DATE(data_criacao) >= ?1 AND DATE(data_criacao) <= ?2", nativeQuery = true)
    List<Ponto> findAllByDataCriacaoBetweenDates(Date dateStart, Date dateEnd);
}
