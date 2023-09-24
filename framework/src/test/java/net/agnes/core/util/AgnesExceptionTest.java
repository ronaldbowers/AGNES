package net.agnes.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AgnesExceptionTest {

    @Nested
    @DisplayName("Test the Happy Path")
    class HappyTests {
        @DisplayName("Confirm that the default constructor works")
        @Test
        public void test1() {
            AgnesException ae = assertThrows(AgnesException.class, () -> {
                throw new AgnesException();
            });
            assertNotNull(ae);
            assertNotNull(ae.getProperties());
            assertEquals(System.getProperties().getProperty("os.name"), ae.getProperties().getProperty("os.name"));
        }

        @DisplayName("Confirm that AgnesException(String) works")
        @Test
        public void test2() {
            final String msg = "ERROR";
            AgnesException ae = assertThrows(AgnesException.class, () -> {
                throw new AgnesException(msg);
            });
            assertNotNull(ae);
            assertEquals(msg, ae.getMessage());
        }

        @DisplayName("Confirm that AgnesException(String, Throwable) works")
        @Test
        public void test3() {
            final String msg = "ERROR";
            final Throwable throwable = new Throwable();
            AgnesException ae = assertThrows(AgnesException.class, () -> {
                throw new AgnesException(msg, throwable);
            });
            assertNotNull(ae);
            assertEquals(msg, ae.getMessage());
            assertEquals(throwable, ae.getCause());
        }

        @DisplayName("Confirm that AgnesException(Throwable) works")
        @Test
        public void test4() {
            final Throwable throwable = new Throwable();
            AgnesException ae = assertThrows(AgnesException.class, () -> {
                throw new AgnesException(throwable);
            });
            assertNotNull(ae);
            assertEquals(throwable, ae.getCause());
        }

        @DisplayName("Confirm that getHostName() works")
        @Test
        public void testHostName() {
            long before = System.currentTimeMillis();
            AgnesException ae = assertThrows(AgnesException.class, () -> {
                throw new AgnesException();
            });
            long after = System.currentTimeMillis();
            assertNotNull(ae);
            assertNotNull(ae.getHostName());
            assertFalse(ae.getHostName().isEmpty());
            assertFalse(ae.getHostName().isBlank());
            assertTrue(after>=before);
        }

        @DisplayName("Confirm that getEventTime() works")
        @Test
        public void test6() {
            long before = System.currentTimeMillis();
            AgnesException ae = assertThrows(AgnesException.class, () -> {
                throw new AgnesException();
            });
            long after = System.currentTimeMillis();
            assertNotNull(ae);
            assertTrue(ae.getEventTime() >= before);
            assertTrue(ae.getEventTime() <= after);
        }

        @DisplayName("Confirm that memory readings work")
        @Test
        public void test7() {
            AgnesException ae = assertThrows(AgnesException.class, () -> {
                throw new AgnesException();
            });
            assertNotNull(ae);
            assertTrue(ae.getFreeMemory() > 0);
            assertTrue(ae.getTotalMemory() > 0);
            assertTrue(ae.getMaxMemory() > 0);
        }

        @DisplayName("Confirm that AgnesException(String, Throwable, boolean, boolean) works")
        @ParameterizedTest(name = "Check AgnesException(String, Throwable, {0}, {1})")
        @CsvSource({"true,true", "true, false", "false, true", "false, false"})
        public void test5(boolean suppress, boolean writeable) {
            final String msg = "ERROR";
            final Throwable throwable = new Throwable();
            AgnesException ae = assertThrows(AgnesException.class, () -> {
                throw new AgnesException(msg, throwable, suppress, writeable);
            });
            assertNotNull(ae);
            assertEquals(throwable, ae.getCause());
        }
    }

    @Nested
    @DisplayName("Test serialization and deserialization behaviors")
    class SerializationTests {

        @Test
        @DisplayName("Confirm that an AgnesException can be serialized to JSON")
        void stTest1() {
            assertDoesNotThrow(() -> {
                ObjectMapper om = new ObjectMapper();
                AgnesException ae = new AgnesException();
                String jsonString = om.writeValueAsString(ae);
                assertTrue(jsonString.contains("AgnesException"));
                assertTrue(jsonString.contains("freeMemory"));
                assertTrue(jsonString.contains("maxMemory"));
                String multiLine = jsonString.replace("},{", "},\n{").replace("}],", "]},\n");
                System.out.println(multiLine);
            });
        }

    }

}