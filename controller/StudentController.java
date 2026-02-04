package mvc.controller;

import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.service.StudentService;
import mvc.service.StudentServiceImpl;

import java.util.List;

public class StudentController {
    private StudentService studentService = new StudentServiceImpl();
    public StudentResponseDto createNewStudent(StudentCreateDto studentCreateDto){
        return studentService.createStudent(studentCreateDto);
    }

    public List<StudentResponseDto> getAllStudents(){
        return studentService.getAllStudents();
    }
}
