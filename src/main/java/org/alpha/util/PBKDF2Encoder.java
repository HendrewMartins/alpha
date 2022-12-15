package org.alpha.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PBKDF2Encoder {
    // !Criptgrafy method of my password to save on my db

    //www.owasp.org/index.php/Hashing_Java

    @ConfigProperty(name = "org.alpha.password.secret")
    String secret;
    @ConfigProperty(name = "org.alpha.password.interaction")
    Integer interaction;
    @ConfigProperty(name = "org.alpha.password.keylenght")
    Integer keylenght;

    public String encode(CharSequence cs) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(
                            new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), interaction, keylenght))
                    .getEncoded();
            
                    return Base64.getEncoder().encodeToString(result);
        
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
