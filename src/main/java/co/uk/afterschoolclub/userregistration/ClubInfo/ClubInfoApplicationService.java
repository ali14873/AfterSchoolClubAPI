package co.uk.afterschoolclub.userregistration.ClubInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClubInfoApplicationService {

    private final ClubInfoViewRepositoryInterface clubInfoViewRepository;

    @Autowired
    public ClubInfoApplicationService(ClubInfoViewRepositoryInterface clubInfoViewRepository) {
        this.clubInfoViewRepository = clubInfoViewRepository;
    }

    public List<ClubInfoDTO> getAllClubInfo(Optional<UUID> userId) {
        List<ClubDetailsView> clubInfoViews;
        if (userId.isPresent()) {
            // Fetch clubs where the user is a member
            clubInfoViews = clubInfoViewRepository.findAllByMember(userId.get());
        } else {
            // Fetch all clubs
            clubInfoViews = (List<ClubDetailsView>) clubInfoViewRepository.findAll();
        }
        return clubInfoViews.stream()
                .map(this::convertToClubInfoDTO)
                .collect(Collectors.toList());
    }

    private ClubInfoDTO convertToClubInfoDTO(ClubDetailsView clubInfoView) {
        // Convert ClubInfoView to ClubInfoDTO
        return ClubInfoDTO.builder()
                .clubID(clubInfoView.getClubid())
                .clubName(clubInfoView.getClubName())
                .description(clubInfoView.getDescription())
                .location(clubInfoView.getLocation())
                .averageRating(clubInfoView.getAverageRating() != null ? clubInfoView.getAverageRating().doubleValue() : null)
                .build();
    }
}
