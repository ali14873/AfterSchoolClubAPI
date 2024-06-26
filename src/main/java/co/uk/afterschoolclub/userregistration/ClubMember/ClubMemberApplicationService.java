package co.uk.afterschoolclub.userregistration.ClubMember;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClubMemberApplicationService {

    @Autowired
    private ClubMemberRepositoryInterface clubMemberRepository;

    public ClubMemberDTO createClubMember(ClubMemberDTO request) throws Exception {
        // Check if the membership already exists
        Optional<ClubMemberTable> existingMembership = clubMemberRepository.findByUserIdAndClubId(request.getUserID(), request.getClubID());
        if (existingMembership.isPresent()) {
            // If membership exists, throw an exception or return an error
            throw new Exception("User already a member of this club.");
        }


        ClubMemberTable clubMember = ClubMemberTable.builder()
                .clubId(request.getClubID())
                .MembershipStatus(request.getMembershipStatus())
                .userId(request.getUserID())
                .build();

        ClubMemberTable savedClubMember = clubMemberRepository.save(clubMember);

        return new ClubMemberDTO(
                savedClubMember.getId(),
                savedClubMember.getClubId(),
                savedClubMember.getMembershipStatus(),
                savedClubMember.getUserId()
        );
    }

    public List<ClubMemberDTO> getAllClubMembers() {
        List<ClubMemberDTO> clubMembers = new ArrayList<>();
        for (ClubMemberTable member : clubMemberRepository.findAll()) {
            ClubMemberDTO dto = new ClubMemberDTO(
                    member.getId(),
                    member.getClubId(),
                    member.getMembershipStatus(),
                    member.getUserId()
            );
            clubMembers.add(dto);
        }
        return clubMembers;
    }

    public ClubMemberDTO editClubMember(UUID id, ClubMemberDTO clubMemberDTO) {
        ClubMemberTable clubMember = clubMemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ClubMember not found with ID: " + id));

        clubMember.setClubId(clubMemberDTO.getClubID());
        clubMember.setMembershipStatus(clubMemberDTO.getMembershipStatus());
        clubMember.setUserId(clubMemberDTO.getUserID());

        clubMemberRepository.save(clubMember);

        return clubMemberDTO;
    }

    public void deleteClubMember(UUID id) {
        ClubMemberTable clubMember = clubMemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ClubMember not found with ID: " + id));

        clubMemberRepository.delete(clubMember);
    }



    public List<ClubMemberDTO> getClubMembersByClubId(UUID clubId) {
        List<ClubMemberTable> clubMemberTables = clubMemberRepository.findByClubId(clubId);
        return clubMemberTables.stream()
                .map(member -> new ClubMemberDTO(
                        member.getId(),
                        member.getClubId(),
                        member.getMembershipStatus(),
                        member.getUserId()))
                .collect(Collectors.toList());
    }

    public List<ClubMemberDTO> getClubMembersByUserId(UUID userId) {
        List<ClubMemberTable> clubMemberTables = clubMemberRepository.findByUserId(userId);
        return clubMemberTables.stream()
                .map(member -> new ClubMemberDTO(
                        member.getId(),
                        member.getClubId(),
                        member.getMembershipStatus(),
                        member.getUserId()))
                .collect(Collectors.toList());
    }

    public void deleteClubMemberByUserIdAndClubId(UUID userId, UUID clubId) {
        Optional<ClubMemberTable> member = clubMemberRepository.findByUserIdAndClubId(userId, clubId);
        if (member.isPresent()) {
            clubMemberRepository.delete(member.get());
        } else {
            throw new EntityNotFoundException("No club member found for User ID: " + userId + " and Club ID: " + clubId);
        }
    }



}
