package com.movieticket.booking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieticket.booking.entity.MovieTheaterModel;
import com.movieticket.booking.entity.Seat;
import com.movieticket.booking.repository.SeatRepository;
import com.movieticket.booking.utilities.JsonResponse;

@RestController
@CrossOrigin
public class SeatController {
	@Autowired
	private SeatRepository seatRepository;
	
	@PostMapping("/admin/createSeats")
	public JsonResponse createTheater(@RequestBody Map<String, String> map) {
		JsonResponse jsonResponse = new JsonResponse();
		Seat seat = new Seat();
		
		MovieTheaterModel mtm = new MovieTheaterModel();
		mtm.setId(Integer.parseInt(map.get("id")));
		seat.setMovieTheater(mtm);
		seat.setSeats(Integer.parseInt(map.get("seats")));
		seat.setSeatTypeIndicator(Integer.parseInt(map.get("indicator")));

		try {
			this.seatRepository.save(seat);
		} catch (Exception e) {
			jsonResponse.setStatus("error");
			jsonResponse.setMessage(e.getMessage());
			return jsonResponse;
		}
		jsonResponse.setStatus("success");
		jsonResponse.setMessage("sucessfully created seats");
		return jsonResponse;

	}
}
