package mvc.service;


import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentCreateDto studentCreateDto);
    String deleteStudentByUuid(String uuid);
    StudentResponseDto updateStudentByUuid(String uuid,
                                           StudentUpdateDto studentUpdateDto
    );
    StudentResponseDto searchByUuid(String uuid);
    List<StudentResponseDto> searchStudentByName(String name);
    List<StudentResponseDto> getAllStudents();
}
