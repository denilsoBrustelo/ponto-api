package br.com.greenmile.ponto_api.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtil {

    private static final int LOG_ROUNDS = 12;
    private static final Logger LOG = LoggerFactory.getLogger(BCryptUtil.class);

    private BCryptUtil() {
    }

    public static String getHash(String password) {
        if (password == null) {
            return password;
        }

        String hash = BCrypt.hashpw(password, BCrypt.gensalt(LOG_ROUNDS));
        return hash;
    }

    public static Boolean check(String candidate, String hash) {
        return BCrypt.checkpw(candidate, hash);
    }
}
