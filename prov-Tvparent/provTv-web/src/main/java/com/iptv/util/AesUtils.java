package com.iptv.util;


import com.iptv.model.tv.helper.TvBase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by w_z91 on 2017/4/17.
 */
public class AesUtils {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String str = "userName=wangzhan&password=wangzhan0418&mac=FCD50900B3CD&soundServerAddress=ssssss&upgradeServerAddress=oooooo&platformType=1";
        List<String> tvBases = new ArrayList<String>();
        TvBase tvBase1 = new TvBase();
        tvBase1.setActivationType((byte) 1);
        tvBase1.setMac("FFFFFFFFFFFFFFFFFF");
        tvBases.add(tvBase1.toString());
        TvBase tvBase2 = new TvBase();
        tvBase2.setActivationType((byte) 2);
        tvBase2.setMac("VVVVVVVVVVVVVVVV");
        tvBase2.setSoundServerAddress("www.baidu.com");
        tvBases.add(tvBase2.toString());
        TvBase tvBase3 = new TvBase();
        tvBase3.setActivationType((byte) 1);
        tvBase3.setMac("FFFFFFFFFFFFFFFFFF");
        tvBases.add(tvBase3.toString());
        TvBase tvBase4 = new TvBase();
        tvBase4.setActivationType((byte) 2);
        tvBase4.setMac("VVVVVVVVVVVVVVVV");
        tvBase4.setSoundServerAddress("www.baidu.com");
        tvBases.add(tvBase4.toString());
        TvBase tvBase5 = new TvBase();
        tvBase5.setActivationType((byte) 1);
        tvBase5.setMac("FFFFFFFFFFFFFFFFFF");
        tvBases.add(tvBase5.toString());
        TvBase tvBase6 = new TvBase();
        tvBase6.setActivationType((byte) 2);
        tvBase6.setMac("VVVVVVVVVVVVVVVV");
        tvBase6.setSoundServerAddress("www.baidu.com");
        tvBases.add(tvBase6.toString());
        TvBase tvBase7 = new TvBase();
        tvBase7.setActivationType((byte) 1);
        tvBase7.setMac("FFFFFFFFFFFFFFFFFF");
        tvBases.add(tvBase7.toString());
        TvBase tvBase8 = new TvBase();
        tvBase8.setActivationType((byte) 2);
        tvBase8.setMac("VVVVVVVVVVVVVVVV");
        tvBase8.setSoundServerAddress("www.baidu.com");
        tvBases.add(tvBase8.toString());
        TvBase tvBase9 = new TvBase();
        tvBase9.setActivationType((byte) 1);
        tvBase9.setMac("FFFFFFFFFFFFFFFFFF");
        tvBases.add(tvBase9.toString());
        TvBase tvBase10 = new TvBase();
        tvBase10.setActivationType((byte) 2);
        tvBase10.setMac("VVVVVVVVVVVVVVVV");
        tvBase10.setSoundServerAddress("www.baidu.com");
        tvBases.add(tvBase10.toString());

        JSONArray json22 = JSONArray.fromObject(tvBases);
        JSONObject jsonobj22 = new JSONObject();
        jsonobj22.put("tv", json22);

        String key = "iptv21\\/22";
        String encrytStr;
        byte[] encrytByte;

        byte[] byteRe = enCrypt(jsonobj22.toString(), key);

        //加密过的二进制数组转化成16进制的字符串
        encrytStr = parseByte2HexStr(byteRe);
        System.out.println("加密后：" + encrytStr);

        //加密过的16进制的字符串转化成二进制数组
        encrytByte = parseHexStr2Byte(encrytStr);
        System.out.println("解密后：" + deCrypt(encrytByte, key));
        String a = deCrypt(encrytByte, key);
        JSONObject jsonobj = JSONObject.fromObject(a);
        JSONArray json = jsonobj.getJSONArray("tv");
        for (Object jsono : json) {
            System.out.println("解密后jsonarray ：" +jsono);
            JSONObject tvBase =  JSONObject.fromObject(jsono);
            TvBase tvBase11 = new TvBase();
            System.out.println("mac==="+tvBase11.getMac());
            tvBase11.setId(Integer.parseInt(String.valueOf(tvBase.get("id"))));
            System.out.println("id==="+tvBase11.getId());
            tvBase11.setUserName(String.valueOf(tvBase.get("userName")));
            System.out.println("userName==="+tvBase11.getUserName());
            tvBase11.setPassword(String.valueOf(tvBase.get("password")));
            System.out.println("password==="+tvBase11.getPassword());
            tvBase11.setSoundServerAddress(String.valueOf(tvBase.get("soundServerAddress")));
            System.out.println("soundServerAddress==="+tvBase11.getSoundServerAddress());
            tvBase11.setUpgradeServerAddress(String.valueOf(tvBase.get("upgradeServerAddress")));
            System.out.println("upgradeServerAddress==="+tvBase11.getUpgradeServerAddress());
            tvBase11.setPlatformType(Byte.parseByte(String.valueOf(tvBase.get("platformType"))));
            System.out.println("platformType==="+tvBase11.getPlatformType());



        }
    }
    /**
     * 加密函数
     * @param content   加密的内容
     * @param strKey    密钥
     * @return          返回二进制字符数组
     * @throws Exception
     */
    public static byte[] enCrypt(String content,String strKey) throws Exception{
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;
        String str = content;

        keygen = KeyGenerator.getInstance("AES");
        keygen.init(128, new SecureRandom(strKey.getBytes()));

        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");

        c.init(Cipher.ENCRYPT_MODE, desKey);

        cByte = c.doFinal(str.getBytes("UTF-8"));

        return cByte;
    }

    /** 解密函数
     * @param src   加密过的二进制字符数组
     * @param strKey  密钥
     * @return
     * @throws Exception
     */
    public static String deCrypt (byte[] src,String strKey) throws Exception{
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;

        keygen = KeyGenerator.getInstance("AES");
        keygen.init(128, new SecureRandom(strKey.getBytes()));

        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");

        c.init(Cipher.DECRYPT_MODE, desKey);


        cByte = c.doFinal(src);

        return new String(cByte,"UTF-8");
    }


    /**2进制转化成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }



}
