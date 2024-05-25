package py.com.kytech.mcs.kytech.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class EncryptUtil {
    public static boolean isPasswordMatch(String passwordPlain, String passwordHash) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(passwordPlain, passwordHash);
    }

    public static String passwordEnconde(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }
}
