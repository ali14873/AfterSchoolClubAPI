package co.uk.afterschoolclub.userregistration.SessionResourceUsage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessionResourceUsage")
public class SessionResourceUsageRestService {

    private final SessionResourceUsageApplicationService sessionResourceUsageApplicationService;

    @Autowired
    public SessionResourceUsageRestService(SessionResourceUsageApplicationService sessionResourceUsageApplicationService) {
        this.sessionResourceUsageApplicationService = sessionResourceUsageApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<SessionResourceUsageDTO> createSessionResourceUsage(@RequestBody SessionResourceUsageDTO sessionResourceUsageDTO) {
        SessionResourceUsageDTO createdSessionResourceUsage = sessionResourceUsageApplicationService.createSessionResourceUsage(sessionResourceUsageDTO);
        return new ResponseEntity<>(createdSessionResourceUsage, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SessionResourceUsageDTO>> getAllSessionResourceUsages() {
        List<SessionResourceUsageDTO> sessionResourceUsageList = sessionResourceUsageApplicationService.getAllSessionResourceUsages();
        return new ResponseEntity<>(sessionResourceUsageList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SessionResourceUsageDTO> getSessionResourceUsageById(@PathVariable UUID id) {
        try {
            SessionResourceUsageDTO sessionResourceUsageDTO = sessionResourceUsageApplicationService.getSessionResourceUsageById(id);
            return ResponseEntity.ok(sessionResourceUsageDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SessionResourceUsageDTO> updateSessionResourceUsage(@PathVariable UUID id, @RequestBody SessionResourceUsageDTO sessionResourceUsageDTO) {
        try {
            SessionResourceUsageDTO updatedSessionResourceUsageDTO = sessionResourceUsageApplicationService.updateSessionResourceUsage(id, sessionResourceUsageDTO);
            return ResponseEntity.ok(updatedSessionResourceUsageDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSessionResourceUsage(@PathVariable UUID id) {
        try {
            sessionResourceUsageApplicationService.deleteSessionResourceUsageById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Additional endpoints can be added here as required
}
