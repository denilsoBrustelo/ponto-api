package br.com.greenmile.ponto_api.controller.query;

import br.com.greenmile.ponto_api.common.controller.queries.IUsuarioQueryRest;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.service.query.UsuarioQueryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/usuarios")
public class UsuarioQueryRestController {

    @Autowired
    private UsuarioQueryService usuarioQueryService;

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
}
