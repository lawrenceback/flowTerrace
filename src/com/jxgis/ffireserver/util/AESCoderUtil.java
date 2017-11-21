package com.jxgis.ffireserver.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jxgis.ffireserver.service.bean.ServerToken;

/**
 * AES Coder<br/>
 * secret key length: 128bit, default: 128 bit<br/>
 * mode: ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/>
 * padding: Nopadding/PKCS5Padding/ISO10126Padding/
 * 
 * @author tracy
 * 
 */
public class AESCoderUtil {

	protected static final Gson GSON = new GsonBuilder().create();

	private static final String KEY_ALGORITHM = "AES";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	public static String initKEY() {
		String KEY = "";
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration("system.properties");
			KEY = config.getString("AES.key");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return KEY;
	}

	/**
	 * 初始化密钥
	 * 
	 * @return [byte[]] 密钥
	 * @throws Exception
	 */
	public static byte[] initSecretKey() {
		// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}
		// 初始化此密钥生成器，使其具有确定的密钥大小
		// AES 要求密钥长度为 128
		kg.init(128);
		// 生成一个密钥
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * 转换密钥
	 * 
	 * @param [key] 二进制密钥
	 * @return 密钥
	 */
	private static Key toKey(byte[] key) {
		// 生成密钥
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param [data] 待加密数据
	 * @param [key] 密钥
	 * @return [byte[]] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            二进制密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param [data] 待加密数据
	 * @param [key] 二进制密钥
	 * @param [cipherAlgorithm] 加密算法/工作模式/填充方式
	 * @return [byte[]] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm);
	}

	/**
	 * 加密
	 * 
	 * @param [data] 待加密数据
	 * @param [key] 密钥
	 * @param [cipherAlgorithm] 加密算法/工作模式/填充方式
	 * @return [byte[]] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm)
			throws Exception {
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param [data] 待解密数据
	 * @param [key] 二进制密钥
	 * @return [byte[]] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 解密
	 * 
	 * @param [data] 待解密数据
	 * @param [key] 密钥
	 * @return [byte[]] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 解密
	 * 
	 * @param [data] 待解密数据
	 * @param [key] 二进制密钥
	 * @param [cipherAlgorithm] 加密算法/工作模式/填充方式
	 * @return [byte[]] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return decrypt(data, k, cipherAlgorithm);
	}

	/**
	 * 解密
	 * 
	 * @param [data] 待解密数据
	 * @param [key] 密钥
	 * @param [cipherAlgorithm] 加密算法/工作模式/填充方式
	 * @return [byte[]] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm)
			throws Exception {
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key);
		// 执行操作
		return cipher.doFinal(data);
	}

	private static String showByteArray(byte[] data) {
		if (null == data) {
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for (byte b : data) {
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 返回加密后字符串
	 * 
	 * @param [data] 加密字符串
	 * @return
	 * @throws [Exception]
	 */
	public static String encodeStr(String data) {
		String response = "";
		try {
			byte[] bytekey = Hex.decodeHex(initKEY().toCharArray());
			Key k = toKey(bytekey);
			byte[] b = encrypt(data.getBytes(), k);
			response = Hex.encodeHexString(b);
		} catch (DecoderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 返回解密后字符串
	 * 
	 * @param [data] 揭秘数据
	 * @return
	 * @throws Exception
	 */
	public static String decodeStr(String data) {
		String response = "";
		try {
			byte[] bytekey = Hex.decodeHex(initKEY().toCharArray());
			Key k = toKey(bytekey);
			byte[] b = Hex.decodeHex(data.toCharArray());
			byte[] decryptData = decrypt(b, k);
			response = new String(decryptData);
		} catch (DecoderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 解密航信密码
	 * 
	 * @param [data]
	 * @return
	 * @throws Exception
	 */
	public static String decodePwd(String pwd) throws Exception {
		String response = null;
		byte[] bytekey = Hex.decodeHex(initKEY().toCharArray());
		Key k = toKey(bytekey);
		byte[] b = Hex.decodeHex(pwd.toCharArray());
		byte[] decryptData = decrypt(b, k);
		response = new String(decryptData);
		return response;
	}

	/**
	 * 加密 token
	 * 
	 * @param serverToken
	 * @return
	 */
	public static String encodeAMStoken(ServerToken serverToken) {
		String xml = XmlUtil.toXml(serverToken, "token");
		String amstoken = encodeStr(xml);
		return amstoken;
	}

	/**
	 * 解密 token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static ServerToken decodeAMStoken(String token)
			throws Exception {
		ServerToken serverToken = null;

		byte[] bytekey = Hex.decodeHex(initKEY().toCharArray());
		Key k = toKey(bytekey);
		byte[] b = Hex.decodeHex(token.toCharArray());
		byte[] decryptData = decrypt(b, k);
		String tokenStr = new String(decryptData);

		serverToken = (ServerToken) XmlUtil.fromXml(tokenStr, ServerToken.class);
		return serverToken;
	}
}