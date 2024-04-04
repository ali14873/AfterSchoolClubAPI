package co.uk.afterschoolclub.userregistration.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;

@Service
public class AttendanceApplicationService {
    private final AttendanceRepositoryInterface attendanceRepository;

    @Autowired
    public AttendanceApplicationService(AttendanceRepositoryInterface attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional
    public List<AttendanceDTO> createAttendanceRecords(List<AttendanceDTO> attendanceRecordDTOList) {
        List<AttendanceTable> attendanceRecordEntities = attendanceRecordDTOList.stream()
                .map(dto -> AttendanceTable.builder()
                        .id(dto.getId())
                        .userId(dto.getUserId())
                        .sessionId(dto.getSessionId())
                        .bookingId(dto.getBookingId())
                        .attendanceStatus(dto.getAttendanceStatus())
                        .build())
                .collect(Collectors.toList());

        Iterable<AttendanceTable> savedEntitiesIterable = attendanceRepository.saveAll(attendanceRecordEntities);
        List<AttendanceTable> savedEntities = StreamSupport.stream(savedEntitiesIterable.spliterator(), false)
                .toList();

        return savedEntities.stream()
                .map(entity -> AttendanceDTO.builder()
                        .id(entity.getId())
                        .userId(entity.getUserId())
                        .sessionId(entity.getSessionId())
                        .bookingId(entity.getBookingId())
                        .attendanceStatus(entity.getAttendanceStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AttendanceDTO> getAllAttendanceRecords() {
        List<AttendanceTable> records = StreamSupport.stream(attendanceRepository.findAll().spliterator(), false)
                .toList();
        return records.stream()
                .map(record -> AttendanceDTO.builder()
                        .id(record.getId())
                        .userId(record.getUserId())
                        .sessionId(record.getSessionId())
                        .bookingId(record.getBookingId())
                        .attendanceStatus(record.getAttendanceStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public AttendanceDTO getAttendanceRecordById(UUID id) {
        Optional<AttendanceTable> record = attendanceRepository.findById(id);
        if (record.isPresent()) {
            AttendanceTable entity = record.get();
            return AttendanceDTO.builder()
                    .id(entity.getId())
                    .userId(entity.getUserId())
                    .bookingId(entity.getBookingId())
                    .sessionId(entity.getSessionId())
                    .attendanceStatus(entity.getAttendanceStatus())
                    .build();
        } else {
            throw new RuntimeException("Attendance record not found with id: " + id); // Customize this as per your exception handling strategy
        }
    }

    public void deleteAttendanceRecordById(UUID id) {
        attendanceRepository.deleteById(id);
    }

    public AttendanceDTO editAttendanceRecord(UUID id, AttendanceDTO attendanceDTO) {
        Optional<AttendanceTable> existingRecord = attendanceRepository.findById(id);
        if (existingRecord.isPresent()) {
            AttendanceTable record = existingRecord.get();
            record.setUserId(attendanceDTO.getUserId());
            record.setBookingId(attendanceDTO.getBookingId());
            record.setSessionId(attendanceDTO.getSessionId());
            record.setAttendanceStatus(attendanceDTO.getAttendanceStatus());
            AttendanceTable savedRecord = attendanceRepository.save(record);
            return AttendanceDTO.builder()
                    .id(savedRecord.getId())
                    .userId(savedRecord.getUserId())
                    .sessionId(savedRecord.getSessionId())
                    .bookingId(savedRecord.getBookingId())
                    .attendanceStatus(savedRecord.getAttendanceStatus())
                    .build();
        } else {
            throw new RuntimeException("Attendance record not found for editing with id: " + id); // Customize this as per your exception handling strategy
        }
    }
}
