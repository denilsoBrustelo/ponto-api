package br.com.greenmile.ponto_api.controller.query;

import br.com.greenmile.ponto_api.common.controller.queries.IUsuarioQueryRest;
import br.com.greenmile.ponto_api.domain.HoraTrabalhada;
import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.service.query.UsuarioQueryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "api/v1/usuarios")
public class UsuarioQueryRestController implements IUsuarioQueryRest {

    @Autowired
    private UsuarioQueryService usuarioQueryService;

    // Início - Usuário
    @GetMapping("/{usuario-id}")
    @Cacheable(value = "usuarios")
    public Usuario findById(@PathVariable("usuario-id") Long id) {
        return this.usuarioQueryService.findById(id);
    }

    @GetMapping("")
    @Cacheable(value = "usuarios")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    public Page<Usuario> findAll(Pageable pageable) {
        return this.usuarioQueryService.findAll(pageable);
    }
    // Fim - Usuário

    // Início - Ponto
    @GetMapping("/{usuario-id}/pontos/{ponto-id}")
    @Cacheable(value = "pontos")
    @Override
    public Ponto findPontoByUsuarioIdAndPontoId(@PathVariable("usuario-id") Long usuarioId,
                                                @PathVariable("ponto-id") Long pontoId) {
        return this.usuarioQueryService.findPontoByUsuarioIdAndPontoId(usuarioId, pontoId);
    }

    @GetMapping("/{usuario-id}/pontos")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @Cacheable(value = "pontos")
    @Override
    public Page<Ponto> findAllPontosByUsuarioId(@PathVariable("usuario-id") Long usuarioId, Pageable pageable) {
        return this.usuarioQueryService.findAllPontosByUsuarioId(usuarioId, pageable);
    }

    @GetMapping("/{usuario-id}/hora-trabalhada")
    @Override
    public Callable<HoraTrabalhada> getHoraTrabalhadaPorUsuarioIdEPeriodo(@PathVariable("usuario-id") Long usuarioId,
                                                                          @RequestParam("data_inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
                                                                          @RequestParam("data_fim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {
        return () -> this.usuarioQueryService.getHoraTrabalhadaPorUsuarioIdEPeriodo(usuarioId, dataInicio, dataFim);
    }


    // Fim - Ponto
}
