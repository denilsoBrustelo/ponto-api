package br.com.greenmile.ponto_api.dto.command;

import br.com.greenmile.ponto_api.common.enums.PermissaoEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioCommandDto {

    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio.")
    @Length(min = 10, max = 255, message = "Nome deve conter entre 10 e 255 caracteres.")
    private String nome;

    @NotEmpty(message = "Username não pode ser vazio.")
    @Length(min = 3, max = 10, message = "Username deve conter entre 3 e 10 caracteres.")
    private String username;

    @NotEmpty(message = "Password não pode ser vazio.")
    @Length(min = 6, max = 20, message = "Password deve conter entre 6 e 20 caracteres.")
    private String password;

    @NotEmpty(message = "Permissao não pode ser vazio.")
    private PermissaoEnum permissao;

    public UsuarioCommandDto() {
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PermissaoEnum getPermissao() {
        return permissao;
    }

    public void setPermissao(PermissaoEnum permissao) {
        this.permissao = permissao;
    }
}
