package setkey;
/**
 * @author arqady
 * @version 1.0
 * @date 11/24/2020
 */
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

public class setkey_scanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入字符：");
        String plain = scanner.nextLine();
        System.out.println("明文: " + plain);
        //自定义密钥key(DES标准为8位)
        String key = "12362642";
        byte[] cipher = encrypt(plain,key);
        String encode = Base64.getEncoder().encodeToString(cipher);
        System.out.println("加密后: " + new String(encode));
        String cipher_n = decrypt(cipher,key);
        System.out.println("解密后: " + cipher_n);
    }

    //DES加密程式
    private static byte[] encrypt(String plain, String key) {
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE,securekey,random);
            byte[] result = cipher.doFinal(plain.getBytes());
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return  null;
    }
    //DES解密程式
    private static String decrypt(byte[] content, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE,securekey,random);
            byte[] result = cipher.doFinal(content);
            return new String(result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}

