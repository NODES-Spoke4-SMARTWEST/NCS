package com.example.nodes.repository;

import com.example.nodes.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
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

