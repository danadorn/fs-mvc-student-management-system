package mvc.controller;

import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.service.StudentService;
import mvc.service.StudentServiceImpl;

import java.util.List;

public class StudentController {

    private final StudentService studentService = new StudentServiceImpl();

    public StudentResponseDto createNewStudent(StudentCreateDto studentCreateDto) {
        return studentService.createStudent(studentCreateDto);
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    public StudentResponseDto searchStudentByUuid(String uuid) {
        return studentService.searchByUuid(uuid);
    }

    public StudentResponseDto updateStudentByUuid(String uuid, StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudentByUuid(uuid, studentUpdateDto);
    }

    public String deleteStudentByUuid(String uuid) {
        return studentService.deleteStudentByUuid(uuid);
    }

    public List<StudentResponseDto> searchStudentsByName(String name) {
        return studentService.searchStudentByName(name);
    }
}
