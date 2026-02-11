package mvc.model.reposiitory;

import mvc.model.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private final String dbUrl = "jdbc:postgresql://localhost:5432/school_db";
    private final String userName = "postgres";
    private final String password = "123";

    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }

        } catch (Exception e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }

        return students;
    }

    public Student save(Student student) {
        String sql = """
                INSERT INTO students 
                (uuid, user_name, email, password, card_id, profile, birth_of_date)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, student.getUuid());
            statement.setString(2, student.getUserName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPassword());
            statement.setString(5, student.getCardId());
            statement.setString(6, student.getProfile());
            statement.setDate(7, Date.valueOf(student.getBirthOfDate()));

            int rowAffected = statement.executeUpdate();

            if (rowAffected > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    student.setId(rs.getInt(1));
                }
                System.out.println("Student added successfully.");
                return student;
            }

        } catch (Exception e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }

        return null;
    }

    public Student findById(Integer id) {
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToStudent(resultSet);
            }

        } catch (Exception e) {
            System.out.println("Error fetching student by ID: " + e.getMessage());
        }

        return null;
    }

    public Student findByUserName(String username) {
        String sql = "SELECT * FROM students WHERE user_name = ?";

        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToStudent(resultSet);
            }

        } catch (Exception e) {
            System.out.println("Error fetching student by username: " + e.getMessage());
        }

        return null;
    }

    public Student update(Student student) {
        String sql = """
                UPDATE students
                SET uuid = ?, user_name = ?, email = ?, password = ?, 
                    card_id = ?, profile = ?, birth_of_date = ?
                WHERE id = ?
                """;

        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getUuid());
            statement.setString(2, student.getUserName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPassword());
            statement.setString(5, student.getCardId());
            statement.setString(6, student.getProfile());
            statement.setDate(7, Date.valueOf(student.getBirthOfDate()));
            statement.setInt(8, student.getId());

            int rowAffected = statement.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Student updated successfully.");
                return student;
            }

        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }

        return null;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowAffected = statement.executeUpdate();

            return rowAffected > 0;

        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }

        return false;
    }

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setUuid(rs.getString("uuid"));
        student.setUserName(rs.getString("user_name"));
        student.setEmail(rs.getString("email"));
        student.setPassword(rs.getString("password"));
        student.setProfile(rs.getString("profile"));
        student.setCardId(rs.getString("card_id"));

        Date birthDate = rs.getDate("birth_of_date");
        if (birthDate != null) {
            student.setBirthOfDate(birthDate.toLocalDate());
        }

        return student;
    }
}
