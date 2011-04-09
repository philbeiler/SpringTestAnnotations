package test.com.beilers.testing.profile;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.ProfileValueSource;

public class RemoteTestingProfile implements ProfileValueSource {

    public static final String  SERVICE_AVAILABLE = "serviceAvailable";
    public static final String  NO                = "no";
    public static final String  YES               = "yes";
    private static final Logger LOGGER            = LoggerFactory.getLogger(RemoteTestingProfile.class);

    private Collection<Server> build() {
        final Collection<Server> rc = new ArrayList<Server>();
        rc.add(new Server("localhost", Integer.valueOf("8080")));
        return rc;
    }

    @Override
    public String get(final String key) {
        String rc = "unknown";
        if (SERVICE_AVAILABLE.equals(key)) {
            rc = isAvailable();
        }
        LOGGER.info("Profile key [{}] had value [{}]", key, rc);
        return rc;
    }

    private String isAvailable() {
        final int timeout = 1;

        for (final Server server : build()) {
            final Socket socket = new Socket();
            final InetSocketAddress endPoint = new InetSocketAddress(server.getHostname(), server.getPort());

            if (endPoint.isUnresolved()) {
                LOGGER.warn("Failure {}", endPoint);
            }
            else {
                try {
                    socket.connect(endPoint, timeout);
                    LOGGER.debug("Port [{}] is availble on host [{}]", server.getPort(), server.getHostname());
                    return YES;
                }
                catch (final IOException e) {
                    LOGGER.warn("Port [{}] is unavailble on host [{}] because [{}]",
                                new Object[]{server.getPort(), server.getHostname(), e.getMessage()});
                }
                finally {

                    if (socket != null) {
                        try {
                            socket.close();
                        }
                        catch (final IOException e) {
                            LOGGER.error("Unable to close socket", e);
                        }
                    }
                }
            }
        }
        return NO;
    }

    class Server {

        private final String  hostname;
        private final Integer port;

        public Server(final String hostname, final Integer port) {
            super();
            this.hostname = hostname;
            this.port = port;
        }

        public String getHostname() {
            return hostname;
        }

        public Integer getPort() {
            return port;
        }
    }

}
