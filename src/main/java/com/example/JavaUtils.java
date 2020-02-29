package com.example;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/30 14:33.
 */


public class JavaUtils {

    public static void main(String[] args) {
        String ip = "123.57.232.191";
        System.out.println(convertDecimalToIP(ip));
    }

    public static String convertDecimalToBinary(int value) {
        return Integer.toHexString(value);
    }

    public static String convertDecimalToIP(String ip) {
        String prefix = "64:ff9b::";
        String[] ip_duan = ip.split("\\.");
        System.out.println(ip_duan.length);
        StringBuffer result = new StringBuffer();
        result.append(prefix);
        int i = 0;
        for (String ip_s : ip_duan) {
            if (i != 1) {
                result.append(convertDecimalToBinary(Integer.valueOf(ip_s)));
            } else if (i == 1) {
                result.append(convertDecimalToBinary(Integer.valueOf(ip_s))).append(":");
            }
            i++;
        }

        return result.toString();
    }
}
