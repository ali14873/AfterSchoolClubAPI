package co.uk.afterschoolclub.userregistration.Teacher;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherApplicationService {

    @Autowired
    TeacherRepoInterface teacherRepoInterface;

    public TeacherDTO createTeacher(TeacherDTO request) {
        TeacherTable teacher = TeacherTable.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        teacherRepoInterface.save(teacher);

        return request;
    }

    public List<TeacherDTO> getAllTeachers() {
        List<TeacherDTO> teachers = new ArrayList<>();
        for(TeacherTable teacher : teacherRepoInterface.findAll()) {
            TeacherDTO dto = TeacherDTO.builder()
                    .id(teacher.getId())
                    .firstName(teacher.getFirstName())
                    .lastName(teacher.getLastName())
                    .email(teacher.getEmail())
                    .phone(teacher.getPhone())
                    .build();
            teachers.add(dto);
        }
        return teachers;
    }

    public TeacherDTO getTeacherById(UUID id) {
        Optional<TeacherTable> teacherOptional = teacherRepoInterface.findById(id);

        if (teacherOptional.isPresent()) {
            TeacherTable teacher = teacherOptional.get();
            return TeacherDTO.builder()
                    .id(teacher.getId())
                    .firstName(teacher.getFirstName())
                    .lastName(teacher.getLastName())
                    .email(teacher.getEmail())
                    .phone(teacher.getPhone())
                    .build();
        } else {
            throw new EntityNotFoundException("Teacher not found with ID: " + id);
        }
    }

    public void deleteTeacherById(UUID id) {
        Optional<TeacherTable> teacherOptional = teacherRepoInterface.findById(id);
        if (teacherOptional.isPresent()) {
            teacherRepoInterface.delete(teacherOptional.get());
        } else {
            throw new EntityNotFoundException("Teacher not found with ID: " + id);
        }
    }

    public List<TeacherDTO> processCSVFile(InputStream inputStream) {
        List<TeacherDTO> createdTeachers = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                TeacherDTO teacherDTO = TeacherDTO.builder()
                        .firstName(nextRecord[0])
                        .lastName(nextRecord[1])
                        .email(nextRecord[2])
                        .phone(nextRecord[3])
                        .build();
                createdTeachers.add(createTeacher(teacherDTO));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
        return createdTeachers;
    }
}
