package co.uk.afterschoolclub.userregistration.SessionBooking;

import co.uk.afterschoolclub.userregistration.Student.StudentDTO;
import co.uk.afterschoolclub.userregistration.Student.StudentTable;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SessionBookingApplicationService {

    private final SessionBookingRepositoryInterface SessionBookingRepositoryInterface;

    @Autowired
    public SessionBookingApplicationService(SessionBookingRepositoryInterface sessionBookingRepository) {
        this.SessionBookingRepositoryInterface = sessionBookingRepository;
    }

    public SessionBookingDTO createSessionBooking(SessionBookingDTO request) {
        SessionBookingTable sessionBooking = SessionBookingTable.builder()
                .sessionID(request.getSessionID())
                .userId(request.getUserID())
                .bookingType(request.getBookingType())
                .status(request.getStatus())
                .build();
        sessionBooking = SessionBookingRepositoryInterface.save(sessionBooking);
        request.setId(sessionBooking.getId());
        return request;

    }

    public List<SessionBookingDTO> getAllSessionBookings() {
        List<SessionBookingDTO> sessionBookingDTO = new ArrayList<SessionBookingDTO>();
        for (SessionBookingTable sessionBookingTable : SessionBookingRepositoryInterface.findAll()) {
            SessionBookingDTO dto = SessionBookingDTO.builder()
                    .id(sessionBookingTable.getId())
                    .SessionID(sessionBookingTable.getSessionID())
                    .UserID(sessionBookingTable.getUserId())
                    .BookingType(sessionBookingTable.getBookingType())
                    .Status(sessionBookingTable.getStatus())
                    .build();
            sessionBookingDTO.add(dto);

        }
        return sessionBookingDTO;


    }

    public void deleteSessionBookingById(UUID id) {
        Optional<SessionBookingTable> bookingOptional = SessionBookingRepositoryInterface.findById(id);
        if (bookingOptional.isPresent()) {
            SessionBookingRepositoryInterface.delete(bookingOptional.get());
        } else {
            throw new EntityNotFoundException("SessionBooking not found with ID: " + id);
        }
    }

    public SessionBookingDTO editSessionBooking(UUID id, SessionBookingDTO sessionBookingDTO) {
        Optional<SessionBookingTable> bookingOptional = SessionBookingRepositoryInterface.findById(id);
        if (bookingOptional.isEmpty()) {
            throw new EntityNotFoundException("SessionBooking not found with ID: " + id);
        }
        SessionBookingTable booking = bookingOptional.get();
        booking.setSessionID(sessionBookingDTO.getSessionID());
        booking.setUserId(sessionBookingDTO.getUserID());
        booking.setBookingType(sessionBookingDTO.getBookingType());
        booking.setStatus(sessionBookingDTO.getStatus());
        SessionBookingRepositoryInterface.save(booking);
        return sessionBookingDTO; // Assuming DTO is directly updated and returned
    }


    public SessionBookingDTO getSessionBookingById(UUID id) {
        Optional<SessionBookingTable> bookingOptional = SessionBookingRepositoryInterface.findById(id);

        if (bookingOptional.isPresent()) {
            SessionBookingTable booking = bookingOptional.get();
            return new SessionBookingDTO(
                    booking.getId(),
                    booking.getSessionID(),
                    booking.getUserId(),
                    booking.getBookingType(),
                    booking.getStatus()
            );
        } else {
            throw new EntityNotFoundException("SessionBooking not found with ID: " + id);
        }
    }

    public List<SessionBookingDTO> getSessionBookingsBySessionID(UUID sessionID) {
        List<SessionBookingTable> bookings = SessionBookingRepositoryInterface.findBySessionID(sessionID);
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<SessionBookingDTO> getSessionBookingsByUserID(UUID userID) {
        List<SessionBookingTable> bookings = SessionBookingRepositoryInterface.findByUserId(userID);
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SessionBookingDTO convertToDTO(SessionBookingTable booking) {
        return new SessionBookingDTO(
                booking.getId(),
                booking.getSessionID(),
                booking.getUserId(),
                booking.getBookingType(),
                booking.getStatus()
        );
    }
}
