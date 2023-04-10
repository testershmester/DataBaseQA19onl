package by.teachmeskills;

import by.teachmeskills.db.client.StudentDBClient;
import by.teachmeskills.db.dto.Student;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Log4j2
public class StudentsTest {
    StudentDBClient dbClient;

    @BeforeMethod
    public void setUp() {
        dbClient = new StudentDBClient();
        dbClient.connect();
    }

    @Test
    public void selectStudents() {
        String expStudentName = new Faker().name().name();
        dbClient.createStudent(expStudentName);
        List<Student> students = dbClient.selectAllStudents();
        log.info(students.toString());
    }

    @AfterMethod
    public void tearDown() {
        dbClient.close();
    }
}
