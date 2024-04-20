package co.uk.afterschoolclub.userregistration.SessionBooking;


import co.uk.afterschoolclub.userregistration.Student.StudentApplicationService;
import co.uk.afterschoolclub.userregistration.Student.StudentDTO;
import co.uk.afterschoolclub.userregistration.Student.StudentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessionBookings")
public class SessionBookingRestService {

    final SessionBookingApplicationService sessionBookingApplicationService;

    @Autowired
    public SessionBookingRestService(SessionBookingApplicationService sessionBookingApplicationService) {
        this.sessionBookingApplicationService = sessionBookingApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<SessionBookingDTO> createStudent(@RequestBody final SessionBookingDTO request){
        SessionBookingDTO student = sessionBookingApplicationService.createSessionBooking(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SessionBookingDTO>> getAllSessionBookings() {
        List<SessionBookingDTO> sessionBookingDTOList = sessionBookingApplicationService.getAllSessionBookings();
        return ResponseEntity.status(HttpStatus.OK).body(sessionBookingDTOList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SessionBookingDTO> getSessionBookingById(@PathVariable UUID id) {
        try {
            SessionBookingDTO sessionBookingDTO = sessionBookingApplicationService.getSessionBookingById(id);
            return ResponseEntity.status(HttpStatus.OK).body(sessionBookingDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SessionBookingDTO> editSessionBooking(@PathVariable UUID id, @RequestBody SessionBookingDTO sessionBookingDTO) {
        try {
            SessionBookingDTO updatedSessionBooking = sessionBookingApplicationService.editSessionBooking(id, sessionBookingDTO);
            return ResponseEntity.ok(updatedSessionBooking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSessionBookingById(@PathVariable UUID id) {
        try {
            sessionBookingApplicationService.deleteSessionBookingById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bySession/{sessionID}")
    public ResponseEntity<List<SessionBookingDTO>> getSessionBookingsBySessionID(@PathVariable UUID sessionID) {
        try {
            List<SessionBookingDTO> bookings = sessionBookingApplicationService.getSessionBookingsBySessionID(sessionID);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/byUser/{userID}")
    public ResponseEntity<List<SessionBookingDTO>> getSessionBookingsByUserID(@PathVariable UUID userID) {
        try {
            List<SessionBookingDTO> bookings = sessionBookingApplicationService.getSessionBookingsByUserID(userID);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/studentsBySession/{sessionId}")
    public ResponseEntity<List<StudentTable>> getStudentsBySessionId(@PathVariable UUID sessionId) {
        List<StudentTable> students = sessionBookingApplicationService.getStudentsBySessionId(sessionId);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }
}
