package br.com.greenmile.ponto_api.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BCryptUtilTests {

    @Test
    public void testGerarHash() {
        String senha = "123456";
        String hash = BCryptUtil.getHash(senha);

        assertNotNull(hash);
        assertTrue(!hash.isEmpty());
        assertTrue(BCryptUtil.check(senha, hash));
    }

    @Test
    public void testVerificarSenhaErrada() {
        String senha = "123456";
        String senhaErrada = "123450";
        String hash = BCryptUtil.getHash(senha);

        assertFalse(BCryptUtil.check(senhaErrada, hash));
    }

    @Test
    public void testVerificarSenhaCandidataNula() {
        String senha = "123456";
        String hash = BCryptUtil.getHash(senha);

        assertFalse(BCryptUtil.check(null, hash));
    }

    @Test
    public void testVerificarHashNulo() {
        String senha = "123456";

        assertFalse(BCryptUtil.check(senha, null));
    }

    @Test
    public void testGerarHashComSenhaVazia() {
        String hash = BCryptUtil.getHash("");

        assertTrue(hash.isEmpty());
    }

    @Test
    public void testGerarHashComSenhaNula() {
        String hash = BCryptUtil.getHash(null);

        assertNull(hash);
    }
}
