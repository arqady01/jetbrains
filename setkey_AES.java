import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class setkey_AES {
    public static void main(String[] args) throws Exception {
        //orginal text
        String text = "hotmail";

        //key
        String key = "1234567887654321";

        //calculator method
        String transformation = "AES";

        String type = "AES";

        //creat locked object
        Cipher cipher = Cipher.getInstance(transformation);

        //creat locked rule
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),type);

        /*init(int opmode,Certificate certificate)
        第一个参数表示模式，加密模式和解密模式
        第二个参数表示加密的规则
        */
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);

        byte[] bytes = cipher.doFinal(text.getBytes());
        //直接打印若在编码表内找不到，会乱码
        String encode = Base64.getEncoder().encodeToString(bytes);
        System.out.println(encode);

    }
}
