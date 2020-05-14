package com.movieticket.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieticket.booking.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
