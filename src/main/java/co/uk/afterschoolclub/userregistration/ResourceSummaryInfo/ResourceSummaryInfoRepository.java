package co.uk.afterschoolclub.userregistration.ResourceSummaryInfo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ResourceSummaryInfoRepository extends CrudRepository<ResourceSummaryInfoView, UUID> {

    // Query to get total quantity of resources available
    @Query("SELECT SUM(rs.quantityAvailable) FROM ResourceSummaryInfoView rs")
    Long totalQuantityOfResourcesAvailable();

    // Query to get the total count of distinct resources
    @Query("SELECT COUNT(DISTINCT rs.inventoryResourceId) FROM ResourceSummaryInfoView rs")
    Long totalDistinctResources();

    // Query to get the quantity of resources used in the last month
    @Query(value = "SELECT SUM(quantity_used) FROM resource_usage_summary " +
            "WHERE session_completionid IN (" +
            "SELECT session_completionid FROM resource_usage_summary " +
            "WHERE CONVERT(date, completion_date) > DATEADD(day, -30, GETDATE()))",
            nativeQuery = true)
    Long quantityOfResourcesUsedInTheLastMonth();



}
