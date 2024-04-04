package co.uk.afterschoolclub.userregistration.SessionCompletion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SessionCompletionApplicationService {

    private final SessionCompletionRepositoryInterface sessionCompletionRepository;

    @Autowired
    public SessionCompletionApplicationService(SessionCompletionRepositoryInterface sessionCompletionRepository) {
        this.sessionCompletionRepository = sessionCompletionRepository;
    }

    @Transactional
    public SessionCompletionDTO createSessionCompletion(SessionCompletionDTO sessionCompletionDTO) {
        SessionCompletionTable sessionCompletion = SessionCompletionTable.builder()
                .sessionID(sessionCompletionDTO.getSessionID())
                .completionDate(sessionCompletionDTO.getCompletionDate())
                .notes(sessionCompletionDTO.getNotes())
                .build();

        sessionCompletion = sessionCompletionRepository.save(sessionCompletion);
        return SessionCompletionDTO.builder()
                .sessionCompletionID(sessionCompletion.getSessionCompletionID())
                .sessionID(sessionCompletion.getSessionID())
                .completionDate(sessionCompletion.getCompletionDate())
                .notes(sessionCompletion.getNotes())
                .build();
    }

    public List<SessionCompletionDTO> getAllSessionCompletions() {
        List<SessionCompletionTable> sessionCompletions = StreamSupport.stream(sessionCompletionRepository.findAll().spliterator(), false)
                .toList();

        return sessionCompletions.stream()
                .map(sessionCompletion -> SessionCompletionDTO.builder()
                        .sessionCompletionID(sessionCompletion.getSessionCompletionID())
                        .sessionID(sessionCompletion.getSessionID())
                        .completionDate(sessionCompletion.getCompletionDate())
                        .notes(sessionCompletion.getNotes())
                        .build())
                .collect(Collectors.toList());
    }

    public SessionCompletionDTO getSessionCompletionById(UUID id) {
        Optional<SessionCompletionTable> sessionCompletionOptional = sessionCompletionRepository.findById(id);

        if (sessionCompletionOptional.isPresent()) {
            SessionCompletionTable sessionCompletion = sessionCompletionOptional.get();
            return SessionCompletionDTO.builder()
                    .sessionCompletionID(sessionCompletion.getSessionCompletionID())
                    .sessionID(sessionCompletion.getSessionID())
                    .completionDate(sessionCompletion.getCompletionDate())
                    .notes(sessionCompletion.getNotes())
                    .build();
        } else {
            throw new RuntimeException("Session Completion not found with id: " + id);
        }
    }

    public SessionCompletionDTO updateSessionCompletion(UUID id, SessionCompletionDTO sessionCompletionDTO) {
        Optional<SessionCompletionTable> sessionCompletionOptional = sessionCompletionRepository.findById(id);

        if (sessionCompletionOptional.isPresent()) {
            SessionCompletionTable sessionCompletion = sessionCompletionOptional.get();
            sessionCompletion.setSessionID(sessionCompletionDTO.getSessionID());
            sessionCompletion.setCompletionDate(sessionCompletionDTO.getCompletionDate());
            sessionCompletion.setNotes(sessionCompletionDTO.getNotes());

            sessionCompletion = sessionCompletionRepository.save(sessionCompletion);
            return SessionCompletionDTO.builder()
                    .sessionCompletionID(sessionCompletion.getSessionCompletionID())
                    .sessionID(sessionCompletion.getSessionID())
                    .completionDate(sessionCompletion.getCompletionDate())
                    .notes(sessionCompletion.getNotes())
                    .build();
        } else {
            throw new RuntimeException("Session Completion not found with id: " + id);
        }
    }

    public void deleteSessionCompletionById(UUID id) {
        Optional<SessionCompletionTable> sessionCompletionOptional = sessionCompletionRepository.findById(id);

        if (sessionCompletionOptional.isPresent()) {
            sessionCompletionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Session Completion not found with id: " + id);
        }
    }
}