package net.agnes.core.state;

import java.util.Stack;

/**
 * A repository of the current and previous states of an Entity.
 *
 *
 * @param <T>
 */
public class StateHistory<T extends StateType> {

    Stack<StateRecord<T>> stack = new Stack<>();

    public void addRecord(long time, T value) {
        StateRecord<T> record = new StateRecord<>(time, value);
        stack.push(record);
    }

    public T getCurrent() {
        return stack.peek().value;
    }

    /**
     * Provides the timestamp
     * @return
     */
    public long getTimeOfCurrent() {
        if (stack.isEmpty()) return -1;
        return stack.peek().time;
    }

    /**
     *
     */
    public void rollback() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public void rollbackTo(long time) {
        if (!stack.isEmpty()) {
            while (stack.peek().time > time) {
                stack.pop();
            }
        }
    }

    public int size() {
        return stack.size();
    }

    private record StateRecord<T>(long time, T value) {
    }
}
