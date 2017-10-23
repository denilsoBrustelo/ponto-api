package br.com.greenmile.ponto_api.dto.command;

import br.com.greenmile.ponto_api.common.enums.TipoPontoEnum;

import java.util.Date;

public class PontoCommandDto {

    private Long id;
    private Date data;
    private TipoPontoEnum tipo;
    private String descricao;
    private Long usuarioId;

    public PontoCommandDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipoPontoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoPontoEnum tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
