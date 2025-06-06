package it.univda.nodes.repository;

import it.univda.nodes.dto.MonthlyBookingStat;
import it.univda.nodes.entity.Booking;
import it.univda.nodes.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Booking b SET b.canceled = :canceled " +
            "WHERE b.id = :id")
    int cancelBooking(@Param("id") Long id,
                   @Param("canceled") boolean canceled);

    List<Booking> findByUser(User user);

    List<Booking> findByInitiativeIdInOrResourceIdIn(List<Long> initiativeIds, List<Long> resourceIds);

    /*@Query("SELECT b FROM Booking b JOIN b.resource r WHERE " +
           "(:location IS NULL OR r.location = :location) AND " +
           "(:type IS NULL OR r.type = :type) AND " +
           "(:quantity IS NULL OR b.quantity = :quantity) AND " +
           "(b.startDate >= :startDate AND b.endDate <= :endDate)")
    List<Booking> searchBookings(@Param("location") String location,
                                 @Param("type") String type,
                                 @Param("quantity") Integer quantity,
                                 @Param("startDate") LocalDateTime startDate,
                                 @Param("endDate") LocalDateTime endDate);*/

    /*@Query("""
    SELECT new it.univda.nodes.dto.MonthlyBookingStat(
        MONTH(b.startDate), YEAR(b.startDate), r.hub_id.id,
        COUNT(DISTINCT CASE WHEN :competence IN elements(u.competences) OR :interest IN elements(u.interests) THEN u.id END)
    )
    FROM Booking b
    JOIN b.resource r
    JOIN b.user u
    WHERE b.canceled = false
      AND b.startDate >= CURRENT_DATE
      AND b.startDate < CURRENT_DATE + INTERVAL 12 MONTH
    GROUP BY YEAR(b.startDate), MONTH(b.startDate), r.hub_id.id
    ORDER BY YEAR(b.startDate), MONTH(b.startDate)
    """)
    List<MonthlyBookingStat> getMonthlyBookingStats(@Param("competence") String competence, @Param("interest") String interest);

     */

}

