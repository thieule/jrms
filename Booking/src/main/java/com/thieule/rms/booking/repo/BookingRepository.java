package com.thieule.rms.booking.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thieule.rms.booking.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
