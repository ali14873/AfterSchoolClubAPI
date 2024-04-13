package co.uk.afterschoolclub.userregistration.EmergencyContact;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emergencyContact")
public class EmergencyContactRestService {

    final EmergencyContactApplicationService emergencyContactApplicationService;

    @Autowired
    public EmergencyContactRestService(EmergencyContactApplicationService emergencyContactApplicationService) {
        this.emergencyContactApplicationService = emergencyContactApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<EmergencyContactDTO> createEmergencyContact(@RequestBody final EmergencyContactDTO request) {
        EmergencyContactDTO emergencyContact = emergencyContactApplicationService.createEmergencyContact(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(emergencyContact);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmergencyContactDTO>> getAllEmergencyContacts() {
        List<EmergencyContactDTO> emergencyContactDTOList = emergencyContactApplicationService.getAllEmergencyContacts();
        return ResponseEntity.status(HttpStatus.OK).body(emergencyContactDTOList);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EmergencyContactDTO> editEmergencyContact(@PathVariable UUID id, @RequestBody EmergencyContactDTO emergencyContactDTO) {
        try {
            EmergencyContactDTO updatedEmergencyContact = emergencyContactApplicationService.editEmergencyContact(id, emergencyContactDTO);
            return ResponseEntity.ok(updatedEmergencyContact);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getByStudentID/{id}")
    public ResponseEntity<List<EmergencyContactDTO>> getEmergencyContactByStudentId(@PathVariable UUID id){
        List<EmergencyContactDTO> emergencyContactDTOList = emergencyContactApplicationService.getEmergencyContactByStudentId(id);
        return ResponseEntity.status(HttpStatus.OK).body(emergencyContactDTOList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmergencyContact(@PathVariable UUID id){
        try {
            emergencyContactApplicationService.deleteEmergencyContact(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/uploadCSV")
    public ResponseEntity<List<EmergencyContactDTO>> uploadEmergencyContactsCSV(@RequestParam("File") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            List<EmergencyContactDTO> createdEmergencyContacts = emergencyContactApplicationService.processCSVFile(file.getInputStream());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmergencyContacts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
