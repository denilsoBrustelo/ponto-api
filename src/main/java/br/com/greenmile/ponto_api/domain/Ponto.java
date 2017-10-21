package br.com.greenmile.ponto_api.domain;

import br.com.greenmile.ponto_api.common.enums.TipoPontoEnum;

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
    @Column(nullable = false)
    private Date data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPontoEnum tipoPonto;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Usuario usuario;

    public Ponto() {
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
