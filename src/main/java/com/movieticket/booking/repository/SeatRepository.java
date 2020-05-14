package com.movieticket.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieticket.booking.entity.Seat;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{

}
