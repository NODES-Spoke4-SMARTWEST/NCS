package it.univda.nodes.controller;

import it.univda.nodes.dto.MatrixResponse;
import it.univda.nodes.dto.MonthYearDTO;
import it.univda.nodes.dto.MonthlyBookingStat;
import it.univda.nodes.repository.BookingRepository;
import it.univda.nodes.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final BookingService bookingService;

    public StatisticsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String showStatisticsPage(Model model) {
        List<MonthYearDTO> months = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (int i = 0; i < 12; i++) {
            LocalDate future = now.plusMonths(i);
            String[] monthNames = {
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            };
            String monthName = monthNames[future.getMonthValue() - 1];
            months.add(new MonthYearDTO(monthName, future.getMonthValue(), future.getYear()));
        }

        model.addAttribute("months", months);
        return "statistics";
    }

    @ResponseBody
    @GetMapping("/matrix")
    public MatrixResponse getMatrix(@RequestParam int year, @RequestParam int month) {
        int a = 0;
        return bookingService.computeMatrix(year, month);
    }
}
