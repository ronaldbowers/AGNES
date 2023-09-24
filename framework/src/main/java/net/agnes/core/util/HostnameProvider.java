package net.agnes.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostnameProvider {

    public static String getLocalHostname() {
        String tmpHostName;
        try {
            tmpHostName = InetAddress.getLocalHost().getHostName();
        }
        //
        // This block will likely remain uncovered in testing
        // due to the pointlessness of wrapping InetAddress and
        // forcing the exception. Wrapping getLocalHost() to
        // capture the checked exception just moves the problem
        // elsewhere.
        //
        catch (UnknownHostException e) {
            tmpHostName = "Exception thrown during resolution - " + e.getMessage();
        }
        return tmpHostName;
    }
}
