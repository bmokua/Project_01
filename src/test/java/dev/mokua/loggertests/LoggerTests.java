package dev.mokua.loggertests;

import dev.mokua.utilities.Logger;
import jdk.jfr.internal.LogLevel;
import org.testng.annotations.Test;

public class LoggerTests {

    @Test
    void info_log_test(){
        Logger.log("Hello", LogLevel.INFO);
        Logger.log("Assembling Message", LogLevel.DEBUG);
    }
}
