package za.ac.cput.novacinemaapp.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.novacinemaapp.domain.*;
import za.ac.cput.novacinemaapp.factory.*;

import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowtimeService showtimeService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private SeatService seatService;
    private static Ticket ticket1, ticket2;
    private static Genre g;
    private static Movie b;
    private static Showtime showtime;
    private static Cinema cinema;
    private static Theatre theatre;
    private static Seat seat1,seat2;

    @Test
    @Order(1)
    void setUp() {
        g = GenreFactory.buildGenre( "Animation", "Movies that are characterized by 2D or 3D graphics.");
        genreService.create(g);
        b = MovieFactory.buildMovie("Cars","After the race at the Piston Cup Championship ends in a three-way tie-breaker, a rookie Lightning McQueen is desperate to make it to the winning position and take over the veteran Strip Weathers.",g,"117 mins","PG-13","imageURL");
        movieService.create(b);
        showtime = ShowtimeFactory.buildShowtime( LocalTime.of(10, 0), LocalTime.of(12, 0), b);
        showtimeService.create(showtime);
        cinema = CinemaFactory.buildCinema( "Grand Cinema");
        cinemaService.create(cinema);
        theatre = TheatreFactory.buildTheatre("IMAX", cinema);
        theatreService.create(theatre);
        seat1 = SeatFactory.buildSeat("D4","Regular",theatre);
        seatService.create(seat1);
        seat2 = SeatFactory.buildSeat("D5","Regular",theatre);
        seatService.create(seat2);
        ticket1 = TicketFactory.buildTicket( b,showtime,seat1,theatre,cinema,69.00);
        assertNotNull(ticket1);
        System.out.println(ticket1);

        ticket2 = TicketFactory.buildTicket( b,showtime,seat2,theatre,cinema, 69.00);
        assertNotNull(ticket2);
        System.out.println(ticket2);
    }

    @Test
    @Order(2)
    void create() {
        Ticket created1 = ticketService.create(ticket1);
        assertNotNull(created1);
        System.out.println(created1);

        Ticket created2 = ticketService.create(ticket2);
        assertNotNull(created2);
        System.out.println(created2);
    }

    @Test
    @Order(3)
    void read() {
        long ticketID = ticket1.getTicketID();  // Ensure ticket1.getTicketID() returns a long
        Ticket read = ticketService.read(ticketID);  // Pass the long ticketID to the read method
        assertNotNull(read);
        System.out.println(read);
    }


    @Test
    @Order(4)
    void update() {
        Ticket newTicket = new Ticket.Builder().copy(ticket2).setTicketPrice(69.69).build();
        Ticket updated = ticketService.update(newTicket);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Order(5)
    void getAll() {
        Set<Ticket> tickets = ticketService.getall();
        assertNotNull(tickets);
        System.out.println(tickets);
    }
}

