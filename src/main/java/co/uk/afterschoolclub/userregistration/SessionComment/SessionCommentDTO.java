package co.uk.afterschoolclub.userregistration.SessionComment;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SessionCommentDTO {

    private UUID id;
    private UUID clubId;  // New field
    private UUID SessionID;
    private UUID UserID;
    private String Comment;
    private Integer Rating;

}

