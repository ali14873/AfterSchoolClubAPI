package co.uk.afterschoolclub.userregistration.Student;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentApplicationService {

    @Autowired
    StudentRepoInterface studentRepoInterface;

    public StudentDTO createStudent(StudentDTO request) {
        StudentTable student = StudentTable.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .yearGroup(request.getYearGroup())
                .build();
        studentRepoInterface.save(student);

        return request;
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> students = new ArrayList<StudentDTO>();
        for(StudentTable student: studentRepoInterface.findAll()){
            StudentDTO dto = StudentDTO.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .dateOfBirth(student.getDateOfBirth())
                    .gender(student.getGender())
                    .yearGroup(student.getYearGroup())
                    .build();
            students.add(dto);

        }
        return students;
    }

    public StudentDTO getStudentById(UUID id) {
        Optional<StudentTable> studentOptional = studentRepoInterface.findById(id);

        if (studentOptional.isPresent()) {
            StudentTable student = studentOptional.get();
            return StudentDTO.builder()
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .gender(student.getGender())
                    .dateOfBirth(student.getDateOfBirth())
                    .yearGroup(student.getYearGroup())
                    .build();
        } else {

            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
    }

    public void deleteStudentById(UUID id) {
        Optional<StudentTable> studentOptional = studentRepoInterface.findById(id);
        if (studentOptional.isPresent()) {
            studentRepoInterface.delete(studentOptional.get());
        } else {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
    }


    public List<StudentDTO> processCSVFile(InputStream inputStream) {
        List<StudentDTO> createdStudents = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                StudentDTO studentDTO = StudentDTO.builder()
                        .firstName(nextRecord[0])
                        .lastName(nextRecord[1])
//                        .dateOfBirth(LocalDate.parse(nextRecord[2], dateFormatter))
                        .gender(nextRecord[3])
                        .yearGroup(nextRecord[4])
                        .build();

                createdStudents.add(createStudent(studentDTO));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
        return createdStudents;
    }


}