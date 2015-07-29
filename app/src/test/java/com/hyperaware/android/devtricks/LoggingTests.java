package com.hyperaware.android.devtricks;

import android.util.Log;

import org.junit.Test;

public class LoggingTests {

    @Test
    public void androidLogMethodsWillCrashYourUnitTests() {
        // Calling Android static log methods will throw a RuntimeException in unit tests.
        // Instead, see com.hyperaware.android.devtricks.AndroidLogHandler to have
        // java logging api calls routed to logcat.
        Log.d("CRASHME", "oops");
    }

    @Test
    public void javaLoggingMethodsWorkJustFine() {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoggingTests.class.getName());
        logger.fine("I don't crash because any JDK has standard java logging APIs");
    }

}
