package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.utils.AppUtils;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AppUtils utils;
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		return repository
				.findAll()
				.stream().map(category -> new UserDTO(category))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		var optional = repository.findById(id);
		var entity = optional
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Entity with id %d not found", id)));
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO insert(UserDTO dto) {
		var category = new User(dto);
		category.setPassword(utils.crypt(dto.getPassword()));
		var entity = repository.save(category);
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
			var entity = repository.getOne(id);
			var update = new User(entity.getId(), dto);
			return new UserDTO(repository.save(update));
		} catch (Exception e) {
			throw new ResourceNotFoundException(String.format("Id not found: %d", id));
		}
		
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Id not found: %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}

	public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
		var list = repository.findAll(pageRequest);
		return list.map(category -> new UserDTO(category));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException("Email not found");
		return user;
	}

}
