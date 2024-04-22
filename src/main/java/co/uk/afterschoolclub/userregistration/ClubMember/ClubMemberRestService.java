package co.uk.afterschoolclub.userregistration.ClubMember;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clubMember")
public class ClubMemberRestService {

    final ClubMemberApplicationService clubMemberApplicationService;

    @Autowired
    public ClubMemberRestService(ClubMemberApplicationService clubMemberApplicationService) {
        this.clubMemberApplicationService = clubMemberApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClubMember(@RequestBody final ClubMemberDTO request) {
        try {
            ClubMemberDTO clubMember = clubMemberApplicationService.createClubMember(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(clubMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClubMemberDTO>> getAllClubMembers() {
        List<ClubMemberDTO> clubMemberDTOList = clubMemberApplicationService.getAllClubMembers();
        return ResponseEntity.status(HttpStatus.OK).body(clubMemberDTOList);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ClubMemberDTO> editClubMember(@PathVariable UUID id, @RequestBody ClubMemberDTO clubMemberDTO) {
        try {
            ClubMemberDTO updatedClubMember = clubMemberApplicationService.editClubMember(id, clubMemberDTO);
            return ResponseEntity.ok(updatedClubMember);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClubMember(@PathVariable UUID id){
        try {
            clubMemberApplicationService.deleteClubMember(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // ... other methods ...

    @GetMapping("/getByClubId/{clubId}")
    public ResponseEntity<List<ClubMemberDTO>> getClubMembersByClubId(@PathVariable UUID clubId) {
        List<ClubMemberDTO> clubMemberDTOs = clubMemberApplicationService.getClubMembersByClubId(clubId);
        return ResponseEntity.ok(clubMemberDTOs);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<ClubMemberDTO>> getClubMembersByUserId(@PathVariable UUID userId) {
        List<ClubMemberDTO> clubMemberDTOs = clubMemberApplicationService.getClubMembersByUserId(userId);
        return ResponseEntity.ok(clubMemberDTOs);
    }

    @DeleteMapping("/deleteByUserIdAndClubId/{userId}/{clubId}")
    public ResponseEntity<Void> deleteClubMemberByUserIdAndClubId(@PathVariable UUID userId, @PathVariable UUID clubId) {
        try {
            clubMemberApplicationService.deleteClubMemberByUserIdAndClubId(userId, clubId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
