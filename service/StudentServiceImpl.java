package mvc.service;

import mvc.mapper.StudentMapper;
import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.entities.Student;
import mvc.model.reposiitory.StudentRepository;

import java.util.List;
import java.util.UUID;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository = new StudentRepository();
    private final StudentMapper studentMapper = new StudentMapper();

    @Override
    public StudentResponseDto createStudent(StudentCreateDto studentCreateDto) {

        Student student = studentMapper
                .mapFromStudentCreateDtoToStudent(studentCreateDto);

        // generate UUID if not generated
        if (student.getUuid() == null) {
            student.setUuid(UUID.randomUUID().toString());
        }

        Student savedStudent = studentRepository.save(student);

        return studentMapper
                .mapFromStudentToStudentResponseDto(savedStudent);
    }

    @Override
    public String deleteStudentByUuid(String uuid) {

        Student student = findStudentByUuid(uuid);

        if (student == null) {
            return "Student not found!";
        }

        boolean deleted = studentRepository.delete(student.getId());

        return deleted ? "Student deleted successfully." : "Delete failed.";
    }

    @Override
    public StudentResponseDto updateStudentByUuid(
            String uuid,
            StudentUpdateDto studentUpdateDto) {

        Student student = findStudentByUuid(uuid);

        if (student == null) {
            return null;
        }

        student.setUserName(studentUpdateDto.userName());
        student.setEmail(studentUpdateDto.email());
        student.setProfile(studentUpdateDto.profile());
        student.setBirthOfDate(studentUpdateDto.birdOfDate());

        Student updatedStudent = studentRepository.update(student);

        return studentMapper
                .mapFromStudentToStudentResponseDto(updatedStudent);
    }

    @Override
    public StudentResponseDto searchByUuid(String uuid) {

        Student student = findStudentByUuid(uuid);

        if (student == null) {
            return null;
        }

        return studentMapper
                .mapFromStudentToStudentResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> searchStudentByName(String name) {

        return studentRepository.findAll().stream()
                .filter(student -> student.getUserName().equalsIgnoreCase(name))
                .map(studentMapper::mapFromStudentToStudentResponseDto)
                .toList();
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {

        return studentRepository.findAll().stream()
                .map(studentMapper::mapFromStudentToStudentResponseDto)
                .toList();
    }

    private Student findStudentByUuid(String uuid) {

        return studentRepository.findAll().stream()
                .filter(student -> student.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }
}
