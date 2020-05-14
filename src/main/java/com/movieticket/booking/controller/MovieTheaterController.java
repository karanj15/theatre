package com.movieticket.booking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieticket.booking.entity.MovieTheaterModel;
import com.movieticket.booking.repository.MovieTheater;
import com.movieticket.booking.utilities.JsonResponse;

@RestController
@CrossOrigin
public class MovieTheaterController {
	@Autowired
	private MovieTheater movieTheaterRepository;
	
	@PostMapping("/admin/createTheater")
	public JsonResponse createTheater(@RequestBody Map<String, String> map) {
		JsonResponse jsonResponse = new JsonResponse();
		MovieTheaterModel movieTheater = new MovieTheaterModel();

		movieTheater.setName(map.get("name"));
		movieTheater.setLocation(map.get("location"));

		try {
			this.movieTheaterRepository.save(movieTheater);
		} catch (Exception e) {
			jsonResponse.setStatus("error");
			jsonResponse.setMessage(e.getMessage());
			return jsonResponse;
		}
		jsonResponse.setStatus("success");
		jsonResponse.setMessage("suucessfully created theater");
		return jsonResponse;

	}
	
	@GetMapping(path = "/admin/getTheaterDetails/{id}")
	public JsonResponse getTheaterById(@PathVariable("id") String id) {
		JsonResponse jsonResponse = new JsonResponse();
		int id1 = 0;
		try {
			id1 = Integer.parseInt(id);
		}
		catch (Exception e) {
			jsonResponse.setStatus("error");
			jsonResponse.setMessage("Could not parse theater id");
			return jsonResponse;
		}
		jsonResponse.setStatus("success");
		jsonResponse.setMessage(this.movieTheaterRepository.findById(id1));
		return jsonResponse;
	}
	
	@GetMapping(path = "/admin/getAllTheaterDetails")
	public JsonResponse getAllTheaters() {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("success");
		jsonResponse.setMessage(this.movieTheaterRepository.findAll());
		return jsonResponse;
	}
}
