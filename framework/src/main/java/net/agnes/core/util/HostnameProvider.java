package net.agnes.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public enum HostnameProvider {
    ;

    public static String getLocalHostname() {

        try {
            return InetAddress.getLocalHost().getHostName();
        }
        //
        // This block will likely remain uncovered in testing
        // due to the pointlessness of wrapping InetAddress and
        // forcing the exception. Wrapping getLocalHost() to
        // capture the checked exception just moves the problem
        // elsewhere.
        //
        catch (UnknownHostException _) {
            return "localhost";
        }
    }
}
