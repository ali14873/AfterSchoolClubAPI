package co.uk.afterschoolclub.userregistration.SessionStaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessionStaff")
public class SessionStaffRestService {

    private final SessionStaffApplicationService sessionStaffApplicationService;

    @Autowired
    public SessionStaffRestService(SessionStaffApplicationService sessionStaffApplicationService) {
        this.sessionStaffApplicationService = sessionStaffApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<SessionStaffDTO> createSessionStaff(@RequestBody SessionStaffDTO sessionStaffDTO) {
        SessionStaffDTO savedSessionStaff = sessionStaffApplicationService.createSessionStaff(sessionStaffDTO);
        return new ResponseEntity<>(savedSessionStaff, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SessionStaffDTO>> getAllSessionStaff() {
        List<SessionStaffDTO> sessionStaffDTOList = sessionStaffApplicationService.getAllSessionStaff();
        return new ResponseEntity<>(sessionStaffDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SessionStaffDTO> getSessionStaffById(@PathVariable UUID id) {
        try {
            SessionStaffDTO sessionStaffDTO = sessionStaffApplicationService.getSessionStaffById(id);
            return new ResponseEntity<>(sessionStaffDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SessionStaffDTO> updateSessionStaff(@PathVariable UUID id, @RequestBody SessionStaffDTO sessionStaffDTO) {
        try {
            SessionStaffDTO updatedSessionStaff = sessionStaffApplicationService.updateSessionStaff(id, sessionStaffDTO);
            return new ResponseEntity<>(updatedSessionStaff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSessionStaff(@PathVariable UUID id) {
        try {
            sessionStaffApplicationService.deleteSessionStaff(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
