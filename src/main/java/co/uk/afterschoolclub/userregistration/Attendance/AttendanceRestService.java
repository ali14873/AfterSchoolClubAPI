package co.uk.afterschoolclub.userregistration.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Attendance")
public class AttendanceRestService {

    private final AttendanceApplicationService attendanceApplicationService;

    @Autowired
    public AttendanceRestService(AttendanceApplicationService attendanceApplicationService) {
        this.attendanceApplicationService = attendanceApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<List<AttendanceDTO>> createAttendanceRecords(@RequestBody List<AttendanceDTO> attendanceRecordDTOList) {
        List<AttendanceDTO> savedRecords = attendanceApplicationService.createAttendanceRecords(attendanceRecordDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecords);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendanceRecords() {
        List<AttendanceDTO> records = attendanceApplicationService.getAllAttendanceRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceRecordById(@PathVariable UUID id) {
        try {
            AttendanceDTO record = attendanceApplicationService.getAttendanceRecordById(id);
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttendanceRecordById(@PathVariable UUID id) {
        try {
            attendanceApplicationService.deleteAttendanceRecordById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AttendanceDTO> editAttendanceRecord(@PathVariable UUID id, @RequestBody AttendanceDTO attendanceDTO) {
        try {
            AttendanceDTO updatedRecord = attendanceApplicationService.editAttendanceRecord(id, attendanceDTO);
            return ResponseEntity.ok(updatedRecord);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
