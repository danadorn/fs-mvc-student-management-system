package mvc.service;


import mvc.mapper.StudentMapper;
import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.entities.Student;
import mvc.model.reposiitory.StudentRepository;

import java.util.Collections;
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
        Student student = studentRepository.findAll().stream()
                .filter(s->s.getUuid().equals(uuid))
                .findFirst().get();
        return String.valueOf(studentRepository.delete(student));
    }

    @Override
    public StudentResponseDto updateStudentByUuid(String uuid, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findAll().stream()
                .filter(s->s.getUuid().equals(uuid))
                .findFirst().get();
        student.setProfile(studentUpdateDto.profile());
        student.setUserName(studentUpdateDto.userName());
        student.setEmail(studentUpdateDto.email());
        student.setBirthOfDate(studentUpdateDto.birdOfDate());
        studentRepository.update(student);
        return null;
    }

    @Override
    public StudentResponseDto searchByUuid(String uuid) {
        Student student = studentRepository.findAll().stream()
                .filter(s->s.getUuid().equals(uuid)).findFirst().get();
        return studentMapper.mapFromStudentToStudentResponseDto(
                studentRepository.findById(student.getId())
        );
    }

    @Override
    public List<StudentResponseDto> searchStudentByName(String name) {
        Student student = (Student) studentRepository.findAll().stream()
                .filter(s-> s.getUserName().equals(name));
        return Collections.singletonList(studentMapper.mapFromStudentToStudentResponseDto(
                studentRepository.findByUserName(student.getUserName())
        ));
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapFromStudentToStudentResponseDto).toList();
    }
}