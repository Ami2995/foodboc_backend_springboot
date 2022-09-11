package com.foodbox.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id", nullable = false, updatable = false)
	private Long id;
	@Column(nullable = false, unique = true)
	private String roleName;
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<User> users;
	
	public String toString() {
		return this.roleName;
	}
}
