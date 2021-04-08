package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	private Double price;
	private String imgUrl;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant date;
	
	@ManyToMany
	@JoinTable(
			name = "tb_product_category",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")	
	)
	private Set<Category> categories = new HashSet<>();	

	public Product() {}

	public Product(String name, String description, Double price, String imgUrl, Instant date) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}
	
	public Product(ProductDTO dto) {
		this.name = dto.getName();
		this.description = dto.getDescription();
		this.price = dto.getPrice();
		this.imgUrl = dto.getImgUrl();
		this.date = dto.getDate();
		saveCategories(dto.getCategories());
	}
	
	public Product(Long id, ProductDTO dto) {
		this.id = id;
		this.name = dto.getName();
		this.description = dto.getDescription();
		this.price = dto.getPrice();
		this.imgUrl = dto.getImgUrl();
		this.date = dto.getDate();
		saveCategories(dto.getCategories());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public Instant getDate() {
		return date;
	}

	public Set<Category> getCategories() {
		return Collections.unmodifiableSet(categories);
	}
	
	private void saveCategories(List<CategoryDTO> categories) {
		this.categories.clear();
		categories.forEach(category -> this.categories.add(new Category(category.getId(), category.getName())));
	}

}
