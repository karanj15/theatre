package com.movieticket.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieticket.booking.entity.MovieTheaterModel;

@Repository
public interface MovieTheater extends JpaRepository<MovieTheaterModel, Integer>{

}
