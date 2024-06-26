package co.uk.afterschoolclub.userregistration.EmergencyContact;

import co.uk.afterschoolclub.userregistration.SessionBooking.SessionBookingDTO;
import co.uk.afterschoolclub.userregistration.SessionBooking.SessionBookingTable;
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
import java.util.stream.Collectors;

@Service
public class EmergencyContactApplicationService {

    @Autowired
    EmergencyContactRepoInterface emergencyContactRepoInterface;

    public EmergencyContactDTO createEmergencyContact(EmergencyContactDTO request) {
        EmergencyContactTable emergencyContact = EmergencyContactTable.builder()
                .id(request.getId())
                .studentID(request.getStudentID())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .relationship(request.getRelationship())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
        emergencyContactRepoInterface.save(emergencyContact);
        return request; // Assume transformation back to DTO is handled appropriately
    }

    public List<EmergencyContactDTO> getAllEmergencyContacts() {
        List<EmergencyContactDTO> emergencyContacts = new ArrayList<>();
        for (EmergencyContactTable contact : emergencyContactRepoInterface.findAll()) {
            EmergencyContactDTO dto = EmergencyContactDTO.builder()
                    .id(contact.getId())
                    .studentID(contact.getStudentID())
                    .firstName(contact.getFirstName())
                    .lastName(contact.getLastName())
                    .relationship(contact.getRelationship())
                    .phone(contact.getPhone())
                    .email(contact.getEmail())
                    .build();
            emergencyContacts.add(dto);
        }
        return emergencyContacts;
    }

    public EmergencyContactDTO editEmergencyContact(UUID id, EmergencyContactDTO emergencyContactDTO) {
        Optional<EmergencyContactTable> emergencyContactOptional = emergencyContactRepoInterface.findById(id);
        if (!emergencyContactOptional.isPresent()) {
            throw new EntityNotFoundException("EmergencyContact not found with ID: " + id);
        }
        EmergencyContactTable emergencyContact = emergencyContactOptional.get();
        emergencyContact.setStudentID(emergencyContactDTO.getStudentID()); // Ensure proper assignment if necessary
        emergencyContact.setFirstName(emergencyContactDTO.getFirstName());
        emergencyContact.setLastName(emergencyContactDTO.getLastName());
        emergencyContact.setRelationship(emergencyContactDTO.getRelationship());
        emergencyContact.setPhone(emergencyContactDTO.getPhone());
        emergencyContact.setEmail(emergencyContactDTO.getEmail());
        emergencyContactRepoInterface.save(emergencyContact);
        return emergencyContactDTO; // Return updated DTO, possibly after re-fetching or updating from the entity
    }

    public List<EmergencyContactDTO> processCSVFile(InputStream inputStream) {
        List<EmergencyContactDTO> createdEmergencyContacts = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                EmergencyContactDTO emergencyContactDTO = EmergencyContactDTO.builder()
                        .firstName(nextRecord[0])
                        .lastName(nextRecord[1])
                        .relationship(nextRecord[2])
                        .phone(nextRecord[3])
                        .email(nextRecord[4])
                        .build();
                // Note: Assigning studentID from CSV data requires additional logic to match students.
                createdEmergencyContacts.add(createEmergencyContact(emergencyContactDTO));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
        return createdEmergencyContacts;
    }

    public void deleteEmergencyContact(UUID id) {
        Optional<EmergencyContactTable> parentOptional = emergencyContactRepoInterface.findById(id);
        if (parentOptional.isPresent()) {
            emergencyContactRepoInterface.delete(parentOptional.get());
        } else {
            throw new EntityNotFoundException("Parent not found with ID: " + id);
        }
    }

    public List<EmergencyContactDTO> getEmergencyContactByStudentId(UUID studentId) {
        List<EmergencyContactTable> EmergencyContactList = emergencyContactRepoInterface.findBystudentID(studentId);
        return EmergencyContactList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private EmergencyContactDTO convertToDTO(EmergencyContactTable emergencyContact) {
        return new EmergencyContactDTO(
                emergencyContact.getId(),
                emergencyContact.getStudentID(),
                emergencyContact.getFirstName(),
                emergencyContact.getLastName(),
                emergencyContact.getEmail(),
                emergencyContact.getPhone(),
                emergencyContact.getRelationship()
        );
    }

}
