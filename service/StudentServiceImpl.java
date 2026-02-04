package mvc.service;



import mvc.mapper.StudentMapper;
import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.entities.Student;
import mvc.model.reposiitory.StudentRepository;

import java.util.List;

public class StudentServiceImpl
        implements StudentService{
    private final StudentRepository studentRepository
            = new StudentRepository();
    private StudentMapper studentMapper = new StudentMapper();
    @Override
    public StudentResponseDto createStudent(StudentCreateDto studentCreateDto) {
        // must map studentCreateDto to student object
        Student student = studentMapper.mapFromStudentCreateDtoToStudent(
                studentCreateDto
        );
        studentRepository.save(student);
        return studentMapper.mapFromStudentToStudentResponseDto(
                student
        );
    }

    @Override
    public String deleteStudentByUuid(String uuid) {
        return "";
    }

    @Override
    public StudentResponseDto updateStudentByUuid(String uuid, StudentUpdateDto studentUpdateDto) {
        return null;
    }

    @Override
    public StudentResponseDto searchByUuid(String uuid) {
        return null;
    }

    @Override
    public List<StudentResponseDto> searchStudentByName(String name) {
        return List.of();
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::mapFromStudentToStudentResponseDto)
                .toList();
    }
}