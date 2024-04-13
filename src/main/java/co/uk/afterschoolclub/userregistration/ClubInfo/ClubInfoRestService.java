package co.uk.afterschoolclub.userregistration.ClubInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clubInfo")
public class ClubInfoRestService {

    final ClubInfoApplicationService clubInfoApplicationService;

    @Autowired
    public ClubInfoRestService(ClubInfoApplicationService clubInfoApplicationService) {
        this.clubInfoApplicationService = clubInfoApplicationService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClubInfoDTO>> getAllClubInfo(@RequestParam Optional<UUID> userId) {
        List<ClubInfoDTO> clubInfoDTOList = clubInfoApplicationService.getAllClubInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(clubInfoDTOList);

    }
}
