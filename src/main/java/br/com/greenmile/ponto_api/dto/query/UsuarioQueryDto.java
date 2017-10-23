package br.com.greenmile.ponto_api.dto.query;

import br.com.greenmile.ponto_api.common.enums.PermissaoEnum;

import java.util.Set;

public class UsuarioQueryDto {

    private Long id;
    private String nome;
    private String username;
    private PermissaoEnum permissao;
    private Set<PontoQueryDto> pontos;

    public UsuarioQueryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PermissaoEnum getPermissao() {
        return permissao;
    }

    public void setPermissao(PermissaoEnum permissao) {
        this.permissao = permissao;
    }

    public Set<PontoQueryDto> getPontos() {
        return pontos;
    }

    public void setPontos(Set<PontoQueryDto> pontos) {
        this.pontos = pontos;
    }
}
