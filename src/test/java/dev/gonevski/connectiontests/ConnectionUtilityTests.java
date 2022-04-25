package dev.gonevski.connectiontests;

import dev.gonevski.utilities.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

public class ConnectionUtilityTests {
    @Test
    void can_connect(){
        Connection conn = ConnectionUtil.createConnection();
        Assertions.assertNotNull(conn);
    }
}
