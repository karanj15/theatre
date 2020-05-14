package com.movieticket.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private int seats;
	private int seatTypeIndicator;
	
	@ManyToOne
	private MovieTheaterModel movieTheater;
	
	public MovieTheaterModel getMovieTheater() {
		return movieTheater;
	}
	public void setMovieTheater(MovieTheaterModel movieTheater) {
		this.movieTheater = movieTheater;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getSeatTypeIndicator() {
		return seatTypeIndicator;
	}
	
	public void setSeatTypeIndicator(int seatTypeIndicator) {
		this.seatTypeIndicator = seatTypeIndicator;
	}
	
	
}
