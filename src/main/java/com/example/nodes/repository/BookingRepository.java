package com.example.nodes.repository;

import com.example.nodes.entity.Booking;
import com.example.nodes.entity.Role;
import com.example.nodes.entity.User;
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


}

