package co.uk.afterschoolclub.userregistration.Allergy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AllergyApplicationService {
    private final AllergyRepositoryInterface allergyRepository;

    @Autowired
    public AllergyApplicationService(AllergyRepositoryInterface allergyRepository) {
        this.allergyRepository = allergyRepository;
    }

    public AllergyDTO createAllergy(AllergyDTO allergyDTO) {
        AllergyTable allergy = AllergyTable.builder()
                .studentId(allergyDTO.getStudentId())
                .allergyType(allergyDTO.getAllergyType())
                .severity(allergyDTO.getSeverity())
                .actionType(allergyDTO.getActionType())
                .action(allergyDTO.getAction())
                .build();
        allergy = allergyRepository.save(allergy);
        allergyDTO.setId(allergy.getId());
        return allergyDTO;
    }

    public List<AllergyDTO> getAllAllergies() {
        return ((List<AllergyTable>) allergyRepository.findAll())
                .stream()
                .map(allergy -> new AllergyDTO(allergy.getId(), allergy.getStudentId(), allergy.getAllergyType(),
                        allergy.getSeverity(), allergy.getActionType(), allergy.getAction()))
                .collect(Collectors.toList());
    }

    public  List<AllergyDTO> getAllergiesByStudentId(UUID studentId) {
        return allergyRepository.findByStudentId(studentId)
                .stream()
                .map(allergy -> new AllergyDTO(allergy.getId(), allergy.getStudentId(), allergy.getAllergyType(),
                        allergy.getSeverity(), allergy.getActionType(), allergy.getAction()))
                .collect(Collectors.toList());
    }

    public AllergyDTO getAllergyById(UUID id) {
        AllergyTable allergy = allergyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allergy not found with id: " + id));
        return new AllergyDTO(allergy.getId(), allergy.getStudentId(), allergy.getAllergyType(),
                allergy.getSeverity(), allergy.getActionType(), allergy.getAction());
    }

    public AllergyDTO updateAllergy(UUID id, AllergyDTO allergyDTO) {
        AllergyTable existingAllergy = allergyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allergy not found with id: " + id));
        existingAllergy.setAllergyType(allergyDTO.getAllergyType());
        existingAllergy.setSeverity(allergyDTO.getSeverity());
        existingAllergy.setActionType(allergyDTO.getActionType());
        existingAllergy.setAction(allergyDTO.getAction());
        allergyRepository.save(existingAllergy);
        return allergyDTO;
    }

    public void deleteAllergy(UUID id) {
        if (!allergyRepository.existsById(id)) {
            throw new RuntimeException("Allergy not found with id: " + id);
        }
        allergyRepository.deleteById(id);
    }

    public Long countUniqueStudentsWithAllergies() {
        return allergyRepository.countUniqueStudentsWithAllergies();
    }

    public Long countStudentsWithHighSeverityAllergies() {
        return allergyRepository.countStudentsWithHighSeverityAllergies();
    }

    public Long countStudentsWithMediumSeverityAllergies() {
        return allergyRepository.countStudentsWithMediumSeverityAllergies();
    }

}
