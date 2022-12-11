package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.utils.exceptions.DatabaseException;
import com.devsuperior.bds02.utils.exceptions.ResourceNotFoundException;

@Service
public class CityService {
	
	private final CityRepository repository;

	public CityService(CityRepository repository) {
		this.repository = repository;
	}
	
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		return list.stream().map(CityDTO::new).toList();
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City(null, dto.getName());
		entity = repository.save(entity);
		return new CityDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("id %d not found!", id));
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation!!");
		}
	}

}
