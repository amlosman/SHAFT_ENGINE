package com.shaft.tools.io;

import org.apache.logging.log4j.Level;

import static io.github.shafthq.shaft.tools.io.helpers.ReportManagerHelper.*;

public class ReportManager {

    private ReportManager() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Creates a custom log entry that will also be added as a step in the execution report
     *
     * @param logText the text that will be logged by action
     */
    public static void log(String logText) {
        if (getDiscreteLogging() && !logText.toLowerCase().contains("failed") && isInternalStep()) {
            createLogEntry(logText, Level.INFO);
        } else {
            writeStepToReport(logText);
        }
    }

    /**
     * Creates a custom log entry that will not be added as a step in the execution report, but you can see it in the attached execution log txt file
     *
     * @param logText the text that will be logged by action
     */
    public static void logDiscrete(String logText) {
        createLogEntry(logText, Level.INFO);
    }

}