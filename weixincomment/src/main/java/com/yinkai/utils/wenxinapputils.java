package com.yinkai.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class wenxinapputils {


    public static String getThisIp()  {
        InetAddress address = null;//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostAddress = address.getHostAddress();

        System.out.println("本地ip："+hostAddress);
        return  hostAddress;
    }


    public static java.sql.Timestamp getNowTimestamp(){

        return  new Timestamp(new java.util.Date().getTime());
    }


}
