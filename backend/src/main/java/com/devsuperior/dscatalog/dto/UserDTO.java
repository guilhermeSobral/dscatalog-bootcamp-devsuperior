package com.devsuperior.dscatalog.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.services.validation.UserInsertValid;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@UserInsertValid
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String firstName;
	private String lastName;
	
	@Email(message = "Favor entrar um email válido")
	private String email;
	private String password;
	
	private Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO() {}

	public UserDTO(String firstName, String lastName, String email, String password, List<RoleDTO> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		roles.forEach(role -> this.roles.add(role));
	}
	
	public UserDTO(String firstName, String lastName, String email, List<RoleDTO> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		roles.forEach(role -> this.roles.add(role));
	}
	
	public UserDTO(User user) {
		id = user.getId();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		user.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}	

	public String getPassword() {
		return password;
	}

	public Set<RoleDTO> getRoles() {
		return Collections.unmodifiableSet(roles);
	}
}
