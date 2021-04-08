package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devsuperior.dscatalog.dto.ClientDTO;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private Double income;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birthDate;
	private Integer children;
	
	public Client() {}

	public Client(String name, String cpf, Double income, Instant birthDate, Integer children) {
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public Client(ClientDTO dto) {
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.income = dto.getIncome();
		this.birthDate = dto.getBirthDate();
		this.children = dto.getChildren();
	}
	
	public Client(Long id, ClientDTO dto) {
		this.id = id;
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.income = dto.getIncome();
		this.birthDate = dto.getBirthDate();
		this.children = dto.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
}
