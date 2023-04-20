package com.foodrecipe.service;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class AES {
	private static final String ALGORITHM = "AES";
	private static final byte[] SECRET_KEY = "586E327235753878".getBytes();
	
	private static Key generateKey() {
		Key key = new SecretKeySpec(SECRET_KEY,ALGORITHM);
		return key;
	}
	
	public static String encrypt(String value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, generateKey());
		
		byte[] encValue = cipher.doFinal(value.getBytes());
		byte[] encryptedByteValue = new Base64().encode(encValue);
		
		return new String(encryptedByteValue);
	}
	
//	public static String decrypt(String encryptedValue) throws Exception {
//	        Cipher cipher = Cipher.getInstance(ALGORITHM);
//	        cipher.init(Cipher.DECRYPT_MODE, generateKey());
//	         
//	        byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
//	 
//	        byte[] enctVal = cipher.doFinal(decodedBytes);
//	        
//	        System.out.println("Decrypted Value :: " + new String(enctVal));
//	        return new String(enctVal);
//	}
}
