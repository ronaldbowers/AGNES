package net.agnes.core.util;


import lombok.Getter;
import java.util.Properties;

/**
 * Runtime exception for when things go wrong.
 * <p>
 * An {@code AgnesException} is a {@link RuntimeException} that should
 * be used whenever something goes wrong in the system. It is intentionally
 * a {@code RuntimeException} to bypass the Java requirement to catch
 * other types of {@code Exception}.
 * <p>
 * In addition to the information contained in the base {@link Exception},
 * {@code AgnesException} also contains a snapshot of system information.
 * The information captured is the host name, the current system time, and
 * the free, max and total memory values. This information is to help track
 * the origin and possible cause of the {@code Exception}, especially if
 * Agnes is being used in a distributed environment and exceptions might
 * be transferred to different computers.
 *
 * @author Ron Bowers
 * @version 1.0
 * @since 1.0
 */
@Getter
public class AgnesException extends RuntimeException {

    private final long eventTime;
    private final long maxMemory;
    private final long freeMemory;
    private final long totalMemory;
    private final String hostName;
    private final Properties properties;

    //
    // Common initialization statements.
    //
    {
        eventTime = System.currentTimeMillis();
        maxMemory = Runtime.getRuntime().maxMemory();
        freeMemory = Runtime.getRuntime().freeMemory();
        totalMemory = Runtime.getRuntime().totalMemory();
        properties = System.getProperties();
        hostName = HostnameProvider.getLocalHostname();
    }


    /**
     * {@inheritDoc}
     */
    public AgnesException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public AgnesException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public AgnesException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public AgnesException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public AgnesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
