package mvc.service;

import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;

import java.util.List;

public class StudentServiceImpl
        implements StudentService{
    @Override
    public StudentResponseDto createStudent(StudentCreateDto studentCreateDto) {
        return null;
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
}
