package com.foodbox.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String emailAddress;
	@Column(nullable = false)
	private String mobileNumber;
	@Column(nullable = false)
	private String password;
	private boolean active = true;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
	@JsonIgnore
	private List<Role> roles = new ArrayList<Role>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	private Set<Order> orders;

	public void add(Order order) {
		 if (order != null) {
	            if (orders == null) {
	                orders = new HashSet<>();
	            }
	            orders.add(order);
	            order.setUser(this);
	        }
	}

	public User(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.emailAddress = user.getEmailAddress();
		this.mobileNumber = user.getMobileNumber();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.orders = user.getOrders();
	}

	public User() {

	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public boolean hasRole(String roleName) {
		Iterator<Role> iterator = roles.iterator();
		Role role = iterator.next();
		if (role.getRoleName().equals(roleName)) {
			return true;
		} else {
			return false;
		}
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
}
