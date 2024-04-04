package co.uk.afterschoolclub.userregistration.SessionStaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SessionStaffApplicationService {

    private final SessionStaffRepositoryInterface sessionStaffRepository;

    @Autowired
    public SessionStaffApplicationService(SessionStaffRepositoryInterface sessionStaffRepository) {
        this.sessionStaffRepository = sessionStaffRepository;
    }

    @Transactional
    public SessionStaffDTO createSessionStaff(SessionStaffDTO sessionStaffDTO) {
        SessionStaffTable sessionStaff = SessionStaffTable.builder()
                .sessionID(sessionStaffDTO.getSessionID())
                .staffID(sessionStaffDTO.getStaffID())
                .role(sessionStaffDTO.getRole())
                .build();
        sessionStaff = sessionStaffRepository.save(sessionStaff);

        return SessionStaffDTO.builder()
                .sessionStaffID(sessionStaff.getSessionStaffID())
                .sessionID(sessionStaff.getSessionID())
                .staffID(sessionStaff.getStaffID())
                .role(sessionStaff.getRole())
                .build();
    }

    public List<SessionStaffDTO> getAllSessionStaff() {
        Iterable<SessionStaffTable> sessionStaffEntitiesIterable = sessionStaffRepository.findAll();
        List<SessionStaffTable> sessionStaffEntities = StreamSupport.stream(sessionStaffEntitiesIterable.spliterator(), false)
                .toList();

        return sessionStaffEntities.stream().map(entity ->
                SessionStaffDTO.builder()
                        .sessionStaffID(entity.getSessionStaffID())
                        .sessionID(entity.getSessionID())
                        .staffID(entity.getStaffID())
                        .role(entity.getRole())
                        .build()
        ).collect(Collectors.toList());
    }


    public SessionStaffDTO getSessionStaffById(UUID id) {
        SessionStaffTable sessionStaff = sessionStaffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session staff not found with id: " + id));

        return SessionStaffDTO.builder()
                .sessionStaffID(sessionStaff.getSessionStaffID())
                .sessionID(sessionStaff.getSessionID())
                .staffID(sessionStaff.getStaffID())
                .role(sessionStaff.getRole())
                .build();
    }

    @Transactional
    public SessionStaffDTO updateSessionStaff(UUID id, SessionStaffDTO sessionStaffDTO) {
        SessionStaffTable sessionStaff = sessionStaffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session staff not found with id: " + id));

        // Update the entity with new values
        sessionStaff.setSessionID(sessionStaffDTO.getSessionID());
        sessionStaff.setStaffID(sessionStaffDTO.getStaffID());
        sessionStaff.setRole(sessionStaffDTO.getRole());

        sessionStaff = sessionStaffRepository.save(sessionStaff);

        return SessionStaffDTO.builder()
                .sessionStaffID(sessionStaff.getSessionStaffID())
                .sessionID(sessionStaff.getSessionID())
                .staffID(sessionStaff.getStaffID())
                .role(sessionStaff.getRole())
                .build();
    }

    @Transactional
    public void deleteSessionStaff(UUID id) {
        SessionStaffTable sessionStaff = sessionStaffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session staff not found with id: " + id));
        sessionStaffRepository.delete(sessionStaff);
    }
}
