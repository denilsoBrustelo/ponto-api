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

        assertTrue(BCryptUtil.check(senha, hash));
    }

    @Test
    public void testGerarHashComSenhaErrada() {
        String senha = "123456";
        String senhaErrada = "123450";
        String hash = BCryptUtil.getHash(senha);

        assertFalse(BCryptUtil.check(senhaErrada, hash));
    }

    @Test
    public void testGerarHashComSenhaNuka() {
        String hash = BCryptUtil.getHash(null);

        assertNull(hash);
    }
}
