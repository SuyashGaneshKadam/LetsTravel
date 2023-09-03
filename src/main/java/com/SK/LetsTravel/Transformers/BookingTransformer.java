package com.SK.LetsTravel.Transformers;

import com.SK.LetsTravel.Models.Booking;
import com.SK.LetsTravel.RequestDTOs.BookTicket;

public class BookingTransformer {
    public static Booking convertDtoToEntity(BookTicket bookTicketInFlight){
        Booking booking = Booking.builder().seatNos(bookTicketInFlight.getSeatNos()).
                journeyDate(bookTicketInFlight.getJourneyDate()).build();
        return booking;
    }
}
