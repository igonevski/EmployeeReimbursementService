package dev.gonevski.LoggerTests;

import dev.gonevski.utilities.Log4J2PropertiesConfig;
import org.junit.jupiter.api.Test;


public class Log4J2PropertiesConfTest {

    @Test
    public void testPerformSomeTask() {
        Log4J2PropertiesConfig log4jpropertiesconf = new Log4J2PropertiesConfig();
        log4jpropertiesconf.performSomeTask();
    }
}
