package co.uk.afterschoolclub.userregistration.SessionComment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SessionComments")
public class SessionCommentTable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "SessionID")
    private UUID sessionID;

    @Column(name = "UserID")
    private UUID userID;

    @Column(name = "Comment", length = 500)
    private String comment;

    @Column(name = "Rating")
    private Integer rating;
}
