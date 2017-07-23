package com.thieule.rms.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thieule.rms.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
