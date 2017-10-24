package br.com.greenmile.ponto_api.repository;

import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    @Query(value = "SELECT MAX(ponto) FROM Ponto ponto WHERE ponto.usuario.id = :id")
    Ponto findLastPontoByUsuarioId(@Param("id") Long id);

    @Query(value = "SELECT p FROM Ponto p JOIN p.usuario u " +
            "WHERE u.id = :usuarioid AND p.id = :pontoid")
    Ponto findPontoByUsuarioIdAndPontoId(@Param("usuarioid") Long usuarioId, @Param("pontoid") Long pontoId);

    @Query("SELECT ponto FROM Ponto ponto WHERE ponto.usuario.id = :id ORDER BY ponto.id ASC")
    List<Ponto> findAllPontosByUsuarioId(@Param("id") Long id);

    @Query("SELECT ponto FROM Ponto ponto WHERE ponto.usuario.id = :id ORDER BY ponto.id ASC")
    Page<Ponto> findAllPontosByUsuarioId(@Param("id") Long id, Pageable pageable);
}
