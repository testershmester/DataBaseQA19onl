package by.teachmeskills.db.client;

import by.teachmeskills.db.dto.Student;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class StudentDBClient extends DBClient {

    public static final String STUDENTS_TABLE_NAME = "`students`";
    public static final String SELECT_STUDENTS = "SELECT %s FROM " + STUDENTS_TABLE_NAME + ";";
    public static final String SELECT_STUDENTS_BY_NAME = "SELECT `groups`.`%s` FROM `students` JOIN  `groups`" +
            "ON `students`.`group_id`= `groups`.`id` WHERE `students`.`name`='%s';";
    public static final String INSERT_STUDENT = "INSERT INTO " + STUDENTS_TABLE_NAME + "(name, group_id) VALUES (?, ?);";

    public List<Student> selectAllStudents() {
        ResultSet studentEntries = executeQuery(String.format(SELECT_STUDENTS, "*"));
        List<Student> students = new ArrayList<>();
        try {
            while (studentEntries.next()) {
                students.add(new Student(studentEntries.getInt("id"), studentEntries.getString("name"),
                        studentEntries.getInt("group_id")));
            }
        } catch (SQLException ex) {
            log.error("Students were not found because of error: {}", ex.getMessage());
        }
        return students;
    }

    public String selectStudentGroupByName(String studentName) {
        ResultSet studentEntries = executeQuery(String.format(SELECT_STUDENTS_BY_NAME, "name", studentName));
        try {
            studentEntries.next();
            return studentEntries.getString("name");
        } catch (SQLException ex) {
            log.error("Student with name {} was not found because of error: {}", studentName, ex.getMessage());
        }
        return "";
    }

    public void createStudent(String studentName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
            preparedStatement.setString(1, studentName);
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            log.error("Student was not created because of error: {}", ex.getMessage());
        }
    }
}