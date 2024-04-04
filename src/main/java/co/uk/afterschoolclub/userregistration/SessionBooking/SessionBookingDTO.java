package co.uk.afterschoolclub.userregistration.SessionBooking;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SessionBookingDTO {

    private UUID id;
    private UUID SessionID;
    private UUID UserID;
    private String BookingType;
    private String Status;

}
