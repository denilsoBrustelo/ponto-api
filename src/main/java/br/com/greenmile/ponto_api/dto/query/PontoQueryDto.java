package br.com.greenmile.ponto_api.dto.query;

import br.com.greenmile.ponto_api.common.enums.TipoPontoEnum;

import java.util.Date;

public class PontoQueryDto {

    private Long id;
    private Date data;
    private TipoPontoEnum tipo;
    private String descricao;

    public PontoQueryDto() {
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
}
