package com.devsuperior.bds02.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	private final EventService service;

	public EventController(EventService service) {
		this.service = service;
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
		EventDTO response = service.update(id, dto);
		return ResponseEntity.ok(response);
	}
	
	
}
