package mvc.mapper;

import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.entities.Student;

import java.util.UUID;

public class StudentMapper {

    public Student mapFromStudentCreateDtoToStudent(
            StudentCreateDto studentCreateDto
    ) {
        Student student = new Student();

        // ID should come from database (do NOT generate here)
        student.setUuid(UUID.randomUUID().toString());
        student.setUserName(studentCreateDto.userName());
        student.setEmail(studentCreateDto.email());
        student.setPassword(studentCreateDto.password());
        student.setProfile(null);
        student.setCardId(null);
        student.setBirthOfDate(studentCreateDto.birdOfDate());

        return student;
    }

    public StudentResponseDto mapFromStudentToStudentResponseDto(
            Student student
    ) {
        if (student == null) {
            return null;
        }

        return new StudentResponseDto(
                student.getUuid(),
                student.getUserName(),
                student.getEmail(),
                student.getProfile(),
                student.getCardId(),
                student.getBirthOfDate()
        );
    }
}
