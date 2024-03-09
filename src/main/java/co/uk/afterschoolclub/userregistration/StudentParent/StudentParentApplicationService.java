package co.uk.afterschoolclub.userregistration.StudentParent;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentParentApplicationService {

    @Autowired
    StudentParentRepoInterface studentParentRepoInterface;

    public StudentParentDTO createStudentParent(StudentParentDTO request) {
        StudentParentTable studentParent = StudentParentTable.builder()
                .studentID(request.getStudentID())
                .parentID(request.getParentID())
                .relationshipType(request.getRelationshipType())
                .build();
        studentParentRepoInterface.save(studentParent);

        return request; // Assume transformation back to DTO is handled appropriately
    }

    public List<StudentParentDTO> getChildrenByParent(UUID parentId) {
        List<StudentParentTable> studentParentRelations = studentParentRepoInterface.findByParentID(parentId);
        List<StudentParentDTO> children = new ArrayList<>();
        for (StudentParentTable relation : studentParentRelations) {
            StudentParentDTO dto = StudentParentDTO.builder()
                    .id(relation.getId())
                    .studentID(relation.getStudentID())
                    .parentID(relation.getParentID())
                    .relationshipType(relation.getRelationshipType())
                    .build();
            children.add(dto);
        }
        if (children.isEmpty()) {
            throw new EntityNotFoundException("No children found for parent with ID: " + parentId);
        }
        return children;
    }

    public StudentParentDTO editStudentParent(UUID id, StudentParentDTO request) {
        Optional<StudentParentTable> existingAssociation = studentParentRepoInterface.findById(id);
        if (existingAssociation.isEmpty()) {
            throw new EntityNotFoundException("StudentParent association not found with ID: " + id);
        }
        StudentParentTable association = existingAssociation.get();
        association.setStudentID(request.getStudentID()); // Assuming you allow changing student and parent in the association
        association.setParentID(request.getParentID());
        association.setRelationshipType(request.getRelationshipType());
        studentParentRepoInterface.save(association);
        return request; // Consider returning the updated object from the database if necessary
    }

    public void deleteStudentParentById(UUID id) {
        if (!studentParentRepoInterface.existsById(id)) {
            throw new EntityNotFoundException("StudentParent association not found with ID: " + id);
        }
        studentParentRepoInterface.deleteById(id);
    }
}
