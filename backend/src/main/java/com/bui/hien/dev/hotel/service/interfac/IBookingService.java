package com.bui.hien.dev.hotel.service.interfac;

import com.bui.hien.dev.hotel.dto.Response;
import com.bui.hien.dev.hotel.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}