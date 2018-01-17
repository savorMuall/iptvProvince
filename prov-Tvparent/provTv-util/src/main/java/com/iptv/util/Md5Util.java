package com.iptv.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 计算文件MD5值
 * @author wangzhan
 *
 */
public class Md5Util {

	private static final Logger log = LoggerFactory.getLogger(Md5Util.class);
	
	private static final String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	
    private static MessageDigest md = null;
 
    //类被加载的时候初始化MessageDigest
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            log.error("NoSuchAlgorithmException: md5", ne);
        }
    }
 
    /**
     * 计算文件M5D值
     * @param target 目标文件
     * @return md5串
     */
    public static String md5Compute(File target) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(target);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
 
            return new String(Hex.encodeHex(md.digest()));
        } catch (FileNotFoundException e) {
            log.error("md5 file " + target.getAbsolutePath() + " failed:" + e.getMessage());
        } catch (IOException e) {
            log.error("md5 file " + target.getAbsolutePath() + " failed:" + e.getMessage());
        } finally {
            try {
                if (fis != null) {
                	fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
	
    /**
     * 计算字符串md5值
     */
    public static String md5Compute(String source){
    	return byteArrayToHexString(md.digest(source.getBytes())).toLowerCase();
    }
    
    private static String byteArrayToHexString(byte[] digest){
    	StringBuffer sb = new StringBuffer();
    	
    	for (int i = 0; i < digest.length; i++){
    		sb.append(byteToHexString(digest[i]));
    	}
    	return sb.toString();
    }
    
    private static String byteToHexString(byte b){
    	int n = b;
    	
    	if (n < 0){
    		n += 256;
    	}
    	int d1 = n/16;
    	int d2 = n%16;
    	return hexDigits[d1] + hexDigits[d2];
    }
    
	/**
	 * 测试方法
	 */
	public static void main(String[] args) {
		
		String path = "D:\\演示\\王府茶楼\\酒楼宣传片";
		File[] files = new File(path).listFiles();
		
		File bakFile = null;
		for(File file : files){
			if (file.getName().endsWith(".mp4")){
				String name = file.getName();
				
				String fileName = name.substring(0, name.length() -  4);
				//String uuid = UUID.randomUUID().toString();
				String uuid = uuid(fileName);
				//bakFile = new File("D:\\点播视频\\9月下18期\\视频列表封面\\"+uuid+".png");
				//file.renameTo(bakFile);
				System.out.println("fileName="+ fileName +", uuid="+uuid);
				System.out.println("fileName="+ fileName +", md5="+ Md5Util.md5Compute(file));
				System.out.println("-------------------------------");
				bakFile = new File(path+"/"+uuid+".mp4");
				file.renameTo(bakFile);
			}
			
		}
//		File file = new File("D:\\test\\apk\\savormedia1.7.2.apk");
//		System.out.println(md5Compute(file));
		
//		File file = new File("D:\\test\\apk\\savormedia1.7.0.apk");
//		System.out.println(md5Compute(file));
		
//		String username = "宁静致远";
//		String password = "password";
//		
//		System.out.println(uuid(username));
//		System.out.println(md5Compute(password));
	}

	
	public static String uuid(String name){
		String tmpName = name.replaceAll("（", "(").replaceAll("）", ")");
		UUID uuid = UUID.nameUUIDFromBytes(tmpName.getBytes());
		return uuid.toString();
	}
}
