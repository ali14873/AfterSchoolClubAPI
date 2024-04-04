package co.uk.afterschoolclub.userregistration.Club;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/club")
public class ClubRestService {

    final ClubApplicationService clubApplicationService;

    @Autowired
    public ClubRestService(ClubApplicationService clubApplicationService) {
        this.clubApplicationService = clubApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ClubDTO> createClub(@RequestBody final ClubDTO request) {
        ClubDTO club = clubApplicationService.createClub(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(club);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClubDTO>> getAllClubs() {
        List<ClubDTO> clubDTOList = clubApplicationService.getAllClubs();
        return ResponseEntity.status(HttpStatus.OK).body(clubDTOList);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ClubDTO> editClub(@PathVariable UUID id, @RequestBody ClubDTO clubDTO) {
        try {
            ClubDTO updatedClub = clubApplicationService.editClub(id, clubDTO);
            return ResponseEntity.ok(updatedClub);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable UUID id){
        try {
            clubApplicationService.deleteClub(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
