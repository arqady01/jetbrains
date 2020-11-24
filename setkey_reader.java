package setkey;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class setkey_reader {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("读取文件"));
        BufferedWriter out = new BufferedWriter(new FileWriter("写出文件"));
        String plain;
        String key = "12345678";
        while ((plain = in.readLine()) != null) {
            byte[] cipher = encrypt(plain,key);
            String encode = Base64.getEncoder().encodeToString(cipher);
            System.out.println(new String(encode));
            out.write(encode + "\n");
            String cipher_n = decrypt(cipher,key);
            System.out.println(cipher_n);
            out.write(cipher_n + "\n");
        }
        out.close();
        in.close();
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
