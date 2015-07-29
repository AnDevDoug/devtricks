package com.hyperaware.android.devtricks.logging;

import java.util.logging.Logger;

public class Logging {

    public static Logger getLogger(final Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }

}
