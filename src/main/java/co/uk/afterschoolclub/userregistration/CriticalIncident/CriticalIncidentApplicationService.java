package co.uk.afterschoolclub.userregistration.CriticalIncident;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CriticalIncidentApplicationService {
    private final CriticalIncidentRepositoryInterface criticalIncidentRepository;

    @Autowired
    public CriticalIncidentApplicationService(CriticalIncidentRepositoryInterface criticalIncidentRepository) {
        this.criticalIncidentRepository = criticalIncidentRepository;
    }

    @Transactional
    public CriticalIncidentDTO createCriticalIncident(CriticalIncidentDTO criticalIncidentDTO) {
        CriticalIncidentTable criticalIncident = CriticalIncidentTable.builder()
                .reportedBy(criticalIncidentDTO.getReportedBy())
                .sessionId(criticalIncidentDTO.getSessionId())
                .incidentDate(criticalIncidentDTO.getIncidentDate())
                .incidentType(criticalIncidentDTO.getIncidentType())
                .description(criticalIncidentDTO.getDescription())
                .actionTaken(criticalIncidentDTO.getActionTaken())
                .resolved(criticalIncidentDTO.getResolved())
                .resolutionDetails(criticalIncidentDTO.getResolutionDetails())
                .build();

        criticalIncident = criticalIncidentRepository.save(criticalIncident);

        return new CriticalIncidentDTO(
                criticalIncident.getId(),
                criticalIncident.getReportedBy(),
                criticalIncident.getSessionId(),
                criticalIncident.getIncidentDate(),
                criticalIncident.getIncidentType(),
                criticalIncident.getDescription(),
                criticalIncident.getActionTaken(),
                criticalIncident.getResolved(),
                criticalIncident.getResolutionDetails()
        );
    }

    public List<CriticalIncidentDTO> getAllCriticalIncidents() {
        List<CriticalIncidentTable> incidents = StreamSupport.stream(criticalIncidentRepository.findAll().spliterator(), false)
                .toList();

        return incidents.stream()
                .map(incident -> CriticalIncidentDTO.builder()
                        .incidentId(incident.getId())
                        .reportedBy(incident.getReportedBy())
                        .sessionId(incident.getSessionId())
                        .incidentDate(incident.getIncidentDate())
                        .incidentType(incident.getIncidentType())
                        .description(incident.getDescription())
                        .actionTaken(incident.getActionTaken())
                        .resolved(incident.getResolved())
                        .resolutionDetails(incident.getResolutionDetails())
                        .build())
                .collect(Collectors.toList());
    }

    public CriticalIncidentDTO getCriticalIncidentById(UUID id) {
        Optional<CriticalIncidentTable> incidentOpt = criticalIncidentRepository.findById(id);
        if (incidentOpt.isPresent()) {
            CriticalIncidentTable incident = incidentOpt.get();
            return CriticalIncidentDTO.builder()
                    .incidentId(incident.getId())
                    .reportedBy(incident.getReportedBy())
                    .sessionId(incident.getSessionId())
                    .incidentDate(incident.getIncidentDate())
                    .incidentType(incident.getIncidentType())
                    .description(incident.getDescription())
                    .actionTaken(incident.getActionTaken())
                    .resolved(incident.getResolved())
                    .resolutionDetails(incident.getResolutionDetails())
                    .build();
        } else {
            throw new EntityNotFoundException("Critical Incident not found with id: " + id);
        }
    }


    public void deleteCriticalIncident(UUID id) {
        criticalIncidentRepository.deleteById(id);

    }

    public CriticalIncidentDTO updateCriticalIncident(UUID id, CriticalIncidentDTO criticalIncidentDTO) {
        Optional<CriticalIncidentTable> existingIncidentOpt = criticalIncidentRepository.findById(id);
        if (existingIncidentOpt.isPresent()) {
            CriticalIncidentTable incident = existingIncidentOpt.get();

            // Updating the existing incident with new details
            incident.setReportedBy(criticalIncidentDTO.getReportedBy());
            incident.setSessionId(criticalIncidentDTO.getSessionId());
            incident.setIncidentDate(criticalIncidentDTO.getIncidentDate());
            incident.setIncidentType(criticalIncidentDTO.getIncidentType());
            incident.setDescription(criticalIncidentDTO.getDescription());
            incident.setActionTaken(criticalIncidentDTO.getActionTaken());
            incident.setResolved(criticalIncidentDTO.getResolved());
            incident.setResolutionDetails(criticalIncidentDTO.getResolutionDetails());

            // Saving the updated incident
            CriticalIncidentTable savedIncident = criticalIncidentRepository.save(incident);

            // Returning the updated DTO
            return CriticalIncidentDTO.builder()
                    .incidentId(savedIncident.getId())
                    .reportedBy(savedIncident.getReportedBy())
                    .sessionId(savedIncident.getSessionId())
                    .incidentDate(savedIncident.getIncidentDate())
                    .incidentType(savedIncident.getIncidentType())
                    .description(savedIncident.getDescription())
                    .actionTaken(savedIncident.getActionTaken())
                    .resolved(savedIncident.getResolved())
                    .resolutionDetails(savedIncident.getResolutionDetails())
                    .build();
        } else {
            throw new RuntimeException("Critical Incident not found for editing with id: " + id); // Customize this as per your exception handling strategy
        }
    }

}
