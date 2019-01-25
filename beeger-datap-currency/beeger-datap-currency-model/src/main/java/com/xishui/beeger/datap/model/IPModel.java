package com.xishui.beeger.datap.model;

import java.net.*;
import java.util.Enumeration;

public enum IPModel {
    IP;

    public String localIP() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
