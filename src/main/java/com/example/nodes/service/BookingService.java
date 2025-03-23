package com.example.nodes.service;

//import com.example.nodes.repository.BookingRepository;
import com.example.nodes.entity.Booking;
import com.example.nodes.entity.User;
import com.example.nodes.repository.BookingRepository;
import com.example.nodes.repository.InitiativeRepository;
import com.example.nodes.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private InitiativeRepository initiativeRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    /*public List<Booking> getBookingsByInitiative(Long initiativeId) {
        return bookingRepository.findByInitiativeId(initiativeId);
    }

     */

    /*public List<Booking> searchBookings(String location, String type, Integer quantity, LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.searchBookings(location, type, quantity, startDate, endDate);
    }

     */

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public void cancelBooking(Booking booking) {
        bookingRepository.cancelBooking(booking.getId(), booking.isCanceled());
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    public List<Booking> getBookingsForAgent(User user) {
        return bookingRepository.findByUser(user);
    }

    public List<Booking> getBookingsForBusiness(User user) {
        // Fetch all initiatives and resources created by the business user
        List<Long> initiativeIds = initiativeRepository.findInitiativeIdsByCreator(user.getId());
        List<Long> resourceIds = resourceRepository.findResourceIdsByCreator(user.getId());

        // Fetch all bookings associated with these initiatives or resources
        return bookingRepository.findByInitiativeIdInOrResourceIdIn(initiativeIds, resourceIds);
    }
}
