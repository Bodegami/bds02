package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.utils.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	private final EventRepository eventRepository;
	private final CityRepository cityRepository;
	
	public EventService(EventRepository eventRepository, CityRepository cityRepository) {
		this.eventRepository = eventRepository;
		this.cityRepository = cityRepository;
	}
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = eventRepository.getOne(id);
			dtoToModel(dto, entity);
			Event event = eventRepository.save(entity);
			return new EventDTO(event);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(String.format("id %d not found!", id));
		}
		
	}
	
	@Transactional(readOnly = true)
	private void dtoToModel(EventDTO dto, Event entity) {
		City dtoCity = cityRepository.getOne(dto.getCityId());
		entity.setDate(dto.getDate());
		entity.setName(dto.getName());
		entity.setUrl(dto.getUrl());
		entity.setCity(dtoCity);
	}
	
	
}
