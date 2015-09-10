package com.xmlconverter.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptUtils {

	public static void main(String[] args) throws Exception {
		if(args.length==0) {
			System.out.println("Inserisci la stringa da criptare");
			System.exit(1);
		}
		System.out.println(encrypt(args[0]));

	}

	private static String key = "5102yliciSevoLI";

	private static String encrypt(String password) throws Exception {
		byte[] keyData = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] hasil = cipher.doFinal(password.getBytes());
		return (new BASE64Encoder().encode(hasil));
	}

	public static String decrypt(String string) throws Exception {
		byte[] keyData = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] hasil = cipher.doFinal(new BASE64Decoder().decodeBuffer(string));
		return (new String(hasil));
	}
}
