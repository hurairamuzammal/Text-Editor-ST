package data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import dal.DatabaseConnection;
public class singletonTest {

    @Test
    public void testSingletonInstance() {
        // Get two instances of DatabaseConnection
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();

        // They must be the exact same object reference
        assertSame(instance1, instance2,"DatabaseConnection should be a Singleton");
        assertNotNull(instance1,"Instance should not be null");
    }
}