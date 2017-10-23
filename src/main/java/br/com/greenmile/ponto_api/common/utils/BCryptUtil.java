package br.com.greenmile.ponto_api.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;

public class BCryptUtil {

    public static final int LOG_ROUNDS = 12;
    private static final Logger LOG = LoggerFactory.getLogger(BCryptUtil.class);

    private BCryptUtil() {
    }

    public static String getHash(String password) {
        if (StringUtils.isEmpty(password)) {
            return password;
        }

        String hash = BCrypt.hashpw(password, BCrypt.gensalt(LOG_ROUNDS));
        return hash;
    }

    public static Boolean check(String candidate, String hash) {
        if (StringUtils.isEmpty(candidate) || StringUtils.isEmpty(hash)) {
            return false;
        }

        return BCrypt.checkpw(candidate, hash);
    }
}
