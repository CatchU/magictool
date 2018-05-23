package com.magic.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/** 
 * RSA加密解密 
 * 此工具类能使用指定的字符串，每次生成相同的公钥和私钥且在linux和windows密钥也相同；相同的原文和密钥生成的密文相同 
 */  
public class RSAUtil {  
    private static final String ALGORITHM_RSA = "RSA";  
    private static final String ALGORITHM_SHA1PRNG = "SHA1PRNG";  
    private static final int KEY_SIZE = 1024;  
    private static final String PUBLIC_KEY = "RSAPublicKey";  
    private static final String PRIVATE_KEY = "RSAPrivateKey";  
    private static final String TRANSFORMATION = "RSA/None/NoPadding";  
      
    /** 
     * 解密 
     * 用私钥解密，解密字符串，返回字符串 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String decryptByPrivateKey(String data, String key) throws Exception {  
        return new String(decryptByPrivateKey(BASE64Util.decodeToByte(data), key));
    }  
      
    /** 
     * 加密 
     * 用公钥加密，加密字符串，返回用base64加密后的字符串 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByPublicKey(String data, String key) throws Exception {  
        return encryptByBytePublicKey(data.getBytes(), key);  
    }  
    /** 
     * 加密 
     * 用公钥加密，加密byte数组，返回用base64加密后的字符串 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByBytePublicKey(byte[] data, String key) throws Exception {  
        return BASE64Util.encodeByte(encryptByPublicKey(data, key));
    }  
      
    /** 
     * 解密 
     * 用私钥解密 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {  
        byte[] keyBytes = BASE64Util.decodeToByte(key);//对私钥解密
        /*取得私钥*/  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);  
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
        /*对数据解密*/  
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, new BouncyCastleProvider());//相同的原文、公钥能生成相同的密文。如果使用Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());//相同的原文、公钥生成的密文不同  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
          
        return cipher.doFinal(data);  
    }  
      
    /** 
     * 加密 
     * 用公钥加密 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {  
        byte[] keyBytes = BASE64Util.decodeToByte(key);//对公钥解密
        /*取得公钥*/  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);  
        Key publicKey = keyFactory.generatePublic(x509KeySpec);  
        /*对数据加密*/  
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, new BouncyCastleProvider());//相同的原文、公钥能生成相同的密文。如果使用Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());//相同的原文、公钥生成的密文不同  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
          
        return cipher.doFinal(data);  
    }  
      
    /** 
     * 取得私钥 
     * @param keyMap 
     * @return 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap) {  
        Key key = (Key) keyMap.get(PRIVATE_KEY);  
        return BASE64Util.encodeByte(key.getEncoded());
    }  
      
    /** 
     * 取得公钥 
     * @param keyMap 
     * @return 
     */  
    public static String getPublicKey(Map<String, Object> keyMap) {  
        Key key = (Key) keyMap.get(PUBLIC_KEY);  
        return BASE64Util.encodeByte(key.getEncoded());
    }  
      
    /** 
     * 初始化公钥和私钥 
     * @param seed 
     * @return 
     * @throws Exception 
     */  
    public static Map<String, Object> initKey(String seed) throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);  
        SecureRandom random = SecureRandom.getInstance(ALGORITHM_SHA1PRNG);//如果使用SecureRandom random = new SecureRandom();//windows和linux默认不同，导致两个平台生成的公钥和私钥不同  
        random.setSeed(seed.getBytes());//使用种子则生成相同的公钥和私钥  
        keyPairGen.initialize(KEY_SIZE, random);  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
          
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();//公钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();//私钥  
          
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
          
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    }  
      
    /** 
     * 使用示例 
     * @param args 
     * @throws Exception 
     */  
    /**
     * 备注：
     * @author liaoyy@belink.com
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {  
    	/*HashMap<String, Object> hashMap = new HashMap<>();
    	hashMap.put("finmallId", "1");
    	hashMap.put("fundOutName", "1dasdas23");
    	
    	HashMap<String, Object> hashMap2 = new HashMap<>();
    	hashMap2.put("finmallId", "2");
    	hashMap2.put("fundOutName", "12412dasdasd23");
    	  
    	List<Object> list = new ArrayList<>();
    	
    	list.add(hashMap);
    	list.add(hashMap2);*/
    	
        //String source = "您好";//原文  
        String seed = "abc1231";//种子  
          
       /* System.out.println("原文：\n" + "12345678");  
        Map<String, Object> keyMap = RSAUtil.initKey(seed);//初始化密钥  
        String publicKey = RSAUtil.getPublicKey(keyMap);//公钥  
        String privateKey = RSAUtil.getPrivateKey(keyMap);//私钥  
        System.out.println("公钥：\n" + publicKey);  
        System.out.println("私钥：\n" + privateKey);  
        String encodedStr = RSAUtil.encryptByPublicKey("12345678", publicKey);//加密  
        System.out.println("密文：\n" + encodedStr);  
        String decodedStr = RSAUtil.decryptByPrivateKey(encodedStr, privateKey);//解密  
        System.out.println("解密后的结果：\n" + decodedStr);  */
        
        
        
       String data = "PhdTFOYBs6jV+Xhohtow5Wllegm5X6oUsdioM52ksQ+730yL90LpMlBN0saXa1zxIFF7uHXIsfuKiIcg0+y6EZ0U1uZVkM+OOn07JqDqJPWauX5GkFfG/HP8XfuSatl1zh384XhfK7ySv3BRZxOzfq/DPOS7esZipi1JZDlatks=";
	  String dd = RSAUtil.decryptByPrivateKey(data, "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJoseQ/IJe5hu+PO0Q83nraXJuKpokhHrEyzYo/AmWuNdMZMT2/diGKDETUf8VGqsG/u1kW8kfF327O888rt2nQVbSpSk9sjzOjoaT7BQjknpAxWBum9DicmsvFvsdfx1jd/SDwpUNaQs3PEAAMdBboWJIqd2GfZK8EcNgt3enDnAgMBAAECgYB5JldfYm0EHMLTqdI4PgG3A6Hn3VsLp16qyw+5XKRykq7foFmP0yp4FvxvnZE5FoMdAjZYiGy6re/0FpgQr8XH05rq5HpdnsKPXVFLeZXMeAKFg4WikD+ipV/kShJrGRYCD8o3C2GqRoDT0buNNlc2tduDY+QaW9irkoICJ24rsQJBAOxI4+oEcvJg8hslNd8DGByr8JElK+g9i2ccE0/1pW37huAtysg7iGu5jysMTys+qotImp3dD08lNsGNfM4cjvkCQQCnCar9NLD3qcM2tOqhjE2ZPk0KhjxIjtaHsrJjuXy/v7tLkJQtk1Iwoe9cn1dBmrHN8jxtNN0cN3e2TOOodJbfAkBVWndKP9UuDSsn9ycgqkN5h/rc06qquCC1XIT1a20Y77VnIeOQtUCweVVoXYfzvS1qJw4DXKl9E5pWDMA6zEIxAkArgdslpInLmE4ee7T5av/zQET0zs344CuEoMjSLMgPcbMpFXli/ZJU1HjyGRazIcXaLJQMpc5JwTDPLOvHPsblAkASWhwD4UYQRdZArfQiKdS6F4X1V/XTMQ6LiGQXMOPHOw1jIHwX2ZiWmSMmDzrmxuvedw+70KfhUEguaK0ii52k");
        
        System.out.println(">>>"+dd);
    }  
}  