package co.uk.afterschoolclub.userregistration.SessionCompletion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessionCompletion")
public class SessionCompletionRestService {

    private final SessionCompletionApplicationService sessionCompletionApplicationService;

    @Autowired
    public SessionCompletionRestService(SessionCompletionApplicationService sessionCompletionApplicationService) {
        this.sessionCompletionApplicationService = sessionCompletionApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<SessionCompletionDTO> createSessionCompletion(@RequestBody SessionCompletionDTO sessionCompletionDTO) {
        SessionCompletionDTO createdSessionCompletion = sessionCompletionApplicationService.createSessionCompletion(sessionCompletionDTO);
        return new ResponseEntity<>(createdSessionCompletion, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SessionCompletionDTO>> getAllSessionCompletions() {
        List<SessionCompletionDTO> sessionCompletions = sessionCompletionApplicationService.getAllSessionCompletions();
        return new ResponseEntity<>(sessionCompletions, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SessionCompletionDTO> getSessionCompletionById(@PathVariable UUID id) {
        try {
            SessionCompletionDTO sessionCompletion = sessionCompletionApplicationService.getSessionCompletionById(id);
            return new ResponseEntity<>(sessionCompletion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SessionCompletionDTO> updateSessionCompletion(@PathVariable UUID id, @RequestBody SessionCompletionDTO sessionCompletionDTO) {
        try {
            SessionCompletionDTO updatedSessionCompletion = sessionCompletionApplicationService.updateSessionCompletion(id, sessionCompletionDTO);
            return new ResponseEntity<>(updatedSessionCompletion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteSessionCompletion(@PathVariable UUID id) {
        try {
            sessionCompletionApplicationService.deleteSessionCompletionById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
