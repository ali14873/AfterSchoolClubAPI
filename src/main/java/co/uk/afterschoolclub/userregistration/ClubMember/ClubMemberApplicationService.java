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

    public ClubMemberDTO createClubMember(ClubMemberDTO request) {
        ClubMemberTable clubMember = ClubMemberTable.builder()
                .id(request.getId())
                .clubId(request.getClubID())
                .MembershipStatus(request.getMembershipStatus())
                .userId(request.getUserID())
                .build();
        clubMemberRepository.save(clubMember);
        return request;
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

}
