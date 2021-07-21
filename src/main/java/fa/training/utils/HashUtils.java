package fa.training.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashUtils {

    public static String generateSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[12];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String generatePwdHash(String pwd, String salt){
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-384");
            msgDigest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] pwdHash = msgDigest.digest(pwd.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(pwdHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateCookieKey(int uid, String pwdHash){
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(String.format("%d", uid).getBytes(StandardCharsets.UTF_8));
            byte[] key = msgDigest.digest(pwdHash.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String salt = generateSalt();
        System.out.println(salt + " : " + salt.length());
        String pwdHash = generatePwdHash("admin", salt);
        System.out.println(pwdHash + " : " + pwdHash.length());
        String key = generateCookieKey(1, pwdHash);
        System.out.println(key + " : " + key.length());
    }
}
