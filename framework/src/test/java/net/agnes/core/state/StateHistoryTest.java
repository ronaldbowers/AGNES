package net.agnes.core.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StateHistoryTest {

    private record Bob(int value) implements StateType {

    }

    @Nested
    @DisplayName("Test the happy path")
    class HappyTests {
        @Test
        @DisplayName("Confirm addition works.")
        public void t0() {
            StateHistory<Bob> stateHistory = new StateHistory<>();
            stateHistory.addRecord(1000, new Bob(10));
            Assertions.assertEquals(1, stateHistory.size());
            stateHistory.addRecord(2000, new Bob(20));
            Assertions.assertEquals(2, stateHistory.size());
            stateHistory.addRecord(3000, new Bob(30));
            Assertions.assertEquals(3, stateHistory.size());
        }

        @Test
        @DisplayName("Confirm rollbackTo() behavior")
        public void t2() {
            StateHistory<Bob> stateHistory = new StateHistory<>();
            stateHistory.addRecord(1000, new Bob(10));
            stateHistory.addRecord(2000, new Bob(20));
            stateHistory.addRecord(3000, new Bob(30));
            stateHistory.rollback();
            Assertions.assertEquals(2000, stateHistory.getTimeOfCurrent());
            Assertions.assertEquals(2, stateHistory.size());
            stateHistory.rollback();
            Assertions.assertEquals(1000, stateHistory.getTimeOfCurrent());
            Assertions.assertEquals(1, stateHistory.size());
            stateHistory.rollback();
            Assertions.assertEquals(-1, stateHistory.getTimeOfCurrent());
            Assertions.assertEquals(0, stateHistory.size());

        }

        @Test
        @DisplayName("Confirm rollbackTo() behavior")
        public void t1() {
            StateHistory<Bob> stateHistory = new StateHistory<>();
            stateHistory.addRecord(1000, new Bob(10));
            stateHistory.addRecord(2000, new Bob(20));
            stateHistory.addRecord(3000, new Bob(30));
            stateHistory.rollbackTo(2000);
            Assertions.assertEquals(2000, stateHistory.getTimeOfCurrent());
            Assertions.assertEquals(2, stateHistory.size());
            stateHistory.rollbackTo(1500);
            Assertions.assertEquals(1000, stateHistory.getTimeOfCurrent());
            Assertions.assertEquals(1, stateHistory.size());
        }
    }


}
