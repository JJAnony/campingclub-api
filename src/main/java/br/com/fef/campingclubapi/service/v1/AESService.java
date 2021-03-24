package br.com.fef.campingclubapi.service.v1;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Service
public class AESService {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "#,}#K2:$Jf*8.2wG";

    public String encrypted(String value) throws Exception {

        Cipher cipher = Cipher.getInstance(ALGORITHM);

        Key key = generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] enc = cipher.doFinal(value.getBytes());

        return Base64.encodeBase64String(enc);
    }

    public String decrypted(String value) throws Exception {

        Cipher cipher = Cipher.getInstance(ALGORITHM);

        Key key = generateKey();
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] dec = cipher.doFinal(Base64.decodeBase64(value));

        String user = new String(dec);
        return user;

    }

    private static SecretKeySpec generateKey() throws Exception {
        return new SecretKeySpec(KEY.getBytes(), ALGORITHM);
    }

}
