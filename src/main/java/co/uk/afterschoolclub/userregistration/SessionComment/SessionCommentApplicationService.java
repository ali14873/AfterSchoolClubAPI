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

    @Transactional
    public SessionCommentDTO createSessionComment(SessionCommentDTO sessionCommentDTO) {
        SessionCommentTable sessionComment = SessionCommentTable.builder()
                .sessionID(sessionCommentDTO.getSessionID())
                .userID(sessionCommentDTO.getUserID())
                .comment(sessionCommentDTO.getComment())
                .rating(sessionCommentDTO.getRating())
                .build();

        sessionComment = sessionCommentRepository.save(sessionComment);
        return new SessionCommentDTO(
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
        if (!comment.isPresent()) {
            throw new RuntimeException("Session Comment not found for ID: " + id);
        }
        SessionCommentTable sessionComment = comment.get();
        return new SessionCommentDTO(
                sessionComment.getId(),
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
        sessionComment.setUserID(sessionCommentDTO.getUserID());
        sessionComment.setComment(sessionCommentDTO.getComment());
        sessionComment.setRating(sessionCommentDTO.getRating());
        sessionComment = sessionCommentRepository.save(sessionComment);

        return new SessionCommentDTO(
                sessionComment.getId(),
                sessionComment.getSessionID(),
                sessionComment.getUserID(),
                sessionComment.getComment(),
                sessionComment.getRating());
    }

    public void deleteSessionCommentById(UUID id) {
        sessionCommentRepository.deleteById(id);
    }
}
