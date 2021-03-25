package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updateddAt;
	
	public Category() {}	
	
	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}		

	public Category(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}	
	
	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdateddAt() {
		return updateddAt;
	}
	
	@PrePersist
	public void prePersist() {
		this.createdAt = Instant.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updateddAt = Instant.now();
	}

	@Override
	public String toString() {
		return "Category [id= " + id + ", name= " + name + "]";
	}	
}
