package dgroomes.spring_playground.caching;

import java.util.Arrays;

public class Util {

    /**
     * Format a stack trace with newlines.
     *
     * @param stackTraceElements the elements of a stack trace. Often gotten from a call to Thread.currentThread().getStackTrace()
     * @return the stack trace formatted as a string with newlines
     */
    public static String formatStackTrace(StackTraceElement[] stackTraceElements) {
        return Arrays.toString(stackTraceElements).replace(',', '\n');
    }
}
