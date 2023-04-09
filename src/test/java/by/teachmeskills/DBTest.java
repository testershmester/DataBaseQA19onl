package by.teachmeskills;


import by.teachmeskills.db.client.DBClient;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Log4j2
public class DBTest {

    DBClient dbClient;

    @BeforeMethod
    public void setUp() {
        dbClient = new DBClient();
        dbClient.connect();
    }

    @AfterMethod
    public void tearDown() {
        dbClient.close();
    }
}