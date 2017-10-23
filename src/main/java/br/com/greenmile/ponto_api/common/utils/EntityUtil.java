package br.com.greenmile.ponto_api.common.utils;

import br.com.greenmile.ponto_api.domain.Ponto;
import br.com.greenmile.ponto_api.domain.Usuario;
import br.com.greenmile.ponto_api.dto.command.UsuarioCommandDto;
import br.com.greenmile.ponto_api.dto.query.UsuarioQueryDto;

import java.lang.reflect.Field;

public class EntityUtil {

    private EntityUtil() {
    }

    public static <T> T merge(T oldValue, T newValue) {
        try {
            Class<?> clazz = oldValue.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Object returnValue = clazz.newInstance();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value1 = field.get(oldValue);
                Object value2 = field.get(newValue);
                Object value = (value2 != null) ? value2 : value1;
                field.set(returnValue, value);
            }
            return (T) returnValue;
        } catch (Exception e) {
            return null;
        }
    }

    public static Usuario converterUsuarioCommandDtoParaUsuario(UsuarioCommandDto usuarioCommandDto) {
        Usuario usuario = new Usuario();

        if (usuarioCommandDto == null) {
            return usuario;
        }

        usuario.setId(usuarioCommandDto.getId());
        usuario.setNome(usuarioCommandDto.getNome());
        usuario.setUsername(usuarioCommandDto.getUsername());
        usuario.setPassword(usuarioCommandDto.getPassword());
        usuario.setPermissao(usuarioCommandDto.getPermissao());
        return usuario;
    }

    public static UsuarioQueryDto converterUsuarioParaUsuarioQueryDto(Usuario usuario) {
        UsuarioQueryDto usuarioQueryDto = new UsuarioQueryDto();

        if (usuario == null) {
            return usuarioQueryDto;
        }

        usuarioQueryDto.setId(usuario.getId());
        usuarioQueryDto.setNome(usuario.getNome());
        usuarioQueryDto.setUsername(usuario.getUsername());
        usuarioQueryDto.setPermissao(usuario.getPermissao());
        return usuarioQueryDto;
    }
}
