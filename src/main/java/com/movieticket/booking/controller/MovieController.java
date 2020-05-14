package com.movieticket.booking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieticket.booking.entity.Movie;
import com.movieticket.booking.entity.Seat;
import com.movieticket.booking.repository.MovieRepository;
import com.movieticket.booking.utilities.JsonResponse;

@RestController
@CrossOrigin
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;
	
	@PostMapping("/admin/createMovie") 
	public JsonResponse createMovie(@RequestBody Map<String, String> map) {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("error");
		Movie movie = new Movie();
		movie.setName(map.get("name"));
		movie.setPrice(Double.parseDouble(map.get("price")));
		movie.setTimings(map.get("timings"));
		
		try {
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(map.get("startDate"));
			movie.setStart(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResponse.setMessage("error parsing start date");
			return jsonResponse;
		}
		
		try {
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(map.get("endDate"));
			movie.setEnd(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResponse.setMessage("error parsing end date");
			return jsonResponse;
		}
		
		Seat seat = new Seat();
		try {
			seat.setId(Integer.parseInt(map.get("seatId")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResponse.setMessage("error parsing seat id");
			return jsonResponse;
		}
		movie.setSeat(seat);
		
		
		try {
			this.movieRepository.save(movie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResponse.setMessage("error creating movie");
			return jsonResponse;
		}
		jsonResponse.setStatus("success");
		jsonResponse.setMessage("successfully created movie");
		return jsonResponse;
		
	}
}
