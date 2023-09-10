package com.jet.qrcodeencrypted.qrcodeencrypt.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EncryptDecrypt {
  static Cipher ecipher;
  static Cipher dcipher;
  // 8-byte Salt
  static byte[] salt = {
    (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
    (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
  };
  // Iteration count
  static int iterationCount = 19;

  @Autowired private Environment env;

  /**
   * @param plainText Text input to be encrypted
   * @return Returns encrypted text
   * @throws java.security.NoSuchAlgorithmException
   * @throws java.security.spec.InvalidKeySpecException
   * @throws javax.crypto.NoSuchPaddingException
   * @throws java.security.InvalidKeyException
   * @throws java.security.InvalidAlgorithmParameterException
   * @throws java.io.UnsupportedEncodingException
   * @throws javax.crypto.IllegalBlockSizeException
   * @throws javax.crypto.BadPaddingException
   */
  public String encrypt(String plainText)
      throws NoSuchAlgorithmException,
          InvalidKeySpecException,
          NoSuchPaddingException,
          InvalidKeyException,
          InvalidAlgorithmParameterException,
          UnsupportedEncodingException,
          IllegalBlockSizeException,
          BadPaddingException {
    // Key generation for enc and desc
    KeySpec keySpec = new PBEKeySpec(env.getProperty("secret.key").toCharArray(), salt, iterationCount);
    SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
    // Prepare the parameter to the ciphers
    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

    // Enc process
    ecipher = Cipher.getInstance(key.getAlgorithm());
    ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
    String charSet = "UTF-8";
    byte[] in = plainText.getBytes(charSet);
    byte[] out = ecipher.doFinal(in);
    return new String(Base64.getEncoder().encode(out));
  }

  /**
   * @param secretKey Key used to decrypt data
   * @param encryptedText encrypted text input to decrypt
   * @return Returns plain text after decryption
   * @throws java.security.NoSuchAlgorithmException
   * @throws java.security.spec.InvalidKeySpecException
   * @throws javax.crypto.NoSuchPaddingException
   * @throws java.security.InvalidKeyException
   * @throws java.security.InvalidAlgorithmParameterException
   * @throws java.io.UnsupportedEncodingException
   * @throws javax.crypto.IllegalBlockSizeException
   * @throws javax.crypto.BadPaddingException
   */
  public String decrypt(String encryptedText)
      throws NoSuchAlgorithmException,
          InvalidKeySpecException,
          NoSuchPaddingException,
          InvalidKeyException,
          InvalidAlgorithmParameterException,
          IllegalBlockSizeException,
          BadPaddingException,
          IOException {
    // Key generation for enc and desc
    KeySpec keySpec = new PBEKeySpec(env.getProperty("secret.key").toCharArray(), salt, iterationCount);
    SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
    // Prepare the parameter to the ciphers
    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
    // Decryption process; same key will be used for decr
    dcipher = Cipher.getInstance(key.getAlgorithm());
    dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
    byte[] enc = Base64.getDecoder().decode(encryptedText);
    byte[] utf8 = dcipher.doFinal(enc);
    String charSet = "UTF-8";
    return new String(utf8, charSet);
  }
}
