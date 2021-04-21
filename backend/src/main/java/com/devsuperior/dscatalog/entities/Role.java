package com.devsuperior.dscatalog.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devsuperior.dscatalog.dto.RoleDTO;

@Entity
@Table(name = "tb_role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authority;
	
	public Role() {}

	public Role(String authority) {
		this.authority = authority;
	}
	
	public Role(RoleDTO dto) {
		id = dto.getId();
		authority = dto.getAuthority();
	}

	public Long getId() {
		return id;
	}

	public String getAuthority() {
		return authority;
	}
}
