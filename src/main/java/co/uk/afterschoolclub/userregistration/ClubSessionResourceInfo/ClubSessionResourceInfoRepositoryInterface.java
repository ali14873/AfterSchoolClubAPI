package co.uk.afterschoolclub.userregistration.ClubSessionResourceInfo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClubSessionResourceInfoRepositoryInterface extends CrudRepository<ClubSessionResourceInfoView, UUID> {

    @Query("SELECT SUM(ri.quantityAvailable) FROM ClubSessionResourceInfoView ri")
    Long sumQuantityAvailable();

    @Query(value = "SELECT SUM(ru.quantity_used) FROM ClubSessionResourceInfoView ru " +
            "WHERE ru.date BETWEEN DATEADD(day, -30, CURRENT_DATE) AND CURRENT_DATE", nativeQuery = true)
    Long sumResourcesUsedInLast30Days();

}
