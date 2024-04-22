package co.uk.afterschoolclub.userregistration.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    public SessionApplicationService(SessionRepositoryInterface sessionRepository) {
        this.sessionRepository = sessionRepository;

    }

    public SessionDTO createSession(SessionDTO request) throws JsonProcessingException {
        String recurringDetailsJson = objectMapper.writeValueAsString(request.getRecurring());

        SessionTable session = SessionTable.builder()
                .clubID(request.getClubID())
                .SessionName(request.getSessionName())
                .Description(request.getDescription())
                .Date(request.getDate())
                .StartTime(request.getStartTime())
                .EndTime(request.getEndTime())
                .Location(request.getLocation())
                .IsRecurring(request.getIsRecurring())
                .recurringDetails(recurringDetailsJson)
                .build();
        session = sessionRepository.save(session);
        request.setId(session.getId());
        return request;
    }

    public List<SessionDTO> getAllSessions() {
        List<SessionDTO> sessions = new ArrayList<>();
        for (SessionTable session : sessionRepository.findAll()) {
            RecurringDTO recurringDetails = null;
            try {
                recurringDetails = objectMapper.readValue(session.getRecurringDetails(), RecurringDTO.class);
            } catch (JsonProcessingException e) {
                // Handle JSON parsing error
                // Depending on your application's requirements, you might want to log this error or handle it accordingly.
            }


            SessionDTO dto = SessionDTO.builder()
                    .id(session.getId())
                    .clubID(session.getClubID())
                    .sessionName(session.getSessionName())
                    .description(session.getDescription())
                    .startTime(session.getStartTime())
                    .endTime(session.getEndTime())
                    .location(session.getLocation())
                    .recurring(recurringDetails)
                    .build();
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

        try {
            String recurringDetailsJson = objectMapper.writeValueAsString(sessionDTO.getRecurring());

            session.setClubID(sessionDTO.getClubID());
            session.setSessionName(sessionDTO.getSessionName());
            session.setDescription(sessionDTO.getDescription());
            session.setDate(sessionDTO.getDate());
            session.setStartTime(sessionDTO.getStartTime());
            session.setEndTime(sessionDTO.getEndTime());
            session.setLocation(sessionDTO.getLocation());
            session.setIsRecurring(sessionDTO.getIsRecurring());
            session.setRecurringDetails(recurringDetailsJson);

            sessionRepository.save(session);

        } catch (JsonProcessingException e) {
            // Handle JSON processing exceptions here (log or throw a wrapped unchecked exception)
            throw new RuntimeException("Failed to serialize recurring details", e);
        }

        return sessionDTO; // Return the modified DTO
    }


    public SessionDTO getSessionById(UUID id) {
        Optional<SessionTable> sessionOptional = sessionRepository.findById(id);
        if (sessionOptional.isEmpty()) {
            throw new EntityNotFoundException("Session not found with ID: " + id);
        }
        SessionTable session = sessionOptional.get();
        RecurringDTO recurringDetails = null;

        if (session.getRecurringDetails() != null && !session.getRecurringDetails().isEmpty()) {
            try {
                recurringDetails = objectMapper.readValue(session.getRecurringDetails(), RecurringDTO.class);
            } catch (JsonProcessingException e) {
                // Handle JSON parsing error. Log this error or handle it accordingly.
                // For simplicity, throwing a runtime exception, but in real scenarios, it should be handled more gracefully.
                throw new RuntimeException("Error parsing recurring details", e);
            }
        }

        return SessionDTO.builder()
                .id(session.getId())
                .clubID(session.getClubID())
                .sessionName(session.getSessionName())
                .description(session.getDescription())
                .date(session.getDate())
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .location(session.getLocation())
                .isRecurring(session.getIsRecurring())
                .recurring(recurringDetails)
                .build();
    }


    public void deleteSessionById(UUID id) {
        Optional<SessionTable> sessionOptional = sessionRepository.findById(id);
        if (sessionOptional.isPresent()) {
            sessionRepository.delete(sessionOptional.get());
        } else {
            throw new EntityNotFoundException("Session not found with ID: " + id);
        }
    }

    public List<SessionDTO> getSessionsByClubID(UUID clubID) {
        return sessionRepository.findByClubID(clubID).stream().map(session -> {
            RecurringDTO recurringDetails = null;
            if (session.getRecurringDetails() != null && !session.getRecurringDetails().isEmpty()) {
                try {
                    recurringDetails = objectMapper.readValue(session.getRecurringDetails(), RecurringDTO.class);
                } catch (JsonProcessingException e) {
                    // Handle JSON parsing error. Log this error or handle it accordingly.
                    // For simplicity, throwing a runtime exception, but in real scenarios, it should be handled more gracefully.
                    throw new RuntimeException("Error parsing recurring details for session ID " + session.getId(), e);
                }
            }

            return SessionDTO.builder()
                    .id(session.getId())
                    .clubID(session.getClubID())
                    .sessionName(session.getSessionName())
                    .description(session.getDescription())
                    .date(session.getDate())
                    .startTime(session.getStartTime())
                    .endTime(session.getEndTime())
                    .location(session.getLocation())
                    .isRecurring(session.getIsRecurring())
                    .recurring(recurringDetails) // Replace this with the RecurringDTO object parsed from JSON
                    .build();
        }).collect(Collectors.toList());
    }

}
