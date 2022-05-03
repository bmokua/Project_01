package dev.mokua.utilities;

import jdk.jfr.internal.LogLevel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {

    public static void log(String message, LogLevel level) {
        // LOG LEVEL + message + TimeStamp
        String logMessage = level.name() + " " + message + " " + new Date() + "\n";

        try
        {
            Path logLocation = Paths.get(System.getProperty("user.dir"), "Log.log");
            if (!Files.exists(logLocation)) Files.createFile(logLocation);
            Files.write(logLocation, logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }
        catch (Exception e)
        {
            System.err.println("Failed to write to Log");
        }
    }
}
