package com.icbc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * String 转 BigDecimal 精度丢失问题
 */
public class StringToBigDecimal {

    public static void main(String[] args) {
        /*String str1="20192.0087844500";
        BigDecimal bd=new BigDecimal(str1);
        System.out.println(bd);

        DecimalFormat df1 = new DecimalFormat("0.00");

        String str = df1.format(bd);

        System.out.println(str); //13.15*/


        //汉字转换成ASCII码
        String str = "最近太他妈烦了谁能陪我说说话呀";
        /*char[] a = new char[str.length()];
        a = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            System.out.print((int)a[i]+" ");
            sb.append((int)a[i]+",");
        }
        System.out.println(sb);*/
        String strGBK = null;
        try {
            strGBK = URLEncoder.encode(str, "GBK");
            System.out.println(strGBK);
            String strUTF8 = URLDecoder.decode(str, "UTF-8");
            System.out.println(strUTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


}
