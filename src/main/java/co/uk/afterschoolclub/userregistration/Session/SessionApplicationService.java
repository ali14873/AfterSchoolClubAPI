package co.uk.afterschoolclub.userregistration.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SessionApplicationService {


    private final SessionRepositoryInterface sessionRepository;

    @Autowired
    public SessionApplicationService(SessionRepositoryInterface sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public SessionDTO createSession(SessionDTO request) {
        SessionTable session = SessionTable.builder()
                .ClubID(request.getClubID())
                .SessionName(request.getSessionName())
                .Description(request.getDescription())
                .Date(request.getDate())
                .StartTime(request.getStartTime())
                .EndTime(request.getEndTime())
                .Location(request.getLocation())
                .IsRecurring(request.getIsRecurring())
                .RecurrenceRule(request.getRecurrenceRule())
                .build();
        session = sessionRepository.save(session);
        request.setId(session.getId());
        return request;
    }

    public List<SessionDTO> getAllSessions() {
        List<SessionDTO> sessions = new ArrayList<>();
        for (SessionTable session : sessionRepository.findAll()) {
            SessionDTO dto = new SessionDTO(
                    session.getId(),
                    session.getClubID(),
                    session.getSessionName(),
                    session.getDescription(),
                    session.getDate(),
                    session.getStartTime(),
                    session.getEndTime(),
                    session.getLocation(),
                    session.getIsRecurring(),
                    session.getRecurrenceRule()
            );
            sessions.add(dto);
        }
        return sessions;
    }

    public SessionDTO editSession(UUID id, SessionDTO sessionDTO) {
        Optional<SessionTable> sessionOptional = sessionRepository.findById(id);
        if (sessionOptional.isEmpty()) {
            throw new EntityNotFoundException("Session not found with ID: " + id);
        }
        SessionTable session = sessionOptional.get();
        session.setClubID(sessionDTO.getClubID());
        session.setSessionName(sessionDTO.getSessionName());
        session.setDescription(sessionDTO.getDescription());
        session.setDate(sessionDTO.getDate());
        session.setStartTime(sessionDTO.getStartTime());
        session.setEndTime(sessionDTO.getEndTime());
        session.setLocation(sessionDTO.getLocation());
        session.setIsRecurring(sessionDTO.getIsRecurring());
        session.setRecurrenceRule(sessionDTO.getRecurrenceRule());
        sessionRepository.save(session);
        return sessionDTO; // Return updated DTO, possibly after re-fetching or updating from the entity
    }

    public SessionDTO getSessionById(UUID id) {
        Optional<SessionTable> sessionOptional = sessionRepository.findById(id);

        if (sessionOptional.isPresent()) {
            SessionTable session = sessionOptional.get();
            return new SessionDTO(
                    session.getId(),
                    session.getClubID(),
                    session.getSessionName(),
                    session.getDescription(),
                    session.getDate(),
                    session.getStartTime(),
                    session.getEndTime(),
                    session.getLocation(),
                    session.getIsRecurring(),
                    session.getRecurrenceRule()
            );
        } else {
            throw new EntityNotFoundException("Session not found with ID: " + id);
        }
    }

    public void deleteSessionById(UUID id) {
        Optional<SessionTable> sessionOptional = sessionRepository.findById(id);
        if (sessionOptional.isPresent()) {
            sessionRepository.delete(sessionOptional.get());
        } else {
            throw new EntityNotFoundException("Session not found with ID: " + id);
        }
    }
}
