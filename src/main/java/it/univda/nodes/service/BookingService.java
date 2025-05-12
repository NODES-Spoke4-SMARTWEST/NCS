package it.univda.nodes.service;

//import it.univda.nodes.repository.BookingRepository;
import it.univda.nodes.dto.MatrixResponse;
import it.univda.nodes.entity.Booking;
import it.univda.nodes.entity.User;
import it.univda.nodes.repository.BookingRepository;
import it.univda.nodes.repository.InitiativeRepository;
import it.univda.nodes.repository.ResourceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    //------

    @PersistenceContext
    private EntityManager em;

    public MatrixResponse computeMatrix(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        String jpql = """
            SELECT h.name, c.name, COUNT(DISTINCT u.id)
            FROM Booking b
            JOIN b.resource r
            JOIN r.hub_id h
            JOIN b.user u
            LEFT JOIN u.competences c
            WHERE b.startDate >= :start AND b.endDate <= :end AND b.canceled = false
            GROUP BY h.name, c.name
        """;

        List<Object[]> results = em.createQuery(jpql, Object[].class)
                .setParameter("start", start.atStartOfDay())
                .setParameter("end", end.plusDays(1).atStartOfDay())
                .getResultList();

        Map<String, Map<String, Long>> matrix = new LinkedHashMap<>();
        Set<String> headers = new TreeSet<>();

        for (Object[] row : results) {
            String hub = (String) row[0];
            String tag = (String) row[1];
            Long count = (Long) row[2];

            headers.add(tag);

            matrix.computeIfAbsent(hub, k -> new HashMap<>()).put(tag, count);
        }

        MatrixResponse response = new MatrixResponse();
        response.setHeaders(new ArrayList<>(headers));
        response.setRows(matrix);
        return response;
    }


}
