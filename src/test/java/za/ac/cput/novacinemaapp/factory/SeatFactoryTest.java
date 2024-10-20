package za.ac.cput.novacinemaapp.factory;
    /*SeatFactoryTest.java
Entity for Seat factory test
Author: Daanyaal Isaacs (220094934)
Date: 17 May
 */
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.novacinemaapp.domain.Cinema;
import za.ac.cput.novacinemaapp.domain.Seat;
import za.ac.cput.novacinemaapp.domain.Theatre;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SeatFactoryTest {
    @Test
    @Order(1)
    void testBuildSeat(){
        Cinema cinema = CinemaFactory.buildCinema( "Grand Cinema", "6 Ferry street");
        assertNotNull(cinema);
        Theatre theatre = TheatreFactory.buildTheatre("IMAX", cinema);
        assertNotNull(theatre);
        Seat seat = SeatFactory.buildSeat("D4","Regular",theatre);
        assertNotNull(seat);
        System.out.println(seat);
    }

    @Test
    @Order(2)
    void testFail(){
        Cinema cinema = CinemaFactory.buildCinema( "Grand Cinema", "6 Ferry street");
        assertNotNull(cinema);
        Theatre theatre = TheatreFactory.buildTheatre("IMAX", cinema);
        assertNotNull(theatre);
        Seat seat = SeatFactory.buildSeat("", "Regular",theatre);
        assertNotNull(seat);
        System.out.println(seat);
    }
}