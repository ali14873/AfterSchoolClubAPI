package co.uk.afterschoolclub.userregistration.SessionResourceUsage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SessionResourceUsageApplicationService {

    private final SessionResourceUsageRepositoryInterface sessionResourceUsageRepository;

    @Autowired
    public SessionResourceUsageApplicationService(SessionResourceUsageRepositoryInterface sessionResourceUsageRepository) {
        this.sessionResourceUsageRepository = sessionResourceUsageRepository;
    }

    @Transactional
    public SessionResourceUsageDTO createSessionResourceUsage(SessionResourceUsageDTO sessionResourceUsageDTO) {
        SessionResourceUsageTable sessionResourceUsage = SessionResourceUsageTable.builder()
                .sessionCompletionID(sessionResourceUsageDTO.getSessionCompletionID())
                .resourceID(sessionResourceUsageDTO.getResourceID())
                .quantityUsed(sessionResourceUsageDTO.getQuantityUsed())
                .build();

        sessionResourceUsage = sessionResourceUsageRepository.save(sessionResourceUsage);
        return SessionResourceUsageDTO.builder()
                .usageID(sessionResourceUsage.getUsageID())
                .sessionCompletionID(sessionResourceUsage.getSessionCompletionID())
                .resourceID(sessionResourceUsage.getResourceID())
                .quantityUsed(sessionResourceUsage.getQuantityUsed())
                .build();
    }

    public List<SessionResourceUsageDTO> getAllSessionResourceUsages() {
        List<SessionResourceUsageTable> sessionResourceUsageList = StreamSupport.stream(sessionResourceUsageRepository.findAll().spliterator(), false)
                .toList();

        return sessionResourceUsageList.stream()
                .map(resourceUsage -> SessionResourceUsageDTO.builder()
                        .usageID(resourceUsage.getUsageID())
                        .sessionCompletionID(resourceUsage.getSessionCompletionID())
                        .resourceID(resourceUsage.getResourceID())
                        .quantityUsed(resourceUsage.getQuantityUsed())
                        .build())
                .collect(Collectors.toList());
    }

    public SessionResourceUsageDTO getSessionResourceUsageById(UUID id) {
        Optional<SessionResourceUsageTable> resourceUsageOptional = sessionResourceUsageRepository.findById(id);

        return resourceUsageOptional.map(resourceUsage -> SessionResourceUsageDTO.builder()
                        .usageID(resourceUsage.getUsageID())
                        .sessionCompletionID(resourceUsage.getSessionCompletionID())
                        .resourceID(resourceUsage.getResourceID())
                        .quantityUsed(resourceUsage.getQuantityUsed())
                        .build())
                .orElseThrow(() -> new RuntimeException("Session Resource Usage not found with id: " + id));
    }

    public SessionResourceUsageDTO updateSessionResourceUsage(UUID id, SessionResourceUsageDTO sessionResourceUsageDTO) {
        SessionResourceUsageTable sessionResourceUsage = sessionResourceUsageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session Resource Usage not found with id: " + id));

        sessionResourceUsage.setSessionCompletionID(sessionResourceUsageDTO.getSessionCompletionID());
        sessionResourceUsage.setResourceID(sessionResourceUsageDTO.getResourceID());
        sessionResourceUsage.setQuantityUsed(sessionResourceUsageDTO.getQuantityUsed());
        sessionResourceUsage = sessionResourceUsageRepository.save(sessionResourceUsage);

        return SessionResourceUsageDTO.builder()
                .usageID(sessionResourceUsage.getUsageID())
                .sessionCompletionID(sessionResourceUsage.getSessionCompletionID())
                .resourceID(sessionResourceUsage.getResourceID())
                .quantityUsed(sessionResourceUsage.getQuantityUsed())
                .build();
    }

    public void deleteSessionResourceUsageById(UUID id) {
        if (sessionResourceUsageRepository.existsById(id)) {
            sessionResourceUsageRepository.deleteById(id);
        } else {
            throw new RuntimeException("Session Resource Usage not found with id: " + id);
        }
    }
}
