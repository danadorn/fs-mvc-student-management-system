package mvc.model.reposiitory;



import mvc.model.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// dao
public class StudentRepository {
    private final String dbUrl = "jdbc:postgresql://localhost:5432/school_db";
    private final String userName = "postgres";
    private final String password = "123";
    // use List<Student> as database to store students' data
    private List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        String sql = """
                SELECT * FROM students;
                """;
        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student  student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setUuid(resultSet.getString("uuid"));
                student.setUserName(resultSet.getString("user_name"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setProfile(resultSet.getString("profile"));
                student.setCardId(resultSet.getString("card_id"));
                student.setBirthOfDate(resultSet.getDate("birth_of_date").toLocalDate());
                // add student object to list
                students.add(student);
            }
            System.out.println("Students: " + students);return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return students;
    }
    public Student save(Student student){
        String sql = """
                INSERT INTO students (uuid, user_name, email, password, card_id, profile, birth_of_date)
                VALUES(?,?,?,?,?,?,?)
                """ ;
        try(Connection connection = DriverManager.getConnection(
                dbUrl,userName,password
        )){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,student.getUuid());
            statement.setString(2,student.getUserName());
            statement.setString(3,student.getEmail());
            statement.setString(4,student.getPassword());
            statement.setString(5,student.getCardId());
            statement.setString(6,student.getProfile());
            statement.setDate(7, Date.valueOf(student.getBirthOfDate()));
            int rowAffected = statement.executeUpdate();
            if(rowAffected>0){
                System.out.println("New student has been added");
                return student;
            }else {
                System.out.println("Insert new student failed.");
            }
        }catch (Exception exception){
            System.out.println("Error during insert student data: " + exception.getMessage());
        }
        return null;
    }
    public Student findById(Integer id){
        String sql = """
                SELECT * FROM students WHERE id = ?;
                """;
        try(Connection connection = DriverManager.getConnection(
                dbUrl,userName,password
        )){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            Student student = new Student();
            while (resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setUuid(resultSet.getString("uuid"));
                student.setUserName(resultSet.getString("user_name"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setProfile(resultSet.getString("profile"));
                student.setCardId(resultSet.getString("card_id"));
                student.setBirthOfDate(resultSet.getDate("birth_of_date").toLocalDate());
                // add student object to list
            }
            return student;
        }catch (Exception exception){
            System.out.println("Error in fetching student by ID from database: " + exception.getMessage());
        }
        return null;
    }

    public <Stirng> Student findByUserName(Stirng username){
        Student student = new Student();
        String sql = """
                SELECT * FROM students WHERE user_name = ?
                """;
        try(Connection connection = DriverManager.getConnection(
                dbUrl, userName, password
        )){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, (String) username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setUuid(resultSet.getString("uuid"));
                student.setUserName(resultSet.getString("user_name"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setCardId(resultSet.getString("card_id"));
                student.setBirthOfDate(resultSet.getDate("birth_of_date").toLocalDate());
            }
            return student;
        }catch (Exception e){
            System.out.println("Error in fetching student by username from database: " + e.getMessage());
        }
        return student;
    }
    public int delete (Student student){
        students.remove(student);
        return 1;
    }
    public Student update (Student student){
        // ...
        return null;
    }
}