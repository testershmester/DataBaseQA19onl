package by.teachmeskills;


import by.teachmeskills.db.client.DBClient;
import by.teachmeskills.db.dto.Student;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class DBTest {

    DBClient dbClient;

    @BeforeMethod
    public void setUp() {
        dbClient = new DBClient();
        dbClient.connect();
    }

    @Test
    public void selectStudents() throws SQLException {
        //Create student
        String expName = "Peter Petrov";
        dbClient.executeUpdate(String.format("INSERT INTO `teachmeskills`.`students`(name, group_id) VALUES ('%s', 1);", expName));

        ResultSet studentEntries = dbClient.executeQuery("SELECT name FROM `teachmeskills`.`students`;");
        List<String> names = new ArrayList<>();
        while (studentEntries.next()) {
            names.add(studentEntries.getString("name"));
        }
        assertThat(names).as("Students tables should contain student with name " + expName)
                .contains(expName);
    }

    @AfterMethod
    public void tearDown() {
        dbClient.close();
    }
}