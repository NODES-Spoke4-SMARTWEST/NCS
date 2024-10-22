package com.example.nodes.service;

//import com.example.nodes.repository.BookingRepository;
import com.example.nodes.entity.Booking;
import com.example.nodes.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    /*public List<Booking> searchBookings(String location, String type, Integer quantity, LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.searchBookings(location, type, quantity, startDate, endDate);
    }

     */

    /*public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

     */

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
