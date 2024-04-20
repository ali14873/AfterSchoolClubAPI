package co.uk.afterschoolclub.userregistration.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class SessionRestService {


    private final SessionApplicationService sessionApplicationService;

    @Autowired
    public SessionRestService(SessionApplicationService sessionApplicationService) {
        this.sessionApplicationService = sessionApplicationService;
    }

    @PostMapping("create")
    public ResponseEntity<SessionDTO> createSession(@RequestBody SessionDTO dto) {
        SessionDTO createdSession = sessionApplicationService.createSession(dto);
        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<SessionDTO>> getAllSessions() {
        List<SessionDTO> sessions = sessionApplicationService.getAllSessions();
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<SessionDTO> updateSession(@PathVariable UUID id, @RequestBody SessionDTO dto) {
        SessionDTO updatedSession = sessionApplicationService.editSession(id, dto);
        return new ResponseEntity<>(updatedSession, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable UUID id) {
        sessionApplicationService.deleteSessionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byClub/{clubId}")
    public ResponseEntity<List<SessionDTO>> getSessionsByClubId(@PathVariable UUID clubId) {
        List<SessionDTO> sessions = sessionApplicationService.getSessionsByClubID(clubId);
        if (sessions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sessions);
    }
}
