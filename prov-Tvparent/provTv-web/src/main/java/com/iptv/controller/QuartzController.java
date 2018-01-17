package com.iptv.controller;

import com.iptv.model.tv.helper.TvBase;
import com.iptv.service.tv.TvBaseService;
import com.iptv.util.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.iptv.util.AesUtils.parseByte2HexStr;

/**
 * 定时任务，执行每天向主服务器发送更新的电视信息
 */
@Slf4j
public class QuartzController {

    @Autowired
    private TvBaseService tvBaseService;

    public void init(){
        handler();
    }

    public void  handler(){
        List<TvBase> lists = new ArrayList<TvBase>();
        //查询前一天所有的更新操作的电视信息，发送给主服务器
        final String beginTime = new DateTime().withMillisOfDay(0).minusDays(1).toString("yyyy-MM-dd HH:mm:ss");
        final String endTime = new DateTime().withMillisOfDay(0).toString("yyyy-MM-dd HH:mm:ss");

        lists = this.tvBaseService.getTvBasesByTime(beginTime,endTime);
        String key = "iptv21\\/22";
        String encrytStr;
        byte[] encrytByte;

        byte[] byteRe;
        try{
            byteRe = AesUtils.enCrypt(lists.toString(),key);
            //加密过的二进制数组转化成16进制的字符串
            encrytStr = parseByte2HexStr(byteRe);
            System.out.println("加密后："+encrytStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = "123.134.66.50:8089/trip-web/getProvTv.do";
        visitPost(url,lists.toString());

    }

    /*使用时注意字符集 "GBK""UTF-8"*/
  public static String visitPost(String urlStr, String code) {
                 try{
                         URL url = new URL(urlStr);
                         HttpURLConnection con = (HttpURLConnection)url.openConnection();
                         con.setRequestMethod("GET");
                         con.connect();
                         BufferedReader reader = new BufferedReader(
                                                             new InputStreamReader(
                                                                     con.getInputStream(),code));
                         String line;
                         StringBuffer buffer = new StringBuffer();
                         while((line = reader.readLine()) != null) {
                                 buffer.append(line);
                             }
                         reader.close();
                        con.disconnect();
                         String res = buffer.toString();
                         return res;
                     } catch(Exception e) {
                         e.printStackTrace();
                     }
                 return null;
             }

}
