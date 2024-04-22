package co.uk.afterschoolclub.userregistration.SessionComment;

import co.uk.afterschoolclub.userregistration.Session.SessionRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SessionCommentApplicationService {

    private final SessionCommentRepositoryInterface sessionCommentRepository;

    @Autowired
    public SessionCommentApplicationService(SessionCommentRepositoryInterface sessionCommentRepository) {
        this.sessionCommentRepository = sessionCommentRepository;
    }

    public SessionCommentDTO createSessionComment(SessionCommentDTO sessionCommentDTO) {
        SessionCommentTable sessionComment = SessionCommentTable.builder()
                .clubId(sessionCommentDTO.getClubId())
                .sessionID(sessionCommentDTO.getSessionID())
                .userID(sessionCommentDTO.getUserID())
                .comment(sessionCommentDTO.getComment())
                .rating(sessionCommentDTO.getRating())
                .build();

        sessionComment = sessionCommentRepository.save(sessionComment);
        return new SessionCommentDTO(
                sessionComment.getClubId(),
                sessionComment.getId(),
                sessionComment.getSessionID(),
                sessionComment.getUserID(),
                sessionComment.getComment(),
                sessionComment.getRating());
    }

    public List<SessionCommentDTO> getAllSessionComments() {
        List<SessionCommentDTO> sessionCommentDTOs = new ArrayList<>();
        for(SessionCommentTable sessionCommentTable: sessionCommentRepository.findAll()){
            SessionCommentDTO dto = SessionCommentDTO.builder()
                    .id(sessionCommentTable.getId())
                    .clubId(sessionCommentTable.getClubId())
                    .SessionID(sessionCommentTable.getSessionID())
                    .UserID(sessionCommentTable.getUserID())
                    .Comment(sessionCommentTable.getComment())
                    .Rating(sessionCommentTable.getRating())
                    .build();
            sessionCommentDTOs.add(dto);
        }
        return sessionCommentDTOs;
    }


    public SessionCommentDTO getSessionCommentById(UUID id) {
        Optional<SessionCommentTable> comment = sessionCommentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new RuntimeException("Session Comment not found for ID: " + id);
        }
        SessionCommentTable sessionComment = comment.get();
        return new SessionCommentDTO(
                sessionComment.getId(),
                sessionComment.getClubId(),
                sessionComment.getSessionID(),
                sessionComment.getUserID(),
                sessionComment.getComment(),
                sessionComment.getRating());
    }

    @Transactional
    public SessionCommentDTO updateSessionComment(UUID id, SessionCommentDTO sessionCommentDTO) {
        SessionCommentTable sessionComment = sessionCommentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session Comment not found for ID: " + id));

        sessionComment.setSessionID(sessionCommentDTO.getSessionID());
        sessionComment.setClubId(sessionCommentDTO.getClubId());
        sessionComment.setUserID(sessionCommentDTO.getUserID());
        sessionComment.setComment(sessionCommentDTO.getComment());
        sessionComment.setRating(sessionCommentDTO.getRating());
        sessionComment = sessionCommentRepository.save(sessionComment);

        return new SessionCommentDTO(
                sessionComment.getId(),
                sessionComment.getClubId(),
                sessionComment.getSessionID(),
                sessionComment.getUserID(),
                sessionComment.getComment(),
                sessionComment.getRating());
    }


    public void deleteSessionCommentById(UUID id) {
        sessionCommentRepository.deleteById(id);
    }


    public List<SessionCommentDTO> getAllSessionCommentsByClubId(UUID clubId) {
        return sessionCommentRepository.findByClubId(clubId).stream()
                .map(comment -> new SessionCommentDTO(
                        comment.getId(),
                        comment.getClubId(),
                        comment.getSessionID(),
                        comment.getUserID(),
                        comment.getComment(),
                        comment.getRating()
                )).collect(Collectors.toList());
    }

    public List<SessionCommentDTO> getAllSessionCommentsBySessionId(UUID sessionId) {
        return sessionCommentRepository.findBySessionID(sessionId).stream()
                .map(comment -> new SessionCommentDTO(
                        comment.getId(),
                        comment.getClubId(),
                        comment.getSessionID(),
                        comment.getUserID(),
                        comment.getComment(),
                        comment.getRating()
                )).collect(Collectors.toList());
    }


    public int getCountBySessionId(UUID sessionId) {
        return sessionCommentRepository.countBySessionID(sessionId);
    }

    public Double getAverageRatingBySessionId(UUID sessionId) {
        return sessionCommentRepository.averageRatingBySessionID(sessionId);
    }

    public Double getAverageRatingForAllComments() {
        return sessionCommentRepository.averageRatingForAllComments();
    }


    public int getCountByClubId(UUID clubId) {
        return sessionCommentRepository.countByClubId(clubId);
    }

    public Double getAverageRatingByClubId(UUID clubId) {
        Double average = sessionCommentRepository.averageRatingByClubId(clubId);
        return average != null ? average : 0.0; // Handle null in case of no ratings
    }
}
