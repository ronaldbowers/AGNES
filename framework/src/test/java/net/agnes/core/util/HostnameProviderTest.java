package net.agnes.core.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HostnameProviderTest {
    @DisplayName("Test the Happy Path")
    @Nested
    class HappyTests {
        @Test
        @DisplayName("Confirm a non-null response")
        public void t1() {
            String response = HostnameProvider.getLocalHostname();
            assertNotNull(response);
        }
    }

}
