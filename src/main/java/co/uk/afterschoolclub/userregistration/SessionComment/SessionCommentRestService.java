package co.uk.afterschoolclub.userregistration.SessionComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessionComments")
public class SessionCommentRestService {

    private final SessionCommentApplicationService sessionCommentApplicationService;

    @Autowired
    public SessionCommentRestService(SessionCommentApplicationService sessionCommentApplicationService) {
        this.sessionCommentApplicationService = sessionCommentApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<SessionCommentDTO> createSessionComment(@RequestBody SessionCommentDTO sessionCommentDTO) {
        SessionCommentDTO createdComment = sessionCommentApplicationService.createSessionComment(sessionCommentDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SessionCommentDTO>> getAllSessionComments() {
        List<SessionCommentDTO> comments = sessionCommentApplicationService.getAllSessionComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SessionCommentDTO> getSessionCommentById(@PathVariable UUID id) {
        try {
            SessionCommentDTO comment = sessionCommentApplicationService.getSessionCommentById(id);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SessionCommentDTO> updateSessionComment(@PathVariable UUID id, @RequestBody SessionCommentDTO sessionCommentDTO) {
        try {
            SessionCommentDTO updatedComment = sessionCommentApplicationService.updateSessionComment(id, sessionCommentDTO);
            return ResponseEntity.ok(updatedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSessionCommentById(@PathVariable UUID id) {
        try {
            sessionCommentApplicationService.deleteSessionCommentById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
