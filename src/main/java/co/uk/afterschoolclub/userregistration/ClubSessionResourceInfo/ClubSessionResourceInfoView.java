package co.uk.afterschoolclub.userregistration.ClubSessionResourceInfo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Immutable
@Entity
@Table(name = "club_sessions_resources")
public class ClubSessionResourceInfoView {

    @Id
    @Column(name = "allocationid")
    private UUID allocationId; // Assuming allocationid can serve as a unique identifier for this view

    @Column(name = "clubid")
    private UUID clubId;

    @Column(name = "club_name")
    private String clubName;

    @Column(name = "club_description")
    private String clubDescription;

    @Column(name = "club_location")
    private String clubLocation;

    @Column(name = "sessionid")
    private UUID sessionId;

    @Column(name = "date")
    private Date date;

    @Column(name = "session_description")
    private String sessionDescription;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "is_recurring")
    private Boolean isRecurring;

    @Column(name = "session_location")
    private String sessionLocation;

    @Column(name = "recurrence_rule")
    private String recurrenceRule;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "completion_date")
    private Date completionDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "quantity_allocated")
    private Integer quantityAllocated;

    @Column(name = "usageid")
    private UUID usageId;

    @Column(name = "quantity_used")
    private Integer quantityUsed;

    @Column(name = "resourceid")
    private UUID resourceId;

    @Column(name = "resource_description")
    private String resourceDescription;

    @Column(name = "inventoryid")
    private UUID inventoryId;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;

    @Column(name = "attendanceid")
    private UUID attendanceId;

    @Column(name = "attendance_status")
    private String attendanceStatus;



}
