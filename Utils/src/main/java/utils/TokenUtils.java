package utils;

import java.util.UUID;

public class TokenUtils {
    //获取一个新的UUID
    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    public static String createToken(String uuid) {
        return MD5Utils.SHA1(uuid);
    }
}
