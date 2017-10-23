package br.com.greenmile.ponto_api.domain;

import br.com.greenmile.ponto_api.common.enums.TipoPontoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ponto")
public class Ponto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Fortaleza")
    @Column(nullable = false)
    private Date dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPontoEnum tipoPonto;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(cascade = {CascadeType.DETACH})
    private Usuario usuario;

    public Ponto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public TipoPontoEnum getTipoPonto() {
        return tipoPonto;
    }

    public void setTipoPonto(TipoPontoEnum tipoPonto) {
        this.tipoPonto = tipoPonto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
