package mvc.mapper;

import mvc.model.dto.StudentCreateDto;
import mvc.model.entities.Student;

import java.util.Random;

public class StudentMapper {
    public Student mapFromStudentCreateDtoToStudent(
            StudentCreateDto studentCreateDto
    ){
        Student student = new Student();
        Student.setId(new Random().nextInt(9999999999999999));
    }
}
