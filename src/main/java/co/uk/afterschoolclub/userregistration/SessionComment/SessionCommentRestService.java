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
        System.out.println("Received: " + sessionCommentDTO);
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

    // Endpoint to get all comments by session ID
    @GetMapping("/getAllBySessionId/{sessionId}")
    public ResponseEntity<List<SessionCommentDTO>> getAllSessionCommentsBySessionId(@PathVariable UUID sessionId) {
        List<SessionCommentDTO> comments = sessionCommentApplicationService.getAllSessionCommentsBySessionId(sessionId);
        return ResponseEntity.ok(comments);
    }

    // Endpoint to get count of comments by session ID
    @GetMapping("/getCountBySessionId/{sessionId}")
    public ResponseEntity<Integer> getCountBySessionId(@PathVariable UUID sessionId) {
        int count = sessionCommentApplicationService.getCountBySessionId(sessionId);
        return ResponseEntity.ok(count);
    }

    // Endpoint to get average rating by session ID
    @GetMapping("/getAverageRatingBySessionId/{sessionId}")
    public ResponseEntity<Double> getAverageRatingBySessionId(@PathVariable UUID sessionId) {
        Double average = sessionCommentApplicationService.getAverageRatingBySessionId(sessionId);
        return ResponseEntity.ok(average);
    }

    // Endpoint to get average rating for all comments
    @GetMapping("/getAverageRatingForAll")
    public ResponseEntity<Double> getAverageRatingForAll() {
        Double average = sessionCommentApplicationService.getAverageRatingForAllComments();
        return ResponseEntity.ok(average);
    }


}
