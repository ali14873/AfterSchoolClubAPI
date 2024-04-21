package co.uk.afterschoolclub.userregistration.StudentParent;

import co.uk.afterschoolclub.userregistration.Student.StudentDTO;
import co.uk.afterschoolclub.userregistration.Student.StudentRepoInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentParentApplicationService {

    private final StudentParentRepoInterface studentParentRepoInterface;
    private final StudentRepoInterface studentRepoInterface;


    @Autowired
    public StudentParentApplicationService(StudentParentRepoInterface studentParentRepoInterface,
                                           StudentRepoInterface studentRepoInterface) {
        this.studentParentRepoInterface = studentParentRepoInterface;
        this.studentRepoInterface = studentRepoInterface;
    }

    public StudentParentDTO createStudentParent(StudentParentDTO request) {
        StudentParentTable studentParent = StudentParentTable.builder()
                .studentID(request.getStudentID())
                .parentID(request.getParentID())
                .relationshipType(request.getRelationshipType())
                .build();
        studentParent = studentParentRepoInterface.save(studentParent);

        return StudentParentDTO.builder()
                .id(studentParent.getId())
                .studentID(studentParent.getStudentID())
                .parentID(studentParent.getParentID())
                .relationshipType(studentParent.getRelationshipType())
                .build();
    }

    public List<StudentParentDTO> getChildrenByParent(UUID parentId) {
        List<StudentParentTable> studentParentRelations = studentParentRepoInterface.findByParentID(parentId);
        if (studentParentRelations.isEmpty()) {
            throw new EntityNotFoundException("No children found for parent with ID: " + parentId);
        }

        return studentParentRelations.stream()
                .map(relation -> StudentParentDTO.builder()
                        .id(relation.getId())
                        .studentID(relation.getStudentID())
                        .parentID(relation.getParentID())
                        .relationshipType(relation.getRelationshipType())
                        .build())
                .collect(Collectors.toList());
    }

    public StudentParentDTO editStudentParent(UUID id, StudentParentDTO request) {
        StudentParentTable association = studentParentRepoInterface.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StudentParent association not found with ID: " + id));

        association = StudentParentTable.builder()
                .id(association.getId()) // Keep existing ID
                .studentID(request.getStudentID())
                .parentID(request.getParentID())
                .relationshipType(request.getRelationshipType())
                .build();

        studentParentRepoInterface.save(association);

        return StudentParentDTO.builder()
                .id(association.getId())
                .studentID(association.getStudentID())
                .parentID(association.getParentID())
                .relationshipType(association.getRelationshipType())
                .build();
    }

    public void deleteStudentParentById(UUID id) {
        if (!studentParentRepoInterface.existsById(id)) {
            throw new EntityNotFoundException("StudentParent association not found with ID: " + id);
        }
        studentParentRepoInterface.deleteById(id);
    }



    public List<StudentDTO> getStudentsByParentId(UUID parentId) {
        // First, fetch the relations
        List<UUID> studentIds = studentParentRepoInterface.findByParentID(parentId)
                .stream()
                .map(StudentParentTable::getStudentID)
                .collect(Collectors.toList());

        // Then, fetch student details using a builder pattern
        return studentRepoInterface.findAllById(studentIds)
                .stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .dateOfBirth(student.getDateOfBirth())
                        .gender(student.getGender())
                        .yearGroup(student.getYearGroup())
                        .build())
                .collect(Collectors.toList());
    }
}
